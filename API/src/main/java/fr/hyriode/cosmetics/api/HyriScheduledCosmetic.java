package fr.hyriode.cosmetics.api;

import org.bukkit.entity.Player;

public interface HyriScheduledCosmetic {

    /**
     * Effect to display at each tick
     * @param player Cosmetic's owner
     * @return true/false
     */
    boolean tick(Player player);

    /**
     * The duration between each tick
     * @return duration
     */
    int tickDuration();

    /**
     * The total duration
     * @return max duration
     */
    int duration();

}