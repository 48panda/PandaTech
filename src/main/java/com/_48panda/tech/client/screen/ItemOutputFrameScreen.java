package com._48panda.tech.client.screen;

import com._48panda.tech.container.ItemOutputFrameContainer;
import com._48panda.tech.network.client.gui.ExtendedContainerScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ItemOutputFrameScreen extends ExtendedContainerScreen<ItemOutputFrameContainer> {
    public ItemOutputFrameScreen(ItemOutputFrameContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title, new ResourceLocation("textures/gui/container/hopper.png"));
        leftPos = 0;
        topPos = 0;
        imageWidth = 176;
        imageHeight = 133;
        this.inventoryLabelY = this.imageHeight - 94;
    }
    

    @Override
    public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        renderTooltip(stack, mouseX, mouseY);
    }
    
}
