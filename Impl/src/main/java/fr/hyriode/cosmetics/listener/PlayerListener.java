package fr.hyriode.cosmetics.listener;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        final CosmeticUser user = HyriCosmetics.get().getUserManager().getUser((Player) event.getEntity());

        if (user == null || user.getCosmetic(CosmeticCategory.Default.MOUNT) == null) {
            return;
        }

        user.unequipCosmetic(CosmeticCategory.Default.MOUNT);
    }

}
