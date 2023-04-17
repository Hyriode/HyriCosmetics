package fr.hyriode.cosmetics.listener;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.spigotmc.event.entity.EntityDismountEvent;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class PlayerListener implements Listener {

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

    @EventHandler (priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().hasMetadata("COSMETICS-PET")) {
            event.setCancelled(true);
            return;
        }

        if (event.getEntity().hasMetadata("COSMETICS-PET") && event.getDamager() instanceof Player) {
            final Player player = Bukkit.getPlayer(event.getEntity().getMetadata("COSMETICS-PET").get(0).asString());
            if (player != null && event.getDamager().getName().equals(player.getName())) {
                event.setDamage(0);
                event.setCancelled(false);
                new ParticleBuilder(ParticleEffect.VILLAGER_ANGRY, event.getEntity().getLocation().clone().add(0, 1, 0)).display();
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity().hasMetadata("COSMETICS-PET") && event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void on(EntityChangeBlockEvent event) {
        if (event.getEntity().getType() == EntityType.SILVERFISH) {
            event.setCancelled(true);
        }
    }

}
