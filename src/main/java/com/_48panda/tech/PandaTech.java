/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and it won't get overwritten.
 *    If you change your modid or package, you need to apply these changes to this file MANUALLY.
 *
 *    Settings in @Mod annotation WON'T be changed in case of the base mod element
 *    files lock too, so you need to set them manually here in such case.
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package com._48panda.tech;

import com._48panda.tech.block.copper_infused.CopperInfusedRedstoneWire;
import com._48panda.tech.client.block.render.CentrifugeRenderer;
import com._48panda.tech.client.block.render.PulveriserRenderer;
import com._48panda.tech.init.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
@Mod.EventBusSubscriber
@Mod("panda_tech")
public class PandaTech {
	@SuppressWarnings("unused")
	public static final Logger LOGGER = LogManager.getLogger(PandaTech.class);
	public static final String MODID = "panda_tech";
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public PandaTech() {
		PandaTechTabs.load();
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		PandaTechBlocks.REGISTRY.register(bus);
		PandaTechItems.REGISTRY.register(bus);
		PandaTechMenus.CONTAINERS.register(bus);
		PandaTechBlockEntities.REGISTRY.register(bus);

		PandaTechMobEffects.REGISTRY.register(bus);
		
		PandaTechRecipes.register(bus);
		
		bus.addListener(this::commonSetup);
		bus.addListener(this::registerBlockColors);
		
		bus.addListener(this::clientSetup);
		
		bus.addListener(this::registerBlockEntityRenderers);

	}

	public void registerBlockColors(final ColorHandlerEvent.Block event) {
		event.getBlockColors().register((state, p_92617_, p_92618_, p_92619_) -> 
				CopperInfusedRedstoneWire.getColorForPower(state.getValue(CopperInfusedRedstoneWire.POWER)), PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE.get());
	}
	public void clientSetup(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(PandaTechBlocks.COPPER_INFUSED_REDSTONE_WIRE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(PandaTechBlocks.COPPER_INFUSED_RESTONE_TORCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(PandaTechBlocks.COPPER_INFUSED_REDSTONE_TORCH_WALL.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(PandaTechBlocks.COPPER_INFUSED_REPEATER.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(PandaTechBlocks.COPPER_INFUSED_COMPARATOR.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(PandaTechBlocks.HIGH_DENSITY_GLASS.get(), RenderType.cutout());
	}
	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PandaTechMessages.register();
			PandaTechMultiblocks.init();
		});
	}
	
	private void registerBlockEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(PandaTechBlockEntities.PULVERISER.get(), PulveriserRenderer::new);
		event.registerBlockEntityRenderer(PandaTechBlockEntities.CENTRIFUGE.get(), CentrifugeRenderer::new);
	}
	
	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
			BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}
