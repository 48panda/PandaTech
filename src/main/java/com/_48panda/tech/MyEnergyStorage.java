package com._48panda.tech;

import net.minecraftforge.energy.EnergyStorage;

public abstract class MyEnergyStorage extends EnergyStorage {
    public MyEnergyStorage(int capacity, int maxIn, int maxOut) {
        super(capacity, maxIn, maxOut);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractedEnergy = super.extractEnergy(maxExtract, simulate);
        if(extractedEnergy != 0 && !simulate) {
            onEnergyChanged();
        }

        return extractedEnergy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receiveEnergy = super.receiveEnergy(maxReceive, simulate);
        if(receiveEnergy != 0 && !simulate) {
            onEnergyChanged();
        }

        return receiveEnergy;
    }

    public int setEnergy(int energy) {
        this.energy = energy;
        return energy;
    }

    public abstract void onEnergyChanged();

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
