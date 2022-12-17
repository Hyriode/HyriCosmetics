package fr.hyriode.cosmetics.listener;

import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.impl.ExempleCosmetic;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class ConnectionListener implements Listener {

    /*tempo c'est pour un test*/public static HashMap<UUID, ExempleCosmetic> cosmeticHashMap = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(HyriCosmeticsPlugin.getPlugin(HyriCosmeticsPlugin.class), () ->
                cosmeticHashMap.put(event.getPlayer().getUniqueId(), new ExempleCosmetic(event.getPlayer())), 5L);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        cosmeticHashMap.get(event.getPlayer().getUniqueId()).delete();
        cosmeticHashMap.remove(event.getPlayer().getUniqueId());
    }

}