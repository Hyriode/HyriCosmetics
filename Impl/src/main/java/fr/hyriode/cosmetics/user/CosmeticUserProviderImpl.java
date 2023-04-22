package fr.hyriode.cosmetics.user;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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

        //TODO: delete the suite only for the opening
        if (!cosmeticUser.hasUnlockedCosmetic(Cosmetic.HYRIODE_BALLOON)) {
            cosmeticUser.addUnlockedCosmetic(Cosmetic.HYRIODE_BALLOON);

            Bukkit.getScheduler().runTaskLater(HyriCosmeticsPlugin.get(), () -> {
                player.sendMessage(HyriLanguageMessage.get("join.gift").getValue(player));
                player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
            }, 5L);
        }
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
