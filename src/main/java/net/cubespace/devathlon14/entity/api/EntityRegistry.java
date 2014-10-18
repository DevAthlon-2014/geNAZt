package net.cubespace.devathlon14.entity.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 18.10.2014.
 */
public class EntityRegistry {

    private static List<Entity> entities = new ArrayList<>();

    /**
     * Add this custom Entity to the Registry
     * @param entity    The Entity which needs to be registered
     */
    public static void addEntity( Entity entity ) {
        entities.add( entity );
    }

    /**
     * Get a copy of the Entity list
     * @return Immutable list of all registered Entities
     */
    public static List<Entity> getEntities() {
        return new ArrayList<>( entities );
    }

}
