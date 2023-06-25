package com._48panda.tech.block.machines.solar_panel;

import com._48panda.tech.block.machines.MachineContainer;
import com._48panda.tech.block.machines.MachineProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class SolarPanelContainer extends MachineContainer {

    //public final ContainerData data;
    
    //CLIENT CONSTRUCTOR
    public SolarPanelContainer(int id, Inventory playerInv) {
        super(id, playerInv, MachineProperties.SOLAR_PANEL);
    }
    
    // SERVER CONSTRUCTOR
    public SolarPanelContainer(int id, Inventory playerInv, IItemHandler slots, IItemHandler outputSlots, BlockPos pos, ContainerData data) {
        super(id, playerInv, slots, outputSlots, pos, MachineProperties.SOLAR_PANEL, data);
    }
    @Override
    public void createSlots(IItemHandler slots, IItemHandler outputSlots) {
        

    }

    @Override
    public boolean stillValid(Player player) {
        return super.stillValid(player) && stillValid(containerAccess, player, properties.block());
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        return super.quickMoveStack(player, index);
    }
}
