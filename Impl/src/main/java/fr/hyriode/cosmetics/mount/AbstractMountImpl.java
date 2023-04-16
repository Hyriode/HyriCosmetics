package fr.hyriode.cosmetics.mount;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.entity.Entity;

public abstract class AbstractMountImpl extends AbstractMount {

    private final Entity mountEntity;

    public AbstractMountImpl(CosmeticUser user, Cosmetics cosmetic, Entity mountEntity) {
        super(user, cosmetic);
        this.mountEntity = mountEntity;
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        this.mountEntity.setPassenger(user.asBukkit());
        user.asBukkit().sendMessage("§7You have equipped the mount §b" + getId());
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        this.mountEntity.remove();
        user.asBukkit().sendMessage("§7You have unequipped the mount §b" + getId());
    }

}
