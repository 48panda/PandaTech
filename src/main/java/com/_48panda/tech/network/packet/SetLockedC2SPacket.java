package com._48panda.tech.network.packet;

import com._48panda.tech.block.cable.entity.AbstractCableBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetLockedC2SPacket {
    private final BlockPos pos;
    private final Direction face;
    private final boolean isLocked;
    
    public SetLockedC2SPacket(BlockPos pos, Direction face, boolean isLocked) {
        this.pos = pos;
        this.face = face;
        this.isLocked = isLocked;
    }
    
    public SetLockedC2SPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        face = Direction.from3DDataValue(buf.readInt());
        isLocked = buf.readBoolean();
    }
    
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeInt(face.get3DDataValue());
        buf.writeBoolean(isLocked);
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            if (level.getBlockEntity(pos) instanceof AbstractCableBlockEntity blockEntity) {
                blockEntity.setLocked(face, isLocked);
            }
        });
        return true;
    }
}
