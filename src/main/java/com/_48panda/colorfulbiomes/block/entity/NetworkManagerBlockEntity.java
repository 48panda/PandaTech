package com._48panda.colorfulbiomes.block.entity;

import net.minecraft.client.Minecraft;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.util.stream.IntStream;
import java.util.Collections;
import java.util.ArrayList;


import com._48panda.colorfulbiomes.init.ColorfulBiomesModBlockEntities;
import com._48panda.colorfulbiomes.Utils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.ListTag;
import org.w3c.dom.Text;


public class NetworkManagerBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

	public NetworkManagerBlockEntity(BlockPos position, BlockState state) {
		super(ColorfulBiomesModBlockEntities.NETWORK_MANAGER.get(), position, state);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		if (!this.tryLoadLootTable(compound))
			this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.stacks);
		if (compound.get("energyStorage") instanceof IntTag intTag)
			energyStorage.deserializeNBT(intTag);
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.stacks);
		}
		compound.put("energyStorage", energyStorage.serializeNBT());
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithFullMetadata();
	}

	@Override
	public int getContainerSize() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public Component getDefaultName() {
		return new TextComponent("network_manager");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return ChestMenu.threeRows(id, inventory);
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Network Manager");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return this.canPlaceItem(index, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	private final EnergyStorage energyStorage = new EnergyStorage(1000000000, 10000000, 10000000, 0) {
		@Override
		public int receiveEnergy(int maxReceive, boolean simulate) {
			int retval = super.receiveEnergy(maxReceive, simulate);
			if (!simulate) {
				setChanged();
				level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
			}
			return retval;
		}

		@Override
		public int extractEnergy(int maxExtract, boolean simulate) {
			int retval = super.extractEnergy(maxExtract, simulate);
			if (!simulate) {
				setChanged();
				level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 2);
			}
			return retval;
		}
	};

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return handlers[facing.ordinal()].cast();
		if (!this.remove && capability == CapabilityEnergy.ENERGY)
			return LazyOptional.of(() -> energyStorage).cast();
		return super.getCapability(capability, facing);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (LazyOptional<? extends IItemHandler> handler : handlers)
			handler.invalidate();
	}
	
	public void tick() {
		if (getLevel() instanceof ServerLevel world) {
			CompoundTag data = getTileData();
			ListTag registers = data.getList("registers", Tag.TAG_INT_ARRAY);
			if (registers.size() == 0) {
			 	return;
			 }
			ArrayList<Integer> totalEnergyNeeded = new ArrayList<>();
			
			int totalEnergyHave = Utils.getEnergy(this);
			ArrayList<Integer> energyDist = new ArrayList<>();
			for (int i = 0; i < registers.size(); i++) {
		      int[] block = registers.getIntArray(i);
		      totalEnergyNeeded.add(block[3]);
		      energyDist.add(0);
		    }
		    ArrayList<Integer> sortedNumbers = new ArrayList<>(totalEnergyNeeded);
		    Collections.sort(sortedNumbers);
		    int width = sortedNumbers.size();
		    int prevHeight = 0;
		    for (int i = 0; i < sortedNumbers.size(); i++) {
		    	int height = sortedNumbers.get(i);
		    	int dh = height - prevHeight;
		    	prevHeight = height;
		    	if (width * dh > totalEnergyHave) {
		    		dh = totalEnergyHave / width;
		    	}
		    	for (int j = 0; j< energyDist.size(); j++) {
		    		if (energyDist.get(j) < totalEnergyNeeded.get(j)) {
		    			energyDist.set(j, energyDist.get(j) + dh);
		    		}
		    	}
		    }
			for (int i = 0; i < registers.size(); i++) {
		      int[] block = registers.getIntArray(i);
		      Utils.transfer(world, this, new BlockPos(block[0],block[1],block[2]), energyDist.get(i));
		    }
		    registers.clear();
		    data.put("registers", registers);
		}
	}
}
