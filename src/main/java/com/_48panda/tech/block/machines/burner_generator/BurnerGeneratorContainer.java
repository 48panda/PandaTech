package com._48panda.tech.block.machines.burner_generator;

import com._48panda.tech.block.machines.AugmentableMachineContainer;
import com._48panda.tech.block.machines.InputSlotItemHandler;
import com._48panda.tech.block.machines.MachineContainer;
import com._48panda.tech.block.machines.MachineProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class BurnerGeneratorContainer extends AugmentableMachineContainer {

    //public final ContainerData data;
    
    //CLIENT CONSTRUCTOR
    public BurnerGeneratorContainer(int id, Inventory playerInv) {
        super(id, playerInv, MachineProperties.BURNER_GENERATOR);
    }
    
    // SERVER CONSTRUCTOR
    public BurnerGeneratorContainer(int id, Inventory playerInv, IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots, BlockPos pos, ContainerData data) {
        super(id, playerInv, slots, outputSlots, augmentSlots, pos, MachineProperties.BURNER_GENERATOR, data);
        //this.data = data;
    }
    public void createSlots(IItemHandler slots, IItemHandler outputSlots, IItemHandler augmentSlots) {
        addSlot(new InputSlotItemHandler(slots, 0, 80, 35));

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
