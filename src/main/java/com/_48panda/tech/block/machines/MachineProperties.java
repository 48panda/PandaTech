package com._48panda.tech.block.machines;

import com._48panda.tech.init.PandaTechBlockEntities;
import com._48panda.tech.init.PandaTechBlocks;
import com._48panda.tech.init.PandaTechMenus;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public record MachineProperties(int capacity, int inputRate, int outputRate, int numSlots, int outputSlots, String id,
                                Block block, BlockEntityType<?> blockEntityType, MenuType<?> menuType,
                                int numDataPieces, AugmentProperties augmentProperties) {

    public static final MachineProperties ELECTRIC_FURNACE = new MachineProperties(
            10000,
            10000,
            10000,
            1,
            1,
            "electric_furnace",
            PandaTechBlocks.ELECTRIC_FURNACE.get(),
            PandaTechBlockEntities.ELECTRIC_FURNACE.get(),
            PandaTechMenus.ELECTRIC_FURNACE.get(),
            2,
            AugmentProperties.ELECTRIC_FURNACE);

    public static final MachineProperties BURNER_GENERATOR = new MachineProperties(
            10000,
            10000,
            100,
            1, 0,
            "burner_generator",
            PandaTechBlocks.BURNER_GENERATOR.get(),
            PandaTechBlockEntities.BURNER_GENERATOR.get(),
            PandaTechMenus.BURNER_GENERATOR.get(),
            2,
            AugmentProperties.BURNER_GENERATOR);

    public static final MachineProperties SOLAR_PANEL = new MachineProperties(
            10000,
            10000,
            100,
            0, 0,
            "solar_panel",
            PandaTechBlocks.SOLAR_PANEL.get(),
            PandaTechBlockEntities.SOLAR_PANEL.get(),
            PandaTechMenus.SOLAR_PANEL.get(),
            0,
            null);

    public static final MachineProperties PULVERISER = new MachineProperties(
            10000,
            10000,
            10000,
            1,
            0,
            "pulveriser",
            PandaTechBlocks.PULVERISER.get(),
            PandaTechBlockEntities.PULVERISER.get(),
            PandaTechMenus.PULVERISER.get(),
            2,
            AugmentProperties.PULVERISER);
    public static final MachineProperties CENTRIFUGE = new MachineProperties(
            10000,
            10000,
            10000,
            1,
            0,
            "centrifuge",
            PandaTechBlocks.CENTRIFUGE.get(),
            PandaTechBlockEntities.CENTRIFUGE.get(),
            PandaTechMenus.CENTRIFUGE.get(),
            2,
            AugmentProperties.CENTRIFUGE);
}
