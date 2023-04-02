
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
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.ForgeRenderTypes;
import net.minecraftforge.client.model.pipeline.ForgeBlockModelRenderer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.internal.ForgeBindings;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import com._48panda.tech.PandaTech;

public class PandaTechBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, PandaTech.MODID);
	public static final RegistryObject<Block> RED_PISTON = REGISTRY.register("red_piston", () -> new RedPistonBlock());
	public static final RegistryObject<Block> ORANGE_PISTON = REGISTRY.register("orange_piston", () -> new OrangePistonBlock());
	public static final RegistryObject<Block> YELLOW_PISTON = REGISTRY.register("yellow_piston", () -> new YellowPistonBlock());
	public static final RegistryObject<Block> GREEN_PISTON = REGISTRY.register("green_piston", () -> new GreenPistonBlock());
	public static final RegistryObject<Block> BLUE_PISTON = REGISTRY.register("blue_piston", () -> new BluePistonBlock());
	public static final RegistryObject<Block> ENERGY_CABLE = REGISTRY.register("energy_cable", () -> new EnergyCableBlock());
	public static final RegistryObject<Block> ITEM_CABLE = REGISTRY.register("item_cable", () -> new ItemCableBlock());
	public static final RegistryObject<Block> NETWORK_MANAGER = REGISTRY.register("network_manager", () -> new NetworkManagerBlock());
	public static final RegistryObject<Block> ROBOT = REGISTRY.register("robot", () -> new RobotBlock());
	
	public static final RegistryObject<Block> ELECTRIC_FURNACE = REGISTRY.register("electric_furnace", () -> new ElectricFurnaceBlock());
	public static final RegistryObject<Block> BURNER_GENERATOR = REGISTRY.register("burner_generator", () -> new BurnerGeneratorBlock());
	
	
	public static final RegistryObject<Block> COPPER_INFUSED_REDSTONE_WIRE = REGISTRY.register("copper_infused_redstone_wire", () -> new CopperInfusedRedstoneWire());
	public static final RegistryObject<Block> COPPER_INFUSED_REDSTONE_BLOCK = REGISTRY.register("copper_infused_redstone_block", () -> new CopperInfusedRedstoneBlock());
	public static final RegistryObject<Block> COPPER_INFUSED_RESTONE_TORCH = REGISTRY.register("copper_infused_redstone_torch", () -> new CopperInfusedRedstoneTorch());
	public static final RegistryObject<Block> COPPER_INFUSED_REDSTONE_TORCH_WALL = REGISTRY.register("copper_infused_redstone_torch_wall", () -> new CopperInfusedRedstoneTorchWall());
	public static final RegistryObject<Block> COPPER_INFUSED_REPEATER = REGISTRY.register("copper_infused_repeater", () -> new CopperInfusedRedstoneRepeater());
	public static final RegistryObject<Block> COPPER_INFUSED_COMPARATOR = REGISTRY.register("copper_infused_comparator", () -> new CopperInfusedComparator());
	public static final RegistryObject<Block> COPPER_LEVER = REGISTRY.register("copper_lever", () -> new CopperLeverBlock());
	public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = REGISTRY.register("copper_pressure_plate", () -> new CopperPressurePlate());
	public static final RegistryObject<Block> COPPER_BUTTON = REGISTRY.register("copper_button", () -> new CopperButton());
}
