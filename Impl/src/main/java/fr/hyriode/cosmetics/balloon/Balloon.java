package fr.hyriode.cosmetics.balloon;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class Balloon extends AbstractBalloonImpl {

    public Balloon(CosmeticUser user, Cosmetic cosmetic) {
        super(user, cosmetic, false);
    }

    @Override
    public void onEquip(CosmeticUser user) {

    }

    @Override
    public void onUnequip(CosmeticUser user) {
        Entity entity = null;
        entity.teleport(new Location(entity.getWorld(), 0, 0, 0));
    }
}
