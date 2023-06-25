package com._48panda.tech.block.multiblock;

import com._48panda.tech.block.machines.AugmentableMachineContainer;
import com._48panda.tech.block.machines.MachineProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraftforge.items.IItemHandler;

public abstract class AbstractMultiblockControllerContainer extends AugmentableMachineContainer {
    public AbstractMultiblockControllerContainer(int id, Inventory playerInv, MachineProperties properties) {
        super(id, playerInv, properties);
    }

    public AbstractMultiblockControllerContainer(int id, Inventory playerInv, IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots, BlockPos pos, MachineProperties properties, ContainerData data) {
        super(id, playerInv, slots, outputSlots, augmentSlots, pos, properties, data);
    }
}
