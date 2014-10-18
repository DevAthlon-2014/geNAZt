package net.cubespace.devathlon14;

import lombok.Getter;
import net.cubespace.devathlon14.listener.PlayerInteractListener;
import net.cubespace.devathlon14.recipe.FlyingEntity;
import net.cubespace.devathlon14.recipe.api.RecipeRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Fabian on 18.10.2014.
 */
public class EffectPlugin extends JavaPlugin {

    @Getter private static EffectPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        // Register all given Recipes
        registerRecipes();

        // Register all Listeners
        registerListener();
    }

    private void registerRecipes() {
        RecipeRegistry.addRecipe( new FlyingEntity() );
    }

    private void registerListener() {
        Bukkit.getPluginManager().registerEvents( new PlayerInteractListener(), this );
    }

}
