package net.cubespace.devathlon14.recipe.api;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

/**
 * Created by Fabian on 18.10.2014.
 */
public abstract class Recipe implements org.bukkit.inventory.Recipe {

    protected int currentLevel = 0;
    protected boolean isLevelable = false;

    /**
     * This method should be called when an Player tries to craft this item
     * @return The itemStack which should be given to the Player
     */
    public abstract ItemStack onCrafting();

    /**
     * Create the shaped Recipe which we need to register to Bukkit
     * @return The recipe
     */
    private ShapedRecipe getShapedRecipe() {
        // Check if the Annotations needed are given
        Preconditions.checkArgument( this.getClass().isAnnotationPresent( RecipeShape.class ) );
        Preconditions.checkArgument( this.getClass().isAnnotationPresent( RecipeContent.class ) || this.getClass().isAnnotationPresent( RecipeContents.class ) );

        // Get the Shape
        String[] shape = this.getClass().getAnnotation( RecipeShape.class ).value();

        // Construct the new Recipe based on that informations
        ShapedRecipe shapedRecipe = new ShapedRecipe( getResult() );
        shapedRecipe.shape( shape );

        // Get the Ingredients
        for ( RecipeContent recipeContent : this.getClass().getAnnotationsByType( RecipeContent.class ) ) {
            shapedRecipe.setIngredient( recipeContent.value(), recipeContent.material() );
        }

        return shapedRecipe;
    }

    /**
     * Register this Recipe to Bukkit
     */
    public void register() {
        // Check if this Recipe is levelable
        if ( this.getClass().isAnnotationPresent( Levelable.class ) ) {
            Levelable levelable = this.getClass().getAnnotation( Levelable.class );

        }

        Bukkit.getServer().addRecipe( getShapedRecipe() );
    }

}
