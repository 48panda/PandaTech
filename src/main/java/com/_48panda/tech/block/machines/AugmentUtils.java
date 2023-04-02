package com._48panda.tech.block.machines;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AugmentUtils {
    public static double getAugmentedSpeedMultiplier(List<ItemStack> augments) {
        double speed = 1;
        for (int i = 0; i < augments.size(); i++) {
            AugmentType aug = AugmentType.getType(augments.get(i).getItem());
            if (aug != null) {
                speed += aug.getSpeed();
            }
        }
        return speed;
    }

    public static double getAugmentedEfficiencyMultiplier(List<ItemStack> augments) {
        double efficiency = 1;
        for (int i = 0; i < augments.size(); i++) {
            AugmentType aug = AugmentType.getType(augments.get(i).getItem());
            if (aug != null) {
                efficiency += aug.getEfficiency();
            }
        }
        return efficiency;
    }
}
