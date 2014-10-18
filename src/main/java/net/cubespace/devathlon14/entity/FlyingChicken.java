package net.cubespace.devathlon14.entity;

import net.cubespace.devathlon14.EffectPlugin;
import net.cubespace.devathlon14.PlayerRegistry;
import net.cubespace.devathlon14.entity.api.Entity;
import net.cubespace.devathlon14.util.Effects;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scoreboard.Score;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Fabian on 18.10.2014.
 */
public class FlyingChicken extends Entity {

    private static final BlockFace[] faces = new BlockFace[]{BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.EAST, BlockFace.UP, BlockFace.DOWN};
    private static final Map<org.bukkit.entity.Entity, Integer> entities = new ConcurrentHashMap<>();

    public FlyingChicken() {
        Bukkit.getScheduler().runTaskTimer( EffectPlugin.getInstance(), () -> {
            entities
                    .forEach( (entity, flytime) -> {
                        // Is the entity dead or has no passenger ?
                        if ( entity.isDead() || !entity.isValid() || entity.getPassenger() == null ) {
                            entities.remove( entity );
                            return;
                        }

                        // Get the entites Level
                        Integer level = entity.getMetadata( "level" ).get( 0 ).asInt();

                        if ( flytime > 40 ) {
                            // Check if the Chicken is flying into a block
                            for ( BlockFace blockFace : faces ) {
                                if ( entity.getLocation().getBlock().getRelative( blockFace ).getType().isSolid() ) {
                                    // Le explode
                                    entity.getWorld().createExplosion( entity.getLocation(), 5f );
                                    entities.remove( entity );

                                    // Le scoreboard
                                    Score score =  EffectPlugin.getInstance().getScoreboard().getObjective( "chicken" ).getScore( ((Player) entity.getPassenger()).getName() );
                                    score.setScore( score.getScore() - level );

                                    return;
                                }
                            }

                            // Explode when two Chicken collide
                            entity
                                    .getNearbyEntities( 2, 2, 2 )
                                    .forEach( aroundEntity -> {
                                        if ( aroundEntity instanceof Chicken && aroundEntity.getPassenger() != null ) {
                                            // Le explode
                                            entity.getWorld().createExplosion( entity.getLocation(), 5f );
                                            entities.remove( entity );
                                            entities.remove( aroundEntity );

                                            // Le scoreboard
                                            Score score =  EffectPlugin.getInstance().getScoreboard().getObjective( "chicken" ).getScore( ((Player) entity.getPassenger()).getName() );
                                            score.setScore( score.getScore() + level );

                                            if ( aroundEntity.hasMetadata( "level" ) ) {
                                                Integer anotherLevel = aroundEntity.getMetadata( "level" ).get( 0 ).asInt();

                                                score = EffectPlugin.getInstance().getScoreboard().getObjective( "chicken" ).getScore( ((Player) aroundEntity.getPassenger()).getName() );
                                                score.setScore( score.getScore() + anotherLevel );
                                            }
                                        }
                                    } );
                        }

                        // Let the Entity fly based on its level
                        entity.setVelocity( entity.getPassenger().getLocation().getDirection().normalize().multiply( level * 0.18 ) );
                        Effects.SPELL.broadcastEffect( Arrays.asList( Bukkit.getOnlinePlayers() ), entity.getLocation(), level, new Vector( 0,0,0 ) );

                        // Put the flytime back in
                        entities.put( entity, flytime++ );
                    } );
        }, 1, 1 );
    }

    @Override
    public void onDamage( EntityDamageByEntityEvent event ) {
        if ( event.getEntity() instanceof Chicken && event.getEntity().hasMetadata( "level" ) && event.getDamager() instanceof Player ) {
            event.getEntity().setPassenger( event.getDamager() );
            entities.put( event.getEntity(), 0 );

            PlayerRegistry.setLevel( (Player) event.getDamager(), event.getEntity().getMetadata( "level" ).get( 0 ).asInt() );

            event.setCancelled( true );
        }
    }

}
