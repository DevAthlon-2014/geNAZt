package net.cubespace.devathlon14.entity;

import net.cubespace.devathlon14.EffectPlugin;
import net.cubespace.devathlon14.entity.api.Entity;
import net.cubespace.devathlon14.util.Effects;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;

/**
 * Created by Fabian on 18.10.2014.
 */
public class FlyingChicken extends Entity {

    private static final BlockFace[] faces = new BlockFace[]{BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.EAST, BlockFace.UP, BlockFace.DOWN};

    @Override
    public void onDamage( EntityDamageByEntityEvent event ) {
        if ( event.getEntity() instanceof Chicken && event.getEntity().hasMetadata( "level" ) ) {
            event.getEntity().setPassenger( event.getDamager() );
            event.setCancelled( true );

            new BukkitRunnable() {

                @Override
                public void run() {
                    if ( event.getEntity().isDead() || !event.getEntity().isValid() || event.getEntity().getPassenger() == null ) {
                        cancel();
                    }

                    for ( BlockFace blockFace : faces ) {
                        if ( event.getEntity().getLocation().getBlock().getRelative( blockFace ).getType().isSolid() ) {
                            // Le explode
                            event.getEntity().getWorld().createExplosion( event.getEntity().getLocation(), 2f );
                            cancel();
                        }
                    }

                    Integer level = event.getEntity().getMetadata( "level" ).get( 0 ).asInt();
                    event.getEntity().setVelocity( event.getDamager().getLocation().getDirection().normalize().multiply( level * 0.18 ) );
                    Effects.SPELL.broadcastEffect( Arrays.asList( Bukkit.getOnlinePlayers() ), event.getEntity().getLocation(), level, new Vector( 0,0,0 ) );
                }

            }.runTaskTimer( EffectPlugin.getInstance(), 1, 1 );
        }
    }

}
