package com._48panda.tech.jei;

import com._48panda.tech.Constants;
import com._48panda.tech.init.PandaTechItems;
import com._48panda.tech.recipe.CentrifugeRecipe;
import com._48panda.tech.recipe.PulveriserRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class PluginJei implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(Constants.ID, "jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(PandaTechItems.ELECTRIC_FURNACE.get()), RecipeTypes.SMELTING);
        registration.addRecipeCatalyst(new ItemStack(PandaTechItems.PULVERISER.get()), new RecipeType<>(PulveriserRecipeCategory.UID, PulveriserRecipe.class));
        registration.addRecipeCatalyst(new ItemStack(PandaTechItems.CENTRIFUGE.get()), new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PulveriserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CentrifugeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<PulveriserRecipe> pulveriserRecipes = rm.getAllRecipesFor(PulveriserRecipe.Type.INSTANCE);
        registration.addRecipes( new RecipeType<>(PulveriserRecipeCategory.UID, PulveriserRecipe.class), pulveriserRecipes);
        List<CentrifugeRecipe> centrifugeRecipes = rm.getAllRecipesFor(CentrifugeRecipe.Type.INSTANCE);
        registration.addRecipes( new RecipeType<>(CentrifugeRecipeCategory.UID, CentrifugeRecipe.class), centrifugeRecipes);
    }
}
