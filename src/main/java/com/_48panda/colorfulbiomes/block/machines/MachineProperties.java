package com._48panda.colorfulbiomes.block.machines;

import com._48panda.colorfulbiomes.init.ColorfulBiomesModBlockEntities;
import com._48panda.colorfulbiomes.init.ColorfulBiomesModBlocks;
import com._48panda.colorfulbiomes.init.ColorfulBiomesModMenus;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class MachineProperties {
    private final int capacity, inputRate, outputRate, numSlots, outputSlots, numDataPieces;
    private final String id;
    private final BlockEntityType<?> blockEntityType;
    private final MenuType<?> menuType;
    private final Block block;

    public Block getBlock() {
        return block;
    }

    public MachineProperties(int capacity,
                             int inputRate,
                             int outputRate,
                             int numSlots,
                             int outputSlots,
                             String id,
                             Block block,
                             BlockEntityType<?> blockEntityType,
                             MenuType<?> menuType,
                             int numDataPieces
    ) {
        this.capacity = capacity;
        this.inputRate = inputRate;
        this.outputRate = outputRate;
        this.numSlots = numSlots;
        this.outputSlots = outputSlots;
        this.id = id;
        this.block = block;
        this.blockEntityType = blockEntityType;
        this.menuType = menuType;
        this.numDataPieces = numDataPieces + 2; // Energy min and max
    }

    public int getCapacity() {
        return capacity;
    }

    public int getInputRate() {
        return inputRate;
    }

    public int getOutputRate() {
        return outputRate;
    }

    public int getNumSlots() {
        return numSlots;
    }

    public int getOutputSlots() {
        return outputSlots;
    }

    public String getId() {
        return id;
    }

    public int getNumDataPieces() {
        return numDataPieces;
    }

    public static final MachineProperties ELECTRIC_FURNACE = new MachineProperties(
            10000,
            20,
            10, 
            1, 1,
            "electric_furnace",
            ColorfulBiomesModBlocks.ELECTRIC_FURNACE.get(),
            ColorfulBiomesModBlockEntities.ELECTRIC_FURNACE.get(),
            ColorfulBiomesModMenus.ELECTRIC_FURNACE.get(),
            2);

    public static final MachineProperties BURNER_GENERATOR = new MachineProperties(
            10000,
            5,
            100,
            1, 0,
            "burner_generator",
            ColorfulBiomesModBlocks.BURNER_GENERATOR.get(),
            ColorfulBiomesModBlockEntities.BURNER_GENERATOR.get(),
            ColorfulBiomesModMenus.BURNER_GENERATOR.get(),
            2);

    public BlockEntityType<?> getBlockEntityType() {
        return blockEntityType;
    }

    public MenuType<?> getMenuType() {
        return menuType;
    }
}
