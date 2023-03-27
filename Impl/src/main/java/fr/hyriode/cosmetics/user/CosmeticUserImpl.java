package fr.hyriode.cosmetics.user;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.transaction.CosmeticTransaction;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class CosmeticUserImpl implements CosmeticUser {

    private final Player player;
    private final Map<CosmeticCategory, PlayerCosmetic<?>> equippedCosmetics;
    private final UserData data;

    public CosmeticUserImpl(Player player) {
        this.player = player;
        this.equippedCosmetics = new HashMap<>();

        UserData data = HyriAPI.get().getPlayerManager().getPlayer(player.getUniqueId()).getData().read("cosmetics", new UserData());

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
    public IHyriPlayer asHyriPlayer() {
        return HyriAPI.get().getPlayerManager().getPlayer(this.player.getUniqueId());
    }

    @Override
    public void equipCosmetic(AbstractCosmetic cosmetic) {
        Objects.requireNonNull(cosmetic, "cosmetic must not be null");

        this.unequipCosmetic(cosmetic.getCategory());
        final PlayerCosmetic<?> equipedCosmetic = this.equippedCosmetics
                .put(cosmetic.getCategory(), new PlayerCosmeticImpl<>(cosmetic, this));
        if (equipedCosmetic != null) {
            equipedCosmetic.equip();
        }
    }

    @Override
    public void unequipCosmetic(CosmeticCategory category) {
        Objects.requireNonNull(category, "category must not be null");

        if (this.equippedCosmetics.containsKey(category)) {
            final PlayerCosmetic<?> playerCosmetic = this.equippedCosmetics.remove(category);
            if (playerCosmetic != null) {
                playerCosmetic.unequip();
            }
        }
    }

    @Override
    public List<AbstractCosmetic> getUnlockedCosmetics() {
        final List<AbstractCosmetic> result = new ArrayList<>();

        if (this.asHyriPlayer().getTransactions().getAll(CosmeticTransaction.TYPE) != null) {
            result.addAll(this.asHyriPlayer().getTransactions().getAll(CosmeticTransaction.TYPE)
                    .stream()
                    .map(o -> (CosmeticTransaction) o)
                    .map(CosmeticTransaction::getCosmeticId)
                    .map(HyriCosmetics.get()::getCosmetic)
                    .collect(Collectors.toList()));
        }

        return result;
    }

    @Override
    public List<AbstractCosmetic> getUnlockedCosmetics(CosmeticCategory category) {
        return this.getUnlockedCosmetics()
                .stream()
                .filter(cosmetic -> cosmetic.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public void addUnlockedCosmetic(AbstractCosmetic cosmetic) {
        this.asHyriPlayer().getTransactions().add(CosmeticTransaction.TYPE, new CosmeticTransaction(cosmetic.getId()));
    }

    @Override
    public void removeUnlockedCosmetic(AbstractCosmetic cosmetic) {
        this.asHyriPlayer().getTransactions().remove(CosmeticTransaction.TYPE, cosmetic.getId());
    }

    @Override
    public boolean hasUnlockedCosmetic(AbstractCosmetic cosmetic) {
        return this.getUnlockedCosmetics().contains(cosmetic);
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
        equippedCosmetics.forEach((category, playerCosmetic) -> data.getEquippedCosmetics().put(category.getName(), playerCosmetic.getCosmetic().getId()));
        final IHyriPlayer hyriPlayer = this.asHyriPlayer();
        hyriPlayer.getData().add("cosmetics", this.data);
        hyriPlayer.update();
    }
}