
package com._48panda.tech.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import com._48panda.tech.init.PandaTechTabs;

public class CapacitorItem extends Item {
	public CapacitorItem() {
		super(new Item.Properties().tab(PandaTechTabs.TAB_ELECTROCUTION_CREATIVE_TAB).stacksTo(64).rarity(Rarity.COMMON));
	}
}
