package net.cubespace.devathlon14.recipe.api;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

/**
 * Created by Fabian on 18.10.2014.
 */
public abstract class Recipe implements org.bukkit.inventory.Recipe {

    protected int currentLevel = 0;
    protected boolean isLevelable = false;
    private int maxLevel = 0;

    /**
     * Called when an Item from this Recipe gets hit on the ground
     * @param playerInteractEvent   The playerInteractEvent which got fired
     */
    public void onInteract( PlayerInteractEvent playerInteractEvent ) {

    }

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
            if ( isLevelable && recipeContent.canLevel() && currentLevel > 1 ) {
                shapedRecipe.setIngredient( recipeContent.value(), recipeContent.material(), currentLevel - 1 );
            } else {
                shapedRecipe.setIngredient( recipeContent.value(), recipeContent.material() );
            }
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
            this.isLevelable = levelable.value();
            this.maxLevel = levelable.maxLevel();

            for ( this.currentLevel = 1; this.currentLevel < this.maxLevel + 1; this.currentLevel++ ) {
                Bukkit.getServer().addRecipe( getShapedRecipe() );
            }
        } else {
            Bukkit.getServer().addRecipe( getShapedRecipe() );
        }
    }

}
