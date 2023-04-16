package fr.hyriode.cosmetics.pet;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.Navigation;
import net.minecraft.server.v1_8_R3.PathEntity;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import org.bukkit.Location;

public class PetPathFinder extends PathfinderGoal {

    private final double speed;
    private final Location loc;
    private final Navigation navigation;

    public PetPathFinder(EntityInsentient entity, Location loc, double speed) {
        this.loc = loc;
        this.navigation = (Navigation) entity.getNavigation();
        this.speed = speed;
    }

    @Override
    public boolean a() {
        return true;
    }

    public void c() {
        PathEntity pathEntity = this.navigation.a(loc.getX(), loc.getY(), loc.getZ());

        this.navigation.a(pathEntity, speed);
    }
}