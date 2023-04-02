package com._48panda.tech.block.machines;

import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public record AugmentSlotProperties(List<AugmentType> allowedTypes) {
    public static final AugmentSlotProperties ALL_GENERIC = new AugmentSlotProperties(Arrays.asList(AugmentType.EFFICIENCY1, AugmentType.SPEED1));
    
    public boolean isValid(ItemStack stack) {
        return allowedTypes.stream().anyMatch(x->x.isValid(stack));
    }
}
