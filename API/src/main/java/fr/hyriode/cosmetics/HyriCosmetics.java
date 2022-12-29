package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;
import fr.hyriode.cosmetics.task.TaskProvider;

import java.util.List;

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

    public abstract CosmeticUserProvider getUserManager();

    public abstract List<CosmeticCategory> getCategories();
}