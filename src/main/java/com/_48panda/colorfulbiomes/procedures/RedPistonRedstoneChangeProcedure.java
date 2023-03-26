package com._48panda.colorfulbiomes.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class RedPistonRedstoneChangeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, int d2) {
		Direction dir = Direction.NORTH;
		double d1 = 0;
		BlockState temp = Blocks.AIR.defaultBlockState();
		dir = new Object() {
			public Direction getDirection(BlockPos pos) {
				BlockState _bs = world.getBlockState(pos);
				Property<?> property = _bs.getBlock().getStateDefinition().getProperty("facing");
				if (property != null && _bs.getValue(property) instanceof Direction _dir)
					return _dir;
				property = _bs.getBlock().getStateDefinition().getProperty("axis");
				if (property != null && _bs.getValue(property) instanceof Direction.Axis _axis)
					return Direction.fromAxisAndDirection(_axis, Direction.AxisDirection.POSITIVE);
				return Direction.NORTH;
			}
		}.getDirection(new BlockPos(x, y, z));
		d1 = 1;
		temp = (world.getBlockState(new BlockPos(dir.getStepX() * d1 + x, dir.getStepY() * d1 + y, dir.getStepZ() * d1 + z)));
		world.setBlock(new BlockPos(dir.getStepX() * d1 + x, dir.getStepY() * d1 + y, dir.getStepZ() * d1 + z),
				(world.getBlockState(new BlockPos(dir.getStepX() * d2 + x, dir.getStepY() * d2 + y, dir.getStepZ() * d2 + z))), 3);
		world.setBlock(new BlockPos(dir.getStepX() * d2 + x, dir.getStepY() * d2 + y, dir.getStepZ() * d2 + z), temp, 3);
	}
}
