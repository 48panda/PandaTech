package com._48panda.tech.block.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AugmentableMachineBlockEntity extends MachineBlockEntity {
    protected ItemStackHandler augments;
    public AugmentableMachineBlockEntity(BlockPos pos, BlockState state, MachineProperties properties) {
        super(pos, state, properties);
        AugmentProperties aug = properties.augmentProperties();
        augments = new ItemStackHandler(aug.numAugments()) {
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return aug.properties().get(slot).allowedTypes().stream().anyMatch(x->x.isValid(stack));
            }
        };
    }
    public List<ItemStack> fromHandler(ItemStackHandler ish) {
        List<ItemStack> out = new ArrayList<>();
        for (int i = 0; i < ish.getSlots(); i++) {
            out.add(ish.getStackInSlot(i));
        }
        return out;
    }
    public double getAugmentedEfficiencyMultiplier() {
        return AugmentUtils.getAugmentedEfficiencyMultiplier(fromHandler(augments));
    }

    public double getAugmentedSpeedMultiplier() {
        return AugmentUtils.getAugmentedSpeedMultiplier(fromHandler(augments));
    }

    @Override
    public void dropContents(Level level, BlockPos pos) {
        super.dropContents(level, pos);
        for (int i = 0; i < augments.getSlots(); i ++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), augments.getStackInSlot(i));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Augments", augments.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        augments.deserializeNBT(tag.getCompound("Augments"));
    }
}
