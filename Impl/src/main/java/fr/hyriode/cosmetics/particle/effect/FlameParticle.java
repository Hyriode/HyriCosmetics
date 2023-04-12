package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.particle.ParticleManager;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.MathUtil;
import fr.hyriode.hyrame.item.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.utils.ParticleUtils;

public class FlameParticle extends AbstractParticleImpl {

    private final static double angle = 0.14D;
    private final static double angularVelocityY = 0.3D;
    private double radius = 0.0D;
    private int height = 70;

    private int time = 0;

    public FlameParticle() {
        super(
                "flame",
                CosmeticRarity.RARE,
                PlayerRank.PLAYER,
                150,
                15000,
                new ItemBuilder(Material.FIREBALL).build()
        );
    }

    @Override
    public void tick(final CosmeticUser user) {
        this.time++;
        this.time %= 1200;

        Location location = user.asBukkit().getLocation().clone().subtract(0.0D, 0.3D, 0.0D);
        double d = time * angularVelocityY;
        for (byte b = 1; b < 4; b++) {
            double d1 = (120 * b);
            Vector vector = new Vector(Math.cos(time * angle + Math.toRadians(d1)) * this.radius, this.height * 0.05D, Math.sin(time * angle + Math.toRadians(d1)) * this.radius);
            MathUtil.rotateAroundAxisY(vector, d);
            this.display(EnumParticle.BLOCK_CRACK.display((ParticleEffect.ParticleData)new ParticleEffect.BlockData(Material.LAVA, (byte)0), 0.1F, 0.1F, 0.1F, 0.0F, 0, location, 128.0D);

            new ParticleBuilder(ParticleEffect.FLAME, location.add(vector))
                .setAmount(1)
                .display(/*TODO: check params*/);

            new Particle
            location.subtract(vector);
        }
        int max = 60;
        if (this.height > max / 2) {
            this.radius += 0.035D;
        } else {
            this.radius -= 0.035D;
        }
        this.height--;
        if (this.height <= 0) {
            this.height = max;
            this.radius = 0.0D;
        }
    }
}