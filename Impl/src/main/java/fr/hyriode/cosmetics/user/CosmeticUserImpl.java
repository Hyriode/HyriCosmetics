package fr.hyriode.cosmetics.user;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.api.server.ILobbyAPI;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.transaction.CosmeticTransaction;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class CosmeticUserImpl implements CosmeticUser {

    private final Player player;
    private final Map<CosmeticCategory, PlayerCosmetic<?>> equippedCosmetics;
    private final UserData data;

    private double lastX, lastY, lastZ;
    private boolean isMoving = false;

    private boolean doubleJumpEnabled = false;

    public CosmeticUserImpl(Player player) {
        this.player = player;
        this.equippedCosmetics = new HashMap<>();

        UserData data = HyriAPI.get().getPlayerManager().getPlayer(player.getUniqueId()).getData().read("cosmetics", new UserData());

        if (data == null) {
            this.data = new UserData();
        } else {
            this.data = data;
        }

        this.data.setUser(this);

        if (HyriCosmetics.get().isLobbyServer()) {
            if (!this.data.getEquippedCosmetics().isEmpty()) {
                for (Map.Entry<String, String> entry : this.data.getEquippedCosmetics().entrySet()) {
                    final Cosmetics cosmetic = HyriCosmetics.get().getCosmetic(entry.getValue());

                    if (cosmetic != null) {
                        this.equipCosmetic(cosmetic, false);
                    }
                }
            }

            this.lastX = player.getLocation().getX();
            this.lastY = player.getLocation().getY();
            this.lastZ = player.getLocation().getZ();
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
    public void equipCosmetic(Cosmetics cosmetic, boolean message) {
        Objects.requireNonNull(cosmetic, "cosmetic must not be null");

        this.unequipCosmetic(cosmetic.getCategory(), false);
        final PlayerCosmetic<?> equippedCosmetic = new PlayerCosmeticImpl<>(HyriCosmetics.get().createCosmetic(cosmetic, this), this);
        this.equippedCosmetics.put(cosmetic.getCategory(), equippedCosmetic);
        equippedCosmetic.equip(message);
    }

    @Override
    public void unequipCosmetic(CosmeticCategory category, boolean message) {
        Objects.requireNonNull(category, "category must not be null");

        if (this.equippedCosmetics.containsKey(category)) {
            this.equippedCosmetics.get(category).unequip(message);
            this.equippedCosmetics.remove(category);
        }
    }

    @Override
    public List<Cosmetics> getUnlockedCosmetics() {
        final List<Cosmetics> result = new ArrayList<>();

        for (Map.Entry<CosmeticCategory, List<Cosmetics>> entry : HyriCosmetics.get().getCosmetics().entrySet()) {
            final CosmeticCategory category = entry.getKey();
            final List<Cosmetics> cosmetics = entry.getValue();

            for (Cosmetics cosmetic : cosmetics) {
//                if (this.asHyriPlayer().getRank().isSuperior(cosmetic.getRequireRank())) {
//
//                }
            }
        }

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
    public List<Cosmetics> getUnlockedCosmetics(CosmeticCategory category) {
        return this.getUnlockedCosmetics()
                .stream()
                .filter(cosmetic -> cosmetic.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public void addUnlockedCosmetic(Cosmetics cosmetic) {
        final IHyriPlayer hyriPlayer = this.asHyriPlayer();
        hyriPlayer.getTransactions().add(CosmeticTransaction.TYPE, new CosmeticTransaction(cosmetic.getId()));
        hyriPlayer.update();
    }

    @Override
    public void removeUnlockedCosmetic(Cosmetics cosmetic) {
        final IHyriPlayer hyriPlayer = this.asHyriPlayer();
        hyriPlayer.getTransactions().remove(CosmeticTransaction.TYPE, cosmetic.getId());
        hyriPlayer.update();
    }

    @Override
    public boolean hasUnlockedCosmetic(Cosmetics cosmetic) {
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
        for (Map.Entry<CosmeticCategory, PlayerCosmetic<?>> entry : this.equippedCosmetics.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null)
                return;
            this.data.getEquippedCosmetics().put(entry.getKey().getName(), entry.getValue().getCosmetic().getId());
        }
        final IHyriPlayer hyriPlayer = this.asHyriPlayer();
        hyriPlayer.getData().add("cosmetics", this.data);
        hyriPlayer.update();
    }

    @Override
    public boolean isDoubleJumpEnabled() {
        return doubleJumpEnabled;
    }

    @Override
    public void setDoubleJumpEnabled(boolean doubleJumpEnabled) {
        if (doubleJumpEnabled) {
            this.player.setAllowFlight(true);
        } else {
            if (!(HyriCosmetics.get().isLobbyServer() && (this.asHyriPlayer().getRank().isSuperior(PlayerRank.VIP_PLUS) || this.asHyriPlayer().getRank().isStaff()))) {
                player.setAllowFlight(false);
            }
        }
        this.doubleJumpEnabled = doubleJumpEnabled;
    }

    @Override
    public double getLastX() {
        return lastX;
    }

    @Override
    public double getLastY() {
        return lastY;
    }

    @Override
    public double getLastZ() {
        return lastZ;
    }

    @Override
    public void updateLastLocation() {
        this.lastX = player.getLocation().getX();
        this.lastY = player.getLocation().getY();
        this.lastZ = player.getLocation().getZ();
    }

    @Override
    public boolean isMoving() {
        return isMoving;
    }

    @Override
    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    @Override
    public boolean hasEquippedCosmetic(CosmeticCategory category) {
        return this.equippedCosmetics.containsKey(category) && this.equippedCosmetics.get(category) != null;
    }

    @Override
    public Cosmetics getEquippedCosmetic(CosmeticCategory category) {
        return this.equippedCosmetics.get(category).getCosmetic().getCosmetic();
    }
}