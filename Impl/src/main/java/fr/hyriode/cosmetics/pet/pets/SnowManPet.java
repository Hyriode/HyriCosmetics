package fr.hyriode.cosmetics.pet.pets;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.pet.AbstractPetImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class SnowManPet extends AbstractPetImpl {

    private ArmorStand head;
    private ArmorStand body;
//    private ArmorStand rightArm;
//    private ArmorStand leftArm;

    public SnowManPet(CosmeticUser user) {
        super(user, Cosmetics.SNOWMAN);
    }

    @Override
    public void onEquip(CosmeticUser user) {
        this.spawn();
        super.onEquip(user);
    }

    @Override
    public void onUnequip(CosmeticUser user) {
        this.remove();
        super.onUnequip(user);
    }

    private void spawn() {
        ArmorStand head = (ArmorStand) getPlayer().getWorld().spawnEntity(getReferenceLocation().clone().add(0.0, 1.27, 0.0), EntityType.ARMOR_STAND);
        head.setMarker(true);
        head.setVisible(false);
        head.setSmall(true);
        head.setGravity(false);
        head.setHelmet(Head.SNOWMAN_HEAD.asItem());

        ArmorStand body = (ArmorStand) getPlayer().getWorld().spawnEntity(getReferenceLocation().clone().subtract(0, 1.35, 0), EntityType.ARMOR_STAND);
        body.setMarker(true);
        body.setVisible(false);
        body.setGravity(false);
        body.setHelmet(Head.SNOWMAN_BODY.asItem());

//        ArmorStand rightArm = (ArmorStand) getPlayer().getWorld().spawnEntity(getReferenceLocation().clone().subtract(0.5, 1.35, 0), EntityType.ARMOR_STAND);
//        rightArm.setMarker(true);
//        rightArm.setVisible(true/*TODO: false*/);
//        rightArm.setGravity(false);
//        rightArm.setArms(true);
//        rightArm.setItemInHand(new ItemStack(Material.STICK));
//        rightArm.setRightArmPose(new EulerAngle(-10, 45, 85));

//        ArmorStand leftArm = (ArmorStand) getPlayer().getWorld().spawnEntity(getReferenceLocation().clone().add(0.5, 1.35, 0), EntityType.ARMOR_STAND);
//        leftArm.setMarker(true);
//        leftArm.setVisible(false);
//        leftArm.setGravity(false);
//        leftArm.setArms(true);
//        leftArm.setItemInHand(new ItemStack(Material.STICK));
//        leftArm.setLeftArmPose(new EulerAngle(180, 90, 155));

        this.head = head;
        this.body = body;
//        this.rightArm = rightArm;
//        this.leftArm = leftArm;
    }

    private void remove() {
        this.head.remove();
        this.head = null;
        this.body.remove();
        this.body = null;
    }

    @Override
    protected void moveAnimationTick() {
        this.motionlessAnimationTick();
    }

    @Override
    public void motionlessAnimationTick() {
        final Location l = getReferenceLocation();
        head.teleport(new Location(l.getWorld(), l.getX(), l.getY() - 0.1, l.getZ(), l.getYaw(), l.getPitch()));
//        leftArm.teleport(new Location(l.getWorld(), l.getX(), l.getY() - 0.1, l.getZ(), l.getYaw(), l.getPitch()));
//        final Vector rightArmVector = l.getDirection().add(new Vector(0.6, -0.25, 0));
//        rightArm.teleport(new Location(l.getWorld(), l.getX(), l.getY() - 0.6, l.getZ(), l.getYaw(), l.getPitch()).add(rightArmVector));
        body.teleport(new Location(l.getWorld(), l.getX(), l.getY() - 1.4, l.getZ(), l.getYaw() - 180, l.getPitch()));
        new ParticleBuilder(ParticleEffect.SNOW_SHOVEL, l).display();
    }
    //0.6, -1.3125, -0.25
}
