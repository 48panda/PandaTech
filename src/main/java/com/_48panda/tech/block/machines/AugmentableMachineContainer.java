package com._48panda.tech.block.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class AugmentableMachineContainer extends MachineContainer {
    protected int augmentSize;
    public AugmentableMachineContainer(int id, Inventory playerInv, MachineProperties properties) {
        this(id, playerInv,
                new ItemStackHandler(properties.numSlots()),
                new ItemStackHandler(properties.outputSlots()),
                new ItemStackHandler(properties.augmentProperties().numAugments()),
                BlockPos.ZERO, properties,
                new SimpleContainerData(properties.numDataPieces() + 2));
    }

    public AugmentableMachineContainer(int id, Inventory playerInv, IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots, BlockPos pos, MachineProperties properties, ContainerData data) {
        super(id, playerInv, slots, outputSlots, pos, properties, data);
        augmentSize = properties.augmentProperties().numAugments();
        int iw = properties.augmentProperties().imageWidth();
        for (int i = 0; i < augmentSize; i++) {
            addSlot(new AugmentSlot(augmentSlots, i, iw + 9, 9 + 23 * i, properties.augmentProperties().properties().get(i)));
        }
        createSlots(slots, outputSlots, augmentSlots);
    }
    public abstract void createSlots(IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots);
    @Override
    public void createSlots(IItemHandler slots, IItemHandler outputSlots) {}
}
