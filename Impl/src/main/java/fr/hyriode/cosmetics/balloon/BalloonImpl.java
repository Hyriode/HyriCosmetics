package fr.hyriode.cosmetics.balloon;

import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.item.ItemBuilder;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.CreatureSpawnEvent;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class BalloonImpl extends AbstractBalloon {

    private Slime reference;
    private ArmorStand armorStand;

    public BalloonImpl(CosmeticUser user, CosmeticInfo info) {
        super(user, info, false);
    }

    @Override
    protected void tick(CosmeticUser user) {}

    @Override
    public void onEquip(CosmeticUser user) {
        final World world = ((CraftPlayer) this.getPlayer()).getHandle().getWorld();
        final CustomBalloonEntity balloon = new CustomBalloonEntity(world, this.getPlayer(), this.info.getIcon());

        world.addEntity(balloon, CreatureSpawnEvent.SpawnReason.CUSTOM);

        this.reference = balloon.getSlime();
        this.armorStand = balloon.getContents();
    }

    @Override
    public void onUnequip(CosmeticUser user) {
        this.reference.remove();

        this.getPlayer().getWorld().playSound(this.armorStand.getLocation().clone().add(0, 2, 0), Sound.CHICKEN_EGG_POP, 1, 2);

        new ParticleBuilder(ParticleEffect.CLOUD, this.armorStand.getLocation().clone().add(0, 2, 0))
                .setOffset(0, 0, 0)
                .setSpeed(0.1F)
                .setAmount(10)
                .display();

        this.armorStand.remove();
    }
}
