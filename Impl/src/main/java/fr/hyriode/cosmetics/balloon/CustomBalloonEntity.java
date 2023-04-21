package fr.hyriode.cosmetics.balloon;

import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EntitySlime;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class CustomBalloonEntity extends EntitySlime {

    private Player player;
    private ArmorStand contents;
    private Location currentLoc;
    private boolean status;
    private boolean yaw;
    private int i;

    public CustomBalloonEntity(World world, Player player, ItemStack item) {
        super(world);
        this.status = true;
        this.yaw = true;
        this.i = 0;
        this.player = player;
        updatePosition();
        this.contents = (ArmorStand)player.getWorld().spawnEntity(this.currentLoc.clone().subtract(0.0D, 1.0D, 0.0D), EntityType.ARMOR_STAND);
        ((Slime)getBukkitEntity()).setSize(-1);
        ((Slime)getBukkitEntity()).setLeashHolder(this.player);
        setInvisible(true);
        setLocation(this.currentLoc.getX(), this.currentLoc.getY(), this.currentLoc.getZ(), this.currentLoc.getYaw(), this.currentLoc.getPitch());
        this.contents.setVisible(false);
        this.contents.setCustomNameVisible(false);
        this.contents.setGravity(false);
        this.contents.setHelmet(item);
        this.contents.setMarker(false);
    }

    @Override
    public boolean isInvulnerable(DamageSource damagesource) {
        return true;
    }

    @Override
    public void t_() {
        updatePosition();
        setLocation(this.currentLoc.getX(), this.currentLoc.getY(), this.currentLoc.getZ(), this.currentLoc.getYaw(), this.currentLoc.getPitch());
        this.contents.teleport(getBukkitEntity().getLocation().clone().subtract(0.0D, 1.0D, 0.0D));
    }

    private void updatePosition() {
        this.currentLoc = this.player.getLocation();
        this.currentLoc.setPitch(-50.0F - random(0.0F, 5.0F));
        if (this.i == 90) {
            this.yaw = false;
        } else if (this.i == 0) {
            this.yaw = true;
        }
        if (this.yaw) {
            this.i--;
        } else {
            this.i++;
        }
        this.currentLoc.setYaw(this.i);
        this.currentLoc.add(this.currentLoc
                .getDirection().multiply(-1.3D).getX(), 2.3D + (this.status ? 0.1D : 0.0D), this.currentLoc

                .getDirection().multiply(-1.8D).getZ());
        this.status = !this.status;
    }

    public ArmorStand getContents() {
        return this.contents;
    }

    public Slime getSlime() {
        return (Slime)getBukkitEntity();
    }

    public float random(float start, float end) {
        return start + ThreadLocalRandom.current().nextFloat() * (end - start);
    }
}