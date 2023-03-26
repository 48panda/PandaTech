package com._48panda.colorfulbiomes.block.machines.burner_generator;

import com._48panda.colorfulbiomes.ColorfulBiomesMod;
import com._48panda.colorfulbiomes.block.machines.MachineScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BurnerGeneratorScreen extends MachineScreen<BurnerGeneratorContainer> {
    
    public BurnerGeneratorScreen(BurnerGeneratorContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title,
                new ResourceLocation(ColorfulBiomesMod.MODID, "textures/gui/burner_generator.png"));
        
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        int energy = this.menu.data.get(0);
        int energyMax = this.menu.data.get(1);
        int progress = this.menu.data.get(2);
        int progressMax = this.menu.data.get(3);
        drawFractionWithFullHover(stack, 155, 11, 177, 32, 8, 64, energy, energyMax, false, mouseX, mouseY, true, false);
        drawFractionWithFullHover(stack, 43, 57, 1, 167, 90, 9, progress, progressMax, true, mouseX, mouseY, false, false);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
    }
}
