package fr.hyriode.cosmetics.user;

import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.Cosmetics;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public interface CosmeticUser {

    Player asBukkit();

    IHyriPlayer asHyriPlayer();

    void equipCosmetic(Cosmetics cosmetic);

    void unequipCosmetic(CosmeticCategory category);

    List<Cosmetics> getUnlockedCosmetics();

    List<Cosmetics> getUnlockedCosmetics(CosmeticCategory category);

    void addUnlockedCosmetic(Cosmetics cosmetic);

    void removeUnlockedCosmetic(Cosmetics cosmetic);

    boolean hasUnlockedCosmetic(Cosmetics cosmetic);

    PlayerCosmetic<?> getCosmetic(CosmeticCategory category);

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

    Cosmetics getEquippedCosmetic(CosmeticCategory category);
}
