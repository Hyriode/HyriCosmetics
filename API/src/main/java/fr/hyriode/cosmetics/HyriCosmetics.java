package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;

import java.util.List;
import java.util.Map;

public abstract class HyriCosmetics {

    private static HyriCosmetics instance;

    public HyriCosmetics() {
        instance = this;
    }

    public abstract TaskProvider getTaskProvider();

    public static HyriCosmetics get() {
        return instance;
    }

    public abstract void registerCategory(CosmeticCategory category);

    public abstract void registerCosmetic(AbstractCosmetic cosmetic);

    public abstract void registerCosmetic(AbstractCosmetic... cosmetic);

    public abstract CosmeticUserProvider getUserManager();

    public abstract List<CosmeticCategory> getCategories();

    public abstract CosmeticCategory getCategory(String name);

    public abstract Map<CosmeticCategory, List<AbstractCosmetic>> getCosmetics();

    public abstract AbstractCosmetic getCosmetic(String name);

    public abstract AbstractCosmetic getCosmetic(String name, CosmeticCategory category);
}