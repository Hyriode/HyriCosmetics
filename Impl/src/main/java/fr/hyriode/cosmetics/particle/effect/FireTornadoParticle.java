package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.api.rank.type.HyriPlayerRankType;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.item.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;

public class FireTornadoParticle extends AbstractParticleImpl {

    public FireTornadoParticle() {
        super(
                "fire_tornado",
                CosmeticRarity.RARE,
                HyriPlayerRankType.PLAYER,
                150,
                15000,
                new ItemBuilder(Material.BLAZE_POWDER)
        );
    }

    @Override
    public void tick(final CosmeticUser user) {
        final Location location = user.asBukkit().getLocation();
        float ratio;
        double angle, x, z;

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 80; j++) {
                ratio = (float) j / 80;
                angle = 10 * ratio * 2 * Math.PI / 8 + (2 * Math.PI * i / 8) + Math.PI / 4;
                x = Math.cos(angle) * ratio * 10;
                z = Math.sin(angle) * ratio * 10;

                location.add(x, 0, z);
                this.display(EnumParticle.FLAME, (float) location.getX(), (float) location.getY(), (float) location.getZ(), 0, 0, 0, 0, 1);
                location.subtract(x, 0, z);
            }
        }
    }
}