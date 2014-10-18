package net.cubespace.devathlon14.recipe;

import net.cubespace.devathlon14.EffectPlugin;
import net.cubespace.devathlon14.recipe.api.Levelable;
import net.cubespace.devathlon14.recipe.api.Recipe;
import net.cubespace.devathlon14.recipe.api.RecipeContent;
import net.cubespace.devathlon14.recipe.api.RecipeShape;
import net.cubespace.devathlon14.util.ItemstackUtils;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by Fabian on 18.10.2014.
 */
@RecipeShape( { " D ", " S ", "   " } )
@RecipeContent( value = 'D', material = Material.FEATHER )
@RecipeContent( value = 'S', material = Material.EGG, canLevel = true )
@Levelable( value = true, maxLevel = 25 )
public class FlyingEntity extends Recipe {

    private static String itemName = "Flying Chicken Level {level}";
    private static String replaceName = itemName.replace( "{level}", "" );

    @Override
    public ItemStack getResult() {
        return ItemstackUtils.renameItem( new ItemStack( Material.EGG, 1, (short) currentLevel ), itemName.replace( "{level}", String.valueOf( currentLevel ) ) );
    }

    @Override
    public void onInteract( PlayerInteractEvent playerInteractEvent ) {
        if (   playerInteractEvent.getItem().getItemMeta().hasDisplayName()
            && playerInteractEvent.getItem().getItemMeta().getDisplayName().startsWith( replaceName ) ) {
            Player player = playerInteractEvent.getPlayer();
            Integer level = Integer.getInteger( playerInteractEvent.getItem().getItemMeta().getDisplayName().replace( replaceName, "" ) );

            Chicken chicken = (Chicken) player.getWorld().spawnEntity( player.getLocation(), EntityType.CHICKEN );
            chicken.setTarget( player );
            chicken.setCustomName( playerInteractEvent.getItem().getItemMeta().getDisplayName() );
            chicken.setMetadata( "level", new FixedMetadataValue( EffectPlugin.getInstance(), level ) );

            playerInteractEvent.setCancelled( true );
        }
    }

}
