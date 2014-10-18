package net.cubespace.devathlon14.util;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Created by Fabian on 18.10.2014.
 */
public enum Effects {

    MOBSPELL( WrapperPlayServerWorldParticles.ParticleEffect.MOB_SPELL.getParticleName() ),
    SMOKE( "smoke" ),
    SPELL( "spell" );

    private String effect;

    Effects( String effect ) {
        this.effect = effect;
    }

    /**
     * Construct the correct Wrapper to play an Effect in the Client
     * @param location          The location where the Effect should be played
     * @param numberOfParticles The number of Particles which should be displayed on that spot
     * @param offset            The offset given in Blocks
     * @return The preconstructed Packet ready to be sent
     */
    private WrapperPlayServerWorldParticles getPacket( Location location, int numberOfParticles, Vector offset ) {
        WrapperPlayServerWorldParticles particles = new WrapperPlayServerWorldParticles();
        particles.setParticleName( effect );
        particles.setNumberOfParticles( numberOfParticles );
        particles.setLocation( location );
        particles.setOffset( offset );
        particles.setParticleSpeed( 0.1f );

        return particles;
    }

    /**
     * Broadcast the given Effect to the Players which are given in the Player list
     * @param playerList        The list of Players which are meant to get this Effect
     * @param location          The location in the World which should be used to play the Effect
     * @param numberOfParticles The number of Particles which should be displayed on that spot
     * @param offset            The offset given in Blocks
     */
    public void broadcastEffect( List<Player> playerList, Location location, int numberOfParticles, Vector offset ) {
        WrapperPlayServerWorldParticles particles = getPacket( location, numberOfParticles, offset );
        playerList.forEach( particles::sendPacket );
    }

}
