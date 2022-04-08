package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.api.HyriCosmeticRarity;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import fr.hyriode.cosmetics.api.HyriCosmeticsAPI;
import fr.hyriode.cosmetics.api.HyriScheduledCosmetic;
import fr.hyriode.cosmetics.api.handlers.HyriMoveHandler;
import fr.hyriode.cosmetics.api.particle.HyriParticle;
import fr.hyriode.cosmetics.api.particle.HyriParticleName;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.function.Consumer;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 02/04/2022 at 13:28
 */
public class FireTornadoParticle extends HyriParticle {

    private final HyriCosmetics plugin;

    public FireTornadoParticle(HyriCosmetics plugin) {
        super(
                HyriParticleName.FIRE_TORNADO,
                HyriCosmeticType.Default.PARTICLE,
                HyriCosmeticRarity.RARE,
                150,
                15,
                Material.BLAZE_POWDER
        );
        this.plugin = plugin;
    }

    @Override
    public void play(Player player) {
        HyriCosmeticsAPI.get().getCosmeticsManager().playScheduledCosmetic(player, this.plugin, this.getScheduledCosmetic(), this);
    }

    @Override
    public HyriScheduledCosmetic getScheduledCosmetic() {
        return new HyriScheduledCosmetic() {
            @Override
            public boolean tick(Player player) {
                Location location = player.getLocation();
                playHelix(location);

                return false;
            }

            @Override
            public int tickDuration() {
                return 5;
            }

            @Override
            public int duration() {
                return -1;
            }
        };
    }

    private void playHelix(final Location loc) {
        new BukkitRunnable() {
            double radius = 0;
            double step;
            final double y = loc.getY();
            final Location location = loc.clone().add(0, 3, 0);

            @Override
            public void run() {
                double inc = (2 * Math.PI) / 50;
                double angle = step * inc + (float) 4;
                Vector v = new Vector();
                v.setX(Math.cos(angle) * radius);
                v.setZ(Math.sin(angle) * radius);
                ParticleEffect.FLAME.display(location);
                location.subtract(v);
                location.subtract(0, 0.1d, 0);
                if (location.getY() <= y) {
                    cancel();
                }
                step += 4;
                radius += 1 / 50f;
            }
        }.runTaskTimer(this.plugin, 0, 1);
    }
}
