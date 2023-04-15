package fr.hyriode.cosmetics;

import org.bukkit.plugin.java.JavaPlugin;

public class HyriCosmeticsPlugin extends JavaPlugin {

    private HyriCosmeticsImpl hyriCosmetics;

    @Override
    public void onEnable() {
        this.hyriCosmetics = new HyriCosmeticsImpl(this);
    }

    @Override
    public void onDisable() {
        hyriCosmetics.getTaskProvider().shutdown();
    }

}
