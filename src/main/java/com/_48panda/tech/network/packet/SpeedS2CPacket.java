package com._48panda.tech.network.packet;

import com._48panda.tech.block.multiblock.CentrifugeMultiblockControllerBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpeedS2CPacket {
    private final BlockPos pos;
    private final double speed;
    
    public SpeedS2CPacket(BlockPos pos, double progress) {
        this.pos = pos;
        this.speed = progress;
        //this.rotation = maxProgress;
    }
    
    public SpeedS2CPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        speed = buf.readDouble();
        //rotation = buf.readDouble();
    }
    
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeDouble(speed);
        //buf.writeDouble(rotation);
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            assert Minecraft.getInstance().level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof CentrifugeMultiblockControllerBlockEntity blockEntity) {
                blockEntity.speed = speed;
                //blockEntity.rotation = rotation;
            }
        });
        return true;
    }
}
