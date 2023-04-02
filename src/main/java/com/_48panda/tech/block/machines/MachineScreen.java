package com._48panda.tech.block.machines;

import com._48panda.tech.network.client.gui.ExtendedContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class MachineScreen<T extends MachineContainer> extends ExtendedContainerScreen<T> {
    public MachineScreen(T container, Inventory playerInv, Component title, ResourceLocation texture) {
        super(container, playerInv, title, texture);
        leftPos = 0;
        topPos = 0;
        imageWidth = 176;
        imageHeight = 166;
    }
}
