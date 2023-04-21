package fr.hyriode.cosmetics.user;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.api.player.IHyriPlayerSession;
import fr.hyriode.api.player.model.IHyriTransaction;
import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.transaction.CosmeticTransaction;
import fr.hyriode.hyrame.utils.Pair;
import org.bukkit.Bukkit;
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

    private boolean isUnequipping = false;
    private Collection<Cosmetic> activeCosmetics = Collections.emptyList();
    private final Map<Cosmetic, String> activeVariant = new HashMap<>();

    private boolean initialized = false;

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
        this.init();
    }

    @Override
    public void init() {
        initialized = true;
        IHyriPlayerSession session = IHyriPlayerSession.get(player.getUniqueId());
        if (session.isModerating() || session.isVanished() || !HyriCosmetics.get().isLobbyServer())
            return;

        Bukkit.getScheduler().runTaskLater(HyriCosmeticsPlugin.get(), () -> {
            if (!this.data.getEquippedCosmetics().isEmpty()) {
                for (Map.Entry<String, Pair<String, String>> entry : this.data.getEquippedCosmetics().entrySet()) {
                    final Cosmetic cosmetic = HyriCosmetics.get().getCosmetic(entry.getValue().getKey());

                    if (cosmetic != null) {
                        PlayerCosmetic<?> playerCosmetic = this.equipCosmetic(cosmetic, false);
                        if (playerCosmetic.getAbstractCosmetic().hasVariants()) {
                            playerCosmetic.getAbstractCosmetic().setVariant(entry.getValue().getValue());
                        }
                    }
                }
            }

            this.lastX = player.getLocation().getX();
            this.lastY = player.getLocation().getY();
            this.lastZ = player.getLocation().getZ();
        }, 5L);
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
    public PlayerCosmetic<?> equipCosmetic(Cosmetic cosmetic, boolean message) {
        Objects.requireNonNull(cosmetic, "cosmetic must not be null");

        this.unequipCosmetic(cosmetic.getCategory(), false);
        final PlayerCosmetic<?> equippedCosmetic = new PlayerCosmeticImpl<>(HyriCosmetics.get().createCosmetic(cosmetic, this), this);
        this.equippedCosmetics.put(cosmetic.getCategory(), equippedCosmetic);
        equippedCosmetic.equip(message);

        return equippedCosmetic;
    }

    @Override
    public void unequipCosmetics(boolean message) {
        for (Map.Entry<CosmeticCategory, PlayerCosmetic<?>> entry : this.equippedCosmetics.entrySet()) {
            entry.getValue().unequip(message);
        }
        this.equippedCosmetics.clear();
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
    public void temporarilyUnequipCosmetics() {
        if (isUnequipping) return;

        this.isUnequipping = true;
        this.activeCosmetics = this.getEquippedCosmetics().values()
                .stream().map(playerCosmetic -> playerCosmetic.getAbstractCosmetic().getType()).collect(Collectors.toList());
        for (PlayerCosmetic<?> playerCosmetic : new ArrayList<>(this.getEquippedCosmetics().values())) {
            this.unequipCosmetic(playerCosmetic.getAbstractCosmetic().getCategory(), false);
            if (playerCosmetic.getAbstractCosmetic().hasVariants()) {
                this.activeVariant.put(playerCosmetic.getAbstractCosmetic().getType(), playerCosmetic.getAbstractCosmetic().getVariant());
            }
        }
    }

    @Override
    public void reactivateCosmeticsTemporarilyUnequipped() {
        if (!isUnequipping) return;

        this.isUnequipping = false;
        for (Cosmetic cosmetic : this.activeCosmetics) {
            PlayerCosmetic<?> playerCosmetic = this.equipCosmetic(cosmetic, false);
            if (activeVariant.containsKey(cosmetic) && playerCosmetic.getAbstractCosmetic().hasVariants()) {
                playerCosmetic.getAbstractCosmetic().setVariant(activeVariant.get(cosmetic));
            }
        }
        this.activeCosmetics = Collections.emptyList();
        this.activeVariant.clear();
    }

    @Override
    public List<Cosmetic> getUnlockedCosmetics() {
        final List<Cosmetic> result = new ArrayList<>();

        for (Map.Entry<CosmeticCategory, List<Cosmetic>> entry : HyriCosmetics.get().getCosmetics().entrySet()) {
            final List<Cosmetic> cosmetics = entry.getValue();

            for (Cosmetic cosmetic : cosmetics) {
                if (cosmetic.isRequireRank() && cosmetic.hasRequiredRank(player)) {
                    result.add(cosmetic);
                }
            }
        }

        if (this.asHyriPlayer().getTransactions().getAll(CosmeticTransaction.TYPE) != null) {
            for (IHyriTransaction transaction : this.asHyriPlayer().getTransactions().getAll(CosmeticTransaction.TYPE)) {
                CosmeticTransaction cosmeticTransaction = (CosmeticTransaction) transaction;
                System.out.println(cosmeticTransaction.getCosmeticId());
                System.out.println(HyriCosmetics.get().getCosmetic(cosmeticTransaction.getCosmeticId()).getTranslatedName().getValue(player));
            }
        }

        return result;
    }

    @Override
    public List<Cosmetic> getUnlockedCosmetics(CosmeticCategory category) {
        return this.getUnlockedCosmetics()
                .stream()
                .filter(cosmetic -> cosmetic.getCategory() == category)
                .collect(Collectors.toList());
    }


    @Override
    public void addUnlockedCosmetic(Cosmetic cosmetic) {
        final IHyriPlayer hyriPlayer = this.asHyriPlayer();
        hyriPlayer.getTransactions().add(CosmeticTransaction.TYPE, new CosmeticTransaction(cosmetic.getId()));
        hyriPlayer.update();
    }

    @Override
    public void removeUnlockedCosmetic(Cosmetic cosmetic) {
        final IHyriPlayer hyriPlayer = this.asHyriPlayer();
        hyriPlayer.getTransactions().remove(CosmeticTransaction.TYPE, cosmetic.getId());
        hyriPlayer.update();
    }

    @Override
    public boolean hasUnlockedCosmetic(Cosmetic cosmetic) {
        return this.getUnlockedCosmetics().contains(cosmetic);
    }

    @Override
    public PlayerCosmetic<?> getPlayerCosmetic(CosmeticCategory category) {
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
            final PlayerCosmetic<?> playerCosmetic = entry.getValue();
            if (playerCosmetic.getAbstractCosmetic().hasVariants()) {
                this.data.putEquippedCosmetics(
                        entry.getKey().getName(),
                        entry.getValue().getAbstractCosmetic().getId(),
                        playerCosmetic.getAbstractCosmetic().getVariant()
                );
            }
            this.data.putEquippedCosmetics(entry.getKey().getName(), entry.getValue().getAbstractCosmetic().getId());
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
    public Cosmetic getEquippedCosmetic(CosmeticCategory category) {
        return this.equippedCosmetics.get(category).getAbstractCosmetic().getType();
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public Collection<Cosmetic> getActiveCosmetics() {
        return activeCosmetics;
    }

    @Override
    public Map<Cosmetic, String> getActiveVariant() {
        return activeVariant;
    }

    @Override
    public boolean isUnequipping() {
        return isUnequipping;
    }
}