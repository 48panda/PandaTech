
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.colorfulbiomes.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class ColorfulBiomesModTabs {
	public static CreativeModeTab TAB_ELECTROCUTION_CREATIVE_TAB;

	public static void load() {
		TAB_ELECTROCUTION_CREATIVE_TAB = new CreativeModeTab("tabelectrocution_creative_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(ColorfulBiomesModItems.TAB_ICON.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
