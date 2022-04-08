package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.listeners.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 01/04/2022 at 18:34
 */
public class HyriCosmetics extends JavaPlugin {

    public static final String NAME = "HyriCosmetics";

    private final HyriCosmeticsImpl api;

    public HyriCosmetics() {
        this.api = new HyriCosmeticsImpl(this);
    }

    @Override
    public void onEnable() {

        log("        _    _            _  _____                         _   _          ");
        log(" | |  | |          (_)/ ____|                       | | (_)");
        log("                | |__| |_   _ _ __ _| |     ___  ___ _ __ ___   ___| |_ _  ___ ___");
        log(" |  __  | | | | '__| | |    / _ \\/ __| '_ ` _ \\ / _ \\ __| |/ __/ __|");
        log(" | |  | | |_| | |  | | |___| (_) \\__ \\ | | | | |  __/ |_| | (__\\__ \\");
        log(" |_|  |_|\\__, |_|  |_|\\_____\\___/|___/_| |_| |_|\\___|\\__|_|\\___|___/");
        log("                __/ |");
        log("         |___/");

        log("Starting " + NAME + "...");
        getCommand("test").setExecutor(new TestCommand());
        this.getServer().getPluginManager().registerEvents(new PlayerHandler(this), this);

    }

    @Override
    public void onDisable() {


    }

    public static void log(String msg) {
        log(Level.INFO, msg);
    }

    public static void log(Level level, String message) {
        String prefix = ChatColor.LIGHT_PURPLE + "[" + NAME + "] ";

        if (level == Level.SEVERE) {
            prefix += ChatColor.RED;
        } else if (level == Level.WARNING) {
            prefix += ChatColor.YELLOW;
        } else {
            prefix += ChatColor.RESET;
        }

        Bukkit.getConsoleSender().sendMessage(prefix + message);
    }

    public HyriCosmeticsImpl getAPI() {
        return this.api;
    }

}
