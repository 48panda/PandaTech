package com._48panda.tech.block.cable.screen;

import com._48panda.tech.block.cable.container.AbstractCableContainer;
import com._48panda.tech.network.client.gui.ExtendedContainerScreen;
import com._48panda.tech.network.client.gui.widgets.ToggleImageButton;
import com._48panda.tech.init.PandaTechMessages;
import com._48panda.tech.network.packet.SetLockedC2SPacket;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractCableScreen<T extends AbstractCableContainer> extends ExtendedContainerScreen<T> {
    protected ToggleImageButton locker;
    public AbstractCableScreen(T container, Inventory playerInv, Component title, ResourceLocation texture) {
        super(container, playerInv, title, texture);
        leftPos = 0;
        topPos = 0;
        imageWidth = 176;
        imageHeight = 166;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        locker.setOn(menu.data.get(1) == 1);
        hover(stack, new TranslatableComponent("container.panda_tech.cable.lock"), leftPos + 160, topPos + 5, 12, 12, mouseX, mouseY);
    }

    void setLocked(boolean isLocked) {
        Direction dir = Direction.from3DDataValue(menu.data.get(0));
        menu.data.set(1, isLocked?1:0);
        int x = menu.data.get(2);
        int y = menu.data.get(3);
        int z = menu.data.get(4);
        if (Minecraft.getInstance().level.isClientSide()) {
            PandaTechMessages.sendToServer(new SetLockedC2SPacket(new BlockPos(x, y, z), dir, isLocked));
        }
    }
    @Override
    protected void init() {
        super.init();
        locker = new ToggleImageButton(leftPos + 157, topPos + 4, 15, 15, 176, 0, texture, this::setLocked);
        addRenderableWidget(locker);
    }
}
