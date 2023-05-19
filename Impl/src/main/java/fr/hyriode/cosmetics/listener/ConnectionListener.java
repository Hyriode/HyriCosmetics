package fr.hyriode.cosmetics.listener;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.hyrame.listener.HyriListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener extends HyriListener<HyriCosmeticsPlugin> {

    public ConnectionListener(HyriCosmeticsPlugin plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        HyriCosmetics.get().getUserProvider().createUser(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        HyriCosmetics.get().getUserProvider().deleteUser(event.getPlayer());
    }

}