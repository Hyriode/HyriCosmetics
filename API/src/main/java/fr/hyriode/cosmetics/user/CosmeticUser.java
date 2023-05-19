package fr.hyriode.cosmetics.user;

import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CosmeticUser {

    void init();

    void equipCosmetics();

    Player asBukkit();

    IHyriPlayer asHyriPlayer();

    PlayerCosmetic<?> equipCosmetic(CosmeticInfo cosmetic, boolean message);

    void unequipCosmetics(boolean message);

    void unequipCosmetic(CosmeticCategory category, boolean message);

    void temporarilyUnequipCosmetics();

    void reactivateCosmeticsTemporarilyUnequipped();

    List<CosmeticInfo> getUnlockedCosmetics();

    List<CosmeticInfo> getUnlockedCosmetics(CosmeticCategory category);

    void addUnlockedCosmetic(CosmeticInfo cosmetic);

    void removeUnlockedCosmetic(CosmeticInfo cosmetic);

    boolean hasUnlockedCosmetic(CosmeticInfo cosmetic);

    PlayerCosmetic<?> getPlayerCosmetic(CosmeticCategory category);

    Map<CosmeticCategory, PlayerCosmetic<?>> getEquippedCosmetics();

    UserData getData();

    void updateData();

    boolean isDoubleJumpEnabled();

    void setDoubleJumpEnabled(boolean doubleJumpEnabled);

    double getLastX();

    double getLastY();

    double getLastZ();

    void updateLastLocation();

    boolean isMoving();

    void setMoving(boolean moving);

    boolean hasEquippedCosmetic(CosmeticCategory category);

    CosmeticInfo getEquippedCosmetic(CosmeticCategory category);

    boolean isInitialized();

    Collection<CosmeticInfo> getActiveCosmetics();

    Map<CosmeticInfo, String> getActiveVariant();

    boolean isUnequipping();
}
