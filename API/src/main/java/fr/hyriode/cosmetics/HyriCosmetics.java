package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.Cosmetics;
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

    public abstract <T extends AbstractCosmetic> T createCosmetic(Cosmetics cosmetic, CosmeticUser user);

    public abstract void registerCosmetic(Cosmetics cosmetic, Class<? extends AbstractCosmetic> cosmeticClass);

    public abstract Class<? extends AbstractCosmetic> getCosmeticClass(Cosmetics cosmetic);

    public abstract TaskProvider getTaskProvider();

    public static HyriCosmetics get() {
        return instance;
    }

    public abstract CosmeticUserProvider getUserProvider();

    public abstract List<CosmeticCategory> getCategories();

    public abstract CosmeticCategory getCategory(String name);

    public abstract Map<CosmeticCategory, List<Cosmetics>> getCosmetics();

    public abstract List<Cosmetics> getFilteredCosmetics(CosmeticUser user, CosmeticCategory category);

    public abstract Cosmetics getCosmetic(String name);

    public abstract Cosmetics getCosmetic(String name, CosmeticCategory category);

    public abstract Map<Cosmetics, Class<? extends AbstractCosmetic>> getCosmeticClasses();
}