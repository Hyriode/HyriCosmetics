package fr.hyriode.cosmetics.user;

import fr.hyriode.hyrame.game.HyriGame;
import fr.hyriode.hyrame.game.HyriGamePlayer;
import org.bukkit.entity.Player;

public class CosmeticPlayer extends HyriGamePlayer {
    /**
     * Constructor of {@link Player}
     *
     * @param game   The running game
     * @param player Spigot player
     */
    public CosmeticPlayer(HyriGame<?> game, Player player) {
        super(game, player);
    }
}
