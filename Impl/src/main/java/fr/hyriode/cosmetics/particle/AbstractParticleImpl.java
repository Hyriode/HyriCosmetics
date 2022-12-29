package fr.hyriode.cosmetics.particle;

import fr.hyriode.api.rank.type.IHyriRankType;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.particle.util.EffectUtil;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.task.CosmeticTask;
import fr.hyriode.cosmetics.task.CosmeticTaskImpl;
import fr.hyriode.hyrame.item.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;

public abstract class AbstractParticleImpl extends AbstractParticle {

    public AbstractParticleImpl(String id, CosmeticRarity rarity, IHyriRankType requireRank, int tokenPrice, int hyrisPrice, ItemBuilder icon) {
        super(id, rarity, requireRank, tokenPrice, hyrisPrice, icon);
    }

    @Override
    CosmeticTask initTask(final CosmeticUser user) {
        return new CosmeticTaskImpl(() -> this.tick(user));
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        super.onEquip(user);
        user.getHyriPlayer().sendMessage("§7You have equipped the particle §b" + getId());
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        super.onEquip(user);
        user.getHyriPlayer().sendMessage("§7You have unequipped the particle §b" + getId());
    }

    public abstract void tick(final CosmeticUser user);

    protected void display(EnumParticle effect, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        EffectUtil.particle(effect, x, y, z, offsetX, offsetY, offsetZ, speed, amount);
    }

}
