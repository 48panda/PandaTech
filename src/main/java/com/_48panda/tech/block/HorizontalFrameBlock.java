package com._48panda.tech.block;

import com._48panda.tech.block.entity.IMinionBlockEntity;
import com._48panda.tech.block.entity.IMultiblockController;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HorizontalFrameBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final BooleanProperty HAS_MULTIBLOCK = FrameBlock.HAS_MULTIBLOCK;
    public HorizontalFrameBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(HAS_MULTIBLOCK, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(HAS_MULTIBLOCK);
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}
