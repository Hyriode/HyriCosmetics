package fr.hyriode.cosmetics.user;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CosmeticUserImpl implements CosmeticUser {

    private final Player player;
    private final Map<CosmeticCategory, PlayerCosmetic<?>> equippedCosmetics;
    private final UserData data;

    public CosmeticUserImpl(Player player) {
        this.player = player;
        this.equippedCosmetics = new HashMap<>();

        UserData data = HyriAPI.get().getPlayerManager().getPlayer(player.getUniqueId()).getData("cosmetics", UserData.class);

        if (data == null) {
            this.data = new UserData();
        } else {
            this.data = data;
        }

        if (!this.data.getEquippedCosmetics().isEmpty()) {
            for (Map.Entry<String, String> entry : this.data.getEquippedCosmetics().entrySet()) {
                final CosmeticCategory category = HyriCosmetics.get().getCategory(entry.getKey());
                final AbstractCosmetic cosmetic = HyriCosmetics.get().getCosmetic(entry.getValue());

                if (cosmetic != null) {
                    this.equippedCosmetics.put(category, new PlayerCosmeticImpl<>(cosmetic, this));
                }
            }
        }
    }

    @Override
    public Player asBukkit() {
        return this.player;
    }

    @Override
    public void equipCosmetic(AbstractCosmetic cosmetic) {
        Objects.requireNonNull(cosmetic, "cosmetic must not be null");

        this.unequipCosmetic(cosmetic.getCategory());
        this.equippedCosmetics.put(cosmetic.getCategory(), new PlayerCosmeticImpl<>(cosmetic, this));
    }

    @Override
    public void unequipCosmetic(CosmeticCategory category) {
        Objects.requireNonNull(category, "category must not be null");

        PlayerCosmetic<?> playerCosmetic = this.equippedCosmetics.remove(category);
        if (playerCosmetic != null) {
            playerCosmetic.unequip();
        }
    }

    @Override
    public PlayerCosmetic<?> getCosmetic(CosmeticCategory category) {
        Objects.requireNonNull(category, "category must not be null");

        return this.equippedCosmetics.get(category);
    }

    @Override
    public Map<CosmeticCategory, PlayerCosmetic<?>> getEquippedCosmetics() {
        return this.equippedCosmetics;
    }

    @Override
    public UserData getData() {
        return data;
    }

    @Override
    public void updateData() {
        IHyriPlayer hyriPlayer = HyriAPI.get().getPlayerManager().getPlayer(this.player.getUniqueId());
        equippedCosmetics.forEach((category, playerCosmetic) -> data.getEquippedCosmetics().put(category.getName(), playerCosmetic.getCosmetic().getId()));
        hyriPlayer.addData("cosmetics", this.data);
        HyriAPI.get().getPlayerManager().updatePlayer(hyriPlayer);
    }
}