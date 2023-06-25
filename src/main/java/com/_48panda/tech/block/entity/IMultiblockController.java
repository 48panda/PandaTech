package com._48panda.tech.block.entity;

public interface IMultiblockController extends IInventoryUpdatable {
    boolean isAttached();
    void attach();
    void detach();
    void multiblockNeighbourChanged();
}
