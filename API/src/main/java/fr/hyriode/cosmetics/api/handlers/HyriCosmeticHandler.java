package fr.hyriode.cosmetics.api.handlers;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 05/04/2022 at 18:31
 */
public abstract class HyriCosmeticHandler implements Listener {

    protected boolean enabled;

    protected final JavaPlugin plugin;

    public HyriCosmeticHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void enable() {}

    public void disable() {}

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled && !enabled) {
            HandlerList.unregisterAll(this);

            this.disable();

            this.enabled = false;
        } else if (!this.enabled && enabled) {
            this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

            this.enable();

            this.enabled = true;
        }
    }

}
