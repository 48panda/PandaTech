package com._48panda.tech.block.multiblock;

import com._48panda.tech.block.entity.IProgressChange;
import com._48panda.tech.block.machines.MachineContainerData;
import com._48panda.tech.block.machines.MachineProperties;
import com._48panda.tech.init.PandaTechMessages;
import com._48panda.tech.init.PandaTechMultiblocks;
import com._48panda.tech.network.packet.ProgressS2CPacket;
import com._48panda.tech.network.packet.SpeedS2CPacket;
import com._48panda.tech.recipe.CentrifugeRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.patchouli.api.IMultiblock;

import java.util.Optional;

public class CentrifugeMultiblockControllerBlockEntity extends AbstractMultiblockControllerBlockEntity implements IProgressChange {
    public int progress = 0;
    public int maxProgress = 200;
    public double speed = 0;
    public double rotation = 0;
    public float prevPartialTicks = 0;
    public int isOn = 0;
    
    private double fractionalEnergy;
    private static final double MAX_SPEED = 36;
    private static final double ACCELERATION = MAX_SPEED / 600; // 600 ticks to reach max speed.
    private static final double DECELERATION = ACCELERATION;
    public CentrifugeMultiblockControllerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, MachineProperties.CENTRIFUGE);
    }

    @Override
    public void tick() {
        super.tick();
        if (!isAttached()) {
            progress = 0;
            return;
        }
        if (isOn > 0) {
            isOn--;
        }
        double oldSpeed = speed;
        int oldProgress = progress;
        rotation += speed;
        if (hasRecipe()) {
            if (progress < maxProgress) {
                maxProgress = (int) (200 / getAugmentedSpeedMultiplier());
                double flowRate = 30 / getAugmentedEfficiencyMultiplier() + fractionalEnergy;
                fractionalEnergy = flowRate % 1;
                int toExtract = (int) Math.floor(flowRate);
                if (energyStorage.extractEnergy(toExtract, true) == toExtract) {
                    energyStorage.extractEnergy(toExtract, false);
                    progress++;
                    isOn = 60;
                    if (speed < MAX_SPEED) {
                        speed = Math.min(speed + ACCELERATION, MAX_SPEED);
                    }
                }
            }
            if (progress >= maxProgress) {
                craftItem();
            }
        } else {
            progress = 0;
        }
        
        if (isOn == 0) {
            if (speed > 0) {
                speed = Math.max(speed - DECELERATION, 0);
            }
        }
        
        
        if (progress != oldProgress) {
            assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            if (!level.isClientSide()) {
                PandaTechMessages.sendToClients(new ProgressS2CPacket(getBlockPos(), progress, maxProgress));
            }
            setChanged();
        }
        
        if (speed != oldSpeed) {
            assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            if (!level.isClientSide()) {
                PandaTechMessages.sendToClients(new SpeedS2CPacket(getBlockPos(), speed));
            }
        }
        
        if (inventory.getStackInSlot(0).isEmpty()) {
            ItemStack res = extractItem(this::isValidItem, true);
            ItemStack done = inventory.insertItem(0, res, true);
            if (!res.isEmpty() && done.isEmpty()) {
                ItemStack transfer = extractItem(this::isValidItem, false);
                inventory.insertItem(0, transfer, false);
                updateSlot(0, false);
            }
        }
    }
    public boolean isValidItem(ItemStack item) {
        SimpleContainer inv = new SimpleContainer(item);
        assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
        Optional<CentrifugeRecipe> match = level.getRecipeManager().getRecipeFor(CentrifugeRecipe.Type.INSTANCE, inv, level);
        return match.isPresent();
    }
    public boolean hasRecipe() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for (int i=0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
        Optional<CentrifugeRecipe> match = level.getRecipeManager().getRecipeFor(CentrifugeRecipe.Type.INSTANCE, inv, level);
        
        return match.isPresent();
    }
    
    public void craftItem() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for (int i=0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
        Optional<CentrifugeRecipe> match = level.getRecipeManager().getRecipeFor(CentrifugeRecipe.Type.INSTANCE, inv, level);
        
        if (match.isPresent()) {
            ItemStack res = insertItem(match.get().getResultItem(), true);
            if (res.isEmpty()) {
                inventory.extractItem(0, 1, false);
                insertItem(match.get().getResultItem(), false);
                updateSlot(0, false);
                progress = 0;
                //level.playSound(null, getBlockPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1, -10);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("progress", progress);
        tag.putInt("maxProgress", maxProgress);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
        maxProgress = tag.getInt("maxProgress");
    }

    @Override
    public IMultiblock getMultiblock() {
        return PandaTechMultiblocks.CENTRIFUGE;
    }

    @Override
    public Direction getMultiblockFacing() {
        return Direction.SOUTH;
    }

    @Override
    public MenuConstructor getServerContainer(BlockPos pos) {
        return (id, playerInv, player) -> new CentrifugeMultiblockControllerContainer(id,
                playerInv, inventory, output, augments, pos,
                new MachineContainerData(this,
                        properties.numDataPieces() + 2));
    }

    @Override
    public int getData(int key) {
        return switch (key) {
            case 0 -> progress;
            case 1 -> maxProgress;
            default -> 0;
        };
    }

    @Override
    public void setData(int i, int value) {
        switch(i) {
            case 0 -> progress = value;
            case 1 -> maxProgress = value;
        }
    }

    @Override
    public void setProgress(int progress, int maxProgress) {
        this.progress = progress;
        this.maxProgress = maxProgress;
    }
}
