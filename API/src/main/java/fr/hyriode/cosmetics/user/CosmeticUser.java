package fr.hyriode.cosmetics.user;

import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public interface CosmeticUser {

    Player asBukkit();

    IHyriPlayer asHyriPlayer();

    void equipCosmetic(AbstractCosmetic cosmetic);

    void unequipCosmetic(CosmeticCategory category);

    List<AbstractCosmetic> getUnlockedCosmetics();

    List<AbstractCosmetic> getUnlockedCosmetics(CosmeticCategory category);

    void addUnlockedCosmetic(AbstractCosmetic cosmetic);

    void removeUnlockedCosmetic(AbstractCosmetic cosmetic);

    boolean hasUnlockedCosmetic(AbstractCosmetic cosmetic);

    PlayerCosmetic<?> getCosmetic(CosmeticCategory category);

    Map<CosmeticCategory, PlayerCosmetic<?>> getEquippedCosmetics();

    UserData getData();

    void updateData();
}
