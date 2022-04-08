package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.api.*;
import fr.hyriode.cosmetics.particle.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 02/04/2022 at 11:58
 */
public class HyriCosmeticsManager implements IHyriCosmeticsManager {

    private final Map<String, HyriCosmetic> cosmetics;
    private final Map<UUID, Map<HyriCosmetic, Integer>> playerScheduler;

    public HyriCosmeticsManager(HyriCosmetics plugin) {
        this.cosmetics = new HashMap<>();
        this.playerScheduler = new HashMap<>();
        this.registerCosmetics(plugin);
    }

    public void handleLogin(Player player) {
        this.playerScheduler.put(player.getUniqueId(), new HashMap<>());
    }

    public void handleLogout(Player player) {
        this.clearCosmetics(player.getUniqueId());
        this.playerScheduler.remove(player.getUniqueId());
    }

    private void registerCosmetics(HyriCosmetics plugin) {
        this.registerCosmetic(new FireTornadoParticle(plugin), plugin);
        this.registerCosmetic(new WaterCircleParticle(plugin), plugin);
        this.registerCosmetic(new SpeedParticle(plugin), plugin);
        this.registerCosmetic(new FlyCloudParticle(plugin), plugin);
        this.registerCosmetic(new FlySuitParticle(plugin), plugin);

        HyriCosmetics.log("Registered " + this.cosmetics.size() + " cosmetics!");
    }

    @Override
    public void registerCosmetic(HyriCosmetic cosmetic, JavaPlugin plugin) {
        this.cosmetics.put(cosmetic.getId(), cosmetic);
        if (cosmetic.getClass().isAssignableFrom(Listener.class)) {
            plugin.getServer().getPluginManager().registerEvents((Listener) cosmetic, plugin);
        }
        HyriCosmetics.log("Registered " + cosmetic.getId().toLowerCase());
    }

    @Override
    public HyriCosmetic getCosmeticById(String id) {
        return cosmetics.values().stream().filter(hyriCosmetic -> hyriCosmetic.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    @Override
    public List<HyriCosmetic> getCosmeticsByRarity(HyriCosmeticRarity rarity) {
        return cosmetics.values().stream().filter(hyriCosmetic -> hyriCosmetic.getRarity() == rarity).collect(Collectors.toList());
    }

    @Override
    public List<HyriCosmetic> getCosmeticsByType(HyriCosmeticType type) {
        return cosmetics.values().stream().filter(hyriCosmetic -> hyriCosmetic.getType() == type).collect(Collectors.toList());
    }

    @Override
    public void clearCosmetic(UUID uuid, HyriCosmetic cosmetic) {
        if (this.playerScheduler.containsKey(uuid)) {
            for (Map.Entry<HyriCosmetic, Integer> cosm : playerScheduler.get(uuid).entrySet()) {
                if (cosm.getKey().equals(cosmetic)) {
                    if (cosm.getValue() != -1) {
                        Bukkit.getScheduler().cancelTask(cosm.getValue());
                        this.playerScheduler.get(uuid).clear();
                        Bukkit.getPlayer(uuid).setWalkSpeed(0.2F);
                        Bukkit.getPlayer(uuid).setAllowFlight(false);
                    }
                }

            }
        }
    }

    @Override
    public void clearCosmetics(UUID uuid) {
        if (this.playerScheduler.containsKey(uuid)) {
            for (Map.Entry<HyriCosmetic, Integer> cosm : playerScheduler.get(uuid).entrySet()) {
                if (cosm.getValue() != -1) {
                    Bukkit.getScheduler().cancelTask(cosm.getValue());
                    this.playerScheduler.get(uuid).clear();
                    Bukkit.getPlayer(uuid).setWalkSpeed(0.2F);
                    Bukkit.getPlayer(uuid).setAllowFlight(false);
                }

            }
        }
    }

    @Override
    public void clearCosmetics(UUID uuid, HyriCosmeticType type) {
        if (this.playerScheduler.containsKey(uuid)) {
            for (Map.Entry<HyriCosmetic, Integer> cosm : playerScheduler.get(uuid).entrySet()) {
                if (cosm.getKey().getType() == type) {
                    if (cosm.getValue() != -1) {
                        Bukkit.getScheduler().cancelTask(cosm.getValue());
                        this.playerScheduler.get(uuid).clear();
                        Bukkit.getPlayer(uuid).setWalkSpeed(0.2F);
                        Bukkit.getPlayer(uuid).setAllowFlight(false);
                    }
                }

            }
        }
    }

    /**
     * To call the cosmetic runnable runnable
     *
     * @param player            Cosmetic's owner
     * @param plugin            Plugin class
     * @param scheduledCosmetic The runnable to call
     */
    @Override
    public void playScheduledCosmetic(Player player, JavaPlugin plugin, HyriScheduledCosmetic scheduledCosmetic, HyriCosmetic cosmetic) {
        AtomicInteger index = new AtomicInteger(0);
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (scheduledCosmetic.tick(player) || index.get() == scheduledCosmetic.duration()) cancel();
                index.getAndIncrement();
            }
        };
        runnable.runTaskTimer(plugin, 0, scheduledCosmetic.tickDuration());
        this.playerScheduler.get(player.getUniqueId()).put(cosmetic, runnable.getTaskId());
    }
}
