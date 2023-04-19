package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;

import java.util.List;
import java.util.Map;

public abstract class HyriCosmetics {

    private static HyriCosmetics instance;

    public HyriCosmetics() {
        instance = this;
    }

    public abstract <T extends AbstractCosmetic> T createCosmetic(Cosmetic cosmetic, CosmeticUser user);

    public abstract void registerCosmetic(Cosmetic cosmetic, Class<? extends AbstractCosmetic> cosmeticClass);

    public abstract Class<? extends AbstractCosmetic> getCosmeticClass(Cosmetic cosmetic);

    public abstract TaskProvider getTaskProvider();

    public static HyriCosmetics get() {
        return instance;
    }

    public abstract CosmeticUserProvider getUserProvider();

    public abstract List<CosmeticCategory> getCategories();

    public abstract CosmeticCategory getCategory(String name);

    public abstract Map<CosmeticCategory, List<Cosmetic>> getCosmetics();

    public abstract List<Cosmetic> getFilteredCosmetics(CosmeticUser user, CosmeticCategory category);

    public abstract Cosmetic getCosmetic(String name);

    public abstract Cosmetic getCosmetic(String name, CosmeticCategory category);

    public abstract Map<Cosmetic, Class<? extends AbstractCosmetic>> getCosmeticClasses();

    public abstract boolean isLobbyServer();
}