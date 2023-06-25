package com._48panda.tech.init;

import com._48panda.tech.Constants;
import com._48panda.tech.recipe.CentrifugeRecipe;
import com._48panda.tech.recipe.PulveriserRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PandaTechRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.ID);
    public static final RegistryObject<RecipeSerializer<PulveriserRecipe>> PULVERISER =
            SERIALIZERS.register("pulveriser", () -> PulveriserRecipe.Serialiser.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CentrifugeRecipe>> CENTRIFUGE =
            SERIALIZERS.register("centrifuge", () -> CentrifugeRecipe.Serialiser.INSTANCE);
    
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
