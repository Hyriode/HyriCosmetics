package fr.hyriode.cosmetics.listeners;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.api.HyriCosmeticsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
* Project: HyriCosmetics
* Created by Akkashi
* on 03/04/2022 at 14:49
*/
public class PlayerHandler implements Listener {

    private final HyriCosmetics plugin;

    public PlayerHandler(HyriCosmetics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        this.plugin.getAPI().getCosmeticsManager().handleLogin(player);
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        this.plugin.getAPI().getCosmeticsManager().handleLogout(player);
    }

}
