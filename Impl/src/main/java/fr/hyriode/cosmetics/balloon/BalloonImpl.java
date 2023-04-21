package fr.hyriode.cosmetics.balloon;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.item.ItemBuilder;
import fr.hyriode.hyrame.packet.PacketUtil;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class BalloonImpl extends AbstractBalloon {

    private final String texture;

    private Slime reference;
    private ArmorStand armorStand;

    public BalloonImpl(CosmeticUser user, Cosmetic cosmetic, String texture) {
        super(user, cosmetic, false);
        this.texture = texture;
    }

    @Override
    protected void tick(CosmeticUser user) {

    }

    @Override
    public void onEquip(CosmeticUser user) {
        World world = ((CraftPlayer) this.getPlayer()).getHandle().getWorld();
        CustomBalloonEntity balloon = new CustomBalloonEntity(world, this.getPlayer(), ItemBuilder.asHead().withHeadTexture(texture).build());
        world.addEntity(balloon, CreatureSpawnEvent.SpawnReason.CUSTOM);
        this.reference = balloon.getSlime();
        this.armorStand = balloon.getContents();

//        PacketUtil.sendPacket(new PacketPlayOutAttachEntity(0, (Entity) reference, (Entity) getPlayer()));
    }

    @Override
    public void onUnequip(CosmeticUser user) {
        this.reference.remove();
        this.getPlayer().getWorld().playSound(this.armorStand.getLocation().clone().add(0, 2, 0), Sound.CHICKEN_EGG_POP, 1, 2);
        new ParticleBuilder(ParticleEffect.CLOUD, this.armorStand.getLocation().clone().add(0, 2, 0))
                .setOffset(0, 0, 0).setSpeed(0.1F).setAmount(10).display();
        this.armorStand.remove();
    }
}
