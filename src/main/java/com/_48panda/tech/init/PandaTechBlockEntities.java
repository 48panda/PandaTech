package com._48panda.tech.init;

import com._48panda.tech.block.cable.entity.EnergyCableBlockEntity;
import com._48panda.tech.block.entity.NetworkManagerBlockEntity;
import com._48panda.tech.block.entity.RobotBlockEntity;
import com._48panda.tech.block.cable.entity.ItemCableBlockEntity;
import com._48panda.tech.block.machines.burner_generator.BurnerGeneratorBlockEntity;
import com._48panda.tech.block.machines.electric_furnace.ElectricFurnaceBlockEntity;
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
	public static final RegistryObject<BlockEntityType<?>> ITEM_CABLE = register("item_cable", PandaTechBlocks.ITEM_CABLE,
			ItemCableBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> ENERGY_CABLE = register("energy_cable", PandaTechBlocks.ENERGY_CABLE,
			EnergyCableBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
