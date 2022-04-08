package fr.hyriode.cosmetics.api.pet;

import fr.hyriode.cosmetics.api.HyriCosmetic;
import fr.hyriode.cosmetics.api.HyriCosmeticRarity;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 08/04/2022 at 16:58
 */
public abstract class HyriPet extends HyriCosmetic {

    private Class<? extends LivingEntity> entity;
    private boolean followable;

    public HyriPet(
            String id,
            HyriCosmeticType type,
            HyriCosmeticRarity rarity,
            int hyodePrice,
            int hyrisPrice,
            Material icon,
            Class<? extends LivingEntity> entity,
            boolean followable
    ) {
        super(
                id,
                type,
                rarity,
                hyodePrice,
                hyrisPrice,
                icon
        );
        this.entity = entity;
        this.followable = followable;
    }
}
