package fr.hyriode.cosmetics.user;

import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public interface CosmeticUser {

    void init();

    Player asBukkit();

    IHyriPlayer asHyriPlayer();

    PlayerCosmetic<?> equipCosmetic(Cosmetic cosmetic, boolean message);

    void unequipCosmetics(boolean message);

    void unequipCosmetic(CosmeticCategory category, boolean message);

    void temporarilyUnequipCosmetics();

    void reactivateCosmeticsTemporarilyUnequipped();

    List<Cosmetic> getUnlockedCosmetics();

    List<Cosmetic> getUnlockedCosmetics(CosmeticCategory category);

    void addUnlockedCosmetic(Cosmetic cosmetic);

    void removeUnlockedCosmetic(Cosmetic cosmetic);

    boolean hasUnlockedCosmetic(Cosmetic cosmetic);

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

    Cosmetic getEquippedCosmetic(CosmeticCategory category);

    boolean isInitialized();
}
