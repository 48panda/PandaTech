package com._48panda.tech.recipe;

import com._48panda.tech.Constants;
import com.mojang.datafixers.util.Function3;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CentrifugeRecipe extends MultiOutputRecipe<SimpleContainer> {

    public CentrifugeRecipe(ResourceLocation id, NonNullList<ItemStack> output, NonNullList<Ingredient> recipeItems) {
        super(id, output, recipeItems);
    }

    @Override
    public boolean matches(SimpleContainer container, @NotNull Level level) {
        return recipeItems.get(0).test(container.getItem(0));
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serialiser.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }
    
    public static class Type extends MultiOutputRecipe.Type {
        public static final Type INSTANCE = new Type();
    }
    
    public static class Serialiser extends MultiOutputRecipe.Serialiser<SimpleContainer, CentrifugeRecipe> {
        public static final Serialiser INSTANCE = new Serialiser();
        @Override
        public Function3<ResourceLocation, NonNullList<ItemStack>, NonNullList<Ingredient>, CentrifugeRecipe> getConstructor() {
            return CentrifugeRecipe::new;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return new ResourceLocation(Constants.ID, "centrifuge");
        }
    }
}
