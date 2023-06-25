
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.tech.init;

import com._48panda.tech.PandaTech;
import com._48panda.tech.block.cable.container.EnergyCableContainer;
import com._48panda.tech.block.cable.container.ItemCableContainer;
import com._48panda.tech.block.machines.burner_generator.BurnerGeneratorContainer;
import com._48panda.tech.block.machines.electric_furnace.ElectricFurnaceContainer;

import com._48panda.tech.block.machines.solar_panel.SolarPanelContainer;
import com._48panda.tech.block.multiblock.CentrifugeMultiblockControllerContainer;
import com._48panda.tech.block.multiblock.PulveriserMultiblockControllerContainer;
import com._48panda.tech.container.ItemInputFrameContainer;
import com._48panda.tech.container.ItemOutputFrameContainer;
import net.minecraft.world.inventory.MenuType;

import com._48panda.tech.world.inventory.RobotGuiMenu;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PandaTechMenus {
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			PandaTech.MODID);
	public static final RegistryObject<MenuType<ElectricFurnaceContainer>> ELECTRIC_FURNACE = CONTAINERS.register("electric_furnace", ()->new MenuType<>(ElectricFurnaceContainer::new));
	public static final RegistryObject<MenuType<BurnerGeneratorContainer>> BURNER_GENERATOR = CONTAINERS.register("burner_generator", ()->new MenuType<>(BurnerGeneratorContainer::new));
	public static final RegistryObject<MenuType<SolarPanelContainer>> SOLAR_PANEL = CONTAINERS.register("solar_panel", ()->new MenuType<>(SolarPanelContainer::new));
	
	public static final RegistryObject<MenuType<PulveriserMultiblockControllerContainer>> PULVERISER = CONTAINERS.register("pulveriser", ()-> new MenuType<>(PulveriserMultiblockControllerContainer::new));
	public static final RegistryObject<MenuType<CentrifugeMultiblockControllerContainer>> CENTRIFUGE = CONTAINERS.register("centrifuge", ()-> new MenuType<>(CentrifugeMultiblockControllerContainer::new));
	public static final RegistryObject<MenuType<ItemCableContainer>> ITEM_CABLE = CONTAINERS.register("item_cable", ()-> new MenuType<>(ItemCableContainer::new));
	public static final RegistryObject<MenuType<EnergyCableContainer>> ENERGY_CABLE = CONTAINERS.register("energy_cable", ()-> new MenuType<>(EnergyCableContainer::new));
	public static final RegistryObject<MenuType<RobotGuiMenu>> ROBOT_GUI = CONTAINERS.register("robot", ()->new MenuType<>(RobotGuiMenu::new));
	public static final RegistryObject<MenuType<ItemInputFrameContainer>> INPUT_FRAME = CONTAINERS.register("input_frame", ()->new MenuType<>(ItemInputFrameContainer::new));
	public static final RegistryObject<MenuType<ItemOutputFrameContainer>> OUTPUT_FRAME = CONTAINERS.register("output_frame", ()->new MenuType<>(ItemOutputFrameContainer::new));
	
	
}
