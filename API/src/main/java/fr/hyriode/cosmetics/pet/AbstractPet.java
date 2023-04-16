package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Silverfish;

public abstract class AbstractPet extends AbstractCosmetic {

    protected TaskNode task;
    protected Silverfish silverfish;

    public AbstractPet(CosmeticUser user, Cosmetics cosmetic, Silverfish silverfish) {
        super(user, cosmetic);
        this.silverfish = silverfish;
    }

    public AbstractPet(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }

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

    public Location getReferenceLocation() {
        return silverfish.getLocation();
    }
}
