package net.cubespace.devathlon14.listener;

import net.cubespace.devathlon14.recipe.api.RecipeRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Fabian on 18.10.2014.
 */
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent event ) {
        if ( event.hasItem() ) {
            RecipeRegistry
                    .getRecipes()
                    .forEach( recipe -> recipe.onInteract( event ) );
        }
    }

}
