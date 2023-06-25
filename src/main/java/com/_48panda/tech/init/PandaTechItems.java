
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.tech.init;

import com._48panda.tech.block.machines.AugmentType;
import com._48panda.tech.item.AugmentItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import com._48panda.tech.item.TabIconItem;
import com._48panda.tech.item.RedstoneArmorItem;
import com._48panda.tech.item.CapacitorItem;
import com._48panda.tech.PandaTech;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class PandaTechItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, PandaTech.MODID);
	public static final RegistryObject<Item> REDSTONE_ARMOR_HELMET = REGISTRY.register("redstone_armor_helmet", RedstoneArmorItem.Helmet::new);
	public static final RegistryObject<Item> REDSTONE_ARMOR_CHESTPLATE = REGISTRY.register("redstone_armor_chestplate", RedstoneArmorItem.Chestplate::new);
	public static final RegistryObject<Item> REDSTONE_ARMOR_LEGGINGS = REGISTRY.register("redstone_armor_leggings", RedstoneArmorItem.Leggings::new);
	public static final RegistryObject<Item> REDSTONE_ARMOR_BOOTS = REGISTRY.register("redstone_armor_boots", RedstoneArmorItem.Boots::new);
	public static final RegistryObject<Item> TAB_ICON = REGISTRY.register("tab_icon", TabIconItem::new);
	public static final RegistryObject<Item> CAPACITOR = REGISTRY.register("capacitor", CapacitorItem::new);
	public static final RegistryObject<Item> WRENCH = REGISTRY.register("wrench", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB).stacksTo(1)));
	public static final RegistryObject<Item> RED_PISTON = block(PandaTechBlocks.RED_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> ORANGE_PISTON = block(PandaTechBlocks.ORANGE_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> YELLOW_PISTON = block(PandaTechBlocks.YELLOW_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> GREEN_PISTON = block(PandaTechBlocks.GREEN_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> BLUE_PISTON = block(PandaTechBlocks.BLUE_PISTON, CreativeModeTab.TAB_REDSTONE);
	public static final RegistryObject<Item> ENERGY_CABLE = block(PandaTechBlocks.ENERGY_CABLE,	PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ITEM_CABLE = block(PandaTechBlocks.ITEM_CABLE,	PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	//public static final RegistryObject<Item> ROBOT = block(PandaTechBlocks.ROBOT, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ELECTRIC_FURNACE = block(PandaTechBlocks.ELECTRIC_FURNACE, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> BURNER_GENERATOR = block(PandaTechBlocks.BURNER_GENERATOR, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> SOLAR_PANEL = block(PandaTechBlocks.SOLAR_PANEL, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);

	public static final RegistryObject<Item> RAW_COAL = REGISTRY.register("raw_coal", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> RAW_DIAMOND = REGISTRY.register("raw_diamond", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> RAW_EMERALD = REGISTRY.register("raw_emerald", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> RAW_LAPIS = REGISTRY.register("raw_lapis", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> RAW_REDSTONE = REGISTRY.register("raw_redstone", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));

	public static final RegistryObject<Item> COAL_DUST = REGISTRY.register("coal_dust", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> COPPER_DUST = REGISTRY.register("copper_dust", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> DIAMOND_DUST = REGISTRY.register("diamond_dust", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> EMERALD_DUST = REGISTRY.register("emerald_dust", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> IRON_DUST = REGISTRY.register("iron_dust", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> GOLD_DUST = REGISTRY.register("gold_dust", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	public static final RegistryObject<Item> LAPIS_DUST = REGISTRY.register("lapis_dust", () -> new Item(new Item.Properties().tab(PandaTechTabs.TAB_ORES_CREATIVE_TAB)));
	
	public static final RegistryObject<Item> RAW_COAL_BLOCK = block(PandaTechBlocks.RAW_COAL_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_DIAMOND_BLOCK = block(PandaTechBlocks.RAW_DIAMOND_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_EMERALD_BLOCK = block(PandaTechBlocks.RAW_EMERALD_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_LAPIS_BLOCK = block(PandaTechBlocks.RAW_LAPIS_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_REDSTONE_BLOCK = block(PandaTechBlocks.RAW_REDSTONE_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	
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
	
	public static final RegistryObject<Item> EMPTY_FRAME = block(PandaTechBlocks.EMPTY_FRAME, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> MECHANICAL_PISTON = block(PandaTechBlocks.MECHANICAL_PISTON, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> HIGH_DENSITY_GLASS = block(PandaTechBlocks.HIGH_DENSITY_GLASS, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ENERGY_FRAME = block(PandaTechBlocks.ENERGY_FRAME, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ITEM_INPUT_FRAME = block(PandaTechBlocks.ITEM_INPUT_FRAME, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> ITEM_OUTPUT_FRAME = block(PandaTechBlocks.ITEM_OUTPUT_FRAME, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> MOTOR = block(PandaTechBlocks.BASIC_MOTOR, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	
	public static final RegistryObject<Item> PULVERISER = block(PandaTechBlocks.PULVERISER, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);
	public static final RegistryObject<Item> CENTRIFUGE = block(PandaTechBlocks.CENTRIFUGE, PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB);

	public static final RegistryObject<Item> TIN_ORE = block(PandaTechBlocks.TIN_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> TITANIUM_ORE = block(PandaTechBlocks.TITANIUM_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> ZINC_ORE = block(PandaTechBlocks.ZINC_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> ALUMINIUM_ORE = block(PandaTechBlocks.ALUMINIUM_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> NICKEL_ORE = block(PandaTechBlocks.NICKEL_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> COBALT_ORE = block(PandaTechBlocks.COBALT_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	
	public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = block(PandaTechBlocks.DEEPSLATE_TIN_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> DEEPSLATE_TITANIUM_ORE = block(PandaTechBlocks.DEEPSLATE_TITANIUM_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> DEEPSLATE_ZINC_ORE = block(PandaTechBlocks.DEEPSLATE_ZINC_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> DEEPSLATE_ALUMINIUM_ORE = block(PandaTechBlocks.DEEPSLATE_ALUMINIUM_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> DEEPSLATE_NICKEL_ORE = block(PandaTechBlocks.DEEPSLATE_NICKEL_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> DEEPSLATE_COBALT_ORE = block(PandaTechBlocks.DEEPSLATE_COBALT_ORE, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	
	public static final RegistryObject<Item> RAW_TIN = tab("RAW_TIN", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_TITANIUM = tab("RAW_TITANIUM", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_ZINC = tab("RAW_ZINC", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_ALUMINIUM = tab("RAW_ALUMINIUM", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_NICKEL = tab("RAW_NICKEL", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_COBALT = tab("RAW_COBALT", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	
	public static final RegistryObject<Item> RAW_TIN_BLOCK = block(PandaTechBlocks.RAW_TIN_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_TITANIUM_BLOCK = block(PandaTechBlocks.RAW_TITANIUM_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_ZINC_BLOCK = block(PandaTechBlocks.RAW_ZINC_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_ALUMINIUM_BLOCK = block(PandaTechBlocks.RAW_ALUMINIUM_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_NICKEL_BLOCK = block(PandaTechBlocks.RAW_NICKEL_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> RAW_COBALT_BLOCK = block(PandaTechBlocks.RAW_COBALT_BLOCK, PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	
	public static final RegistryObject<Item> TIN_DUST = tab("TIN_DUST", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> TITANIUM_DUST = tab("TITANIUM_DUST", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> ZINC_DUST = tab("ZINC_DUST", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> ALUMINIUM_DUST = tab("ALUMINIUM_DUST", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> NICKEL_DUST = tab("NICKEL_DUST", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> COBALT_DUST = tab("COBALT_DUST", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	
	public static final RegistryObject<Item> TIN_INGOT = tab("TIN_INGOT", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> TITANIUM_INGOT = tab("TITANIUM_INGOT", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> ZINC_INGOT = tab("ZINC_INGOT", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> ALUMINIUM_INGOT = tab("ALUMINIUM_INGOT", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> NICKEL_INGOT = tab("NICKEL_INGOT", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> COBALT_INGOT = tab("COBALT_INGOT", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	
	public static final RegistryObject<Item> ALUMINIUM_PLATE = tab("aluminium_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> COBALT_PLATE = tab("cobalt_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> COPPER_PLATE = tab("copper_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> DIAMOND_PLATE = tab("diamond_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> EMERALD_PLATE = tab("emerald_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> GOLD_PLATE = tab("gold_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> IRON_PLATE = tab("iron_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> LAPIS_PLATE = tab("lapis_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> NICKEL_PLATE = tab("nickel_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> REDSTONE_PLATE = tab("redstone_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> TIN_PLATE = tab("tin_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> TITANIUM_PLATE = tab("titanium_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);
	public static final RegistryObject<Item> ZINC_PLATE = tab("zinc_plate", PandaTechTabs.TAB_ORES_CREATIVE_TAB);


	@SuppressWarnings("SameParameterValue")
	private static RegistryObject<Item> block(String id, Supplier<Item> sawbi) {
		return REGISTRY.register(id, sawbi);
	}
	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
	
	private static RegistryObject<Item> tab(String name, CreativeModeTab tab) {
		return REGISTRY.register(name.toLowerCase(), () -> new Item((new Item.Properties()).tab(tab)));
	}
}
