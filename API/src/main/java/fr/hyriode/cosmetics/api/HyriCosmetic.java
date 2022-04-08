package fr.hyriode.cosmetics.api;

import fr.hyriode.cosmetics.api.handlers.HyriCosmeticHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 01/04/2022 at 21:01
 */
public abstract class HyriCosmetic {

    private final Map<Class<? extends HyriCosmeticHandler>, HyriCosmeticHandler> handlers;

    private final String id;
    private final HyriCosmeticType type;
    private final HyriCosmeticRarity rarity;
    private final int hyodePrice;
    private final int hyrisPrice;
    private final Material icon;

    public HyriCosmetic(String id,
                        HyriCosmeticType type,
                        HyriCosmeticRarity rarity,
                        int hyodePrice,
                        int hyrisPrice,
                        Material icon) {
        this.id = id;
        this.type = type;
        this.rarity = rarity;
        this.hyodePrice = hyodePrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.handlers = new HashMap<>();
    }

    /**
     * Called when the player clicks on his inventory to enable the cosmetic
     *
     * @param player Cosmetic's owner
     */
    public abstract void enable(Player player);

    /**
     * Effect to display
     *
     * @param player Cosmetic's owner
     */
    public abstract void play(Player player);

    /**
     * To use a runnable in the cosmetic
     *
     * @return new ScheduledCosmetic
     */
    public abstract HyriScheduledCosmetic getScheduledCosmetic();

    public String getId() {
        return this.id;
    }

    public HyriCosmeticType getType() {
        return this.type;
    }

    public HyriCosmeticRarity getRarity() {
        return this.rarity;
    }

    public int getHyodePrice() {
        return this.hyodePrice;
    }

    public int getHyrisPrice() {
        return this.hyrisPrice;
    }

    public Material getIcon() {
        return this.icon;
    }

    protected <T extends HyriCosmeticHandler> T enableHandler(T handler) {
        this.handlers.put(handler.getClass(), handler);

        handler.setEnabled(true);

        return handler;
    }

    protected void disableHandler(Class<? extends HyriCosmeticHandler> clazz) {
        final HyriCosmeticHandler handler = this.handlers.get(clazz);

        if (handler != null) {
            handler.setEnabled(false);
        }
    }
}
