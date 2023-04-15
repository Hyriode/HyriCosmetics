package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.texture.BlockTexture;

import static java.lang.Math.PI;

public class StepInTheAirParticle  extends AbstractParticleImpl {

    private final Player player;

    private int ticks = 0;

    public StepInTheAirParticle(CosmeticUser user) {
        super(user, Cosmetics.STEP_IN_THE_AIR);

        this.player = user.asBukkit();
    }

    @Override
    public void onEquip(CosmeticUser user) {
        super.onEquip(user);
        user.setDoubleJumpEnabled(true);
    }

    @Override
    public void onUnequip(CosmeticUser user) {
        super.onUnequip(user);
        user.setDoubleJumpEnabled(false);
    }

    @Override @SuppressWarnings("deprecation")
    public void tick(CosmeticUser user) {
        new ParticleBuilder(ParticleEffect.BLOCK_CRACK, getLocation()).setParticleData(new BlockTexture(Material.WOOL)).setAmount(1).setSpeed(0.1F).display();

        if (player.isOnGround() && !player.getAllowFlight() && !player.isFlying()) {
            player.setAllowFlight(true);
        }

        if (isMoving() && !player.isOnGround() && !player.getAllowFlight()) {
            vortex(2, .05f, PI / 16, 3, 4 , ticks,
                    new Location(getLocation().getWorld(), getLocation().getX(), getLocation().getY() + 0.5, getLocation().getZ(), getLocation().getYaw(), getLocation().getPitch()));
        }

        if (ticks > 60) {
            ticks = 0;
        }
        ticks++;
    }

    private void vortex(float radius, float grow, double radials, int circles, int helixes, int step, Location loc) {
        for (int x = 0; x < circles; x++) {
            for (int i = 0; i < helixes; i++) {
                double angle = step * radials + (2 * PI * i / helixes);
                Vector v = new Vector(Math.cos(angle) * radius, step * grow, Math.sin(angle) * radius);
                MathUtil.rotateAroundAxisX(v, (loc.getPitch() + 90) * PI / 180);
                MathUtil.rotateAroundAxisY(v, -loc.getYaw() * PI / 180);

                loc.add(v);
                new ParticleBuilder(ParticleEffect.CLOUD, loc).setAmount(1).setSpeed(0).display();
                loc.subtract(v);
            }
            step++;
        }
    }
}
