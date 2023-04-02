package com._48panda.tech.network.client.gui.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class ToggleImageButton extends Button {
    private final ResourceLocation resourceLocation;
    private final int xTexStart;
    private final int yTexStart;
    private final int yTexOnDiff;
    private final int xTexOnHover;
    private final int textureWidth;
    private final int textureHeight;
    private final Consumer<Boolean> press;
    private boolean on;

    public ToggleImageButton(int x, int y, int width, int height, int xTex, int yTex, ResourceLocation texture, Consumer<Boolean> onPress) {
        this(x, y, width, height, xTex, yTex, height, width, texture, 256, 256, onPress);
    }

    public ToggleImageButton(int x, int y, int width, int height, int xTex, int yTex, int yTexOnDiff, int xTexOnHover, ResourceLocation texture, Consumer<Boolean> onPress) {
        this(x, y, width, height, xTex, yTex, yTexOnDiff, xTexOnHover, texture, 256, 256, onPress);
    }

    public ToggleImageButton(int x, int y, int width, int height, int xTex, int yTex, int yTexOnDiff, int xTexOnHover, ResourceLocation texture, int texWidth, int texHeight, Consumer<Boolean> onPress) {
        this(x, y, width, height, xTex, yTex, yTexOnDiff, xTexOnHover, texture, texWidth, texHeight, onPress, TextComponent.EMPTY);
    }

    public ToggleImageButton(int x, int y, int width, int height, int xTex, int yTex, int yTexOnDiff, int xTexOnHover, ResourceLocation texture, int texWidth, int texHeight, Consumer<Boolean> onPress, Component message) {
        this(x, y, width, height, xTex, yTex, yTexOnDiff, xTexOnHover, texture, texWidth, texHeight, onPress, NO_TOOLTIP, message);
    }

    public ToggleImageButton(int x, int y, int width, int height, int xTex, int yTex, int yTexOnDiff, int xTexOnHover, ResourceLocation texture, int texWidth, int texHeight, Consumer<Boolean> onPress, Button.OnTooltip tooltip, Component message) {
        super(x, y, width, height, message, z -> {}, tooltip);
        this.textureWidth = texWidth;
        this.textureHeight = texHeight;
        this.xTexStart = xTex;
        this.yTexStart = yTex;
        this.yTexOnDiff = yTexOnDiff;
        this.xTexOnHover = xTexOnHover;
        this.resourceLocation = texture;
        this.press = onPress;
        this.on = false;
    }

    @Override
    public void onPress() {
        this.press.accept(!on);
    }
    
    public void setOn(boolean isOn) {
        on = isOn;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void renderButton(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, this.resourceLocation);
        int i = this.yTexStart;
        if (on) {
            i += this.yTexOnDiff;
        }
        int j = this.xTexStart;
        if (this.isHovered) {
            j += this.xTexOnHover;
        }

        RenderSystem.enableDepthTest();
        blit(stack, this.x, this.y, (float)j, (float)i, this.width, this.height, this.textureWidth, this.textureHeight);
        if (this.isHovered) {
            this.renderToolTip(stack, mouseX, mouseY);
        }

    }
}