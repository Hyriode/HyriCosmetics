package fr.hyriode.cosmetics.api.gadget;

import fr.hyriode.cosmetics.api.HyriCosmetic;
import fr.hyriode.cosmetics.api.HyriCosmeticRarity;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 02/04/2022 at 13:03
 */
public abstract class HyriGadget extends HyriCosmetic {

    public HyriGadget(String id, HyriCosmeticType type, HyriCosmeticRarity rarity, int hyodePrice, int hyrisPrice, Material icon) {
        super(id, type, rarity, hyodePrice, hyrisPrice, icon);
    }

    /**
     * Item to give in Player's inventory
     *
     * @return item's type
     */
    public abstract Material getItem();
}
