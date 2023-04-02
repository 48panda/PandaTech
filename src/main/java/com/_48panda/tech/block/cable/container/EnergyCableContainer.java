package com._48panda.tech.block.cable.container;

import com._48panda.tech.block.machines.OutputSlotItemHandler;
import com._48panda.tech.init.PandaTechBlocks;
import com._48panda.tech.init.PandaTechMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class EnergyCableContainer extends AbstractCableContainer {

    //CLIENT CONSTRUCTOR
    public EnergyCableContainer(int id, Inventory playerInv) {
        super(PandaTechMenus.ENERGY_CABLE.get(), id, playerInv, 0, 5);

    }

    // SERVER CONSTRUCTOR
    public EnergyCableContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) {
        super(PandaTechMenus.ENERGY_CABLE.get(), id, playerInv, slots, pos, data);
    }

    @Override
    public boolean stillValid(Player player) {
        return super.stillValid(player) && stillValid(containerAccess, player, PandaTechBlocks.ENERGY_CABLE.get());
    }

    @Override
    void createSlots(IItemHandler slots) {

    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return super.quickMoveStack(player, index);
    }
}