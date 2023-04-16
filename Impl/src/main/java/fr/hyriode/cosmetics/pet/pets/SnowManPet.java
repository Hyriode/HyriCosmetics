package fr.hyriode.cosmetics.pet.pets;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.pet.AbstractPetImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Chunk;
import net.minecraft.server.v1_8_R3.EnumSkyBlock;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class SnowManPet extends AbstractPetImpl {

    private ArmorStand head;
    private ArmorStand body;

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
        body.teleport(new Location(l.getWorld(), l.getX(), l.getY() - 1.4, l.getZ(), l.getYaw() - 180, l.getPitch()));
        new ParticleBuilder(ParticleEffect.SNOW_SHOVEL, l).display();
    }
}
