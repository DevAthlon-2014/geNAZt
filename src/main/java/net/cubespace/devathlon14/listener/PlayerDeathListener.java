package net.cubespace.devathlon14.listener;

import com.comphenix.packetwrapper.WrapperPlayClientClientCommand;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.cubespace.devathlon14.EffectPlugin;
import net.cubespace.devathlon14.PlayerRegistry;
import net.cubespace.devathlon14.recipe.FlyingEntity;
import net.cubespace.devathlon14.util.ItemstackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Fabian on 18.10.2014.
 */
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeathListener( PlayerDeathEvent event ) {
        // Instant respawn
        Bukkit.getScheduler().scheduleSyncDelayedTask( EffectPlugin.getInstance(), () -> {
            WrapperPlayClientClientCommand wrapperPlayClientClientCommand = new WrapperPlayClientClientCommand();
            wrapperPlayClientClientCommand.setCommand( EnumWrappers.ClientCommand.PERFORM_RESPAWN );
            wrapperPlayClientClientCommand.recievePacket( event.getEntity() );
        }, 10 );
    }

    @EventHandler
    public void onPlayerRespawnListener( PlayerRespawnEvent event ) {
        // Give the Player its items back
        event.getPlayer().getInventory().clear();

        int playerLevel = PlayerRegistry.getLevel( event.getPlayer() );
        if ( playerLevel > 0 ) {
            event.getPlayer().getInventory().addItem( ItemstackUtils.renameItem( new ItemStack( Material.EGG, 1, (short) playerLevel ), FlyingEntity.getItemName().replace( "{level}", String.valueOf( playerLevel ) ) ) );
        } else {
            event.getPlayer().getInventory().addItem( new ItemStack( Material.EGG ) );
        }

        event.getPlayer().getInventory().addItem( new ItemStack( Material.FEATHER, 64 ) );

        // Tp to World Spawn
        event.getPlayer().teleport( event.getPlayer().getWorld().getSpawnLocation() );
    }

}
