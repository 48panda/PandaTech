package com._48panda.tech.block.multiblock;

import com._48panda.tech.block.machines.AugmentableMachineScreen;
import com._48panda.tech.block.machines.MachineProperties;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractMultiblockControllerScreen<T extends AbstractMultiblockControllerContainer> extends AugmentableMachineScreen<T> {
    
    public AbstractMultiblockControllerScreen(T container, Inventory playerInv, Component title, ResourceLocation texture, MachineProperties properties) {
        super(container, playerInv, title, texture, properties);
    }
    @Override
    public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void init() {
        super.init();
    }
    
}
