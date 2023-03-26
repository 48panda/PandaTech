
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.colorfulbiomes.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import com._48panda.colorfulbiomes.item.TabIconItem;
import com._48panda.colorfulbiomes.item.RedstoneArmorItem;
import com._48panda.colorfulbiomes.item.CapacitorItem;
import com._48panda.colorfulbiomes.ColorfulBiomesMod;

public class ColorfulBiomesModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ColorfulBiomesMod.MODID);
	public static final RegistryObject<Item> REDSTONE_ARMOR_HELMET = REGISTRY.register("redstone_armor_helmet", () -> new RedstoneArmorItem.Helmet());
	public static final RegistryObject<Item> REDSTONE_ARMOR_CHESTPLATE = REGISTRY.register("redstone_armor_chestplate",
			() -> new RedstoneArmorItem.Chestplate());
	public static final RegistryObject<Item> REDSTONE_ARMOR_LEGGINGS = REGISTRY.register("redstone_armor_leggings",
			() -> new RedstoneArmorItem.Leggings());
	public static final RegistryObject<Item> REDSTONE_ARMOR_BOOTS = REGISTRY.register("redstone_armor_boots", () -> new RedstoneArmorItem.Boots());
	public static final RegistryObject<Item> TAB_ICON = REGISTRY.register("tab_icon", () -> new TabIconItem());
	public static final RegistryObject<Item> CAPACITOR = REGISTRY.register("capacitor", () -> new CapacitorItem());
	public static final RegistryObject<Item> RED_PISTON = block(ColorfulBiomesModBlocks.RED_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> ORANGE_PISTON = block(ColorfulBiomesModBlocks.ORANGE_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> YELLOW_PISTON = block(ColorfulBiomesModBlocks.YELLOW_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> GREEN_PISTON = block(ColorfulBiomesModBlocks.GREEN_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> BLUE_PISTON = block(ColorfulBiomesModBlocks.BLUE_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> ENERGY_CABLE = block(ColorfulBiomesModBlocks.ENERGY_CABLE,
			ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> NETWORK_MANAGER = block(ColorfulBiomesModBlocks.NETWORK_MANAGER,
			ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> TEST_GENERATOR = block(ColorfulBiomesModBlocks.TEST_GENERATOR,
			ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> TEST_CONSUMER = block(ColorfulBiomesModBlocks.TEST_CONSUMER,
			ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ROBOT = block(ColorfulBiomesModBlocks.ROBOT, ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ELECTRIC_FURNACE = block(ColorfulBiomesModBlocks.ELECTRIC_FURNACE, ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> BURNER_GENERATOR = block(ColorfulBiomesModBlocks.BURNER_GENERATOR, ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
