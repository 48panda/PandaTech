
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com._48panda.colorfulbiomes.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import com._48panda.colorfulbiomes.potion.ElectocutedMobEffect;
import com._48panda.colorfulbiomes.ColorfulBiomesMod;

public class ColorfulBiomesModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ColorfulBiomesMod.MODID);
	public static final RegistryObject<MobEffect> ELECTOCUTED = REGISTRY.register("electocuted", () -> new ElectocutedMobEffect());
}
