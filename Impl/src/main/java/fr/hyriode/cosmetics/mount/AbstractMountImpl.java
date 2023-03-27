package fr.hyriode.cosmetics.mount;

import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractMountImpl extends AbstractMount {

    private final Entity mountEntity;

    public AbstractMountImpl(String id, CosmeticRarity rarity, IHyriRankType requireRank, int tokenPrice, int hyrisPrice, ItemStack icon, Entity mountEntity) {
        super(id, rarity, requireRank, tokenPrice, hyrisPrice, icon);
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
