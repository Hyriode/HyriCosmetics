package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import org.bukkit.entity.Player;

import java.util.Map;

public interface CosmeticUser {

    Player asBukkit();

    void equipCosmetic(AbstractCosmetic cosmetic);

    void unequipCosmetic(CosmeticCategory category);

    PlayerCosmetic<?> getCosmetic(CosmeticCategory category);

    Map<CosmeticCategory, PlayerCosmetic<?>> getEquippedCosmetics();

    UserData getData();

    void updateData();
}
