package fr.hyriode.cosmetics.particle;

import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.task.CosmeticTask;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractParticle extends AbstractCosmetic {

    protected CosmeticTask task;

    public AbstractParticle(String id, CosmeticRarity rarity, IHyriRankType requireRank, int tokenPrice, int hyrisPrice, ItemStack icon) {
        super(id, rarity, requireRank, tokenPrice, hyrisPrice, icon, CosmeticCategory.Default.PARTICLE);
    }

    abstract CosmeticTask initTask(final CosmeticUser user);

    protected abstract void tick(final CosmeticUser user);

    public CosmeticTask getTask() {
        return task;
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().execute(() -> tick(user));
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().remove(task);
    }
}
