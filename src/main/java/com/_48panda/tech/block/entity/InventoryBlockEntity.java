package com._48panda.tech.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryBlockEntity extends BlockEntity {
    public final int size, outputSize;
    protected boolean requiresUpdate;

    public final ItemStackHandler inventory;
    public final ItemStackHandler output;
    
    protected LazyOptional<IItemHandler> handler;
    protected LazyOptional<IItemHandler> outputHandler;
    protected LazyOptional<IItemHandler> combinedHandler;
    public InventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int size) {
        this(type, pos, state, size, 0);
    }
    public InventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int size, int outputSize) {
        super(type, pos, state);
        if (size <= 0) {
            size = 1;
        }
        this.size = size;
        this.outputSize = outputSize;
        this.inventory = createInventory();
        this.handler = LazyOptional.of(() -> inventory);
        this.output = createOutput();
        this.outputHandler = LazyOptional.of(() -> output);
        this.combinedHandler = LazyOptional.of(() -> new CombinedInvWrapper(inventory, output));
        
    }
    protected ItemStackHandler createInventory() {
        return new ItemStackHandler(size) {
            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (simulate) {
                    return ItemStack.EMPTY;
                }
                InventoryBlockEntity.this.update();
                return super.extractItem(slot, amount, false);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                InventoryBlockEntity.this.update();
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
    protected ItemStackHandler createOutput() {
        return new ItemStackHandler(outputSize) {
            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                InventoryBlockEntity.this.update();
                return super.extractItem(slot, amount, simulate);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return stack;
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return false;
            }
        };
    }
    public void tick() {
        if (requiresUpdate && level != null) {
            update();
            requiresUpdate = false;
        }
    }
    public void update() {
        requestModelDataUpdate();
        setChanged();
        if (level != null) {
            level.setBlockAndUpdate(worldPosition, getBlockState());
        }
    }
    
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction direction) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return combinedHandler.cast();
        }
        return super.getCapability(cap, direction);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
        tag.put("Output", output.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("Inventory"));
        output.deserializeNBT(tag.getCompound("Output"));
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        handler.invalidate();
        outputHandler.invalidate();
        combinedHandler.invalidate();
    }
}
