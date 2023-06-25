
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.tech.init;

import com._48panda.tech.block.*;
import com._48panda.tech.block.cable.EnergyCableBlock;
import com._48panda.tech.block.cable.ItemCableBlock;
import com._48panda.tech.block.copper_infused.*;
import com._48panda.tech.block.machines.burner_generator.BurnerGeneratorBlock;
import com._48panda.tech.block.machines.electric_furnace.ElectricFurnaceBlock;
import com._48panda.tech.block.machines.solar_panel.SolarPanelBlock;
import com._48panda.tech.block.multiblock.CentrifugeMultiblockControllerBlock;
import com._48panda.tech.block.multiblock.MultiblockDisplayBlock;
import com._48panda.tech.block.multiblock.PulveriserMultiblockControllerBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import com._48panda.tech.PandaTech;

public class PandaTechBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, PandaTech.MODID);
	public static final RegistryObject<Block> RED_PISTON = REGISTRY.register("red_piston", RedPistonBlock::new);
	public static final RegistryObject<Block> ORANGE_PISTON = REGISTRY.register("orange_piston", OrangePistonBlock::new);
	public static final RegistryObject<Block> YELLOW_PISTON = REGISTRY.register("yellow_piston", YellowPistonBlock::new);
	public static final RegistryObject<Block> GREEN_PISTON = REGISTRY.register("green_piston", GreenPistonBlock::new);
	public static final RegistryObject<Block> BLUE_PISTON = REGISTRY.register("blue_piston", BluePistonBlock::new);
	public static final RegistryObject<Block> ENERGY_CABLE = REGISTRY.register("energy_cable", EnergyCableBlock::new);
	public static final RegistryObject<Block> ITEM_CABLE = REGISTRY.register("item_cable", ItemCableBlock::new);
	public static final RegistryObject<Block> NETWORK_MANAGER = REGISTRY.register("network_manager", NetworkManagerBlock::new);
	public static final RegistryObject<Block> ROBOT = REGISTRY.register("robot", RobotBlock::new);
	
	public static final RegistryObject<Block> ELECTRIC_FURNACE = REGISTRY.register("electric_furnace", ElectricFurnaceBlock::new);
	public static final RegistryObject<Block> BURNER_GENERATOR = REGISTRY.register("burner_generator", BurnerGeneratorBlock::new);
	public static final RegistryObject<Block> SOLAR_PANEL = REGISTRY.register("solar_panel", SolarPanelBlock::new);
	public static final RegistryObject<Block> RAW_COAL_BLOCK = REGISTRY.register("raw_coal_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> RAW_DIAMOND_BLOCK = REGISTRY.register("raw_diamond_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DIAMOND).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> RAW_EMERALD_BLOCK = REGISTRY.register("raw_emerald_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.EMERALD).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> RAW_LAPIS_BLOCK = REGISTRY.register("raw_lapis_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.LAPIS).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	public static final RegistryObject<Block> RAW_REDSTONE_BLOCK = REGISTRY.register("raw_redstone_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
	
	
	public static final RegistryObject<Block> COPPER_INFUSED_REDSTONE_WIRE = REGISTRY.register("copper_infused_redstone_wire", CopperInfusedRedstoneWire::new);
	public static final RegistryObject<Block> COPPER_INFUSED_REDSTONE_BLOCK = REGISTRY.register("copper_infused_redstone_block", CopperInfusedRedstoneBlock::new);
	public static final RegistryObject<Block> COPPER_INFUSED_RESTONE_TORCH = REGISTRY.register("copper_infused_redstone_torch", CopperInfusedRedstoneTorch::new);
	public static final RegistryObject<Block> COPPER_INFUSED_REDSTONE_TORCH_WALL = REGISTRY.register("copper_infused_redstone_torch_wall", CopperInfusedRedstoneTorchWall::new);
	public static final RegistryObject<Block> COPPER_INFUSED_REPEATER = REGISTRY.register("copper_infused_repeater", CopperInfusedRedstoneRepeater::new);
	public static final RegistryObject<Block> COPPER_INFUSED_COMPARATOR = REGISTRY.register("copper_infused_comparator", CopperInfusedComparator::new);
	public static final RegistryObject<Block> COPPER_LEVER = REGISTRY.register("copper_lever", CopperLeverBlock::new);
	public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = REGISTRY.register("copper_pressure_plate", CopperPressurePlate::new);
	public static final RegistryObject<Block> COPPER_BUTTON = REGISTRY.register("copper_button", CopperButton::new);
	
	public static final RegistryObject<Block> EMPTY_FRAME = REGISTRY.register("empty_frame", EmptyFrameBlock::new);
	public static final RegistryObject<Block> MECHANICAL_PISTON = REGISTRY.register("mechanical_piston", MechanicalPistonFrameBlock::new);
	public static final RegistryObject<Block> HIGH_DENSITY_GLASS = REGISTRY.register("high_density_glass", HighDensityGlassFrameBlock::new);
	public static final RegistryObject<Block> ENERGY_FRAME = REGISTRY.register("energy_frame", EnergyFrameBlock::new);
	public static final RegistryObject<Block> ITEM_INPUT_FRAME = REGISTRY.register("item_input_frame", ItemInputFrameBlock::new);
	public static final RegistryObject<Block> ITEM_OUTPUT_FRAME = REGISTRY.register("item_output_frame", ItemOutputFrameBlock::new);
	public static final RegistryObject<Block> BASIC_MOTOR = REGISTRY.register("basic_motor", BasicMotorBlock::new);
	
	public static final RegistryObject<Block> DISPLAY_MULTIBLOCK = REGISTRY.register("multiblock_display", MultiblockDisplayBlock::new);
	public static final RegistryObject<Block> PULVERISER = REGISTRY.register("pulveriser", PulveriserMultiblockControllerBlock::new);
	public static final RegistryObject<Block> CENTRIFUGE = REGISTRY.register("centrifuge", CentrifugeMultiblockControllerBlock::new);
	
	public static final RegistryObject<Block> TIN_ORE = REGISTRY.register("tin_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistryObject<Block> TITANIUM_ORE = REGISTRY.register("titanium_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistryObject<Block> ZINC_ORE = REGISTRY.register("zinc_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistryObject<Block> ALUMINIUM_ORE = REGISTRY.register("aluminium_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistryObject<Block> NICKEL_ORE = REGISTRY.register("nickel_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
	public static final RegistryObject<Block> COBALT_ORE = REGISTRY.register("cobalt_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

	public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = REGISTRY.register("deepslate_tin_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE = REGISTRY.register("deepslate_titanium_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = REGISTRY.register("deepslate_zinc_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistryObject<Block> DEEPSLATE_ALUMINIUM_ORE = REGISTRY.register("deepslate_aluminium_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistryObject<Block> DEEPSLATE_NICKEL_ORE = REGISTRY.register("deepslate_nickel_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));
	public static final RegistryObject<Block> DEEPSLATE_COBALT_ORE = REGISTRY.register("deepslate_cobalt_ore", () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

	public static final RegistryObject<Block> RAW_TIN_BLOCK = REGISTRY.register("raw_tin_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
	public static final RegistryObject<Block> RAW_TITANIUM_BLOCK = REGISTRY.register("raw_titanium_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
	public static final RegistryObject<Block> RAW_ZINC_BLOCK = REGISTRY.register("raw_zinc_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
	public static final RegistryObject<Block> RAW_ALUMINIUM_BLOCK = REGISTRY.register("raw_aluminium_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
	public static final RegistryObject<Block> RAW_NICKEL_BLOCK = REGISTRY.register("raw_nickel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
	public static final RegistryObject<Block> RAW_COBALT_BLOCK = REGISTRY.register("raw_cobalt_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)));
	
	
}
