package net.cubespace.devathlon14.recipe;

import net.cubespace.devathlon14.recipe.api.Levelable;
import net.cubespace.devathlon14.recipe.api.Recipe;
import net.cubespace.devathlon14.recipe.api.RecipeContent;
import net.cubespace.devathlon14.recipe.api.RecipeShape;
import net.cubespace.devathlon14.util.ItemstackUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Fabian on 18.10.2014.
 */
@RecipeShape( {" D ", " S ", "   "} )
@RecipeContent( value = 'D', material = Material.FEATHER )
@RecipeContent( value = 'S', material = Material.EGG )
@Levelable( value = true, maxLevel = 25 )
public class FlyingEntity extends Recipe {

    private static String itemName = "";

    @Override
    public ItemStack getResult() {
        return ItemstackUtils.renameItem( new ItemStack( Material.EGG ), itemName );
    }

    @Override public ItemStack onCrafting() {
        return null;
    }
}
