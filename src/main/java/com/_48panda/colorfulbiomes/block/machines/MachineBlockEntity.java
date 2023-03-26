package com._48panda.colorfulbiomes.block.machines;

import com._48panda.colorfulbiomes.ColorfulBiomesMod;
import com._48panda.colorfulbiomes.MyEnergyStorage;
import com._48panda.colorfulbiomes.block.entity.EnergyBlockEntity;
import com._48panda.colorfulbiomes.block.entity.InventoryBlockEntity;
import com._48panda.colorfulbiomes.block.entity.TickableBlockEntity;
import com._48panda.colorfulbiomes.init.ColorfulBiomesModMessages;
import com._48panda.colorfulbiomes.network.packet.EnergySyncS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class MachineBlockEntity extends InventoryBlockEntity implements EnergyBlockEntity, TickableBlockEntity {
    public final Component TITLE;
    protected final MachineProperties properties;
    protected MyEnergyStorage energyStorage;
    public MachineBlockEntity(BlockPos pos, BlockState state, MachineProperties properties) {
        super(properties.getBlockEntityType(), pos, state, properties.getNumSlots(), properties.getOutputSlots());
        TITLE = new TranslatableComponent("container." + ColorfulBiomesMod.MODID + "." + properties.getId());
        this.properties = properties; 
        energyStorage = new MyEnergyStorage(properties.getCapacity(), properties.getInputRate(), properties.getOutputRate()) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                //ColorfulBiomesModMessages.sendToClients(new EnergySyncS2CPacket(getEnergyStored(), getBlockPos()));
            }
        };
    }
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {}
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
    public int getMaxInput() {
        return properties.getInputRate();
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
}
