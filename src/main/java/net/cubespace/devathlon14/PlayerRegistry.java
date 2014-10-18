package net.cubespace.devathlon14;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fabian on 18.10.2014.
 */
public class PlayerRegistry {

    private static Map<Player, Integer> playerLevel = new HashMap<>();

    /**
     * Create a new Entry for a Player.
     * @param player The player for which the new Entry should be for
     */
    public static void addPlayer( Player player ) {
        playerLevel.put( player, 0 );
    }

    /**
     * Get the current level the Player has
     * @param player The player for which we want to know the Level
     * @return The level as Integer. Maxmium is 25
     */
    public static int getLevel( Player player ) {
        return playerLevel.get( player );
    }

    /**
     * Set the Player level to the given Level. Any level over 25 will result in Level 25.
     * @param player
     * @param level
     */
    public static void setLevel( Player player, Integer level ) {
        if ( level > 25 ) level = 25;
        playerLevel.put( player, level );
    }

    /**
     * Increase the Level if its not 25
     * @param player
     */
    public static void increaseLevel( Player player ) {
        if ( playerLevel.get( player ) == 25 ) return;
        playerLevel.put( player, playerLevel.get( player ) + 1 );
    }

    /**
     * Reset the Players level to 0
     * @param player
     */
    public static void resetLevel( Player player ) {
        playerLevel.put( player, 0 );
    }

    /**
     * Remove the Level of a Player completly
     * @param player
     */
    public static void removePlayer( Player player ) {
        playerLevel.remove( player );
    }
}
