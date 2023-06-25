package com._48panda.tech.block.multiblock;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum MultiBlockDisplay implements StringRepresentable {
    PULVERISER("pulveriser"),
    PULVERISER_PISTON("pulveriser_piston"),
    CENTRIFUGE("centrifuge"),
    CENTRIFUGE_ARM("centrifuge_arm");
    
    private final String name;

    MultiBlockDisplay(String name) {
        this.name = name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }
}
