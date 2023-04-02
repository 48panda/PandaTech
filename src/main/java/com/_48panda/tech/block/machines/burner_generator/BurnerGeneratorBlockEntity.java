package com._48panda.tech.block.machines.burner_generator;

import com._48panda.tech.ItemHelper;
import com._48panda.tech.block.machines.AugmentableMachineBlockEntity;
import com._48panda.tech.block.machines.MachineBlockEntity;
import com._48panda.tech.block.machines.MachineProperties;
import com._48panda.tech.block.machines.MachineContainerData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BurnerGeneratorBlockEntity extends AugmentableMachineBlockEntity {
    private int burnTime;
    private int maxBurnTime;
    private int litCooldown;
    private Optional<SmeltingRecipe> prevRecipe;
    public BurnerGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, MachineProperties.BURNER_GENERATOR);
        burnTime = 0;
        maxBurnTime = 1;
        litCooldown = 0;
        prevRecipe = Optional.empty();
    }

    @Override
    public void tick() {
        super.tick();
        if (burnTime > 0) {
            int energyRate = (int)(5 * getAugmentedEfficiencyMultiplier());
            int energyRecieved = energyStorage.receiveEnergy(energyRate, true);
            if (energyRecieved == energyRate) {
                litCooldown = 60;
                energyStorage.receiveEnergy(energyRate, false);
                burnTime--;
            }
        }
        else if (burnTime == 0) {
            ItemStack recieved = ItemHelper.simulateExtractItem(inventory, 0, 1);
            burnTime = (int) (ForgeHooks.getBurnTime(recieved, RecipeType.SMELTING) * getAugmentedSpeedMultiplier());
            if (burnTime != 0) {
                maxBurnTime = burnTime;
                inventory.extractItem(0, 1, false);
            }
        }
        if (litCooldown > 0) {
            litCooldown--;
        }
        boolean lit = litCooldown > 0;
        BlockState state = getBlockState();
        boolean wasLit = state.getValue(BurnerGeneratorBlock.LIT);
        if (lit != wasLit) {
            setChanged();
            state = state.setValue(BurnerGeneratorBlock.LIT, Boolean.valueOf(lit));
            getLevel().setBlockAndUpdate(getBlockPos(), state);
            setChanged(getLevel(), getBlockPos(), state);
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
                BurnerGeneratorBlockEntity.this.update();
                return super.extractItem(slot, amount, false);
            }

            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                BurnerGeneratorBlockEntity.this.update();
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
        tag.putInt("burnTime", burnTime);
        tag.putInt("maxBurnTime", maxBurnTime);
        tag.putInt("litCooldown", litCooldown);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        burnTime = tag.getInt("burnTime");
        maxBurnTime = tag.getInt("maxBurnTime");
        litCooldown = tag.getInt("litCooldown");
    }

    @Override
    public MenuConstructor getServerContainer(BlockPos pos) {
        return (id, playerInv, player) -> new BurnerGeneratorContainer(id,
                playerInv, inventory, output, augments, pos,
                new MachineContainerData(this,
                        properties.numDataPieces() + 2));
        
    }

    @Override
    public int getData(int key) {
        return switch (key) {
            case 0 -> burnTime;
            case 1 -> maxBurnTime;
            default -> throw new UnsupportedOperationException("There is no value corresponding to key '" + key + "' in '" + this + "'");
        };
    }

    @Override
    public void setData(int key, int value) {
        switch (key) {
            case 0 -> burnTime = value;
            case 1 -> maxBurnTime = value;
            default -> throw new UnsupportedOperationException("There is no value corresponding to key '" + key + "' in '" + this +  ";");
        }
    }
}
