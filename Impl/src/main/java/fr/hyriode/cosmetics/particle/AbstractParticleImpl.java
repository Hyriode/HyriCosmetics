package fr.hyriode.cosmetics.particle;

import fr.hyriode.api.rank.type.IHyriRankType;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.particle.util.EffectUtil;
import fr.hyriode.cosmetics.task.CosmeticTask;
import fr.hyriode.cosmetics.task.CosmeticTaskImpl;
import fr.hyriode.hyrame.item.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class AbstractParticleImpl extends AbstractParticle {
    public AbstractParticleImpl(String id, Player player, CosmeticRarity rarity, IHyriRankType requireRank, int tokenPrice, int hyrisPrice, ItemBuilder icon) {
        super(id, player, rarity, requireRank, tokenPrice, hyrisPrice, icon);
    }

    @Override
    protected Location getLocation() {
        return player.getLocation();
    }

    @Override
    CosmeticTask initTask() {
        return new CosmeticTaskImpl(this::tick);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        player.sendMessage("§7You have equipped the particle §b" + getId());
    }

    @Override
    public void onUnequip() {
        super.onEquip();
        player.sendMessage("§7You have unequipped the particle §b" + getId());
    }

    public abstract void tick();

    protected void display(EnumParticle effect, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
        EffectUtil.particle(effect, x, y, z, offsetX, offsetY, offsetZ, speed, amount);
    }
}
