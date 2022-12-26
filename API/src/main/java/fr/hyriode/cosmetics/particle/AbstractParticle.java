package fr.hyriode.cosmetics.particle;

import fr.hyriode.api.rank.type.IHyriRankType;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.common.CosmeticType;
import fr.hyriode.cosmetics.task.CosmeticTask;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.entity.Player;

public abstract class AbstractParticle extends AbstractCosmetic {

    protected final Player player;
    protected final CosmeticTask task;

    public AbstractParticle(String id, Player player, CosmeticRarity rarity, IHyriRankType requireRank, int tokenPrice, int hyrisPrice, ItemBuilder icon) {
        super(id, CosmeticType.Default.PARTICLE, rarity, requireRank, tokenPrice, hyrisPrice, icon, CosmeticCategory.Default.PARTICLE);
        this.player = player;
        this.task = initTask();
    }

    abstract CosmeticTask initTask();

    abstract void tick();

    public Player getPlayer() {
        return player;
    }

    public CosmeticTask getTask() {
        return task;
    }

    @Override
    public void onEquip() {
        HyriCosmetics.get().getTaskProvider().execute(task);
    }

    @Override
    public void onUnequip() {
        HyriCosmetics.get().getTaskProvider().remove(task);
    }
}
