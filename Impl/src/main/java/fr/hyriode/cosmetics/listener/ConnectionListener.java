package fr.hyriode.cosmetics.listener;

import fr.hyriode.api.HyriAPI;
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
        HyriAPI.get().getPlayerManager().getPlayer(event.getPlayer().getUniqueId()).getHyris().add(100000).exec();
        HyriAPI.get().getPlayerManager().getPlayer(event.getPlayer().getUniqueId()).getHyodes().add(100000).exec();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        HyriCosmetics.get().getUserProvider().deleteUser(event.getPlayer());
    }

}