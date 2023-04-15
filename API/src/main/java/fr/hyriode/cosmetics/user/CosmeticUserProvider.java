package fr.hyriode.cosmetics.user;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface CosmeticUserProvider {

    CosmeticUser getUser(final Player player);

    CosmeticUser getUser(final UUID uuid);

    void createUser(final Player player);

    void deleteUser(final Player player);

    Map<UUID, CosmeticUser> getUsers();
}
