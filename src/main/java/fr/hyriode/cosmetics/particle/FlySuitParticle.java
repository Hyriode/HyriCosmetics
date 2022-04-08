package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.api.HyriCosmeticRarity;
import fr.hyriode.cosmetics.api.HyriCosmeticType;
import fr.hyriode.cosmetics.api.HyriCosmeticsAPI;
import fr.hyriode.cosmetics.api.HyriScheduledCosmetic;
import fr.hyriode.cosmetics.api.particle.HyriParticle;
import fr.hyriode.cosmetics.api.particle.HyriParticleName;
import fr.hyriode.cosmetics.api.util.MathUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.ParticlePacket;

import java.awt.*;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 03/04/2022 at 11:44
 */
public class FlySuitParticle extends HyriParticle {

    private final HyriCosmetics plugin;

    private float step = 0;

    public FlySuitParticle(HyriCosmetics plugin) {
        super(
                HyriParticleName.FLY_SUIT,
                HyriCosmeticType.Default.PARTICLE,
                HyriCosmeticRarity.LEGENDARY,
                2500,
                250,
                Material.FLINT
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
                draw(player);
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

    private void draw(Player player) {
        for (int i = 0; i < 2; i++) {
            double inc = (2 * Math.PI) / 100;
            double toAdd = 0;
            if (i == 1)
                toAdd = 3.5;
            double angle = step * inc + toAdd;
            Vector v = new Vector();
            v.setX(Math.cos(angle));
            v.setZ(Math.sin(angle));
            if (i == 0) {
                MathUtils.rotateAroundAxisZ(v, 180);
            } else {
                MathUtils.rotateAroundAxisZ(v, 90);
            }
            new ParticleBuilder(ParticleEffect.VILLAGER_HAPPY, player.getLocation().clone().add(0, 1, 0).add(v))
                    .display();
        }
        step += 3;
    }


}
