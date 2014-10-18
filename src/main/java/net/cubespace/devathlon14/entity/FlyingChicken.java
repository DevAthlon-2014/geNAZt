package net.cubespace.devathlon14.entity;

import net.cubespace.devathlon14.entity.api.Entity;
import org.bukkit.entity.Chicken;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Fabian on 18.10.2014.
 */
public class FlyingChicken extends Entity {

    @Override
    public void onDamage( EntityDamageByEntityEvent event ) {
        if ( event.getEntity() instanceof Chicken ) {
            event.getEntity().setPassenger( event.getDamager() );
            event.setCancelled( true );
        }
    }

}
