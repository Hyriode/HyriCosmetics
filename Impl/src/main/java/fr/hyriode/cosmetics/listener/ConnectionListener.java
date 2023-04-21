package fr.hyriode.cosmetics.listener;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.api.player.IHyriPlayerSession;
import fr.hyriode.cosmetics.HyriCosmetics;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        HyriCosmetics.get().getUserProvider().createUser(event.getPlayer());
        final IHyriPlayer hyriPlayer = HyriAPI.get().getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
        hyriPlayer.getHyodes().add(10000).exec();
        hyriPlayer.getHyris().add(10000).exec();
        hyriPlayer.update();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        HyriCosmetics.get().getUserProvider().deleteUser(event.getPlayer());
    }

}