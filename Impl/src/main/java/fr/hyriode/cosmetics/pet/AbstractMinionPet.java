package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public abstract class AbstractMinionPet<T> extends AbstractPetImpl<T> {

    protected ArmorStand minion;

    public AbstractMinionPet(CosmeticUser user, Cosmetic cosmetic, boolean hasVariants) {
        super(user, cosmetic, hasVariants);
    }

    @Override
    protected void tick(CosmeticUser user) {
        if (isMoving()) {
            this.moveAnimationTick();
        } else {
            this.motionlessAnimationTick();
        }
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        super.onEquip(user);
        minion = (ArmorStand) getPlayer().getWorld().spawnEntity(getReferenceLocation(), EntityType.ARMOR_STAND);

        minion.setMarker(true);
        minion.setFireTicks(Integer.MAX_VALUE);
        minion.setSmall(true);
        minion.setGravity(false);
        minion.setBasePlate(false);
        minion.setArms(true);

        this.spawn();
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        super.onUnequip(user);
        this.remove();
        this.minion.remove();
    }

    @Override
    public Location getReferenceLocation() {
        Location location = getLocation().clone();
        location.setPitch(0.0F);
        location.setYaw(location.getYaw() + 50.0F);
        location.add(getRightVector(location).normalize().multiply(1)).add(0.0D, 1.0D, 0.0D);
        location.setYaw(getLocation().getYaw());
        location.setPitch(getLocation().getPitch());
        return location;
    }

    private Vector getRightVector(Location paramLocation) {
        float f1 = (float)(paramLocation.getX() + -1.0D * Math.cos(Math.toRadians((paramLocation.getYaw() + 0.0F))));
        float f2 = (float)(paramLocation.getZ() + -1.0D * Math.sin(Math.toRadians(paramLocation.getYaw() + 1.8D)));
        return new Vector(f1 - paramLocation.getX(), 0.0D, f2 - paramLocation.getZ());
    }
}
