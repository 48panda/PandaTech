package com._48panda.colorfulbiomes.block.machines;

import com._48panda.colorfulbiomes.block.machines.electric_furnace.ElectricFurnaceContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;

public abstract class MachineScreen<T extends MachineContainer> extends AbstractContainerScreen<T> {
    private final ResourceLocation texture;
    public MachineScreen(T container, Inventory playerInv, Component title, ResourceLocation texture) {
        super(container, playerInv, title);
        leftPos = 0;
        topPos = 0;
        imageWidth = 176;
        imageHeight = 166;
        this.texture = texture;
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        renderBackground(stack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, texture);
        blit(stack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        //this.font.draw(stack, title, leftPos + 7, topPos + 5, 0x404040);
        //this.font.draw(stack, playerInventoryTitle, this.leftPos + 7, this.topPos + 74, 0x404040);
    }
    public void drawFractionWithFullHover(PoseStack stack, int x, int y, int u, int v, int w, int h, int numerator, int denominator, boolean doHorizontal,
                                          int mouseX, int mouseY, boolean startBottomOrRight, boolean inverse) {
        float percent = (float)numerator / (float)denominator;
        if (inverse) {
            percent = 1f - percent;
        }
        int imWidth = w;
        int imHeight = h;
        if (doHorizontal) {
            imWidth = Math.round(w * percent);
        } else {
            imHeight = Math.round(h * percent);
        }
        int imX = x;
        int imY = y;
        if (startBottomOrRight) {
            if (doHorizontal) {
                imX = imX + w - imWidth;
            } else {
                imY = imY + h - imHeight;
            }
        }
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, texture);
        blit(stack, leftPos + imX, topPos + imY, u, v, imWidth, imHeight);
        hover(stack, new TextComponent(Integer.toString(numerator) + " / " + Integer.toString(denominator)), leftPos + x, topPos + y, w, h, mouseX, mouseY);

    }
    public void hover(PoseStack stack,Component toShow, int x, int y, int w, int h, int mouseX, int mouseY) {
        if (x <= mouseX && mouseX <= x + w && y <= mouseY && mouseY <= y + h) {
            renderTooltip(stack, toShow, mouseX, mouseY);
        }
    }
}
