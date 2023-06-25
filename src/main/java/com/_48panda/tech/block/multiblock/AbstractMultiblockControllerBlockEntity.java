package com._48panda.tech.block.multiblock;

import com._48panda.tech.Utils;
import com._48panda.tech.block.FrameBlock;
import com._48panda.tech.block.entity.IMinionBlockEntity;
import com._48panda.tech.block.entity.IMultiblockController;
import com._48panda.tech.block.entity.ItemInputFrameBlockEntity;
import com._48panda.tech.block.entity.ItemOutputFrameBlockEntity;
import com._48panda.tech.block.machines.AugmentableMachineBlockEntity;
import com._48panda.tech.block.machines.MachineProperties;
import com._48panda.tech.init.PandaTechMessages;
import com._48panda.tech.network.packet.InventoryUpdateS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import vazkii.patchouli.api.IMultiblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;


public abstract class AbstractMultiblockControllerBlockEntity extends AugmentableMachineBlockEntity implements IMultiblockController {
    private static boolean isConductingInvestigation = false;
    private List<BlockPos> inputs;
    private List<BlockPos> outputs;
    public AbstractMultiblockControllerBlockEntity(BlockPos pos, BlockState state, MachineProperties properties) {
        super(pos, state, properties);
        isConductingInvestigation = false;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
    }

    @Override
    public boolean isAttached() {
        return getBlockState().getValue(FrameBlock.HAS_MULTIBLOCK);
    }

    @Override
    public void setItem(int slot, ItemStack setTo, boolean isOutput) {
        if (isOutput) { 
            output.setStackInSlot(slot, setTo);
        } else {
            inventory.setStackInSlot(slot, setTo);
        }
    }

    @Override
    public void attach() {
        BlockState state = getBlockState();
        if (!state.getValue(FrameBlock.HAS_MULTIBLOCK)) {
            assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            level.setBlockAndUpdate(getBlockPos(), state.setValue(FrameBlock.HAS_MULTIBLOCK, true));
        }
    }

    @Override
    public void detach() {
        BlockState state = getBlockState();
        if (state.getValue(FrameBlock.HAS_MULTIBLOCK)) {
            assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            level.setBlockAndUpdate(getBlockPos(), state.setValue(FrameBlock.HAS_MULTIBLOCK, false));
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        if (!isAttached()) {
            return super.getRenderBoundingBox();
        }
        Direction dir = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        BlockPos corner1 = getBlockPos().relative(dir.getClockWise());
        BlockPos corner2 = getBlockPos().relative(dir.getOpposite(), 2).relative(dir.getCounterClockWise());
        BlockPos mincorner = new BlockPos(Math.min(corner1.getX(), corner2.getX()),Math.min(corner1.getY(), corner2.getY()),Math.min(corner1.getZ(), corner2.getZ()));
        return new AABB(mincorner, mincorner.offset(3, 3, 3));
    }
    public ItemStack extractItem(Function<ItemStack, Boolean> filter, boolean simulate) {
        for (BlockPos pos: inputs) {
            if (level.getBlockEntity(pos) instanceof ItemInputFrameBlockEntity be) {
                for (int i=0; i < be.inventory.getSlots(); i++) {
                    ItemStack res = be.inventory.getStackInSlot(i).copy();
                    res.setCount(1);
                    if (!res.isEmpty() && filter.apply(res)) {
                        if (simulate) {
                            return res;
                        }
                        return be.inventory.extractItem(i, 1, false);
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }
    
    public void updateSlot(int slot, boolean isOutput) {
        PandaTechMessages.sendToClients(new InventoryUpdateS2CPacket(getBlockPos(), slot, (isOutput?output:inventory).getStackInSlot(slot), isOutput));
    }
    
    public static ItemStack insertItem(ItemStackHandler items, int slot, ItemStack stack, boolean simulate) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        //if (!isItemValid(slot, stack))
        //    return stack;

        if (slot < 0 || slot >= items.getSlots())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + items.getSlots() + ")");

        ItemStack existing = items.getStackInSlot(slot);

        int limit = Math.min(items.getSlotLimit(slot), stack.getMaxStackSize());

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                items.setStackInSlot(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            //items.onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
    }

    public ItemStack insertItem(ItemStack toInsert, boolean simulate) {
        ItemStack res = toInsert.copy();
        for (BlockPos pos: outputs) {
            if (level.getBlockEntity(pos) instanceof ItemOutputFrameBlockEntity be) {
                for (int i=0; i < be.output.getSlots(); i++) {
                    res = insertItem(be.output, i, res, simulate);
                    if (res.isEmpty()) {
                        return res;
                    }
                }
            }
        }
        return res;
    }
    
    public void setInputsAndOutputs(List<BlockPos> inputs, List<BlockPos> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }
    @Override
    public void multiblockNeighbourChanged() {
        if (isConductingInvestigation) {
            return;
        }
        isConductingInvestigation = true;
        IMultiblock multiblock = getMultiblock();
        assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
        Rotation res = multiblock.validate(level, getBlockPos());
        if (res == null) {
            Direction isFacing = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
            Rotation multiblockRot = Utils.getRotation(getMultiblockFacing(), isFacing);
            Collection<IMultiblock.SimulateResult> blocks = multiblock.simulate(level, getBlockPos(), multiblockRot, false).getSecond();
            blocks.forEach(x->{
                BlockPos worldPos = x.getWorldPosition();
                BlockEntity blockEntity1 = level.getBlockEntity(worldPos);
                if (blockEntity1 instanceof IMinionBlockEntity newBlockEntity) {
                    newBlockEntity.detachMain();
                } else if (blockEntity1 instanceof IMultiblockController newBlockEntity) {
                    newBlockEntity.detach();
                }
            });
        }
        isConductingInvestigation = false;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ListTag in = new ListTag();
        for (BlockPos pos: inputs) {
            in.add(new IntArrayTag(Arrays.asList(pos.getX(), pos.getY(), pos.getZ())));
        }
        tag.put("inputs", in);
        ListTag out = new ListTag();
        for (BlockPos pos: outputs) {
            out.add(new IntArrayTag(Arrays.asList(pos.getX(), pos.getY(), pos.getZ())));
        }
        tag.put("outputs", out);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inputs = new ArrayList<>();
        ListTag in = tag.getList("inputs", Tag.TAG_INT_ARRAY);
        for (int i=0; i < in.size(); i++) {
            int[]pos = in.getIntArray(i);
            inputs.add(new BlockPos(pos[0], pos[1], pos[2]));
        }
        outputs = new ArrayList<>();
        ListTag out = tag.getList("outputs", Tag.TAG_INT_ARRAY);
        for (int i=0; i < out.size(); i++) {
            int[]pos = out.getIntArray(i);
            outputs.add(new BlockPos(pos[0], pos[1], pos[2]));
        }
    }

    public abstract IMultiblock getMultiblock();
    public abstract Direction getMultiblockFacing();
}
