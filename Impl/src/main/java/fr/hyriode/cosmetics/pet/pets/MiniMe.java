package fr.hyriode.cosmetics.pet.pets;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.pet.AbstractComplexPet;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MiniMe extends AbstractComplexPet {

    private ArmorStand minime;

    public MiniMe(CosmeticUser user) {
        super(user, Cosmetic.MINI_ME);
    }

    @Override
    protected void spawn() {
        minime = (ArmorStand) getPlayer().getWorld().spawnEntity(this.setupLoc(getPlayer()), EntityType.ARMOR_STAND);

        minime.setMarker(true);
        minime.setFireTicks(Integer.MAX_VALUE);
        minime.setSmall(true);
        minime.setGravity(false);
        minime.setBasePlate(false);
        minime.setArms(true);

        minime.setHelmet(ItemBuilder.asHead().withPlayerHead(getPlayer().getUniqueId()).build());
        minime.setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).withLeatherArmorColor(Color.RED).build());
        minime.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).withLeatherArmorColor(Color.RED).build());
        minime.setBoots(new ItemBuilder(Material.LEATHER_BOOTS).withLeatherArmorColor(Color.RED).build());
    }

    @Override
    protected void remove() {
        this.minime.remove();
    }

    @Override
    protected void moveAnimationTick() {
        this.motionlessAnimationTick();
    }

    @Override
    public void motionlessAnimationTick() {
        minime.teleport(this.setupLoc(getPlayer()));
    }

    private Vector getRightVector(Location paramLocation) {
        float f1 = (float)(paramLocation.getX() + -1.0D * Math.cos(Math.toRadians((paramLocation.getYaw() + 0.0F))));
        float f2 = (float)(paramLocation.getZ() + -1.0D * Math.sin(Math.toRadians(paramLocation.getYaw() + 1.8D)));
        return new Vector(f1 - paramLocation.getX(), 0.0D, f2 - paramLocation.getZ());
    }

    private Location setupLoc(Player paramPlayer) {
        Location location = paramPlayer.getLocation();
        location.setPitch(0.0F);
        location.setYaw(location.getYaw() + 50.0F);
        location.add(getRightVector(location).normalize().multiply(1)).add(0.0D, 1.0D, 0.0D);
        location.setYaw(paramPlayer.getLocation().getYaw());
        location.setPitch(paramPlayer.getLocation().getPitch());
        return location;
    }
}
