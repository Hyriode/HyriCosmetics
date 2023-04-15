package fr.hyriode.cosmetics.listener;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        final CosmeticUser user = HyriCosmetics.get().getUserProvider().getUser((Player) event.getEntity());

        if (user == null || user.getCosmetic(CosmeticCategory.Default.MOUNT) == null) {
            return;
        }

        user.unequipCosmetic(CosmeticCategory.Default.MOUNT);
    }

    @EventHandler
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event) {
        final CosmeticUser user = HyriCosmetics.get().getUserProvider().getUser(event.getPlayer());

        if (!user.isDoubleJumpEnabled()) {
            return;
        }

        if (user.isDoubleJumpEnabled()) {
            event.setCancelled(true);
            user.asBukkit().setAllowFlight(false);
            user.asBukkit().setFlying(false);
            user.asBukkit().setVelocity(user.asBukkit().getLocation().getDirection().multiply(1.5).setY(1));
        }
    }

}
