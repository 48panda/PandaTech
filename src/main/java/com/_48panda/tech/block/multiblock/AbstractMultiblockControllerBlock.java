package com._48panda.tech.block.multiblock;

import com._48panda.tech.block.FrameBlock;
import com._48panda.tech.block.entity.IMinionBlockEntity;
import com._48panda.tech.block.entity.IMultiblockController;
import com._48panda.tech.block.entity.ItemInputFrameBlockEntity;
import com._48panda.tech.block.entity.ItemOutputFrameBlockEntity;
import com._48panda.tech.block.machines.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.patchouli.api.IMultiblock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractMultiblockControllerBlock extends Machine {
    private static final BooleanProperty HAS_MULTIBLOCK = FrameBlock.HAS_MULTIBLOCK;
    protected AbstractMultiblockControllerBlock(Properties properties) {
        super(properties.noOcclusion());
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HAS_MULTIBLOCK, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected boolean shouldOpen(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return state.getValue(HAS_MULTIBLOCK) && super.shouldOpen(state, world, pos, player, hand, hitResult);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
        if (level.isClientSide || hand.equals(InteractionHand.OFF_HAND)) {
            return InteractionResult.PASS;
        }
        if (!state.getValue(HAS_MULTIBLOCK) && player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AbstractMultiblockControllerBlockEntity be) {
                IMultiblock multiblock = be.getMultiblock();
                Rotation res = multiblock.validate(level, pos);
                if (res != null) {
                    if (level.getBlockEntity(pos) instanceof IMultiblockController newBlockEntity) {
                        newBlockEntity.attach();
                    }
                    Collection<IMultiblock.SimulateResult> blocks = multiblock.simulate(level, pos, res, false).getSecond();
                    List<BlockPos> inputs = new ArrayList<>();
                    List<BlockPos> outputs = new ArrayList<>();
                    blocks.forEach(x->{
                        BlockPos worldPos = x.getWorldPosition();
                        BlockEntity blockEntity1 = level.getBlockEntity(worldPos);
                        if (blockEntity1 instanceof IMinionBlockEntity newBlockEntity) {
                            newBlockEntity.attachMain(pos);
                        } else if (blockEntity1 instanceof IMultiblockController newBlockEntity) {
                            newBlockEntity.attach();
                        }
                        if (blockEntity1 instanceof ItemInputFrameBlockEntity) {
                            inputs.add(worldPos);
                        } else if (blockEntity1 instanceof ItemOutputFrameBlockEntity) {
                            outputs.add(worldPos);
                        }
                    });
                    be.setInputsAndOutputs(inputs, outputs);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b) {
        super.neighborChanged(state, level, blockPos, block, blockPos1, b);
        if (state.getValue(FrameBlock.HAS_MULTIBLOCK)) {
            if (level.getBlockEntity(blockPos) instanceof IMultiblockController be) {
                be.multiblockNeighbourChanged();
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(HAS_MULTIBLOCK);
    }
}
