package com._48panda.tech.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Function3;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MultiOutputRecipe<C extends Container> implements Recipe<C> {
    protected final ResourceLocation id;
    protected final NonNullList<ItemStack> output;
    protected final NonNullList<Ingredient> recipeItems;

    public MultiOutputRecipe(ResourceLocation id, NonNullList<ItemStack> output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }
    
    @Override
    @Deprecated
    // DO NOT USE THIS METHOD. ONLY HERE TO SATISFY IMPLEMENTS.
    public @NotNull ItemStack assemble(@NotNull C container) {
        throw new UnsupportedOperationException("Cannot 'Assemble' multi output recipe.");
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    // AVOID CALLING. ONLY USED IN RecipeManager TO SORT LIST
    public @NotNull ItemStack getResultItem() {
        return output.get(0);
    }
    public NonNullList<ItemStack> getResultItems() {
        return output;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    public abstract static class Type implements RecipeType<CentrifugeRecipe> {
        protected Type() {}
    }

    public abstract static class Serialiser<C extends Container, R extends MultiOutputRecipe<C>> implements RecipeSerializer<R> {
        public abstract Function3<ResourceLocation, NonNullList<ItemStack>, NonNullList<Ingredient>, R> getConstructor();
        
        @Override
        public @NotNull R fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            JsonArray outputArray = GsonHelper.getAsJsonArray(json, "output");
            NonNullList<ItemStack> outputs = NonNullList.withSize(outputArray.size(), ItemStack.EMPTY);

            for (int i = 0; i < outputArray.size(); i++) {
                outputs.set(i, ShapedRecipe.itemStackFromJson(outputArray.get(i).getAsJsonObject()));
            }

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return getConstructor().apply(id, outputs, inputs);
        }

        @Nullable
        @Override
        public R fromNetwork(@NotNull ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            NonNullList<ItemStack> output = NonNullList.withSize(buf.readInt(), ItemStack.EMPTY);
            output.replaceAll(ignored -> buf.readItem());
            return getConstructor().apply(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, R recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing: recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeInt(recipe.getResultItems().size());
            for (Ingredient ing: recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return this;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return castClass(RecipeSerializer.class);
        }
        @SuppressWarnings("SameParameterValue")
        private static <G> Class<G> castClass(Class<?> cls) {
            //noinspection unchecked
            return (Class<G>)cls;
        }
    }
}
