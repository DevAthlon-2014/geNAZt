package net.cubespace.devathlon14.listener;

import net.cubespace.devathlon14.EffectPlugin;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Fabian on 18.10.2014.
 */
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin( PlayerJoinEvent event ) {
        // Set to survival
        event.getPlayer().setGameMode( GameMode.SURVIVAL );

        // Give items
        event.getPlayer().getInventory().clear();
        event.getPlayer().getInventory().addItem( new ItemStack( Material.EGG ) );
        event.getPlayer().getInventory().addItem( new ItemStack( Material.FEATHER, 64 ) );

        // Add to Scoreboard
        event.getPlayer().setScoreboard( EffectPlugin.getInstance().getScoreboard() );
    }

}
