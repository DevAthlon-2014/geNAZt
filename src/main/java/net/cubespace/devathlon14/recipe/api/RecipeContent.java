package net.cubespace.devathlon14.recipe.api;

import org.bukkit.Material;

import java.lang.annotation.*;

/**
 * Created by Fabian on 18.10.2014.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
@Repeatable( RecipeContents.class )
public @interface RecipeContent {
    char value() default ' ';
    Material material() default Material.AIR;
    boolean canLevel() default false;
}
