package net.cubespace.devathlon14.recipe.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 18.10.2014.
 */
public class RecipeRegistry {
    private static List<Recipe> recipes = new ArrayList<>();

    /**
     * Register a Recipe and add it to the list
     * @param recipe The recipe which should be registered
     */
    public static void addRecipe( Recipe recipe ) {
        recipes.add( recipe );
        recipe.register();
    }

    /**
     * Get a copy of the Recipe list
     * @return Immutable list of all registered Recipes
     */
    public static List<Recipe> getRecipes() {
        return new ArrayList<>( recipes );
    }
}
