
package com._48panda.tech.block.cable;

import com._48panda.tech.block.cable.type.CableType;
import com._48panda.tech.block.cable.entity.ItemCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ItemCableBlock extends AbstractCableBlock {

	public ItemCableBlock() {
		super(CableType.ITEM);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ItemCableBlockEntity(pos, state);
	}
}
