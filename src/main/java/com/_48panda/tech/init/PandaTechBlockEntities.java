package com._48panda.tech.init;

import com._48panda.tech.block.cable.entity.EnergyCableBlockEntity;
import com._48panda.tech.block.cable.entity.ItemCableBlockEntity;
import com._48panda.tech.block.entity.*;
import com._48panda.tech.block.machines.burner_generator.BurnerGeneratorBlockEntity;
import com._48panda.tech.block.machines.electric_furnace.ElectricFurnaceBlockEntity;
import com._48panda.tech.block.machines.solar_panel.SolarPanelBlockEntity;
import com._48panda.tech.block.multiblock.CentrifugeMultiblockControllerBlockEntity;
import com._48panda.tech.block.multiblock.PulveriserMultiblockControllerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import com._48panda.tech.PandaTech;

public class PandaTechBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,
			PandaTech.MODID);
	public static final RegistryObject<BlockEntityType<?>> NETWORK_MANAGER = register("network_manager", PandaTechBlocks.NETWORK_MANAGER,
			NetworkManagerBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ROBOT = register("robot", PandaTechBlocks.ROBOT, RobotBlockEntity::new);
	
	public static final RegistryObject<BlockEntityType<?>> ELECTRIC_FURNACE = register("electric_furnace", PandaTechBlocks.ELECTRIC_FURNACE,
			ElectricFurnaceBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BURNER_GENERATOR = register("burner_generator", PandaTechBlocks.BURNER_GENERATOR,
			BurnerGeneratorBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> SOLAR_PANEL = register("solar_panel", PandaTechBlocks.SOLAR_PANEL,
			SolarPanelBlockEntity::new);

	public static final RegistryObject<BlockEntityType<?>> EMPTY_FRAME = register("empty_frame", PandaTechBlocks.EMPTY_FRAME,
			EmptyFrameBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> MECHANICAL_PISTON = register("mechanical_piston", PandaTechBlocks.MECHANICAL_PISTON,
			MechanicalPistonFrameBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> HIGH_DENSITY_GLASS = register("high_density_glass", PandaTechBlocks.HIGH_DENSITY_GLASS,
			MechanicalPistonFrameBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ENERGY_FRAME = register("energy_frame", PandaTechBlocks.ENERGY_FRAME,
			EnergyFrameBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ITEM_INPUT_FRAME = register("item_input_frame", PandaTechBlocks.ITEM_INPUT_FRAME,
			ItemInputFrameBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ITEM_OUTPUT_FRAME = register("item_output_frame", PandaTechBlocks.ITEM_OUTPUT_FRAME,
			ItemOutputFrameBlockEntity::new);
	
	
	public static final RegistryObject<BlockEntityType<?>> ITEM_CABLE = register("item_cable", PandaTechBlocks.ITEM_CABLE,
			ItemCableBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ENERGY_CABLE = register("energy_cable", PandaTechBlocks.ENERGY_CABLE,
			EnergyCableBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> BASIC_MOTOR = register("basic_motor", PandaTechBlocks.BASIC_MOTOR,
			BasicMotorBlockEntity::new);

	public static final RegistryObject<BlockEntityType<PulveriserMultiblockControllerBlockEntity>> PULVERISER = register("pulveriser", PandaTechBlocks.PULVERISER,
			PulveriserMultiblockControllerBlockEntity::new, PulveriserMultiblockControllerBlockEntity.class);
	public static final RegistryObject<BlockEntityType<CentrifugeMultiblockControllerBlockEntity>> CENTRIFUGE = register("centrifuge", PandaTechBlocks.CENTRIFUGE,
			CentrifugeMultiblockControllerBlockEntity::new, CentrifugeMultiblockControllerBlockEntity.class);
	
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		//noinspection ConstantConditions
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
	@SuppressWarnings({"ConstantConditions", "unused", "RedundantCast"})
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryname, RegistryObject<Block> block,
																					   BlockEntityType.BlockEntitySupplier<T> supplier, Class<T> c) {
		return REGISTRY.register(registryname, () -> (BlockEntityType<T>) BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
