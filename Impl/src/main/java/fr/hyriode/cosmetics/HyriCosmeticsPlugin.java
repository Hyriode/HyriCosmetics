package fr.hyriode.cosmetics;

import org.bukkit.plugin.java.JavaPlugin;

public class HyriCosmeticsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new HyriCosmeticsImpl(this);
    }

}
