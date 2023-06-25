package com._48panda.tech.network.packet;

import com._48panda.tech.block.entity.IProgressChange;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ProgressS2CPacket {
    private final BlockPos pos;
    private final int progress, maxProgress;
    
    public ProgressS2CPacket(BlockPos pos, int progress, int maxProgress) {
        this.pos = pos;
        this.progress = progress;
        this.maxProgress = maxProgress;
    }
    
    public ProgressS2CPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        progress = buf.readInt();
        maxProgress = buf.readInt();
    }
    
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeInt(progress);
        buf.writeInt(maxProgress);
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level==null) {
                return; // We've got bigger problems if this happens, just shutting up some warnings.
            }
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof IProgressChange blockEntity) {
                blockEntity.setProgress(progress, maxProgress);
            }
        });
        return true;
    }
}
