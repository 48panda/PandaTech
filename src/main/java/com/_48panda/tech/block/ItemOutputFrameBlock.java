package com._48panda.tech.block;

import com._48panda.tech.block.entity.ItemOutputFrameBlockEntity;
import com._48panda.tech.container.ItemOutputFrameContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemOutputFrameBlock extends FrameBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new ItemOutputFrameBlockEntity(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!world.isClientSide && world.getBlockEntity(pos) instanceof final ItemOutputFrameBlockEntity be) {
            MenuProvider container = new SimpleMenuProvider((id, playerInv, ply) -> new ItemOutputFrameContainer(id,
                    playerInv, be.output, pos), be.TITLE);
            NetworkHooks.openGui((ServerPlayer) player, container, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState mystate, @NotNull Level level, @NotNull BlockPos pos, BlockState state, boolean mysteryBoolean) {
        if (!mystate.is(state.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof ItemOutputFrameBlockEntity be) {
                be.dropContents(level, pos);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(mystate, level, pos, state, mysteryBoolean);
        }
    }
}
