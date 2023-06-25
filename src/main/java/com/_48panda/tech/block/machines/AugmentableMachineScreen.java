package com._48panda.tech.block.machines;

import com._48panda.tech.PandaTech;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AugmentableMachineScreen<T extends AugmentableMachineContainer> extends MachineScreen<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(PandaTech.MODID, "textures/gui/augment.png");
    private final MachineProperties properties;
    public AugmentableMachineScreen(T container, Inventory playerInv, Component title, ResourceLocation texture, MachineProperties properties) {
        super(container, playerInv, title, texture);
        this.properties = properties;
    }

    @Override
    protected void renderBg(@NotNull PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(stack, partialTicks, mouseX, mouseY);
            bindTexture(TEXTURE, 1.0f, 0.6f, 0.1f, 1.0f);
        blit(stack, leftPos + imageWidth, topPos, 0, 0, 34, 6);
        for (int i = 0; i < properties.augmentProperties().numAugments(); i++) {
            blit(stack, leftPos + imageWidth, topPos + 6 + i * 23, 0, 6, 34, 23);
        }
        blit(stack, leftPos + imageWidth, topPos + 6 + properties.augmentProperties().numAugments() * 23, 0, 29, 34, 5);
        bindTexture(TEXTURE);
        blit(stack, leftPos + imageWidth - 10, topPos + 3, 34, 0, 7, 7);
        hover(stack, getInfoHover(), leftPos + imageWidth - 10, topPos + 3, 7, 7, mouseX, mouseY);
    }
    public abstract List<Component> getInfoHover();
}
