package fr.hyriode.cosmetics.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CosmeticUserProviderImpl implements CosmeticUserProvider {

    private final Map<UUID, CosmeticUser> users = new HashMap<>();

    @Override
    public CosmeticUser getUser(Player player) {
        return this.users.get(player.getUniqueId());
    }

    @Override
    public CosmeticUser getUser(UUID uuid) {
        return this.users.get(uuid);
    }

    @Override
    public void createUser(Player player) {
        this.users.put(player.getUniqueId(), new CosmeticUserImpl(player));
    }

    @Override
    public void deleteUser(Player player) {
        this.users.get(player.getUniqueId()).updateData();
        this.users.remove(player.getUniqueId());
    }
}
