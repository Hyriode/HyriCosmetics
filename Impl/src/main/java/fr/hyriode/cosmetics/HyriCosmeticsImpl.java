package fr.hyriode.cosmetics;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.server.ILobbyAPI;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.Filters.Owned;
import fr.hyriode.cosmetics.common.Filters.Price;
import fr.hyriode.cosmetics.common.Filters.Rarity;
import fr.hyriode.cosmetics.listener.AccountListener;
import fr.hyriode.cosmetics.listener.ConnectionListener;
import fr.hyriode.cosmetics.listener.PlayerListener;
import fr.hyriode.cosmetics.particle.effect.EnchantedParticle;
import fr.hyriode.cosmetics.particle.effect.FireInvocationParticle;
import fr.hyriode.cosmetics.particle.effect.RainbowTwinsParticle;
import fr.hyriode.cosmetics.particle.effect.StepInTheAirParticle;
import fr.hyriode.cosmetics.pet.pets.MiniMe;
import fr.hyriode.cosmetics.pet.pets.SnowManComplexPet;
import fr.hyriode.cosmetics.task.MainTask;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.task.TaskProviderImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;
import fr.hyriode.cosmetics.user.CosmeticUserProviderImpl;
import fr.hyriode.hyrame.HyrameLoader;
import fr.hyriode.hyrame.IHyrame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public class HyriCosmeticsImpl extends HyriCosmetics {
    
    private final JavaPlugin plugin;
    private final IHyrame hyrame;

    private final boolean lobbyServer;

    private final TaskProvider taskProvider;
    private final CosmeticUserProvider userProvider;

    private final Map<Cosmetic, Class<? extends AbstractCosmetic>> cosmeticClasses;

    public HyriCosmeticsImpl(JavaPlugin plugin) {
        this.plugin = plugin;
        this.hyrame = HyrameLoader.load(new HyriCosmeticsProvider(plugin));

        this.lobbyServer = HyriAPI.get().getServer().getType().equals(ILobbyAPI.TYPE) || HyriAPI.get().getConfig().isDevEnvironment();

        this.taskProvider = new TaskProviderImpl();
        this.userProvider = new CosmeticUserProviderImpl();

        this.cosmeticClasses = new HashMap<>();

        Bukkit.getScheduler().runTaskTimer(plugin, new MainTask(), 0, 1L);

        HyriAPI.get().getEventBus().register(new AccountListener(this));
        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListener(), plugin);
        if (this.lobbyServer) Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);

        this.registerCosmetics();
    }

    public void registerCosmetics() {
        // == Particles ==
        this.registerCosmetic(Cosmetic.ENCHANTED, EnchantedParticle.class);
        this.registerCosmetic(Cosmetic.FIRE_INVOCATION, FireInvocationParticle.class);
        this.registerCosmetic(Cosmetic.STEP_IN_THE_AIR, StepInTheAirParticle.class);
        this.registerCosmetic(Cosmetic.RAINBOW_TWINS, RainbowTwinsParticle.class);

        // == Pets ==
        this.registerCosmetic(Cosmetic.SNOWMAN, SnowManComplexPet.class);
        this.registerCosmetic(Cosmetic.MINI_ME, MiniMe.class);
    }

    @Override @SuppressWarnings("all")
    public <T extends AbstractCosmetic> T createCosmetic(Cosmetic cosmetic, CosmeticUser user) {
        try {
            return (T) this.getCosmeticClass(cosmetic).getConstructor(CosmeticUser.class).newInstance(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void registerCosmetic(Cosmetic cosmetic, Class<? extends AbstractCosmetic> cosmeticClass) {
        this.cosmeticClasses.put(cosmetic, cosmeticClass);
    }

    @Override
    public Class<? extends AbstractCosmetic> getCosmeticClass(Cosmetic cosmetic) {
        return this.cosmeticClasses.get(cosmetic);
    }

    @Override
    public TaskProvider getTaskProvider() {
        return this.taskProvider;
    }

    @Override
    public CosmeticUserProvider getUserProvider() {
        return this.userProvider;
    }

    @Override
    public List<CosmeticCategory> getCategories() {
        return Cosmetic.getCategories();
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
    public Map<CosmeticCategory, List<Cosmetic>> getCosmetics() {
        return Cosmetic.getCosmetics();
    }

    @Override
    public List<Cosmetic> getFilteredCosmetics(CosmeticUser user, CosmeticCategory category) {
        final Owned owned = user.getData().getFilters().getOwned();
        final Rarity rarity = user.getData().getFilters().getRarity();
        final Price price = user.getData().getFilters().getPrice();

        List<Cosmetic> cosmetics = new ArrayList<>(this.getCosmetics().get(category));

        if (owned != Owned.ALL) {
            if (owned == Owned.YES) {
                cosmetics = cosmetics.stream().filter(cosmetic -> user.hasUnlockedCosmetic(cosmetic)).collect(Collectors.toList());
            } else if (owned == Owned.NO) {
                cosmetics = cosmetics.stream().filter(cosmetic -> !user.hasUnlockedCosmetic(cosmetic)).collect(Collectors.toList());
            }
        }

        if (rarity != Rarity.NO) {
            Comparator<Cosmetic> rarityComparator = Comparator.comparing(Cosmetic::getRarity);
            if (rarity == Rarity.ASCENDING) {
                cosmetics.sort(rarityComparator);
            } else if (rarity == Rarity.DESCENDING) {
                cosmetics.sort(rarityComparator.reversed());
            }
        }

        if (price != Price.NO) {
            Comparator<Cosmetic> priceComparator = Comparator.comparing(Cosmetic::getHyrisPrice);
            if (price == Price.ASCENDING) {
                cosmetics.sort(priceComparator);
            } else if (price == Price.DESCENDING) {
                cosmetics.sort(priceComparator.reversed());
            }
        }

        return cosmetics;
    }

    @Override
    public Cosmetic getCosmetic(final String name) {
        for (CosmeticCategory category : this.getCosmetics().keySet()) {
            for (Cosmetic cosmetic : this.getCosmetics().get(category)) {
                if (cosmetic.getId().equalsIgnoreCase(name)) {
                    return cosmetic;
                }
            }
        }
        return null;
    }

    @Override
    public Cosmetic getCosmetic(final String name, final CosmeticCategory category) {
        for (Cosmetic cosmetic : this.getCosmetics().get(category)) {
            if (cosmetic.getId().equalsIgnoreCase(name)) {
                return cosmetic;
            }
        }
        return null;
    }

    @Override
    public Map<Cosmetic, Class<? extends AbstractCosmetic>> getCosmeticClasses() {
        return cosmeticClasses;
    }

    @Override
    public boolean isLobbyServer() {
        return lobbyServer;
    }
}
