package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffectType;

public abstract class AbstractPetImpl extends AbstractPet {

    protected Zombie zombie;
    protected TaskNode task;

    public AbstractPetImpl(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
        this.zombie = (Zombie) user.asBukkit().getWorld().spawnEntity(user.asBukkit().getLocation(), EntityType.ZOMBIE);
        this.setEntitySilent();

        zombie.setBaby(true);
        zombie.setTarget(user.asBukkit());
        zombie.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
        zombie.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1));
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        task = HyriCosmetics.get().getTaskProvider().initTaskNode(new TaskNode(() -> tick(user)));
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().removeTaskNode(task.getUUID());
    }

    @Override
    protected void tick(CosmeticUser user) {
        final double distance = zombie.getLocation().distance(getPlayer().getLocation());
        if (zombie.getTarget() == null) {
            zombie.setTarget(getPlayer());
        }

        if ((isMoving() && distance > 2.5) || distance > 3) {
            if (zombie.hasPotionEffect(PotionEffectType.SLOW)) {
                zombie.removePotionEffect(PotionEffectType.SLOW);
            }
            if (distance > 12) {
                zombie.teleport(getPlayer());
            }
            this.moveAnimationTick();
        } else {
            if (!zombie.hasPotionEffect(PotionEffectType.SLOW)) {
                zombie.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 255));
            }
            this.motionlessAnimationTick();
        }
    }

    protected abstract void moveAnimationTick();
    public abstract void motionlessAnimationTick();

    public void setEntitySilent() {
        EntityZombie handle = ((CraftZombie) zombie).getHandle();
        NBTTagCompound tag = new NBTTagCompound();
        handle.c(tag);
        tag.setBoolean("Silent", true);
        handle.f(tag);
    }

    @Override
    public Location getReferenceLocation() {
        return zombie.getLocation();
    }

    public void moveToPoint(final ArmorStand entity, final Location destination, final double speed) {
        final Location origin = entity.getLocation();
        final double distance = origin.distance(destination);
        final double x = (1.0D + 0.07D * distance) * (destination.getX() - origin.getX()) / distance;
        final double y = (1.0D + 0.03D * distance) * (destination.getY() - origin.getY()) / distance;
        final double z = (1.0D + 0.07D * distance) * (destination.getZ() - origin.getZ()) / distance;
        entity.setVelocity(entity.getVelocity().setX(x * speed).setY(y * speed).setZ(z * speed));
    }
}
