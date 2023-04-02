package com._48panda.tech.block.machines;

import com._48panda.tech.init.PandaTechItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;

public enum AugmentType {
    SPEED1(PandaTechItems.SPEED_UPGRADE_1, 0.05, -0.25),
    EFFICIENCY1(PandaTechItems.EFFICIENCY_UPGRADE_1, 0, 0.05);

    private final double speed;
    private final double efficiency;
    private RegistryObject<Item> item;
    
    public static AugmentType getType(Item i) {
        for (AugmentType aug: getValues()) {
            if (aug.getItem().equals(i)) {
                return aug;
            }
        }
        return null;
    }
    
    public static List<AugmentType> getValues() {
        return Arrays.asList(SPEED1, EFFICIENCY1);
    }
    AugmentType(RegistryObject<Item> item, double speed, double efficiency) {
        this.item = item;
        this.speed = speed;
        this.efficiency = efficiency;
    }

    public Item getItem() {
        return item.get();
    }

    public double getSpeed() {
        return speed;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public boolean isValid(ItemStack toCheck) {
        return !toCheck.isEmpty() && toCheck.is(item.get());   
    }
}
