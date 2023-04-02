package com._48panda.tech.block.cable.entity;

import com._48panda.tech.ItemHelper;
import com._48panda.tech.block.cable.Connection;
import com._48panda.tech.block.cable.ConnectionType;
import com._48panda.tech.block.cable.container.ItemCableContainer;
import com._48panda.tech.block.cable.container.data.ItemCableContainerData;
import com._48panda.tech.block.cable.type.EnergyCableType;
import com._48panda.tech.init.PandaTechBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.VanillaInventoryCodeHooks;
import org.lwjgl.system.CallbackI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCableBlockEntity extends AbstractCableBlockEntity {
    private Map<Direction, Integer> pipeIndex;

    public ItemCableBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.ITEM_CABLE.get(), pos, state, new TranslatableComponent("container.panda_tech.item_cable"));
        pipeIndex = new HashMap<>();
        for (Direction d: Direction.values()) {
            pipeIndex.put(d, 0);
        }
    }
    public int getRate() {
        return 1;
    }
    @Override
    public void sendTo(Direction dir, List<Connection> connections) {
        if (connections.size() == 0) {
            return;
        }
        BlockEntity be = level.getBlockEntity(worldPosition.relative(dir));
        if (be != null) {
            LazyOptional<IItemHandler> itemStorage = be.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir.getOpposite());
            if (itemStorage.isPresent()) {
                IItemHandler items = itemStorage.orElse(null); // WILL NEVER BE NULL;
                int toTransfer = getRate();
                int index = pipeIndex.get(dir);
                index = index >= connections.size() ? 0 : index;
                int lastSuccess = index;
                while (toTransfer > 0) {
                    boolean success = false;
                    Connection connection = connections.get(index);
                    IItemHandler toItems = level.getBlockEntity(connection.getPos()).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, connection.getDir()).orElse(null);
                    if (toItems != null) {
                        for (int i = 0; i < items.getSlots(); i++) {
                            ItemStack attempt = items.extractItem(i, 1, true);
                            ItemStack result = ItemHelper.insertItem(toItems, attempt, true);
                            if (result.isEmpty() && !attempt.isEmpty()) {
                                items.extractItem(i, 1, false);
                                ItemHelper.insertItem(toItems, attempt, false);
                                success = true;
                                break;
                            }
                        }
                    }
                    if (success) {
                        lastSuccess = index;
                        toTransfer--;
                    }
                    index++;
                    index = index % connections.size();
                    if (index == lastSuccess && !success) {
                        break;
                    }
                }
                pipeIndex.put(dir, lastSuccess + 1);
            }
        }
    }
    

    @Override
    public MenuConstructor getServerContainer(BlockPos pos, Direction dir) {
        return (id, playerInv, player) -> new ItemCableContainer(id,
                playerInv, inventory, pos,
                new ItemCableContainerData(this,5, dir));

    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        for (Direction d: Direction.values()) {
            tag.putInt(d.getSerializedName(), pipeIndex.get(d));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        for (Direction d: Direction.values()) {
            pipeIndex.put(d, tag.getInt(d.getSerializedName()));
        }
    }
}
