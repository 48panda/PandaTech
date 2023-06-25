package com._48panda.tech.block.machines;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public record AugmentProperties(int numAugments, List<AugmentSlotProperties> properties, int imageWidth) {
    public static final AugmentProperties ELECTRIC_FURNACE = new AugmentProperties(1, Arrays.asList(
            AugmentSlotProperties.ALL_GENERIC
    ), 
            176);
    public static final AugmentProperties BURNER_GENERATOR = new AugmentProperties(1, Arrays.asList(
            AugmentSlotProperties.ALL_GENERIC
    ),
            176);
    public static final AugmentProperties PULVERISER = new AugmentProperties(3, Arrays.asList(
            AugmentSlotProperties.ALL_GENERIC,
            AugmentSlotProperties.ALL_GENERIC,
            AugmentSlotProperties.ALL_GENERIC
    ),
            176);
    public static final AugmentProperties CENTRIFUGE = new AugmentProperties(3, Arrays.asList(
            AugmentSlotProperties.ALL_GENERIC,
            AugmentSlotProperties.ALL_GENERIC,
            AugmentSlotProperties.ALL_GENERIC
    ),
            176);
}
