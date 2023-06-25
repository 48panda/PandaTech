package com._48panda.tech.init;

import com._48panda.tech.Constants;
import com._48panda.tech.recipe.PulveriserRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PandaTechEvents {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, PulveriserRecipe.Type.id, PulveriserRecipe.Type.INSTANCE);
    }
}
