package fr.hyriode.cosmetics;

import fr.hyriode.cosmetics.api.HyriCosmeticsAPI;
import fr.hyriode.cosmetics.api.IHyriCosmeticsManager;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 01/04/2022 at 20:48
 */
public class HyriCosmeticsImpl extends HyriCosmeticsAPI {

    private final HyriCosmeticsManager cosmeticsManager;

    public HyriCosmeticsImpl(HyriCosmetics plugin) {
        this.cosmeticsManager = new HyriCosmeticsManager(plugin);
    }

    @Override
    public HyriCosmeticsManager getCosmeticsManager() {
        return this.cosmeticsManager;
    }
}
