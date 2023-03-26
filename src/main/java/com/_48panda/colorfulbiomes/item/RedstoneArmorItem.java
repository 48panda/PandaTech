
package com._48panda.colorfulbiomes.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

import com._48panda.colorfulbiomes.init.ColorfulBiomesModTabs;

public abstract class RedstoneArmorItem extends ArmorItem {
	public RedstoneArmorItem(EquipmentSlot slot, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForSlot(EquipmentSlot slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 9;
			}

			@Override
			public int getDefenseForSlot(EquipmentSlot slot) {
				return new int[]{1, 4, 3, 1}[slot.getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 5;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.REDSTONE));
			}

			@Override
			public String getName() {
				return "redstone_armor";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, slot, properties);
	}

	public static class Helmet extends RedstoneArmorItem {
		public Helmet() {
			super(EquipmentSlot.HEAD, new Item.Properties().tab(ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB));
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("-2 Electrocution damage"));
			list.add(new TextComponent("Set bonus -- No electrocution damage"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "colorful_biomes:textures/models/armor/redstone_layer_1.png";
		}
	}

	public static class Chestplate extends RedstoneArmorItem {
		public Chestplate() {
			super(EquipmentSlot.CHEST, new Item.Properties().tab(ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB));
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("-2 Electrocution damage"));
			list.add(new TextComponent("Set bonus -- No electrocution damage"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "colorful_biomes:textures/models/armor/redstone_layer_1.png";
		}
	}

	public static class Leggings extends RedstoneArmorItem {
		public Leggings() {
			super(EquipmentSlot.LEGS, new Item.Properties().tab(ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB));
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("-2 Electrocution damage"));
			list.add(new TextComponent("Set bonus -- No electrocution damage"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "colorful_biomes:textures/models/armor/redstone_layer_2.png";
		}
	}

	public static class Boots extends RedstoneArmorItem {
		public Boots() {
			super(EquipmentSlot.FEET, new Item.Properties().tab(ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB));
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("-2 Electrocution damage"));
			list.add(new TextComponent("Set bonus -- No electrocution damage"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "colorful_biomes:textures/models/armor/redstone_layer_1.png";
		}
	}
}
