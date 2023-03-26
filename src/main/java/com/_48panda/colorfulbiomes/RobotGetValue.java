/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com._48panda.colorfulbiomes as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com._48panda.colorfulbiomes;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import java.util.Arrays;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.block.state.BlockState;

public class RobotGetValue {
	public static class returnValue<T> {
		public final T toReturn;
		public final double[] stack;
		public returnValue(T toReturn, double[] stack) {
			this.toReturn = toReturn;
			this.stack = stack;
		}
	}
	public static returnValue<Boolean> getBoolean(LevelAccessor world, BlockPos pos, double[] stack) {
		int operation = (int)stack[0];
		double[] newStack = Arrays.copyOfRange(stack, 1, stack.length);
		switch (operation) {
			case 0:
				return new returnValue<Boolean>(false, newStack);
			case 1:
				return new returnValue<Boolean>(true, newStack);
			case 2:
				returnValue<Boolean> value = getBoolean(world, pos, newStack);
				return new returnValue<Boolean>(!(value.toReturn), value.stack);
			case 3:
				returnValue<Boolean> arg1 = getBoolean(world, pos, newStack);
				returnValue<Boolean> arg2 = getBoolean(world, pos, arg1.stack);
				return new returnValue<Boolean>((arg1.toReturn && arg2.toReturn), arg2.stack);
			case 4:
				arg1 = getBoolean(world, pos, newStack);
				arg2 = getBoolean(world, pos, arg1.stack);
				return new returnValue<Boolean>((arg1.toReturn || arg2.toReturn), arg2.stack);
			case 11:
				returnValue<String> block1 = getBlock(world, pos, newStack);
				returnValue<String> block2 = getBlock(world, pos, block1.stack);
				return new returnValue<Boolean>(block1.toReturn.equals(block2.toReturn), block2.stack);
			default:
				return new returnValue<Boolean>(true, newStack);
		}
	}
	public static returnValue<String> getBlock(LevelAccessor world, BlockPos pos, double[] stack) {
		int operation = (int)stack[0];
		double[] newStack = Arrays.copyOfRange(stack, 1, stack.length);
		switch (operation) {
			case 0:
				BlockState state = world.getBlockState(pos);
				Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
				ResourceLocation id = ForgeRegistries.BLOCKS.getKey(world.getBlockState(pos.relative(facing)).getBlock());
				return new returnValue<String>(id.toString(), newStack);
			case 1:
				facing = Direction.UP;
				id = ForgeRegistries.BLOCKS.getKey(world.getBlockState(pos.relative(facing)).getBlock());
				return new returnValue<String>(id.toString(), newStack);
			case 2:
				facing = Direction.DOWN;
				id = ForgeRegistries.BLOCKS.getKey(world.getBlockState(pos.relative(facing)).getBlock());
				return new returnValue<String>(id.toString(), newStack);
			case 3:
				int stringMapIndex = (int)newStack[0];
				newStack = Arrays.copyOfRange(newStack, 1, stack.length);
				String blockStr = world.getBlockEntity(pos).getTileData().getList("strings", Tag.TAG_STRING).getString(stringMapIndex);
				return new returnValue<String>(blockStr, newStack);
			default:
				return new returnValue<String>("", newStack);
		}
	}
}
