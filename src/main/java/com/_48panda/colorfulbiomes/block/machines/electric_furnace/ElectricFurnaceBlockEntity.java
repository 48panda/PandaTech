package com._48panda.colorfulbiomes.block.machines.electric_furnace;

import com._48panda.colorfulbiomes.block.machines.ConsumerMachineBlockEntity;
import com._48panda.colorfulbiomes.block.machines.MachineContainerData;
import com._48panda.colorfulbiomes.block.machines.MachineProperties;
import com.mojang.math.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class ElectricFurnaceBlockEntity extends ConsumerMachineBlockEntity {
    private int progress;
    private int maxProgress;
    private int litCooldown;
    private Optional<SmeltingRecipe> prevRecipe;
    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, MachineProperties.ELECTRIC_FURNACE);
        progress = 0;
        maxProgress = 0;
        litCooldown = 0;
        prevRecipe = Optional.empty();
    }

    @Override
    public void tick() {
        super.tick();
        if (litCooldown > 0) {
            litCooldown--;
        }
        Optional<SmeltingRecipe> recipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(inventory.getStackInSlot(0)), level);
        if (recipe.isPresent()) {
            maxProgress = recipe.get().getCookingTime();
            if (prevRecipe.isPresent()) {
                if (recipe.isEmpty()||!prevRecipe.get().equals(recipe.get())) {
                    progress = 0;
                }
            }
            if (energyStorage.getEnergyStored() >= 10) {
                if (energyStorage.extractEnergy(10, true) == 10) {
                    energyStorage.extractEnergy(10, false);
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
            state = state.setValue(ElectricFurnaceBlock.LIT, Boolean.valueOf(lit));
            getLevel().setBlockAndUpdate(getBlockPos(), state);
            setChanged(getLevel(), getBlockPos(), state);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("progress", progress);
        tag.putInt("progressMax", maxProgress);
        tag.putInt("litCooldown", litCooldown);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
        maxProgress = tag.getInt("progressMax");
        litCooldown = tag.getInt("litCooldown");
    }

    @Override
    public MenuConstructor getServerContainer(BlockPos pos) {
        return (id, playerInv, player) -> new ElectricFurnaceContainer(id,
                playerInv, inventory, output, pos,
                new MachineContainerData(this,
                        properties.getNumDataPieces()));
        
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
