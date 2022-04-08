package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.api.HyriCosmeticRarity;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import fr.hyriode.cosmetics.api.HyriCosmeticsAPI;
import fr.hyriode.cosmetics.api.HyriScheduledCosmetic;
import fr.hyriode.cosmetics.api.particle.HyriParticle;
import fr.hyriode.cosmetics.api.particle.HyriParticleName;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleEffect;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 02/04/2022 at 19:07
 */
public class WaterCircleParticle extends HyriParticle {

    private final HyriCosmetics plugin;

    public WaterCircleParticle(HyriCosmetics plugin) {
        super(
                HyriParticleName.WATER_CIRCLE,
                HyriCosmeticType.Default.PARTICLE,
                HyriCosmeticRarity.RARE,
                150,
                15,
                Material.WATER_BUCKET
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
                playCircle(player.getLocation());
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

    private void playCircle(final Location center) {
        for(double angle = 0; angle < 360; angle += 5) {
            double x = Math.sin(angle);
            double z = Math.cos(angle);
            ParticleEffect.WATER_WAKE.display(center.clone().add(x, 0.15, z));
        }
    }

}
