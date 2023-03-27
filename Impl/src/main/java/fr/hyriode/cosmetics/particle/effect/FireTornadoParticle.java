package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.item.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;

public class FireTornadoParticle extends AbstractParticleImpl {

    private static final double pi4 = Math.PI / 4;
    private static final double pi2 = Math.PI / 2;

    public FireTornadoParticle() {
        super(
                "fire_tornado",
                CosmeticRarity.RARE,
                PlayerRank.PLAYER,
                150,
                15000,
                new ItemBuilder(Material.BLAZE_POWDER).build()
        );
    }

    @Override
    public void tick(final CosmeticUser user) {
        final Location location = user.asBukkit().getLocation();

        float ratio;
        double angle, x, z;

        final double xloc = location.getX();
        final double yloc = location.getY();
        final double zloc = location.getZ();

        for (int i = 1; i <= 8; i++) {
            angle = pi2 * i + pi4;
            for (int j = 1; j <= 80; j++) {
                ratio = (float) j / 80;
                x = Math.cos(angle) * ratio * 10;
                z = Math.sin(angle) * ratio * 10;

                location.add(x, 0, z);
                this.display(EnumParticle.FLAME, (float) xloc, (float) yloc, (float) zloc, 0, 0, 0, 0, 1);
                location.subtract(x, 0, z);
            }
        }
    }
}