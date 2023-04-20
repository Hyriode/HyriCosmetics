package fr.hyriode.cosmetics.listener;

import fr.hyriode.api.event.HyriEventHandler;
import fr.hyriode.api.player.event.ModerationUpdatedEvent;
import fr.hyriode.api.player.event.VanishUpdatedEvent;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;

public class AccountListener {

    private final HyriCosmetics instance;

    public AccountListener(HyriCosmetics instance) {
        this.instance = instance;
    }

    @HyriEventHandler
    public void onModeration(ModerationUpdatedEvent event) {
        final CosmeticUser user = this.instance.getUserProvider().getUser(event.getPlayerId());
        if (!event.isModerating()) {
            if (!user.isInitialized()) {
                user.init();
            } else {
                user.reactivateCosmeticsTemporarilyUnequipped();
            }
        } else {
            user.temporarilyUnequipCosmetics();
        }
    }

    @HyriEventHandler
    public void onVanish(VanishUpdatedEvent event) {
        final CosmeticUser user = this.instance.getUserProvider().getUser(event.getPlayerId());
        if (!event.isVanished()) {
            user.reactivateCosmeticsTemporarilyUnequipped();
        } else {
            user.temporarilyUnequipCosmetics();
        }
    }
}
