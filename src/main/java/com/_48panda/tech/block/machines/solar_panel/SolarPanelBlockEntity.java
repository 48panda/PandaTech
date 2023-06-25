package com._48panda.tech.block.machines.solar_panel;

import com._48panda.tech.block.machines.MachineBlockEntity;
import com._48panda.tech.block.machines.MachineContainerData;
import com._48panda.tech.block.machines.MachineProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class SolarPanelBlockEntity extends MachineBlockEntity {
    private double fractionalEnergy;
    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, MachineProperties.SOLAR_PANEL);
        fractionalEnergy = 0;
    }
    public int getPower() {
        assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
        int i = level.getBrightness(LightLayer.SKY, getBlockPos().relative(Direction.UP)) - level.getSkyDarken();
        float f = level.getSunAngle(1.0F);
        if (i > 0) {
            float f1 = f < (float)Math.PI ? 0.0F : ((float)Math.PI * 2F);
            f += (f1 - f) * 0.2F;
            i = Math.round((float)i * Mth.cos(f));
        }

        return Mth.clamp(i, 0, 15);
    }
    @Override
    public void tick() {
        super.tick();
        double energyRate = getPower() / 1.5 + fractionalEnergy;
        fractionalEnergy = energyRate % 1;
        int flow = (int) Math.floor(energyRate);
        int energyRecieved = energyStorage.receiveEnergy(flow, true);
        if (energyRecieved == flow) {
            energyStorage.receiveEnergy(flow, false);
        }        
    }

    @Override
    protected ItemStackHandler createInventory() {
        return new ItemStackHandler(size) {
            @NotNull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (simulate) {
                    return ItemStack.EMPTY;
                }
                SolarPanelBlockEntity.this.update();
                return super.extractItem(slot, amount, false);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                SolarPanelBlockEntity.this.update();
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return super.isItemValid(slot, stack) &&
                        ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
            }
        };
    }
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putDouble("fractionalEnergy", fractionalEnergy);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        fractionalEnergy = tag.getDouble("fractionalEnergy");
    }

    @Override
    public MenuConstructor getServerContainer(BlockPos pos) {
        return (id, playerInv, player) -> new SolarPanelContainer(id,
                playerInv, inventory, output, pos,
                new MachineContainerData(this,
                        properties.numDataPieces() + 2));
        
    }

    @Override
    public int getData(int key) {
        throw new UnsupportedOperationException("There is no value corresponding to key '" + key + "' in '" + this + "'");
    }

    @Override
    public void setData(int key, int value) {
        throw new UnsupportedOperationException("There is no value corresponding to key '" + key + "' in '" + this +  ";");
    }
}
