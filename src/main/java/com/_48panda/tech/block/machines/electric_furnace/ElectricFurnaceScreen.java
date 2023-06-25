package com._48panda.tech.block.machines.electric_furnace;

import com._48panda.tech.PandaTech;
import com._48panda.tech.block.machines.AugmentUtils;
import com._48panda.tech.block.machines.AugmentableMachineScreen;
import com._48panda.tech.block.machines.MachineProperties;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElectricFurnaceScreen extends AugmentableMachineScreen<ElectricFurnaceContainer> {
    
    public ElectricFurnaceScreen(ElectricFurnaceContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title,
                new ResourceLocation(PandaTech.MODID, "textures/gui/electric_furnace.png"), MachineProperties.ELECTRIC_FURNACE);
        
    }

    @Override
    public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        int energy = this.menu.data.get(0);
        int energyMax = this.menu.data.get(1);
        int progress = this.menu.data.get(2);
        int progressMax = this.menu.data.get(3);
        drawFractionWithFullHover(stack, 155, 11, 177, 32, 8, 64, energy, energyMax, false, mouseX, mouseY, true, false, true);
        drawFractionWithFullHover(stack, 80, 35, 177, 14, 22, 16, progress, progressMax, true, mouseX, mouseY, false, false, false);
        renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    public List<Component> getInfoHover() {
        List<ItemStack> items = new ArrayList<>();
        items.add(menu.slots.get(36).getItem());
        Component speed = new TranslatableComponent("tooltip.panda_tech.electric_furnace.speed_effect", 200/AugmentUtils.getAugmentedSpeedMultiplier(items));
        Component efficiency = new TranslatableComponent("tooltip.panda_tech.electric_furnace.efficiency_effect", 10/AugmentUtils.getAugmentedEfficiencyMultiplier(items));
        return Arrays.asList(speed, efficiency);
    }

    @Override
    protected void init() {
        super.init();
    }
}
