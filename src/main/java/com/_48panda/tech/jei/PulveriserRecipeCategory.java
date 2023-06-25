package com._48panda.tech.jei;

import com._48panda.tech.Constants;
import com._48panda.tech.init.PandaTechBlocks;
import com._48panda.tech.recipe.PulveriserRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PulveriserRecipeCategory implements IRecipeCategory<PulveriserRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Constants.ID, "pulveriser");
    public final static ResourceLocation TEXTURE = new ResourceLocation(Constants.ID, "textures/gui/jei/pulveriser.png");
    
    private final IDrawable background;
    private final IDrawable icon;

    public PulveriserRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(TEXTURE, 0, 0, 176, 80);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(PandaTechBlocks.PULVERISER.get()));
    }

    @Override
    public @NotNull RecipeType<PulveriserRecipe> getRecipeType() {
        return new RecipeType<>(UID, PulveriserRecipe.class);
    }

    @Override
    public @NotNull Component getTitle() {
        return new TranslatableComponent("block.panda_tech.pulveriser");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PulveriserRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 35).addIngredients(recipe.getIngredients().get(0));
        
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(recipe.getResultItem());
    }

    @SuppressWarnings({"removal", "NullableProblems"})
    @Override public ResourceLocation getUid(){return null;}
    @SuppressWarnings({"removal", "NullableProblems"})
    @Override public Class<?extends PulveriserRecipe>getRecipeClass(){return null;}


}
