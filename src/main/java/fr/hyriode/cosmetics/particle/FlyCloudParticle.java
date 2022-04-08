package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.api.*;
import fr.hyriode.cosmetics.api.particle.HyriParticle;
import fr.hyriode.cosmetics.api.particle.HyriParticleName;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 03/04/2022 at 17:15
 */
public class FlyCloudParticle extends HyriParticle {

    private final HyriCosmetics plugin;

    public FlyCloudParticle(HyriCosmetics plugin) {
        super(
                HyriParticleName.FLY_CLOUD,
                HyriCosmeticType.Default.PARTICLE,
                HyriCosmeticRarity.EPIC,
                2500,
                25,
                Material.FEATHER
        );

        this.plugin = plugin;
    }

    @Override
    public void play(Player player) {
        player.setAllowFlight(true);
        HyriCosmeticsAPI.get().getCosmeticsManager().playScheduledCosmetic(player, this.plugin, this.getScheduledCosmetic(), this);
    }

    @Override
    public HyriScheduledCosmetic getScheduledCosmetic() {
        return new HyriScheduledCosmetic() {
            @Override
            public boolean tick(Player player) {
                new ParticleBuilder(ParticleEffect.CLOUD, player.getLocation().add(0, 0.1, 0))
                        .display();
                return false;
            }

            @Override
            public int tickDuration() {
                return 1;
            }

            @Override
            public int duration() {
                return -1;
            }
        };
    }
}
