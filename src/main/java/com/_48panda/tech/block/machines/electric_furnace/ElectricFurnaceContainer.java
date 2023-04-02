package com._48panda.tech.block.machines.electric_furnace;

import com._48panda.tech.block.machines.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ElectricFurnaceContainer extends AugmentableMachineContainer {

    //public final ContainerData data;
    
    //CLIENT CONSTRUCTOR
    public ElectricFurnaceContainer(int id, Inventory playerInv) {
        super(id, playerInv, MachineProperties.ELECTRIC_FURNACE);
    }
    
    // SERVER CONSTRUCTOR
    public ElectricFurnaceContainer(int id, Inventory playerInv, IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots, BlockPos pos, ContainerData data) {
        super(id, playerInv, slots, outputSlots, augmentSlots, pos, MachineProperties.ELECTRIC_FURNACE, data);
        //this.data = data;
    }
    public void createSlots(IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots) {
        addSlot(new InputSlotItemHandler(slots, 0, 56, 35));
        addSlot(new OutputSlotItemHandler(outputSlots, 0, 116, 35));

    }

    @Override
    public boolean stillValid(Player player) {
        return super.stillValid(player) && stillValid(containerAccess, player, properties.block());
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return super.quickMoveStack(player, index);
    }
}
