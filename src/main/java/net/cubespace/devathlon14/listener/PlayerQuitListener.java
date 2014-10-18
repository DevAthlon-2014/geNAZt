package net.cubespace.devathlon14.listener;

import net.cubespace.devathlon14.PlayerRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Fabian on 18.10.2014.
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit( PlayerQuitEvent event ) {
        PlayerRegistry.removePlayer( event.getPlayer() );
    }

}
