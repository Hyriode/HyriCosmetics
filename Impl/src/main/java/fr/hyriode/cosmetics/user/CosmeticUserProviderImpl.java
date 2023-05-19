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
        CosmeticUser cosmeticUser = this.users.get(player.getUniqueId());
        cosmeticUser.init();
    }

    @Override
    public void deleteUser(Player player) {
        final CosmeticUser cosmeticUser = this.users.get(player.getUniqueId());
        cosmeticUser.updateData();
        if (cosmeticUser.isUnequipping()) {
            cosmeticUser.reactivateCosmeticsTemporarilyUnequipped();
        }
        for (CosmeticCategory category : HyriCosmetics.get().getRegistry().getCategories()) {
            cosmeticUser.unequipCosmetic(category, false);
        }

        this.users.remove(player.getUniqueId());
    }

    @Override
    public Map<UUID, CosmeticUser> getUsers() {
        return users;
    }
}
