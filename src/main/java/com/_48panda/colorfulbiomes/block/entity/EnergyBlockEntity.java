package com._48panda.colorfulbiomes.block.entity;

public interface EnergyBlockEntity {
    public void setEnergyLevel(int energy);
    public int getEnergyLevel();
    public int getMaxInput();
    public int getEnergyCapacity();
    public void setEnergyCapacity(int capacity);
}
