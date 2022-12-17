package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.listener.ConnectionListener;
import fr.hyriode.cosmetics.listener.EntityListener;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.task.TaskProviderImpl;
import fr.hyriode.hyrame.HyrameLoader;
import fr.hyriode.hyrame.IHyrame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HyriCosmeticsImpl extends HyriCosmetics {

    private final JavaPlugin plugin;
    private final IHyrame hyrame;

    private final TaskProvider taskProvider;

    public HyriCosmeticsImpl(JavaPlugin plugin) {
        this.plugin = plugin;
        this.hyrame = HyrameLoader.load(new HyriCosmeticsProvider(plugin));

        this.taskProvider = new TaskProviderImpl();
        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityListener(), plugin);
    }

    @Override
    public TaskProvider getTaskProvider() {
        return this.taskProvider;
    }
}
