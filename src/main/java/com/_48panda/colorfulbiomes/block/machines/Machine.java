package com._48panda.colorfulbiomes.block.machines;

import com._48panda.colorfulbiomes.block.entity.TickableBlockEntity;
import com._48panda.colorfulbiomes.block.machines.electric_furnace.ElectricFurnaceBlockEntity;
import com._48panda.colorfulbiomes.block.machines.electric_furnace.ElectricFurnaceContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public abstract class Machine extends HorizontalDirectionalBlock 
    implements EntityBlock {
    private Class container;
    protected Machine(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!world.isClientSide && world.getBlockEntity(pos) instanceof final MachineBlockEntity furnace) {
            MenuProvider container = new SimpleMenuProvider(furnace.getServerContainer(pos), furnace.TITLE);
            NetworkHooks.openGui((ServerPlayer)player, container, pos);
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
    @Override
    public void onRemove(BlockState p_51538_, Level level, BlockPos pos, BlockState state, boolean mysteryBoolean) {
        if (!p_51538_.is(state.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof MachineBlockEntity be) {
                ((MachineBlockEntity) blockentity).dropContents(level, pos);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(p_51538_, level, pos, state, mysteryBoolean);
        }
    }
    
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
       if (world.getBlockEntity(pos) instanceof MachineBlockEntity be) {
           be.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
       }
    }
    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        if (world.getBlockEntity(pos) instanceof MachineBlockEntity be) {
            be.onPlace(blockstate, world, pos, oldState, moving);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide()? null : ($0, pos, $1, blockEntity) -> {
            if (blockEntity instanceof TickableBlockEntity entity) {
                entity.tick();
            }
        };
    }
}
