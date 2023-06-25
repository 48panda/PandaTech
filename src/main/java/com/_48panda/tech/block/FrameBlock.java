package com._48panda.tech.block;

import com._48panda.tech.block.entity.IMinionBlockEntity;
import com._48panda.tech.block.entity.IMultiblockController;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;

public abstract class FrameBlock extends Block implements EntityBlock {
    public static final BooleanProperty HAS_MULTIBLOCK = BooleanProperty.create("has_multiblock");
    public FrameBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).sound(SoundType.METAL).strength(5.0f, 6.0f));
        registerDefaultState(defaultBlockState().setValue(HAS_MULTIBLOCK, false));
    }
    public FrameBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(HAS_MULTIBLOCK, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos pos2, boolean bool) {
        super.neighborChanged(state, level, pos, block, pos2, bool);
        if (level.getBlockEntity(pos) instanceof IMinionBlockEntity be) {
            if (be.hasMainAttached()) {
                if (level.getBlockEntity(be.getMainPos()) instanceof IMultiblockController blockEntity) {
                    if (blockEntity.isAttached()) {
                        blockEntity.multiblockNeighbourChanged();
                    } else {
                        be.detachMain();
                    }
                } else {
                    be.detachMain();
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HAS_MULTIBLOCK);
    }
}
