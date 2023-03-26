
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.colorfulbiomes.init;

import com._48panda.colorfulbiomes.block.entity.*;
import com._48panda.colorfulbiomes.block.machines.burner_generator.BurnerGeneratorBlockEntity;
import com._48panda.colorfulbiomes.block.machines.electric_furnace.ElectricFurnaceBlockEntity;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import com._48panda.colorfulbiomes.ColorfulBiomesMod;

public class ColorfulBiomesModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,
			ColorfulBiomesMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> NETWORK_MANAGER = register("network_manager", ColorfulBiomesModBlocks.NETWORK_MANAGER,
			NetworkManagerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> TEST_GENERATOR = register("test_generator", ColorfulBiomesModBlocks.TEST_GENERATOR,
			TestGeneratorBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> TEST_CONSUMER = register("test_consumer", ColorfulBiomesModBlocks.TEST_CONSUMER,
			TestConsumerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ROBOT = register("robot", ColorfulBiomesModBlocks.ROBOT, RobotBlockEntity::new);
	
	public static final RegistryObject<BlockEntityType<?>> ELECTRIC_FURNACE = register("electric_furnace", ColorfulBiomesModBlocks.ELECTRIC_FURNACE,
			ElectricFurnaceBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BURNER_GENERATOR = register("burner_generator", ColorfulBiomesModBlocks.BURNER_GENERATOR,
			BurnerGeneratorBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
