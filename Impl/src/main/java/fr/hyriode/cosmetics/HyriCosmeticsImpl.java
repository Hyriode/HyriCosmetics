package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.listener.ConnectionListener;
import fr.hyriode.cosmetics.listener.EntityListener;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;
import fr.hyriode.cosmetics.user.CosmeticUserProviderImpl;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.task.TaskProviderImpl;
import fr.hyriode.hyrame.HyrameLoader;
import fr.hyriode.hyrame.IHyrame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class HyriCosmeticsImpl extends HyriCosmetics {

    private final JavaPlugin plugin;
    private final IHyrame hyrame;

    private final TaskProvider taskProvider;
    private final CosmeticUserProvider userProvider;

    private final Map<CosmeticCategory, List<AbstractCosmetic>> cosmetics;

    public HyriCosmeticsImpl(JavaPlugin plugin) {
        this.plugin = plugin;
        this.hyrame = HyrameLoader.load(new HyriCosmeticsProvider(plugin));

        this.taskProvider = new TaskProviderImpl();
        this.userProvider = new CosmeticUserProviderImpl();

        this.cosmetics = new HashMap<>();

        for (CosmeticCategory category : CosmeticCategory.Default.values()) {
            this.registerCategory(category);
        }

        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityListener(), plugin);
    }

    @Override
    public TaskProvider getTaskProvider() {
        return this.taskProvider;
    }

    @Override
    public void registerCategory(final CosmeticCategory category, final AbstractCosmetic... cosmetics) {
        this.cosmetics.put(category, new ArrayList<>(Arrays.asList(cosmetics)));
    }

    @Override
    public void registerCosmetic(final AbstractCosmetic cosmetic) {
        this.cosmetics.get(cosmetic.getCategory()).add(cosmetic);
    }

    @Override
    public void registerCosmetic(final AbstractCosmetic... cosmetic) {
        for (AbstractCosmetic abstractCosmetic : cosmetic) {
            this.registerCosmetic(abstractCosmetic);
        }
    }

    @Override
    public CosmeticUserProvider getUserManager() {
        return this.userProvider;
    }

    @Override
    public List<CosmeticCategory> getCategories() {
        return new ArrayList<>(cosmetics.keySet());
    }
}
