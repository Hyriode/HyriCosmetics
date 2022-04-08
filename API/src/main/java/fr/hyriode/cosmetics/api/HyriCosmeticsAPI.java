package fr.hyriode.cosmetics.api;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 01/04/2022 at 19:44
 */
public abstract class HyriCosmeticsAPI {

    private static HyriCosmeticsAPI instance;

    public HyriCosmeticsAPI() {
        instance = this;
    }

    public abstract IHyriCosmeticsManager getCosmeticsManager();

    public static HyriCosmeticsAPI get() {
        if (instance == null) {
            throw new IllegalStateException("This might not worked, please restart.");
        }
        return instance;
    }


}
