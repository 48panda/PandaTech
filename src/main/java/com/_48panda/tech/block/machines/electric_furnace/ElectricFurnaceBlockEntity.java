package com._48panda.tech.block.machines.electric_furnace;

import com._48panda.tech.block.machines.AugmentableMachineBlockEntity;
import com._48panda.tech.block.machines.MachineContainerData;
import com._48panda.tech.block.machines.MachineProperties;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class ElectricFurnaceBlockEntity extends AugmentableMachineBlockEntity {
    private int progress;
    private int maxProgress;
    private int litCooldown;
    private Optional<SmeltingRecipe> prevRecipe;
    private double fractionalEnergy;
    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, MachineProperties.ELECTRIC_FURNACE);
        progress = 0;
        maxProgress = 0;
        litCooldown = 0;
        prevRecipe = Optional.empty();
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
                ElectricFurnaceBlockEntity.this.update();
                return super.extractItem(slot, amount, false);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                ElectricFurnaceBlockEntity.this.update();
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                if (!super.isItemValid(slot, stack)) return false;
                assert level != null;  // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
                return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), level).isPresent();
            }
        };
    }
    @Override
    public void tick() {
        super.tick();
        if (litCooldown > 0) {
            litCooldown--;
        }
        assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
        Optional<SmeltingRecipe> recipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(inventory.getStackInSlot(0)), level);
        if (recipe.isPresent()) {
            maxProgress = (int)(recipe.get().getCookingTime() / getAugmentedSpeedMultiplier());
            double flowRate = 10 / getAugmentedEfficiencyMultiplier() + fractionalEnergy;
            fractionalEnergy = flowRate % 1;
            int toExtract = (int) Math.floor(flowRate);
            if (prevRecipe.isPresent()) {
                if (!prevRecipe.get().equals(recipe.get())) {
                    progress = 0;
                }
            }
            if (energyStorage.getEnergyStored() >= toExtract && progress < maxProgress) {
                if (energyStorage.extractEnergy(toExtract, true) == toExtract) {
                    energyStorage.extractEnergy(toExtract, false);
                    progress++;
                    litCooldown = 60;
                }
            }
            if (progress >= maxProgress) {
                ItemStack out = output.getStackInSlot(0);
                if (out == ItemStack.EMPTY || out.getItem() instanceof AirItem ||
                        (out.getItem().equals(recipe.get().getResultItem().getItem() )
                                && out.getCount() + recipe.get().getResultItem().getCount() <= out.getMaxStackSize())) {
                        output.setStackInSlot(0, new ItemStack(recipe.get().getResultItem().getItem(), out.getCount() + recipe.get().getResultItem().getCount()));
                        inventory.extractItem(0, 1, false);
                        progress = 0;
                    
                }
            }
        } else {
            if (progress != 0) {
                progress = Mth.clamp(progress - 2, 0, maxProgress);
            }
        }
        prevRecipe = recipe;
        boolean lit = litCooldown > 0;
        BlockState state = getBlockState();
        boolean wasLit = state.getValue(ElectricFurnaceBlock.LIT);
        if (lit != wasLit) {
            setChanged();
            state = state.setValue(ElectricFurnaceBlock.LIT, lit);
            Objects.requireNonNull(getLevel()).setBlockAndUpdate(getBlockPos(), state);
            setChanged(getLevel(), getBlockPos(), state);
        }
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("progress", progress);
        tag.putInt("progressMax", maxProgress);
        tag.putInt("litCooldown", litCooldown);
        tag.putDouble("fractionalEnergy", fractionalEnergy);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
        maxProgress = tag.getInt("progressMax");
        litCooldown = tag.getInt("litCooldown");
        fractionalEnergy = tag.getDouble("fractionalEnergy");
    }

    @Override
    public MenuConstructor getServerContainer(BlockPos pos) {
        return (id, playerInv, player) -> new ElectricFurnaceContainer(id,
                playerInv, inventory, output, augments, pos,
                new MachineContainerData(this,
                        properties.numDataPieces() + 2));
        
    }

    @Override
    public int getData(int key) {
        return switch (key) {
            case 0 -> progress;
            case 1 -> maxProgress;
            default -> throw new UnsupportedOperationException("There is no value corresponding to key '" + key + "' in '" + this + "'");
        };
    }

    @Override
    public void setData(int key, int value) {
        switch (key) {
            case 0 -> progress = value;
            case 1 -> maxProgress = value;
            default -> throw new UnsupportedOperationException("There is no value corresponding to key '" + key + "' in '" + this +  ";");
        }
    }
}
