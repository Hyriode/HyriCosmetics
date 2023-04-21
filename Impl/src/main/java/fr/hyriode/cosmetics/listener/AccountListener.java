package fr.hyriode.cosmetics.listener;

import fr.hyriode.api.event.HyriEventHandler;
import fr.hyriode.api.language.HyriLanguageUpdatedEvent;
import fr.hyriode.api.player.event.ModerationUpdatedEvent;
import fr.hyriode.api.player.event.VanishUpdatedEvent;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.user.PlayerCosmetic;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AccountListener {

    private final HyriCosmetics instance;

    public AccountListener(HyriCosmetics instance) {
        this.instance = instance;
    }

    @HyriEventHandler
    public void onModeration(ModerationUpdatedEvent event) {
        final CosmeticUser user = this.instance.getUserProvider().getUser(event.getPlayerId());
        System.out.println("onModeration: " + event.isModerating());
        if (!event.isModerating()) {
            if (!user.isInitialized()) {
                user.equipCosmetics();
            }

            user.reactivateCosmeticsTemporarilyUnequipped();
        } else {
            user.temporarilyUnequipCosmetics();
        }
    }

    @HyriEventHandler
    public void onVanish(VanishUpdatedEvent event) {
        final CosmeticUser user = this.instance.getUserProvider().getUser(event.getPlayerId());
        if (!event.isVanished()) {
            if (!user.isInitialized()) {
                user.equipCosmetics();
            }
            user.reactivateCosmeticsTemporarilyUnequipped();
        } else {
            user.temporarilyUnequipCosmetics();
        }
    }


    @HyriEventHandler
    public void onLanguageUpdated(HyriLanguageUpdatedEvent event) {
        final Player player = Bukkit.getPlayer(event.getPlayerId());

        if (player == null) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(HyriCosmeticsPlugin.get(), () -> {
            CosmeticUser user = this.instance.getUserProvider().getUser(player.getUniqueId());

            for (PlayerCosmetic<?> playerCosmetic : user.getEquippedCosmetics().values()) {
                if (playerCosmetic.getAbstractCosmetic().hasVariants()) {
                    playerCosmetic.getAbstractCosmetic().reloadVariants();
                }
            }
        }, 5L);
    }
}
