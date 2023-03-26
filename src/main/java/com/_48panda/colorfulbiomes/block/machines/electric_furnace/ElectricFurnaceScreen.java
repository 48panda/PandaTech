package com._48panda.colorfulbiomes.block.machines.electric_furnace;

import com._48panda.colorfulbiomes.ColorfulBiomesMod;
import com._48panda.colorfulbiomes.block.machines.MachineScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class ElectricFurnaceScreen extends MachineScreen<ElectricFurnaceContainer> {
    
    public ElectricFurnaceScreen(ElectricFurnaceContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title,
                new ResourceLocation(ColorfulBiomesMod.MODID, "textures/gui/electric_furnace.png"));
        
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        int energy = this.menu.data.get(0);
        int energyMax = this.menu.data.get(1);
        int progress = this.menu.data.get(2);
        int progressMax = this.menu.data.get(3);
        drawFractionWithFullHover(stack, 155, 11, 177, 32, 8, 64, energy, energyMax, false, mouseX, mouseY, true, false);
        drawFractionWithFullHover(stack, 80, 35, 177, 14, 22, 16, progress, progressMax, true, mouseX, mouseY, false, false);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
    }
}
