package com._48panda.tech.block.machines.solar_panel;

import com._48panda.tech.PandaTech;
import com._48panda.tech.block.machines.MachineScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class SolarPanelScreen extends MachineScreen<SolarPanelContainer> {
    
    public SolarPanelScreen(SolarPanelContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title,
                new ResourceLocation(PandaTech.MODID, "textures/gui/solar_panel.png"));
        
    }

    @Override
    public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        int energy = this.menu.data.get(0);
        int energyMax = this.menu.data.get(1);
        drawFractionWithFullHover(stack, 155, 11, 177, 32, 8, 64, energy, energyMax, false, mouseX, mouseY, true, false, true);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
    }
}
