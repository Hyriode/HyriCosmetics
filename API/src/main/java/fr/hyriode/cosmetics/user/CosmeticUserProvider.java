package fr.hyriode.cosmetics.user;

import fr.hyriode.hyrame.game.HyriGamePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface CosmeticUserProvider {

    CosmeticUser getUser(final Player player);

    CosmeticUser getUser(final UUID uuid);

    void createUser(final Player player);

    void deleteUser(final Player player);

}
