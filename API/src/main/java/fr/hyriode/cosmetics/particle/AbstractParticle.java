package fr.hyriode.cosmetics.particle;

import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractParticle extends AbstractCosmetic {

    protected TaskNode task;

    public AbstractParticle(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }

    abstract TaskNode initTask(final CosmeticUser user);

    protected abstract void tick(final CosmeticUser user);

    public TaskNode getTask() {
        return task;
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        task = HyriCosmetics.get().getTaskProvider().initTaskNode(new TaskNode(() -> tick(user)));
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().removeTaskNode(task.getUUID());
    }
}
