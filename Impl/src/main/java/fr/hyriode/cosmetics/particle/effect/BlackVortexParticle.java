package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.Material;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;
import xyz.xenondevs.particle.data.texture.BlockTexture;

import java.awt.*;

public class BlackVortexParticle extends AbstractParticleImpl {

    private static final double MAX_HEIGHT = 0.8D;
    private static final int LINES = 5;
    private static final double HEIGHT_INCREMENT = 0.1D;
    private static final double ANGLE_SPEED = 1.5;

    private int tick = 0;

    public BlackVortexParticle(CosmeticUser user) {
        super(user, Cosmetic.BLACK_VORTEX, false);
    }

    @Override
    public void tick(final CosmeticUser user) {
        this.tick++;

        if (isMoving()) {
            Location location = getPlayer().getLocation().clone().subtract(0.0D, 0.3D, 0.0D);
            double angle = this.tick * ANGLE_SPEED;

            tornado(MAX_HEIGHT, LINES, HEIGHT_INCREMENT, angle, location,
                    new ParticleBuilder(ParticleEffect.REDSTONE).setParticleData(new RegularColor(Color.BLACK)),
                    new ParticleBuilder(ParticleEffect.PORTAL)
            );
        }
    }

    public void tornado(double max_height, int lines, double height_increment, double angle, Location location, ParticleBuilder firstColor, ParticleBuilder secondColor) {
        for (int l = 0; l < lines; l++) {
            for (double y = 0.0D; y < max_height; y += height_increment) {
                double radius = y * 2;
                double x = Math.cos(Math.toRadians((360 / lines * l) + y * 25.0D - angle)) * radius;
                double z = Math.sin(Math.toRadians((360 / lines * l) + y * 25.0D - angle)) * radius;
                particles(firstColor, (float) ((float) location.getX() + x), (float) ((float) location.getY() + y), (float) ((float) location.getZ() + z), 0.0F, 0.0F, 0.0F);
                particles(secondColor, (float) ((float) location.getX() + x), (float) ((float) location.getY() + y), (float) ((float) location.getZ() + z), 0.0F, 0.0F, 0.0F);
            }
        }
    }

    private void particles(ParticleBuilder particle, float x, float y, float z, float offsetX, float offsetY, float offsetZ) {
        particle.setLocation(new Location(getPlayer().getWorld(), x, y, z)).setOffset(offsetX, offsetY, offsetZ).display();
    }
}