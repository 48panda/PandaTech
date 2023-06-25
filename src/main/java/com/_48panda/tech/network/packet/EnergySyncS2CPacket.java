package com._48panda.tech.network.packet;

import com._48panda.tech.block.entity.IEnergyBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergySyncS2CPacket {
    private final int energy;
    private final BlockPos pos;
    
    @SuppressWarnings("unused")
    public EnergySyncS2CPacket(int energy, BlockPos pos) {
        this.energy = energy;
        this.pos = pos;
    }
    
    public EnergySyncS2CPacket(FriendlyByteBuf buf) {
        energy = buf.readInt();
        pos = buf.readBlockPos();
    }
    
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            assert Minecraft.getInstance().level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof IEnergyBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);
            }
        });
        return true;
    }
}
