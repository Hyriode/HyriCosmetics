package fr.hyriode.cosmetics.api.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 05/04/2022 at 18:48
 */
public class HyriMoveHandler extends HyriCosmeticHandler {

    private Consumer<Player> moveCallback;
    private Consumer<Player> stayCallback;

    public HyriMoveHandler(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(event.getFrom() != event.getTo()) {
            this.moveCallback.accept(event.getPlayer());
        } else {
            this.stayCallback.accept(event.getPlayer());
        }
    }

    public void onMove(Consumer<Player> moveCallback) {
        this.moveCallback = moveCallback;
    }

    public void onStay(Consumer<Player> stayCallback) {
        this.stayCallback = stayCallback;
    }
}
