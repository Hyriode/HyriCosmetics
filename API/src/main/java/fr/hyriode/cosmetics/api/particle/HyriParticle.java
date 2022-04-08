package fr.hyriode.cosmetics.api.particle;

import fr.hyriode.cosmetics.api.HyriCosmetic;
import fr.hyriode.cosmetics.api.HyriCosmeticRarity;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
* Project: HyriCosmetics
* Created by Akkashi
* on 02/04/2022 at 13:30
*/

public abstract class HyriParticle extends HyriCosmetic {
    public HyriParticle(String id, HyriCosmeticType type, HyriCosmeticRarity rarity, int hyodePrice, int hyrisPrice, Material icon) {
        super(id, type, rarity, hyodePrice, hyrisPrice, icon);
    }

    @Override
    public void enable(Player player) {
        this.play(player);
    }
}
