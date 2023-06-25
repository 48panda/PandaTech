
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.tech.init;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class PandaTechTabs {
	public static CreativeModeTab TAB_ORES_CREATIVE_TAB;
	public static CreativeModeTab TAB_ELECTROCUTION_CREATIVE_TAB;

	public static void load() {
		TAB_ELECTROCUTION_CREATIVE_TAB = new CreativeModeTab("tabelectrocution_creative_tab") {
			@Override
			public @NotNull ItemStack makeIcon() {
				return new ItemStack(PandaTechItems.TAB_ICON.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}

			@Override
			public void fillItemList(@NotNull NonNullList<ItemStack> items) {
				super.fillItemList(items);
			}
		};
		TAB_ORES_CREATIVE_TAB = new CreativeModeTab("panda_tech_ores_creative_tab") {
			@Override
			public @NotNull ItemStack makeIcon() {
				return new ItemStack(Items.IRON_ORE);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}

			@Override
			public void fillItemList(@NotNull NonNullList<ItemStack> items) {
				List<Item> ores = Arrays.asList(
						Items.COAL_ORE,
						Items.COPPER_ORE,
						Items.IRON_ORE,
						Items.GOLD_ORE,
						Items.LAPIS_ORE,
						Items.REDSTONE_ORE,
						Items.DIAMOND_ORE,
						Items.EMERALD_ORE,
						PandaTechItems.TIN_ORE.get(),
						PandaTechItems.TITANIUM_ORE.get(),
						PandaTechItems.ZINC_ORE.get(),
						PandaTechItems.ALUMINIUM_ORE.get(),
						PandaTechItems.NICKEL_ORE.get(),
						PandaTechItems.COBALT_ORE.get()
				);
				List<Item> deepslate_ores = Arrays.asList(
						Items.DEEPSLATE_COAL_ORE,
						Items.DEEPSLATE_COPPER_ORE,
						Items.DEEPSLATE_IRON_ORE,
						Items.DEEPSLATE_GOLD_ORE,
						Items.DEEPSLATE_LAPIS_ORE,
						Items.DEEPSLATE_REDSTONE_ORE,
						Items.DEEPSLATE_DIAMOND_ORE,
						Items.DEEPSLATE_EMERALD_ORE,
						PandaTechItems.DEEPSLATE_TIN_ORE.get(),
						PandaTechItems.DEEPSLATE_TITANIUM_ORE.get(),
						PandaTechItems.DEEPSLATE_ZINC_ORE.get(),
						PandaTechItems.DEEPSLATE_ALUMINIUM_ORE.get(),
						PandaTechItems.DEEPSLATE_NICKEL_ORE.get(),
						PandaTechItems.DEEPSLATE_COBALT_ORE.get()
				);
				List<Item> raw_ores = Arrays.asList(
						PandaTechItems.RAW_COAL.get(),
						Items.         RAW_COPPER,
						Items.         RAW_IRON,
						Items.         RAW_GOLD,
						PandaTechItems.RAW_LAPIS.get(),
						PandaTechItems.RAW_REDSTONE.get(),
						PandaTechItems.RAW_DIAMOND.get(),
						PandaTechItems.RAW_EMERALD.get(),
						PandaTechItems.RAW_TIN.get(),
						PandaTechItems.RAW_TITANIUM.get(),
						PandaTechItems.RAW_ZINC.get(),
						PandaTechItems.RAW_ALUMINIUM.get(),
						PandaTechItems.RAW_NICKEL.get(),
						PandaTechItems.RAW_COBALT.get()
				);
				List<Item> raw_ore_blocks = Arrays.asList(
						PandaTechItems.RAW_COAL_BLOCK.get(),
						Items.         RAW_COPPER_BLOCK,
						Items.         RAW_IRON_BLOCK,
						Items.         RAW_GOLD_BLOCK,
						PandaTechItems.RAW_LAPIS_BLOCK.get(),
						PandaTechItems.RAW_REDSTONE_BLOCK.get(),
						PandaTechItems.RAW_DIAMOND_BLOCK.get(),
						PandaTechItems.RAW_EMERALD_BLOCK.get(),
						PandaTechItems.RAW_TIN_BLOCK.get(),
						PandaTechItems.RAW_TITANIUM_BLOCK.get(),
						PandaTechItems.RAW_ZINC_BLOCK.get(),
						PandaTechItems.RAW_ALUMINIUM_BLOCK.get(),
						PandaTechItems.RAW_NICKEL_BLOCK.get(),
						PandaTechItems.RAW_COBALT_BLOCK.get()
				);
				List<Item> dust = Arrays.asList(
						PandaTechItems.COAL_DUST.get(),
						PandaTechItems.COPPER_DUST.get(),
						PandaTechItems.IRON_DUST.get(),
						PandaTechItems.GOLD_DUST.get(),
						PandaTechItems.LAPIS_DUST.get(),
						Items.         REDSTONE,
						PandaTechItems.DIAMOND_DUST.get(),
						PandaTechItems.EMERALD_DUST.get(),
						PandaTechItems.TIN_DUST.get(),
						PandaTechItems.TITANIUM_DUST.get(),
						PandaTechItems.ZINC_DUST.get(),
						PandaTechItems.ALUMINIUM_DUST.get(),
						PandaTechItems.NICKEL_DUST.get(),
						PandaTechItems.COBALT_DUST.get()
				);
				
				List<Item> ingots = Arrays.asList(
						Items.COAL,
						Items.COPPER_INGOT,
						Items.IRON_INGOT,
						Items.GOLD_INGOT,
						Items.LAPIS_LAZULI,
						Items.REDSTONE,
						Items.DIAMOND,
						Items.EMERALD,
						PandaTechItems.TIN_INGOT.get(),
						PandaTechItems.TITANIUM_INGOT.get(),
						PandaTechItems.ZINC_INGOT.get(),
						PandaTechItems.ALUMINIUM_INGOT.get(),
						PandaTechItems.NICKEL_INGOT.get(),
						PandaTechItems.COBALT_INGOT.get()
				);

				List<Item> plates = Arrays.asList(
						null,
						PandaTechItems.COPPER_PLATE.get(),
						PandaTechItems.IRON_PLATE.get(),
						PandaTechItems.GOLD_PLATE.get(),
						PandaTechItems.LAPIS_PLATE.get(),
						PandaTechItems.REDSTONE_PLATE.get(),
						PandaTechItems.DIAMOND_PLATE.get(),
						PandaTechItems.EMERALD_PLATE.get(),
						PandaTechItems.TIN_PLATE.get(),
						PandaTechItems.TITANIUM_PLATE.get(),
						PandaTechItems.ZINC_PLATE.get(),
						PandaTechItems.ALUMINIUM_PLATE.get(),
						PandaTechItems.NICKEL_PLATE.get(),
						PandaTechItems.COBALT_PLATE.get()
				);
				
				List<List<Item>> itemCategories = Arrays.asList(
						ores,
						deepslate_ores,
						raw_ores,
						raw_ore_blocks,
						dust,
						ingots,
						plates
				);
				
				for (List<Item> category: itemCategories) {
					int i = 0;
					for (; i < category.size(); i++) {
						if (category.get(i) != null) {
							items.add(new ItemStack(category.get(i)));
						} else {
							items.add(ItemStack.EMPTY);
						}
					}
					for (;i % 9 != 0; i++) {
						items.add(ItemStack.EMPTY);
					}
				}
			}
		};
	}
}
