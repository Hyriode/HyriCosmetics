package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticManager;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;
import fr.hyriode.cosmetics.user.task.TaskProvider;

public abstract class HyriCosmetics {

    private static HyriCosmetics instance;

    public HyriCosmetics() {
        instance = this;
    }

    public abstract TaskProvider getTaskProvider();

    public static HyriCosmetics get() {
        return instance;
    }

    public abstract void registerCategory(CosmeticCategory category, Class<? extends CosmeticManager> manager);

    abstract <T extends CosmeticManager> T getManager(Class<? extends CosmeticManager> clazz);

    public abstract CosmeticUserProvider getUserManager();
}