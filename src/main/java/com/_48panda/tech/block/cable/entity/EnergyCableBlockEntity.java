package com._48panda.tech.block.cable.entity;

import com._48panda.tech.block.cable.Connection;
import com._48panda.tech.block.cable.ConnectionType;
import com._48panda.tech.block.cable.container.EnergyCableContainer;
import com._48panda.tech.block.cable.container.data.EnergyCableContainerData;
import com._48panda.tech.block.cable.type.CableType;
import com._48panda.tech.block.cable.type.EnergyCableType;
import com._48panda.tech.init.PandaTechBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.List;

public class EnergyCableBlockEntity extends AbstractCableBlockEntity {
    private int maxBoost;
    public EnergyCableBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.ENERGY_CABLE.get(), pos, state, new TranslatableComponent("container.panda_tech.energy_cable"));
    }

    @Override
    public void sendTo(Direction dir, List<Connection> connections) {
        if (connections.size() == 0) {
            return;
        }
        BlockEntity be = level.getBlockEntity(worldPosition.relative(dir));
        if (be != null) {
            LazyOptional<IEnergyStorage> energyStorage = be.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite());
            if (energyStorage.isPresent()) {
                int totalEnergy = energyStorage.map(x->x.extractEnergy(EnergyCableType.max, true)).orElse(0);
                int perConnection = totalEnergy / connections.size();
                if (perConnection > 0) {
                    for (Connection con: connections) {
                        BlockEntity to = level.getBlockEntity(con.getPos());
                        LazyOptional<IEnergyStorage> energyTo = to.getCapability(CapabilityEnergy.ENERGY, con.getDir());
                        int toTransfer = energyTo.map(x->x.receiveEnergy(perConnection, true)).orElse(0);
                        if (toTransfer > 0) {
                            energyStorage.map(x->x.extractEnergy(toTransfer, false));
                            energyTo.map(x->x.receiveEnergy(toTransfer, false));
                        }
                    }
                }
            }
        }
    }

    @Override
    public MenuConstructor getServerContainer(BlockPos pos, Direction dir) {
        return (id, playerInv, player) -> new EnergyCableContainer(id,
                playerInv, inventory, pos,
                new EnergyCableContainerData(this, 5, dir));
    }
}
