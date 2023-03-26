package com._48panda.colorfulbiomes.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;

import com._48panda.colorfulbiomes.init.ColorfulBiomesModItems;

public class ElectocutedOnEffectActiveTickProcedure {
	public static void execute(Entity entity, double amplifier) {
		if (entity == null)
			return;
		double armorPieces = 0;
		double damageTaken = 0;
		armorPieces = 0;
		if (ColorfulBiomesModItems.REDSTONE_ARMOR_BOOTS
				.get() == (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)
						.getItem()) {
			armorPieces = armorPieces + 1;
		}
		if (ColorfulBiomesModItems.REDSTONE_ARMOR_LEGGINGS
				.get() == (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)
						.getItem()) {
			armorPieces = armorPieces + 1;
		}
		if (ColorfulBiomesModItems.REDSTONE_ARMOR_CHESTPLATE
				.get() == (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)
						.getItem()) {
			armorPieces = armorPieces + 1;
		}
		if (ColorfulBiomesModItems.REDSTONE_ARMOR_HELMET
				.get() == (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)
						.getItem()) {
			armorPieces = armorPieces + 1;
		}
		if (armorPieces < 4) {
			if (amplifier - armorPieces * 2 > 0) {
				if (entity instanceof LivingEntity _entity)
					_entity.hurt(new DamageSource("electrocution").bypassArmor(), (float) (amplifier - armorPieces * 2));
			}
		}
	}
}
