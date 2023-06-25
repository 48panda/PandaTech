package com._48panda.tech.block.entity;

import com._48panda.tech.init.PandaTechBlockEntities;
import com._48panda.tech.init.PandaTechMessages;
import com._48panda.tech.network.packet.MinionUpdateS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnergyFrameBlockEntity extends FrameBlockEntity implements IMinionBlockEntity {
    public EnergyFrameBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.ENERGY_FRAME.get(), pos, state);
    }

    @Override
    public void attachMain(BlockPos pos) {
        super.attachMain(pos);
        assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
        if (!level.isClientSide()) {
            PandaTechMessages.sendToClients(new MinionUpdateS2CPacket(getBlockPos(), getMainPos()));
        }
    }
    private static final IEnergyStorage PLACEHOLDER = new IEnergyStorage() {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return 0;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return 0;
        }

        @Override
        public int getEnergyStored() {
            return 0;
        }

        @Override
        public int getMaxEnergyStored() {
            return 0;
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    };

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY && hasMainAttached()) {
            BlockPos pos = getMainPos();
            assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            BlockEntity be = level.getBlockEntity(pos);
            if (be != null) {
                return be.getCapability(cap, null);
            }
        } else if (cap == CapabilityEnergy.ENERGY ) {
            return LazyOptional.of(()->PLACEHOLDER).cast();
        }
        return super.getCapability(cap, side);
    }
}
