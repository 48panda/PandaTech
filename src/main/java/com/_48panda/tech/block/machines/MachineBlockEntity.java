package com._48panda.tech.block.machines;

import com._48panda.tech.PandaTech;
import com._48panda.tech.MyEnergyStorage;
import com._48panda.tech.block.entity.IEnergyBlockEntity;
import com._48panda.tech.block.entity.ITickableBlockEntity;
import com._48panda.tech.block.entity.InventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

public abstract class MachineBlockEntity extends InventoryBlockEntity implements IEnergyBlockEntity, ITickableBlockEntity {
    public final Component TITLE;
    protected final MachineProperties properties;
    protected MyEnergyStorage energyStorage;
    public MachineBlockEntity(BlockPos pos, BlockState state, MachineProperties properties) {
        super(properties.blockEntityType(), pos, state, properties.numSlots(), properties.outputSlots());
        TITLE = new TranslatableComponent("container." + PandaTech.MODID + "." + properties.id());
        this.properties = properties; 
        energyStorage = new MyEnergyStorage(properties.capacity(), properties.inputRate(), properties.outputRate()) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                //ColorfulBiomesModMessages.sendToClients(new EnergySyncS2CPacket(getEnergyStored(), getBlockPos()));
            }
        };
    }
    @SuppressWarnings("unused")
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {}
    @SuppressWarnings("unused")
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {}
    public abstract MenuConstructor getServerContainer(BlockPos pos);
    public abstract int getData(int key);
    
    @Override
    public int getEnergyLevel() {
        return energyStorage.getEnergyStored();
    }

    @Override
    public void setEnergyLevel(int energy) {
        energyStorage.setEnergy(energy);
    }

    @Override
    public int getEnergyCapacity() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public void setEnergyCapacity(int capacity) {
        energyStorage.setCapacity(capacity);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("energyStorage", energyStorage.serializeNBT());
    }
    public void dropContents(Level level, BlockPos pos) {
        for (int i = 0; i < size; i ++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
        }
        for (int i = 0; i < outputSize; i ++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), output.getStackInSlot(i));
        }
    }
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.get("energyStorage") instanceof IntTag intTag)
            energyStorage.deserializeNBT(intTag);
    }

    public abstract void setData(int i, int value);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction direction) {
        if (cap == CapabilityEnergy.ENERGY && (direction == null || direction.equals(getBlockState().getValue(HorizontalDirectionalBlock.FACING).getOpposite()))) {
            return LazyOptional.of(()->energyStorage).cast();
        }
        return super.getCapability(cap, direction);
    }
}
