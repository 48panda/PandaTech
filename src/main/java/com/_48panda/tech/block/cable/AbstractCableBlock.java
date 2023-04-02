package com._48panda.tech.block.cable;

import com._48panda.tech.Pair;
import com._48panda.tech.Triple;
import com._48panda.tech.block.cable.type.CableType;
import com._48panda.tech.block.entity.SimpleBlockEntityTicker;
import com._48panda.tech.block.cable.entity.AbstractCableBlockEntity;

import com._48panda.tech.init.PandaTechItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCableBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<ConnectionType> UP = EnumProperty.create("up", ConnectionType.class);
    public static final EnumProperty<ConnectionType> DOWN = EnumProperty.create("down", ConnectionType.class);
    public static final EnumProperty<ConnectionType> NORTH = EnumProperty.create("north", ConnectionType.class);
    public static final EnumProperty<ConnectionType> SOUTH = EnumProperty.create("south", ConnectionType.class);
    public static final EnumProperty<ConnectionType> EAST = EnumProperty.create("east", ConnectionType.class);
    public static final EnumProperty<ConnectionType> WEST = EnumProperty.create("west", ConnectionType.class);
    private static final double top = 16;
    private static final double bot = 0;
    private static final double C = 8;
    private static final double w = 3;
    private static final double sm = C - w;
    private static final double lg = C + w;
    protected static final VoxelShape SHAPE_CORE = Block.box(sm, sm, sm, lg, lg, lg);
    protected static final VoxelShape SHAPE_UP = Block.box(sm, sm, sm, lg, top, lg);
    protected static final VoxelShape SHAPE_DOWN = Block.box(sm, bot, sm, lg, lg, lg);
    protected static final VoxelShape SHAPE_NORTH = Block.box(sm, sm, bot, lg, lg, lg);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(sm, sm, sm, lg, lg, top);
    protected static final VoxelShape SHAPE_WEST = Block.box(bot, sm, sm, lg, lg, lg);
    protected static final VoxelShape SHAPE_EAST = Block.box(sm, sm, sm, top, lg, lg);
    public CableType<?> type;

    private static final List<Triple<VoxelShape, EnumProperty<ConnectionType>, Direction>> SHAPES = Arrays.asList(
            new Triple<>(SHAPE_NORTH, NORTH, Direction.NORTH),
            new Triple<>(SHAPE_SOUTH, SOUTH, Direction.SOUTH),
            new Triple<>(SHAPE_WEST, WEST, Direction.WEST),
            new Triple<>(SHAPE_EAST, EAST, Direction.EAST),
            new Triple<>(SHAPE_UP, UP, Direction.UP),
            new Triple<>(SHAPE_DOWN, DOWN, Direction.DOWN)
    );

    private static final List<Pair<VoxelShape, Direction>> EXTRACT_SHAPES = Arrays.asList(
            new Pair<>(SHAPE_NORTH, Direction.NORTH),
            new Pair<>(SHAPE_SOUTH, Direction.SOUTH),
            new Pair<>(SHAPE_WEST, Direction.WEST),
            new Pair<>(SHAPE_EAST, Direction.EAST),
            new Pair<>(SHAPE_UP, Direction.UP),
            new Pair<>(SHAPE_DOWN, Direction.DOWN)
    );

    public AbstractCableBlock(CableType<?> cableType) {
        super(BlockBehaviour.Properties.of(Material.WOOL).sound(SoundType.WOOL).strength(1f, 1f).noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false)
                .setValue(UP, ConnectionType.NONE)
                .setValue(DOWN, ConnectionType.NONE)
                .setValue(NORTH, ConnectionType.NONE)
                .setValue(SOUTH, ConnectionType.NONE)
                .setValue(EAST, ConnectionType.NONE)
                .setValue(WEST, ConnectionType.NONE)
        );
        this.type = cableType;
    }
    
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape shape = SHAPE_CORE;
        if (state.getValue(UP).isConnected()) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_UP, BooleanOp.OR);
        }
        if (state.getValue(DOWN).isConnected()) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_DOWN, BooleanOp.OR);
        }
        if (state.getValue(WEST).isConnected()) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_WEST, BooleanOp.OR);
        }
        if (state.getValue(EAST).isConnected()) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_EAST, BooleanOp.OR);
        }
        if (state.getValue(NORTH).isConnected()) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_NORTH, BooleanOp.OR);
        }
        if (state.getValue(SOUTH).isConnected()) {
            shape = Shapes.joinUnoptimized(shape, SHAPE_SOUTH, BooleanOp.OR);
        }
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(UP);
        builder.add(DOWN);
        builder.add(NORTH);
        builder.add(SOUTH);
        builder.add(EAST);
        builder.add(WEST);
    }

    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        AbstractCableBlockEntity.markCablesDirty(world, pos);
        BlockState newstate = getState(world, pos, blockstate);
        world.setBlock(pos, newstate, 3);
    }
    public float getBlockReachDistance(Player player) {
        float distance = (float) player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
        return player.isCreative() ? distance : distance - 0.5F;
    }
    private double checkShape(BlockState state, BlockGetter world, BlockPos pos, Vec3 start, Vec3 end, VoxelShape shape, EnumProperty<ConnectionType> direction) {
        if (direction != null && !state.getValue(direction).isConnected()) {
            return Double.MAX_VALUE;
        }
        BlockHitResult blockRayTraceResult = world.clipWithInteractionOverride(start, end, pos, shape, state);
        if (blockRayTraceResult == null) {
            return Double.MAX_VALUE;
        }
        return blockRayTraceResult.getLocation().distanceTo(start);
    }

    private double checkShape(BlockState state, BlockGetter world, BlockPos pos, Vec3 start, Vec3 end, VoxelShape shape,
                              @Nullable AbstractCableBlockEntity pipe, Direction side) {
        if (pipe != null && !pipe.isExtracting(side)) {
            return Double.MAX_VALUE;
        }
        BlockHitResult blockRayTraceResult = world.clipWithInteractionOverride(start, end, pos, shape, state);
        if (blockRayTraceResult == null) {
            return Double.MAX_VALUE;
        }
        return blockRayTraceResult.getLocation().distanceTo(start);
    }
    public Pair<Direction, VoxelShape> getSelection(BlockState state, BlockGetter blockReader, BlockPos pos, Player player) {
        Vec3 start = player.getEyePosition(1F);
        Vec3 end = start.add(player.getLookAngle().normalize().scale(getBlockReachDistance(player)));

        Direction direction = null;
        VoxelShape selection = null;
        double shortest = Double.MAX_VALUE;

        double d = checkShape(state, blockReader, pos, start, end, SHAPE_CORE, null);
        if (d < shortest) {
            shortest = d;
        }

        if (!(blockReader instanceof LevelAccessor)) {
            return new Pair<>(null, null);
        }

        AbstractCableBlockEntity pipe = getBlockEntity((LevelAccessor) blockReader, pos);

        for (int i = 0; i < Direction.values().length; i++) {
            Pair<VoxelShape, Direction> extract = EXTRACT_SHAPES.get(i);
            Triple<VoxelShape, EnumProperty<ConnectionType>, Direction> shape = SHAPES.get(i);
            if (pipe != null && pipe.isExtracting(extract.b)) {
                d = checkShape(state, blockReader, pos, start, end, extract.a, pipe, extract.b);
                if (d < shortest) {
                    shortest = d;
                    direction = extract.b;
                    selection = extract.a;
                }
            } else {
                d = checkShape(state, blockReader, pos, start, end, shape.a, shape.b);
                if (d < shortest) {
                    shortest = d;
                    direction = shape.c;
                    selection = shape.a;
                }
            }
        }
        return new Pair<>(direction, selection);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return getState(context.getLevel(), context.getClickedPos(), this.defaultBlockState().setValue(WATERLOGGED, flag));
    }
    private BlockState getState(Level world, BlockPos pos, BlockState old) {
        return old.setValue(UP, old.getValue(UP).setConnected(type.isConnected(world, pos, Direction.UP)))
                  .setValue(DOWN, old.getValue(DOWN).setConnected(type.isConnected(world, pos, Direction.DOWN)))
                  .setValue(NORTH, old.getValue(NORTH).setConnected(type.isConnected(world, pos, Direction.NORTH)))
                  .setValue(SOUTH, old.getValue(SOUTH).setConnected(type.isConnected(world, pos, Direction.SOUTH)))
                  .setValue(EAST, old.getValue(EAST).setConnected(type.isConnected(world, pos, Direction.EAST)))
                  .setValue(WEST, old.getValue(WEST).setConnected(type.isConnected(world, pos, Direction.WEST)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos,
                                  BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return new SimpleBlockEntityTicker<>();
    }

    public boolean isConnected(Level level, BlockPos pos, Direction d) {
        return getConnection(level, pos, d).isConnected();
    }
    public AbstractCableBlockEntity getBlockEntity(LevelAccessor level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof AbstractCableBlockEntity blockEntity) return blockEntity;
        return null;
    }
    public boolean isExtracting(Level level, BlockPos pos, Direction d) {
        return getConnection(level, pos, d).isConnectedExtract();
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var x = super.use(state, level, pos, player, hand, hit);
        Vec3 hitLoc = hit.getLocation();
        
        Vec3 blockpos = new Vec3(pos.getX(), pos.getY(), pos.getZ());
        if (!level.isClientSide()) {
            System.out.println(hand);
            Vec3 clickPos = hitLoc.subtract(blockpos).subtract(0.5,0.5,0.5);
            double absX = Math.abs(clickPos.x);
            double absY = Math.abs(clickPos.y);
            double absZ = Math.abs(clickPos.z);
            double mag;
            Direction dir;
            if (absX > absY && absX > absZ) {
                if (clickPos.x > 0) {
                    dir = Direction.EAST;
                } else {
                    dir = Direction.WEST;
                }
            } else if (absY > absZ) {
                if (clickPos.y > 0) {
                    dir = Direction.UP;
                } else {
                    dir = Direction.DOWN;
                }
            } else {
                if (clickPos.z > 0) {
                    dir = Direction.SOUTH;
                } else {
                    dir = Direction.NORTH;
                }
            }
            if (player.getItemInHand(hand).is(PandaTechItems.WRENCH.get())) {
                if (level.getBlockEntity(pos) instanceof AbstractCableBlockEntity be) {
                    if (!be.getIsLocked(dir)) {
                        wrench(state, level, pos, dir);
                    }
                };
            } else if (player.getItemInHand(hand).isEmpty() || player.getItemInHand(hand).is(Items.AIR)) {
                if (getConnection(level, pos, dir).isConnectedExtract()) {
                    if (level.getBlockEntity(pos) instanceof final AbstractCableBlockEntity block) {
                        MenuProvider container = new SimpleMenuProvider(block.getServerContainer(pos, dir), block.title);
                        NetworkHooks.openGui((ServerPlayer) player, container, pos);
                    }
                }
            } else {
                return InteractionResult.PASS;
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
    
    
    public void wrench(BlockState state, Level level, BlockPos pos, Direction dir) {
        if (level.getBlockState(pos.relative(dir)).getBlock() instanceof AbstractCableBlock) {
            return;
        }
        ConnectionType type = getConnection(state, dir);
        BlockState newState = state;
        if (type == ConnectionType.NORMAL) {
            newState = setConnection(state, dir, ConnectionType.EXTRACT);
        } else if (type == ConnectionType.EXTRACT) {
            newState = setConnection(state, dir, ConnectionType.NORMAL);
        }
        level.setBlockAndUpdate(pos, newState);
    }

    public ConnectionType getConnection(Level level, BlockPos pos, Direction d) {
        EnumProperty<ConnectionType> dir;
        switch (d) {
            case NORTH -> dir = NORTH;
            case SOUTH -> dir = SOUTH;
            case EAST -> dir = EAST;
            case WEST -> dir = WEST;
            case UP -> dir = UP;
            case DOWN -> dir = DOWN;
            default -> throw new UnsupportedOperationException("Unsupported direction '" + d + "'");
        }
        return level.getBlockState(pos).getValue(dir);
    }
    public ConnectionType getConnection(BlockState state, Direction d) {
        EnumProperty<ConnectionType> dir;
        switch (d) {
            case NORTH -> dir = NORTH;
            case SOUTH -> dir = SOUTH;
            case EAST -> dir = EAST;
            case WEST -> dir = WEST;
            case UP -> dir = UP;
            case DOWN -> dir = DOWN;
            default -> throw new UnsupportedOperationException("Unsupported direction '" + d + "'");
        }
        return state.getValue(dir);
    }
    public BlockState setConnection(BlockState state, Direction d, ConnectionType toSetTo) {
        return switch (d) {
            case NORTH -> state.setValue(NORTH, toSetTo);
            case SOUTH -> state.setValue(SOUTH, toSetTo);
            case EAST -> state.setValue(EAST, toSetTo);
            case WEST -> state.setValue(WEST, toSetTo);
            case UP -> state.setValue(UP, toSetTo);
            case DOWN -> state.setValue(DOWN, toSetTo);
            default -> throw new UnsupportedOperationException("Unsupported direction '" + d + "'");
        };
    }
}
