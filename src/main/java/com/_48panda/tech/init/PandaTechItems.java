
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.tech.init;

import com._48panda.tech.block.machines.AugmentType;
import com._48panda.tech.item.AugmentItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import com._48panda.tech.item.TabIconItem;
import com._48panda.tech.item.RedstoneArmorItem;
import com._48panda.tech.item.CapacitorItem;
import com._48panda.tech.PandaTech;

import java.util.function.Supplier;

public class PandaTechItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, PandaTech.MODID);
	public static final RegistryObject<Item> REDSTONE_ARMOR_HELMET = REGISTRY.register("redstone_armor_helmet", () -> new RedstoneArmorItem.Helmet());
	public static final RegistryObject<Item> REDSTONE_ARMOR_CHESTPLATE = REGISTRY.register("redstone_armor_chestplate", () -> new RedstoneArmorItem.Chestplate());
	public static final RegistryObject<Item> REDSTONE_ARMOR_LEGGINGS = REGISTRY.register("redstone_armor_leggings", () -> new RedstoneArmorItem.Leggings());
	public static final RegistryObject<Item> REDSTONE_ARMOR_BOOTS = REGISTRY.register("redstone_armor_boots", () -> new RedstoneArmorItem.Boots());
	public static final RegistryObject<Item> TAB_ICON = REGISTRY.register("tab_icon", () -> new TabIconItem());
	public static final RegistryObject<Item> CAPACITOR = REGISTRY.register("capacitor", () -> new CapacitorItem());
	public static final RegistryObject<Item> WRENCH = REGISTRY.register("wrench", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB).stacksTo(1)));
	public static final RegistryObject<Item> RED_PISTON = block(PandaTechBlocks.RED_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> ORANGE_PISTON = block(PandaTechBlocks.ORANGE_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> YELLOW_PISTON = block(PandaTechBlocks.YELLOW_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> GREEN_PISTON = block(PandaTechBlocks.GREEN_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> BLUE_PISTON = block(PandaTechBlocks.BLUE_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> ENERGY_CABLE = block(PandaTechBlocks.ENERGY_CABLE,	PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ITEM_CABLE = block(PandaTechBlocks.ITEM_CABLE,	PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	//public static final RegistryObject<Item> NETWORK_MANAGER = block(PandaTechBlocks.NETWORK_MANAGER, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	//public static final RegistryObject<Item> ROBOT = block(PandaTechBlocks.ROBOT, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ELECTRIC_FURNACE = block(PandaTechBlocks.ELECTRIC_FURNACE, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> BURNER_GENERATOR = block(PandaTechBlocks.BURNER_GENERATOR, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> SPEED_UPGRADE_1 = REGISTRY.register("speed_upgrade_1", () -> new AugmentItem(1, AugmentType.SPEED1));
	public static final RegistryObject<Item> EFFICIENCY_UPGRADE_1 = REGISTRY.register("efficiency_upgrade_1", () -> new AugmentItem(1, AugmentType.EFFICIENCY1));


	public static final RegistryObject<Item> COPPER_WIRE = block(PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> COPPER_BLOCK = block(PandaTechBlocks.COPPER_INFUSED_REDSTONE_BLOCK, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> COPPER_TORCH = block("copper_infused_redstone_torch", () -> new StandingAndWallBlockItem(PandaTechBlocks.COPPER_INFUSED_RESTONE_TORCH.get(), PandaTechBlocks.COPPER_INFUSED_REDSTONE_TORCH_WALL.get(), new Item.Properties().tab(PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB)));
	public static final RegistryObject<Item> COPPER_REPEATER = block(PandaTechBlocks.COPPER_INFUSED_REPEATER, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> COPPER_COMPARATOR = block(PandaTechBlocks.COPPER_INFUSED_COMPARATOR, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> COPPER_LEVER = block(PandaTechBlocks.COPPER_LEVER, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> COPPER_PRESSURE_PLATE = block(PandaTechBlocks.COPPER_PRESSURE_PLATE, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> COPPER_BUTTON = block(PandaTechBlocks.COPPER_BUTTON, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	

	private static RegistryObject<Item> block(String id, Supplier<Item> sawbi) {
		return REGISTRY.register(id, sawbi);
	}
	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
