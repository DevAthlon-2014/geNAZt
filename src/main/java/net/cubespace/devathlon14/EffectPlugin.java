package net.cubespace.devathlon14;

import lombok.Getter;
import net.cubespace.devathlon14.recipe.FlyingEntity;
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
    }

    private void registerRecipes() {
        new FlyingEntity().register();
    }

}
