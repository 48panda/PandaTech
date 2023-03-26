
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.colorfulbiomes.init;

import com._48panda.colorfulbiomes.block.*;
import com._48panda.colorfulbiomes.block.machines.burner_generator.BurnerGeneratorBlock;
import com._48panda.colorfulbiomes.block.machines.electric_furnace.ElectricFurnaceBlock;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import com._48panda.colorfulbiomes.ColorfulBiomesMod;

public class ColorfulBiomesModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ColorfulBiomesMod.MODID);
	public static final RegistryObject<Block> RED_PISTON = REGISTRY.register("red_piston", () -> new RedPistonBlock());
	public static final RegistryObject<Block> ORANGE_PISTON = REGISTRY.register("orange_piston", () -> new OrangePistonBlock());
	public static final RegistryObject<Block> YELLOW_PISTON = REGISTRY.register("yellow_piston", () -> new YellowPistonBlock());
	public static final RegistryObject<Block> GREEN_PISTON = REGISTRY.register("green_piston", () -> new GreenPistonBlock());
	public static final RegistryObject<Block> BLUE_PISTON = REGISTRY.register("blue_piston", () -> new BluePistonBlock());
	public static final RegistryObject<Block> ENERGY_CABLE = REGISTRY.register("energy_cable", () -> new EnergyCableBlock());
	public static final RegistryObject<Block> NETWORK_MANAGER = REGISTRY.register("network_manager", () -> new NetworkManagerBlock());
	public static final RegistryObject<Block> TEST_GENERATOR = REGISTRY.register("test_generator", () -> new TestGeneratorBlock());
	public static final RegistryObject<Block> TEST_CONSUMER = REGISTRY.register("test_consumer", () -> new TestConsumerBlock());
	public static final RegistryObject<Block> ROBOT = REGISTRY.register("robot", () -> new RobotBlock());
	
	public static final RegistryObject<Block> ELECTRIC_FURNACE = REGISTRY.register("electric_furnace", () -> new ElectricFurnaceBlock());
	public static final RegistryObject<Block> BURNER_GENERATOR = REGISTRY.register("burner_generator", () -> new BurnerGeneratorBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			EnergyCableBlock.registerRenderLayer();
		}
	}
}
