package net.cubespace.devathlon14.recipe.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Fabian on 18.10.2014.
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.TYPE )
public @interface Levelable {

    boolean value() default false;
    int maxLevel() default 0;

}
