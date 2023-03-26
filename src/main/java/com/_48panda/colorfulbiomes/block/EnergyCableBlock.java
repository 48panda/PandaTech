
package com._48panda.colorfulbiomes.block;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import java.util.List;
import java.util.Collections;

import com._48panda.colorfulbiomes.init.ColorfulBiomesModBlocks;
import com.mojang.realmsclient.util.task.DownloadTask;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import com._48panda.colorfulbiomes.Utils;

public class EnergyCableBlock extends Block implements SimpleWaterloggedBlock

{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 0, 63);
	
	private static final double top = 16;
	  private static final double bot = 0;
	  private static final double C = 8;
	  private static final double w = 3;
	  private static final double sm = C - w;
	  private static final double lg = C + w;
	  protected static final VoxelShape AABB = Block.box(sm, sm, sm, lg, lg, lg);
	  protected static final VoxelShape AABB_UP = Block.box(sm, sm, sm, lg, top, lg);
	  protected static final VoxelShape AABB_DOWN = Block.box(sm, bot, sm, lg, lg, lg);
	  protected static final VoxelShape AABB_NORTH = Block.box(sm, sm, bot, lg, lg, lg);
	  protected static final VoxelShape AABB_SOUTH = Block.box(sm, sm, sm, lg, lg, top);
	  protected static final VoxelShape AABB_WEST = Block.box(bot, sm, sm, lg, lg, lg);
	  protected static final VoxelShape AABB_EAST = Block.box(sm, sm, sm, top, lg, lg);

	public EnergyCableBlock() {
		super(BlockBehaviour.Properties.of(Material.WOOL).sound(SoundType.WOOL).strength(1f, 10f).noOcclusion()
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false)
		.setValue(UP, false)
		.setValue(DOWN, false)
		.setValue(NORTH, false)
		.setValue(SOUTH, false)
		.setValue(EAST, false)
		.setValue(WEST, false)
		);
		
	}
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
    VoxelShape shape = AABB;
    if (state.getValue(UP)) {
      shape = Shapes.joinUnoptimized(shape, AABB_UP, BooleanOp.OR);
    }
    if (state.getValue(DOWN)) {
      shape = Shapes.joinUnoptimized(shape, AABB_DOWN, BooleanOp.OR);
    }
    if (state.getValue(WEST)) {
      shape = Shapes.joinUnoptimized(shape, AABB_WEST, BooleanOp.OR);
    }
    if (state.getValue(EAST)) {
      shape = Shapes.joinUnoptimized(shape, AABB_EAST, BooleanOp.OR);
    }
    if (state.getValue(NORTH)) {
      shape = Shapes.joinUnoptimized(shape, AABB_NORTH, BooleanOp.OR);
    }
    if (state.getValue(SOUTH)) {
      shape = Shapes.joinUnoptimized(shape, AABB_SOUTH, BooleanOp.OR);
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
		builder.add(DISTANCE);
	}
	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		world.setBlock(pos, getState(world, pos, blockstate), 3);		
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return getState(context.getLevel(), context.getClickedPos(), this.defaultBlockState().setValue(WATERLOGGED, flag));
	}
	private Integer getValInDirection(Level world, BlockPos pos, Direction dir) {
		BlockState lookingAt = world.getBlockState(pos.relative(dir));
		ResourceLocation myTagId = new ResourceLocation("forge", "electrocution_energy_cable");
		ResourceLocation connect = new ResourceLocation("forge", "electrocution_network_manager_tag");
		if (lookingAt.is(BlockTags.create(myTagId))) {
			return lookingAt.getValue(DISTANCE);
		} else if (lookingAt.is(BlockTags.create(connect))) {
			return 64;
		} else {
			return 0;
		}
		
	}
	private Integer getMaxDirection(Level world, BlockPos pos) {
		Integer a = getValInDirection(world, pos, Direction.UP);
		Integer b = getValInDirection(world, pos, Direction.DOWN);
		Integer c = getValInDirection(world, pos, Direction.NORTH);
		Integer d = getValInDirection(world, pos, Direction.SOUTH);
		Integer e = getValInDirection(world, pos, Direction.EAST);
		Integer f = getValInDirection(world, pos, Direction.WEST);
		return Math.max(Math.max(Math.max(a, b), Math.max(Math.max(c, d), Math.max(e, f))), 1) - 1;
	}
	private BlockState getState(Level world, BlockPos pos, BlockState old) {
		return this.defaultBlockState().setValue(WATERLOGGED, old.getValue(WATERLOGGED))
			.setValue(UP, Utils.isConnected(world, pos, Direction.UP))
			.setValue(DOWN, Utils.isConnected(world, pos, Direction.DOWN))
			.setValue(NORTH, Utils.isConnected(world, pos, Direction.NORTH))
			.setValue(SOUTH, Utils.isConnected(world, pos, Direction.SOUTH))
			.setValue(EAST, Utils.isConnected(world, pos, Direction.EAST))
			.setValue(WEST, Utils.isConnected(world, pos, Direction.WEST))
			.setValue(DISTANCE, getMaxDirection(world, pos));
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

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderLayer() {
	//	ItemBlockRenderTypes.setRenderLayer(ColorfulBiomesModBlocks.ENERGY_CABLE.get(), renderType -> renderType == RenderType.cutout());
	}
}
