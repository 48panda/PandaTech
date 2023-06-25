package com._48panda.tech.block.entity;

import com._48panda.tech.PandaTech;
import com._48panda.tech.init.PandaTechBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ItemInputFrameBlockEntity extends FrameInventoryBlockEntity {
    public final Component TITLE;
    public ItemInputFrameBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.ITEM_INPUT_FRAME.get(), pos, state, 5, 0);
        TITLE = new TranslatableComponent("container." + PandaTech.MODID + "." + "input_frame");
    }

    public void dropContents(Level level, BlockPos pos) {
        for (int i = 0; i < size; i ++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
        }
        for (int i = 0; i < outputSize; i ++) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), output.getStackInSlot(i));
        }
    }
}
