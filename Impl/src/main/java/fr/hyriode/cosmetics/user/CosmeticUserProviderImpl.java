package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory;
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
        this.users.get(player.getUniqueId()).init();
    }

    @Override
    public void deleteUser(Player player) {
        final CosmeticUser cosmeticUser = this.users.get(player.getUniqueId());

        if (cosmeticUser.isUnequipping()) {
            cosmeticUser.reactivateCosmeticsTemporarilyUnequipped();
        }
        cosmeticUser.updateData();
        for (CosmeticCategory category : HyriCosmetics.get().getCategories()) {
            cosmeticUser.unequipCosmetic(category, false);
        }
        this.users.remove(player.getUniqueId());
    }

    @Override
    public Map<UUID, CosmeticUser> getUsers() {
        return users;
    }
}
