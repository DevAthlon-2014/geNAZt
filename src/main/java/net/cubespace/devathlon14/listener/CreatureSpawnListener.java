package net.cubespace.devathlon14.listener;

import org.bukkit.entity.Chicken;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by Fabian on 18.10.2014.
 */
public class CreatureSpawnListener implements Listener {

    @EventHandler
    public void onCreatureSpawn( CreatureSpawnEvent event ) {
        if ( !(event.getEntity() instanceof Chicken) ) {
            event.setCancelled( true );
        }
    }

}
