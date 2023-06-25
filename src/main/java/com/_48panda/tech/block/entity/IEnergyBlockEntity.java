package com._48panda.tech.block.entity;

public interface IEnergyBlockEntity {
    void setEnergyLevel(int energy);
    int getEnergyLevel();
    int getEnergyCapacity();
    void setEnergyCapacity(int capacity);
}
