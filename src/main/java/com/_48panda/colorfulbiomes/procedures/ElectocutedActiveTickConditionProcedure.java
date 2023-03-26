package com._48panda.colorfulbiomes.procedures;

import net.minecraft.world.item.ItemStack;

public class ElectocutedActiveTickConditionProcedure {
	public static boolean execute(double duration) {
		double baseRate = 0;
		ItemStack e = ItemStack.EMPTY;
		baseRate = 20;
		return duration % Math.floor(baseRate) == 0;
	}
}
