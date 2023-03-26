
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.colorfulbiomes.init;

import com._48panda.colorfulbiomes.ColorfulBiomesMod;
import com._48panda.colorfulbiomes.block.machines.burner_generator.BurnerGeneratorContainer;
import com._48panda.colorfulbiomes.block.machines.electric_furnace.ElectricFurnaceContainer;

import net.minecraft.world.inventory.MenuType;

import com._48panda.colorfulbiomes.world.inventory.RobotGuiMenu;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ColorfulBiomesModMenus {
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			ColorfulBiomesMod.MODID);
	public static final RegistryObject<MenuType<ElectricFurnaceContainer>> ELECTRIC_FURNACE = CONTAINERS.register("electric_furnace", ()->new MenuType<>(ElectricFurnaceContainer::new));
	public static final RegistryObject<MenuType<BurnerGeneratorContainer>> BURNER_GENERATOR = CONTAINERS.register("burner_generator", ()->new MenuType<>(BurnerGeneratorContainer::new));
	public static final RegistryObject<MenuType<RobotGuiMenu>> ROBOT_GUI = CONTAINERS.register("robot", ()->new MenuType<>(RobotGuiMenu::new));
	
}
