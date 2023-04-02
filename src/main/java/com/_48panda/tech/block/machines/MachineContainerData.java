package com._48panda.tech.block.machines;

import net.minecraft.world.inventory.SimpleContainerData;

public class MachineContainerData extends SimpleContainerData {
    private final MachineBlockEntity blockEntity;
    public MachineContainerData(MachineBlockEntity blockEntity, int amount) {
        super(amount);
        this.blockEntity = blockEntity;
    }

    @Override
    public int get(int key) {
        if (key == 0) {
            return blockEntity.getEnergyLevel();
        } else if (key == 1) {
            return blockEntity.getEnergyCapacity();
        }
        return blockEntity.getData(key - 2);
    }

    @Override
    public void set(int key, int value) {
        if (key == 0) {
            blockEntity.setEnergyLevel(value);
        } else if (key == 1) {
            blockEntity.setEnergyCapacity(value);
        } else {
            blockEntity.setData(key - 2, value);
        }
    }
}
