package com._48panda.tech.block.cable.screen;

import com._48panda.tech.PandaTech;
import com._48panda.tech.block.cable.container.EnergyCableContainer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EnergyCableScreen extends AbstractCableScreen<EnergyCableContainer> {
    private static final ResourceLocation texture = new ResourceLocation(PandaTech.MODID, "textures/gui/cable.png");
    public EnergyCableScreen(EnergyCableContainer container, Inventory inv, Component title, ResourceLocation texture) {
        super(container, inv, title, texture);
    }

    public EnergyCableScreen(EnergyCableContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title, texture);

    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);

        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
    }
}
