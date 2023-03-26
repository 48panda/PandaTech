package com._48panda.colorfulbiomes.init;

import com._48panda.colorfulbiomes.ColorfulBiomesMod;
import com._48panda.colorfulbiomes.block.machines.burner_generator.BurnerGeneratorScreen;
import com._48panda.colorfulbiomes.block.machines.electric_furnace.ElectricFurnaceScreen;
import com._48panda.colorfulbiomes.client.gui.RobotGuiScreen;
import com._48panda.colorfulbiomes.init.ColorfulBiomesModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ColorfulBiomesMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ColorfulBiomesModMenus.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
        MenuScreens.register(ColorfulBiomesModMenus.ROBOT_GUI.get(), RobotGuiScreen::new);
        MenuScreens.register(ColorfulBiomesModMenus.BURNER_GENERATOR.get(), BurnerGeneratorScreen::new);
    }
}
