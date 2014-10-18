package net.cubespace.devathlon14.listener;

import net.cubespace.devathlon14.entity.api.EntityRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Fabian on 18.10.2014.
 */
public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity( EntityDamageByEntityEvent event ) {
        EntityRegistry
                .getEntities()
                .forEach( entity -> entity.onDamage( event ) );
    }

}
