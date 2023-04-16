package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.algorithm.PowerConsciousHSV;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.MathUtil;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;

public class RainbowTwinsParticle extends AbstractParticleImpl {

    private final static int orbs = 2;
    private final static double radius = 1;
    private final static int numSteps = 60;
    private final static int maxStepY = 30;

    private int stepX = 0;
    private int stepY = 0;
    private boolean reverse = false;

    public RainbowTwinsParticle(CosmeticUser user) {
        super(user, Cosmetics.RAINBOW_TWINS);
    }

    @Override
    public void tick(CosmeticUser user) {
        if (isMoving()) {
                new ParticleBuilder(ParticleEffect.REDSTONE, getLocation().clone().add(0, 0.8, 0))
                        .setAmount(4)
                        .setSpeed(0)
                        .setParticleData(new RegularColor(PowerConsciousHSV.run()))
                        .display();
            return;
        }
        this.stepX++;
        if (this.stepX > numSteps) {
            this.stepX = 0;
        }

        if (this.reverse) {
            this.stepY++;
            if (this.stepY > maxStepY)
                this.reverse = false;
        } else {
            this.stepY--;
            if (this.stepY < -maxStepY)
                this.reverse = true;
        }

        for (int i = 0; i < orbs; i++) {
            double slice = Math.PI * 2 / numSteps;
            double orbSlice = Math.PI * 2 / orbs;

            double dx = -MathUtil.cos(slice * this.stepX + orbSlice * i) * radius;
            double dy = (this.stepY / (double) maxStepY);
            double dz = -MathUtil.sin(slice * this.stepX + orbSlice * i) * radius;
            new ParticleBuilder(ParticleEffect.REDSTONE, getLocation().clone().add(dx, dy+ 0.8, dz))
                    .setAmount(4)
                    .setSpeed(0)
                    .setParticleData(new RegularColor(PowerConsciousHSV.run()))
                    .display();
        }
    }
}
