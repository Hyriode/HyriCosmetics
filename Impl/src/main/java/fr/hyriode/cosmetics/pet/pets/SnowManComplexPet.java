package fr.hyriode.cosmetics.pet.pets;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.pet.AbstractComplexPet;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class SnowManComplexPet extends AbstractComplexPet {

    private ArmorStand head;
    private ArmorStand body;

    public SnowManComplexPet(CosmeticUser user) {
        super(user, Cosmetics.SNOWMAN);
    }

    @Override
    protected void spawn() {
        final Location l = getReferenceLocation();
        ArmorStand head = (ArmorStand) getPlayer().getWorld().spawnEntity(
                new Location(l.getWorld(), l.getX(), l.getY() - 0.1, l.getZ(), l.getYaw(), l.getPitch()), EntityType.ARMOR_STAND);
        head.setMarker(true);
        head.setVisible(false);
        head.setFireTicks(Integer.MAX_VALUE);
        head.setSmall(true);
        head.setGravity(false);
        head.setHelmet(Head.SNOWMAN_HEAD.asItem());

        ArmorStand body = (ArmorStand) getPlayer().getWorld().spawnEntity(
                new Location(l.getWorld(), l.getX(), l.getY() - 1.4, l.getZ(), l.getYaw() - 180, l.getPitch()), EntityType.ARMOR_STAND);
        body.setMarker(true);
        body.setVisible(false);
        body.setFireTicks(Integer.MAX_VALUE);
        body.setGravity(false);
        body.setHelmet(Head.SNOWMAN_BODY.asItem());

        this.head = head;
        this.body = body;
        this.moveAnimationTick();
    }

    @Override
    protected void remove() {
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
        body.teleport(new Location(l.getWorld(), l.getX(), l.getY() - 1.4, l.getZ(), l.getYaw() - 180, l.getPitch()));
        new ParticleBuilder(ParticleEffect.SNOW_SHOVEL, l).display();
    }
}
