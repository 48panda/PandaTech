package com._48panda.tech.block.entity;

public interface EnergyBlockEntity {
    public void setEnergyLevel(int energy);
    public int getEnergyLevel();
    public int getMaxInput();
    public int getEnergyCapacity();
    public void setEnergyCapacity(int capacity);
}
