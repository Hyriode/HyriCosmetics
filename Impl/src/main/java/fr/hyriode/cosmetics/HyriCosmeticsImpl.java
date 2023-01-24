package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticManager;
import fr.hyriode.cosmetics.listener.ConnectionListener;
import fr.hyriode.cosmetics.listener.EntityListener;
import fr.hyriode.cosmetics.particle.effect.EnchantedParticle;
import fr.hyriode.cosmetics.particle.effect.FireTornadoParticle;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.task.TaskProviderImpl;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;
import fr.hyriode.cosmetics.user.CosmeticUserProviderImpl;
import fr.hyriode.hyrame.HyrameLoader;
import fr.hyriode.hyrame.IHyrame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HyriCosmeticsImpl extends HyriCosmetics {

    private final JavaPlugin plugin;
    private final IHyrame hyrame;

    private final TaskProvider taskProvider;
    private final CosmeticUserProvider userProvider;

    private final Map<CosmeticCategory, List<AbstractCosmetic>> cosmetics;
    private final Map<CosmeticCategory, CosmeticManager<?>> comseticManagers;

    public HyriCosmeticsImpl(JavaPlugin plugin) {
        this.plugin = plugin;
        this.hyrame = HyrameLoader.load(new HyriCosmeticsProvider(plugin));

        this.taskProvider = new TaskProviderImpl();
        this.userProvider = new CosmeticUserProviderImpl();

        this.cosmetics = new HashMap<>();
        this.comseticManagers = new HashMap<>();

        for (CosmeticCategory category : CosmeticCategory.Default.values()) {
            this.registerCategory(category);
        }

        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new EntityListener(), plugin);

        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
        this.registerCosmetic(new FireTornadoParticle());
        this.registerCosmetic(new EnchantedParticle());
    }

    @Override
    public TaskProvider getTaskProvider() {
        return this.taskProvider;
    }

    @Override
    public void registerCategory(final CosmeticCategory category) {
        this.cosmetics.put(category, new ArrayList<>());
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

    @Override
    public CosmeticCategory getCategory(String name) {
        for (CosmeticCategory category : this.getCategories()) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }

        return null;
    }

    @Override
    public Map<CosmeticCategory, List<AbstractCosmetic>> getCosmetics() {
        return cosmetics;
    }

    @Override
    public AbstractCosmetic getCosmetic(final String name) {
        for (CosmeticCategory category : this.cosmetics.keySet()) {
            for (AbstractCosmetic cosmetic : this.cosmetics.get(category)) {
                if (cosmetic.getId().equalsIgnoreCase(name)) {
                    return cosmetic;
                }
            }
        }
        return null;
    }

    @Override
    public AbstractCosmetic getCosmetic(final String name, final CosmeticCategory category) {
        for (AbstractCosmetic cosmetic : this.cosmetics.get(category)) {
            if (cosmetic.getId().equalsIgnoreCase(name)) {
                return cosmetic;
            }
        }
        return null;
    }

}
