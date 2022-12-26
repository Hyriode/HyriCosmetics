package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.api.rank.type.HyriPlayerRankType;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.particle.util.EffectUtil;
import fr.hyriode.hyrame.item.ItemBuilder;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.security.SecureRandom;
import java.util.Random;

public class EnchantedParticle extends AbstractParticleImpl {

    private static final Random random = new SecureRandom();

    public EnchantedParticle(final Player player) {
        super(
                "enchanted",
                player,
                CosmeticRarity.RARE,
                HyriPlayerRankType.PLAYER,
                150,
                15000,
                new ItemBuilder(Material.BLAZE_POWDER).withGlow()
        );
    }

    @Override
    public void tick() {
        final Location location = getLocation();
        float dx = (float) location.getX() + random.nextFloat() % 3f - 1.5f;
        float dy = (float) location.getY() + random.nextFloat() % 2f;
        float dz = (float) location.getZ() + random.nextFloat() % 3f - 1.5f;
        this.display(EnumParticle.ENCHANTMENT_TABLE, dx, dy, dz, 0, 0, 0, 0, 1);
    }

}
