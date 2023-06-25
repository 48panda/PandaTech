package com._48panda.tech.init;

import com._48panda.tech.PandaTech;
import com._48panda.tech.network.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PandaTechMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(PandaTech.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        
        INSTANCE = net;
        
        net.messageBuilder(EnergySyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EnergySyncS2CPacket::new)
                .encoder(EnergySyncS2CPacket::toBytes)
                .consumer(EnergySyncS2CPacket::handle)
                .add();
        
        net.messageBuilder(ProgressS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ProgressS2CPacket::new)
                .encoder(ProgressS2CPacket::toBytes)
                .consumer(ProgressS2CPacket::handle)
                .add();
        net.messageBuilder(SpeedS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SpeedS2CPacket::new)
                .encoder(SpeedS2CPacket::toBytes)
                .consumer(SpeedS2CPacket::handle)
                .add();

        net.messageBuilder(InventoryUpdateS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(InventoryUpdateS2CPacket::new)
                .encoder(InventoryUpdateS2CPacket::toBytes)
                .consumer(InventoryUpdateS2CPacket::handle)
                .add();
        net.messageBuilder(MinionUpdateS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MinionUpdateS2CPacket::new)
                .encoder(MinionUpdateS2CPacket::toBytes)
                .consumer(MinionUpdateS2CPacket::handle)
                .add();
        net.messageBuilder(SetLockedC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SetLockedC2SPacket::new)
                .encoder(SetLockedC2SPacket::toBytes)
                .consumer(SetLockedC2SPacket::handle)
                .add();
    }
    
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    @SuppressWarnings("unused")
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(()-> player), message);
    }
    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
