package fr.hyriode.cosmetics.task;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.entity.Player;

public class MainTask implements Runnable {
    @Override
    public void run() {
        for (CosmeticUser user : HyriCosmetics.get().getUserProvider().getUsers().values()) {
            final Player player = user.asBukkit();
            user.setMoving(player.getLocation().getX() != user.getLastX() || player.getLocation().getY() != user.getLastY() || player.getLocation().getZ() != user.getLastZ());
            user.updateLastLocation();
        }
    }
}
