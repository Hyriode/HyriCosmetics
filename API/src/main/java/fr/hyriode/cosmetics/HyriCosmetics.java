package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRegistry;
import fr.hyriode.cosmetics.task.TaskProvider;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.user.CosmeticUserProvider;

import java.util.List;
import java.util.Map;

public abstract class HyriCosmetics {

    public static final String NAME = "HyriCosmetics";

    private static HyriCosmetics instance;

    public HyriCosmetics() {
        instance = this;
    }

    public static HyriCosmetics get() {
        return instance;
    }

    public abstract CosmeticRegistry getRegistry();

    public abstract TaskProvider getTaskProvider();

    public abstract CosmeticUserProvider getUserProvider();

    public abstract boolean isLobbyServer();

}