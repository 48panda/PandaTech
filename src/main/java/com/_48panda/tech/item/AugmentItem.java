package com._48panda.tech.item;

import com._48panda.tech.ComponentHelper;
import com._48panda.tech.block.machines.AugmentType;
import com._48panda.tech.init.PandaTechTabs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class AugmentItem extends Item {
    protected AugmentType type;
    public AugmentItem(int stackSize, AugmentType type) {
        super(new Item.Properties().tab(PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB).stacksTo(stackSize).rarity(Rarity.COMMON));
        this.type = type;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, components, isAdvanced);
        if (Screen.hasShiftDown()) {
            ComponentHelper.addToListIfNotEmpty(components, ComponentHelper.getComponentFromAddDouble(type.getSpeed(),
                    new TranslatableComponent("tooltip.panda_tech.augment_generic.speed").withStyle(ChatFormatting.RESET),
                    true));
            ComponentHelper.addToListIfNotEmpty(components, ComponentHelper.getComponentFromAddDouble(type.getEfficiency(),
                    new TranslatableComponent("tooltip.panda_tech.augment_generic.efficiency").withStyle(ChatFormatting.RESET),
                    true));
            
        } else {
            components.add(new TranslatableComponent("tooltip.panda_tech.more_info"));
        }
    }
}
