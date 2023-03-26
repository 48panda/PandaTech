package com._48panda.colorfulbiomes.block.machines.burner_generator;

import com._48panda.colorfulbiomes.block.machines.GeneratorMachineBlockEntity;
import com._48panda.colorfulbiomes.block.machines.MachineContainerData;
import com._48panda.colorfulbiomes.block.machines.MachineProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

import java.util.Optional;

public class BurnerGeneratorBlockEntity extends GeneratorMachineBlockEntity {
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
            int energyRecieved = energyStorage.receiveEnergy(properties.getInputRate(), true);
            if (energyRecieved == properties.getInputRate()) {
                litCooldown = 60;
                energyStorage.receiveEnergy(properties.getInputRate(), false);
                burnTime--;
            }
        }
        else if (burnTime == 0) {
            ItemStack recieved = inventory.extractItem(0, 1, true);
            burnTime = ForgeHooks.getBurnTime(recieved, RecipeType.SMELTING);
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
                playerInv, inventory, output, pos,
                new MachineContainerData(this,
                        properties.getNumDataPieces()));
        
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
