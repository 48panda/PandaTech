
package com._48panda.tech.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com._48panda.tech.procedures.ElectocutedOnEffectActiveTickProcedure;
import com._48panda.tech.procedures.ElectocutedActiveTickConditionProcedure;
import org.jetbrains.annotations.NotNull;

public class ElectocutedMobEffect extends MobEffect {
	public ElectocutedMobEffect() {
		super(MobEffectCategory.HARMFUL, -205);
	}

	@Override
	public @NotNull String getDescriptionId() {
		return "effect.colorful_biomes.electocuted";
	}

	@Override
	public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
		ElectocutedOnEffectActiveTickProcedure.execute(entity, amplifier);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return ElectocutedActiveTickConditionProcedure.execute(duration);
	}
}
