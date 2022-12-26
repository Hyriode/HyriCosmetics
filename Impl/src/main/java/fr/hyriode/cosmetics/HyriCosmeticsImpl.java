package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticManager;
import fr.hyriode.cosmetics.gadget.GadgetManager;
import fr.hyriode.cosmetics.listener.ConnectionListener;
import fr.hyriode.cosmetics.listener.EntityListener;
import fr.hyriode.cosmetics.particle.ParticleManager;
import fr.hyriode.cosmetics.particle.ParticleManagerImpl;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.task.TaskProviderImpl;
import fr.hyriode.hyrame.HyrameLoader;
import fr.hyriode.hyrame.IHyrame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class HyriCosmeticsImpl extends HyriCosmetics {

    private final JavaPlugin plugin;
    private final IHyrame hyrame;

    private final TaskProvider taskProvider;

    private final Map<Class<? extends CosmeticManager>, CosmeticCategory> categories;

    public HyriCosmeticsImpl(JavaPlugin plugin) {
        this.plugin = plugin;
        this.hyrame = HyrameLoader.load(new HyriCosmeticsProvider(plugin));

        this.taskProvider = new TaskProviderImpl();

        this.categories = new HashMap<>();
        for (CosmeticCategory category : CosmeticCategory.Default.values()) {
            switch (category.getManager()) {
                case ParticleManager.class: {
                    this.categories.put(category.getManager(), new ParticleManagerImpl());
                }
            }
        }

        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityListener(), plugin);
    }

    @Override
    public TaskProvider getTaskProvider() {
        return this.taskProvider;
    }

    @Override
    public void registerCategory(final CosmeticCategory category, final Class<? extends CosmeticManager> manager) {
        this.categories.put(manager, category);
    }

    @Override
    <T extends CosmeticManager> T getManager(final Class<? extends CosmeticManager> clazz) {
        if (this.categories.containsKey(clazz)) {
            return (T) this.categories.get(clazz);
        }
        throw new IllegalArgumentException("No manager found for " + clazz.getName());
    }
}
