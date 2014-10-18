package net.cubespace.devathlon14;

import lombok.Getter;
import net.cubespace.devathlon14.entity.FlyingChicken;
import net.cubespace.devathlon14.entity.api.EntityRegistry;
import net.cubespace.devathlon14.listener.*;
import net.cubespace.devathlon14.recipe.FlyingEntity;
import net.cubespace.devathlon14.recipe.api.RecipeRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Arrays;

/**
 * Created by Fabian on 18.10.2014.
 */
public class EffectPlugin extends JavaPlugin {

    @Getter private static EffectPlugin instance;
    @Getter private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        instance = this;

        // New scoreboard
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective( "chicken", "dummy" );
        objective.setDisplayName( "Chicken Explode" );
        objective.setDisplaySlot( DisplaySlot.SIDEBAR );

        // Register all given Recipes, Entities and Listener
        registerEntities();
        registerRecipes();
        registerListener();

        // Set the needed Crafting Tables
        World world = Bukkit.getWorld( "world" );
        for ( int i = 0; i < 10; i++ ) {
            world.getSpawnLocation().clone().add( i, 3, i ).getBlock().setType( Material.WORKBENCH );
        }

        // Remove all unwanted entities
        for ( Entity entity : world.getEntities() ) {
            entity.remove();
        }

        // Let the Plugin disable after 10 Minutes
        Bukkit.getScheduler().scheduleSyncDelayedTask( this, () -> {
            // Remove all Entities
            for ( Entity entity : Bukkit.getWorld( "world" ).getEntities() ) {
                entity.remove();
            }

            // Get the player with the most points
            Arrays
                    .asList( Bukkit.getOnlinePlayers() )
                    .forEach( player -> player.sendMessage( "Das Spiel ist vorbei. Bitte entnehme den Gewinner aus dem Scoreboard." ) );

            // Shutdown after 30 Seconds
            Bukkit.getScheduler().scheduleSyncDelayedTask( this, Bukkit::shutdown, 30 * 20 );
        }, 10 * 60 * 20 );
    }

    private void registerRecipes() {
        RecipeRegistry.addRecipe( new FlyingEntity() );
    }

    private void registerEntities() {
        EntityRegistry.addEntity( new FlyingChicken() );
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents( new PlayerInteractListener(), this );
        Bukkit.getPluginManager().registerEvents( new EntityDamageByEntityListener(), this );
        Bukkit.getPluginManager().registerEvents( new PlayerJoinListener(), this );
        Bukkit.getPluginManager().registerEvents( new PlayerDeathListener(), this );
        Bukkit.getPluginManager().registerEvents( new CreatureSpawnListener(), this );
        Bukkit.getPluginManager().registerEvents( new PlayerQuitListener(), this );
    }

}
