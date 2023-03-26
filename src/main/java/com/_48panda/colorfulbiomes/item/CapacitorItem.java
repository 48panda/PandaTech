
package com._48panda.colorfulbiomes.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import com._48panda.colorfulbiomes.init.ColorfulBiomesModTabs;

public class CapacitorItem extends Item {
	public CapacitorItem() {
		super(new Item.Properties().tab(ColorfulBiomesModTabs.TAB_ELECTROCUTION_CREATIVE_TAB).stacksTo(64).rarity(Rarity.COMMON));
	}
}
