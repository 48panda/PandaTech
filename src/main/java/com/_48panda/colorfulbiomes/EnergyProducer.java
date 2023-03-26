package com._48panda.colorfulbiomes;

import com._48panda.colorfulbiomes.block.EnergyCableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import com._48panda.colorfulbiomes.Utils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Random;
import net.minecraft.server.level.ServerLevel;


public class EnergyProducer extends Block {
	public EnergyProducer(BlockBehaviour.Properties props) {
		super(props);
	}
	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		updateState(world, pos);
	}
	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		updateState(world, pos);
	}
	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
		super.tick(blockstate, world, pos, random);
		BlockEntity be = world.getBlockEntity(pos);
		CompoundTag data = be.getTileData();
		if (data.getBoolean("hasNetworkManager")) {
			int[] NetworkManagerPos = data.getIntArray("networkManagerPos");
			BlockPos sendToPos = new BlockPos(NetworkManagerPos[0], NetworkManagerPos[1], NetworkManagerPos[2]);
			int amount = Utils.getEnergy(world, pos);
			Utils.takeEnergy(world, pos, amount);
			Utils.sendEnergy(world, sendToPos, amount);
		}
	}
	private void updateState(Level world, BlockPos pos) {
		BlockEntity be = world.getBlockEntity(pos);
		CompoundTag data = be.getTileData();
		data.putBoolean("hasNetworkManager", false);
		if (getMaxDirection(world, pos) > 0) {
			for (Direction dir: Direction.values()) {
				if (getValInDirection(world, pos, dir) > 0) {
					BlockPos networkManager = getNetworkManagerPos(world, pos.relative(dir));
					if (networkManager != null) {
						data.putBoolean("hasNetworkManager", true);
						int[] myIntArray = {networkManager.getX(),networkManager.getY(),networkManager.getZ()};
						data.putIntArray("networkManagerPos", myIntArray);
					}
				}
			}
		}	
	}
	private Integer getValInDirection(Level world, BlockPos pos, Direction dir) {
		BlockState lookingAt = world.getBlockState(pos.relative(dir));
		ResourceLocation myTagId = new ResourceLocation("forge", "electrocution_energy_cable");
		ResourceLocation connect = new ResourceLocation("forge", "electrocution_network_manager_tag");
		if (!Utils.isConnected(world, pos.relative(dir), dir.getOpposite())) {
			return 0;
		}
		if (lookingAt.is(BlockTags.create(myTagId))) {
			return lookingAt.getValue(EnergyCableBlock.DISTANCE);
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
		return Math.max(Math.max(a, b), Math.max(Math.max(c, d), Math.max(e, f)));
	}
	private Direction getDirectionOfMax(Level world, BlockPos pos) {
		Integer maxVal = getMaxDirection(world, pos);
		if (getValInDirection(world, pos, Direction.UP) == maxVal) {
			return Direction.UP;
		}
		if (getValInDirection(world, pos, Direction.DOWN) == maxVal) {
			return Direction.DOWN;
		}
		if (getValInDirection(world, pos, Direction.NORTH) == maxVal) {
			return Direction.NORTH;
		}
		if (getValInDirection(world, pos, Direction.SOUTH) == maxVal) {
			return Direction.SOUTH;
		}
		if (getValInDirection(world, pos, Direction.EAST) == maxVal) {
			return Direction.EAST;
		}
		return Direction.WEST;
	}
	private BlockPos getNetworkManagerPos(Level world, BlockPos pos) {
		if (getMaxDirection(world, pos) < world.getBlockState(pos).getValue(EnergyCableBlock.DISTANCE)) {
			return null;
		}
		if (getMaxDirection(world, pos) == 64) {
			return pos.relative(getDirectionOfMax(world, pos));
		} else {
			return getNetworkManagerPos(world, pos.relative(getDirectionOfMax(world, pos)));
		}
	}
}
