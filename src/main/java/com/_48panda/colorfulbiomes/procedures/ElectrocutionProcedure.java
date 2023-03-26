package com._48panda.colorfulbiomes.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import com._48panda.colorfulbiomes.init.ColorfulBiomesModMobEffects;

@Mod.EventBusSubscriber
public class ElectrocutionProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level, event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState StandingOn = Blocks.AIR.defaultBlockState();
		BlockState StandingIn = Blocks.AIR.defaultBlockState();
		StandingIn = (world.getBlockState(new BlockPos(x, y, z)));
		StandingOn = (world.getBlockState(new BlockPos(x, y - 1, z)));
		if (StandingIn.getBlock() == Blocks.REDSTONE_WIRE) {
			if ((StandingIn.getBlock().getStateDefinition().getProperty("power") instanceof IntegerProperty _getip3
					? StandingIn.getValue(_getip3)
					: -1) != 0) {
				if (entity.isOnGround()) {
					if (entity instanceof LivingEntity _entity)
						_entity.addEffect(new MobEffectInstance(ColorfulBiomesModMobEffects.ELECTOCUTED.get(), 60,
								(int) ((StandingIn.getBlock().getStateDefinition().getProperty("power") instanceof IntegerProperty _getip5
										? StandingIn.getValue(_getip5)
										: -1) - 1)));
				}
			}
		}
	}
}
