package com._48panda.tech.init;

import com._48panda.tech.PandaTech;
import com._48panda.tech.block.cable.container.EnergyCableContainer;
import com._48panda.tech.block.cable.container.ItemCableContainer;
import com._48panda.tech.block.cable.screen.EnergyCableScreen;
import com._48panda.tech.block.cable.screen.ItemCableScreen;
import com._48panda.tech.block.machines.burner_generator.BurnerGeneratorScreen;
import com._48panda.tech.block.machines.electric_furnace.ElectricFurnaceScreen;
import com._48panda.tech.network.client.gui.RobotGuiScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PandaTech.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class PandaTechScreens {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(PandaTechMenus.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
        MenuScreens.register(PandaTechMenus.ROBOT_GUI.get(), RobotGuiScreen::new);
        MenuScreens.register(PandaTechMenus.BURNER_GENERATOR.get(), BurnerGeneratorScreen::new);
        MenuScreens.register(PandaTechMenus.ITEM_CABLE.get(), (MenuScreens.ScreenConstructor<ItemCableContainer, ItemCableScreen>) ItemCableScreen::new);
        MenuScreens.register(PandaTechMenus.ENERGY_CABLE.get(), (MenuScreens.ScreenConstructor<EnergyCableContainer, EnergyCableScreen>) EnergyCableScreen::new);
    }
}
