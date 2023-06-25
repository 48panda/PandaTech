package com._48panda.tech.block.multiblock;

import com._48panda.tech.block.machines.MachineProperties;
import com._48panda.tech.block.machines.ViewingSlotItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraftforge.items.IItemHandler;

public class CentrifugeMultiblockControllerContainer extends AbstractMultiblockControllerContainer {

    public CentrifugeMultiblockControllerContainer(int id, Inventory playerInv) {
        super(id, playerInv, MachineProperties.CENTRIFUGE);
    }

    public CentrifugeMultiblockControllerContainer(int id, Inventory playerInv, IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots, BlockPos pos, ContainerData data) {
        super(id, playerInv, slots, outputSlots, augmentSlots, pos, MachineProperties.CENTRIFUGE, data);
    }
    

    @Override
    public void createSlots(IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots) {
        addSlot(new ViewingSlotItemHandler(slots, 0, 80, 35));
    }
}
