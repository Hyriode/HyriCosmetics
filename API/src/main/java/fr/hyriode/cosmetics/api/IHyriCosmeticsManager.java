package fr.hyriode.cosmetics.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 02/04/2022 at 13:25
 */
public interface IHyriCosmeticsManager {

    void registerCosmetic(HyriCosmetic cosmetic, JavaPlugin plugin);

    HyriCosmetic getCosmeticById(String id);

    List<HyriCosmetic> getCosmeticsByRarity(HyriCosmeticRarity rarity);

    List<HyriCosmetic> getCosmeticsByType(HyriCosmeticType type);
    
    void clearCosmetic(UUID uuid, HyriCosmetic cosmetic);
    
    void clearCosmetics(UUID uuid);
    
    void clearCosmetics(UUID uuid, HyriCosmeticType type);

    void playScheduledCosmetic(Player player, JavaPlugin plugin, HyriScheduledCosmetic scheduledCosmetic, HyriCosmetic cosmetic);
    
}
