package com._48panda.tech.block.multiblock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

public class MultiblockDisplayBlock extends Block {
    public final static EnumProperty<MultiBlockDisplay> DISPLAY = EnumProperty.create("display", MultiBlockDisplay.class);
    public MultiblockDisplayBlock() {
        super(Properties.of(Material.STONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(DISPLAY);
    }
}
