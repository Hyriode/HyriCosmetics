package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.api.HyriCosmeticRarity;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import fr.hyriode.cosmetics.api.HyriCosmeticsAPI;
import fr.hyriode.cosmetics.api.HyriScheduledCosmetic;
import fr.hyriode.cosmetics.api.particle.HyriParticle;
import fr.hyriode.cosmetics.api.particle.HyriParticleName;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 03/04/2022 at 11:22
 */
public class SpeedParticle extends HyriParticle {

    private final HyriCosmetics plugin;

    public SpeedParticle(HyriCosmetics plugin) {
        super(
                HyriParticleName.SPEED,
                HyriCosmeticType.Default.PARTICLE,
                HyriCosmeticRarity.EPIC,
                150,
                15,
                Material.SUGAR
        );

        this.plugin = plugin;
    }

    @Override
    public void play(Player player) {
        player.setWalkSpeed(0.4F);
        HyriCosmeticsAPI.get().getCosmeticsManager().playScheduledCosmetic(player, this.plugin, this.getScheduledCosmetic(), this);
    }

    @Override
    public HyriScheduledCosmetic getScheduledCosmetic() {
        return new HyriScheduledCosmetic() {
            @Override
            public boolean tick(Player player) {

                for (double i = 0; i < 1; i+=0.05) {
                    new ParticleBuilder(ParticleEffect.FIREWORKS_SPARK, player.getLocation().add(0, i, 0))
                            .display();
                }

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
