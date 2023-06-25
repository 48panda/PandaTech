package com._48panda.tech.network.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class ExtendedContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    protected final ResourceLocation texture;

    public ExtendedContainerScreen(T container, Inventory inv, Component title, ResourceLocation texture) {
        super(container, inv, title);
        this.texture = texture;
    }

    @Override
    protected void renderBg(@NotNull PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        renderBackground(stack);
        bindTexture(texture);
        blit(stack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
    public void bindTexture(ResourceLocation tex) {
        bindTexture(tex, 1, 1, 1,1);
    }
    public void bindTexture(ResourceLocation tex, float r, float g, float b, float a) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(r,g,b,a);
        RenderSystem.setShaderTexture(0, tex);
    }
    @Override
    public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        //this.font.draw(stack, title, leftPos + 7, topPos + 5, 0x404040);
        //this.font.draw(stack, playerInventoryTitle, this.leftPos + 7, this.topPos + 74, 0x404040);
        
    }
    public void drawFractionWithFullHover(PoseStack stack, int x, int y, int u, int v, int w, int h, int numerator, int denominator, boolean doHorizontal,
                                          int mouseX, int mouseY, boolean startBottomOrRight, boolean inverse, boolean doHover) {
        float percent = (float)numerator / (float)denominator;
        if (inverse) {
            percent = 1f - percent;
        }
        percent = Math.min(1, Math.max(percent, 0));
        int imWidth = w;
        int imHeight = h;
        if (doHorizontal) {
            imWidth = Math.round(w * percent);
        } else {
            imHeight = Math.round(h * percent);
        }
        int imX = x;
        int imY = y;
        int imU = u;
        int imV = v;
        if (startBottomOrRight) {
            if (doHorizontal) {
                imX = imX + w - imWidth;
                imU = imU + w - imWidth;
            } else {
                imY = imY + h - imHeight;
                imV = imV + h - imHeight;
            }
        }
        bindTexture(texture);
        blit(stack, leftPos + imX, topPos + imY, imU, imV, imWidth, imHeight);
        if (doHover) {
            hover(stack, new TextComponent(numerator + " / " +denominator), leftPos + x, topPos + y, w, h, mouseX, mouseY);
        }

    }
    public void hover(PoseStack stack, Component toShow, int x, int y, int w, int h, int mouseX, int mouseY) {
        if (x <= mouseX && mouseX <= x + w && y <= mouseY && mouseY <= y + h) {
            renderTooltip(stack, toShow, mouseX, mouseY);
        }
    }
    public void hover(PoseStack stack, List<Component> toShow, int x, int y, int w, int h, int mouseX, int mouseY) {
        if (x <= mouseX && mouseX <= x + w && y <= mouseY && mouseY <= y + h) {
            renderTooltip(stack, toShow, Optional.empty(), mouseX, mouseY);
        }
    }
}
