package com._48panda.tech.block.machines.electric_furnace;

import com._48panda.tech.ComponentHelper;
import com._48panda.tech.PandaTech;
import com._48panda.tech.block.machines.AugmentUtils;
import com._48panda.tech.block.machines.AugmentableMachineScreen;
import com._48panda.tech.block.machines.MachineProperties;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElectricFurnaceScreen extends AugmentableMachineScreen<ElectricFurnaceContainer> {
    
    public ElectricFurnaceScreen(ElectricFurnaceContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title,
                new ResourceLocation(PandaTech.MODID, "textures/gui/electric_furnace.png"), MachineProperties.ELECTRIC_FURNACE);
        
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
    public List<Component> getInfoHover() {
        List<ItemStack> items = new ArrayList<>();
        items.add(menu.slots.get(36).getItem());
        Component speed = ComponentHelper.getComponentFromMulDouble(1/AugmentUtils.getAugmentedSpeedMultiplier(items),
                new TranslatableComponent("tooltip.panda_tech.electric_furnace.speed_effect"), false);
        Component efficiency = ComponentHelper.getComponentFromMulDouble(1/AugmentUtils.getAugmentedEfficiencyMultiplier(items),
                new TranslatableComponent("tooltip.panda_tech.electric_furnace.efficiency_effect"), false);
        return Arrays.asList(speed, efficiency);
    }

    @Override
    protected void init() {
        super.init();
    }
}
