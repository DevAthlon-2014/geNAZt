package net.cubespace.devathlon14.entity.api;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Fabian on 18.10.2014.
 */
public abstract class Entity {

    /**
     * When an Entity gets damaged this will be exeuted
     * @param event The EntityDamageByEntityEvent which has been fired from Bukkit
     */
    public void onDamage( EntityDamageByEntityEvent event ) {

    }

}
