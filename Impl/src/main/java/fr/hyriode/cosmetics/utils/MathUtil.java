package fr.hyriode.cosmetics.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {

    private static final Random random = ThreadLocalRandom.current();

    private static final int SIN_BITS, SIN_MASK, SIN_COUNT;
    private static final double radFull, radToIndex;
    private static final double degFull, degToIndex;
    private static final double[] sin, cos;

    static {
        SIN_BITS = 12;
        SIN_MASK = ~(-1 << SIN_BITS);
        SIN_COUNT = SIN_MASK + 1;

        radFull = Math.PI * 2.0;
        degFull = 360.0;
        radToIndex = SIN_COUNT / radFull;
        degToIndex = SIN_COUNT / degFull;

        sin = new double[SIN_COUNT];
        cos = new double[SIN_COUNT];

        for (int i = 0; i < SIN_COUNT; i++) {
            sin[i] = Math.sin((i + 0.5f) / SIN_COUNT * radFull);
            cos[i] = Math.cos((i + 0.5f) / SIN_COUNT * radFull);
        }

        for (int i = 0; i < 360; i += 90) {
            sin[(int) (i * degToIndex) & SIN_MASK] = Math.sin(i * Math.PI / 180.0);
            cos[(int) (i * degToIndex) & SIN_MASK] = Math.cos(i * Math.PI / 180.0);
        }
    }

    public static final Vector rotateAroundAxisX(Vector v, double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static final Vector rotateAroundAxisY(Vector v, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static double sin(double rad) {
        return sin[(int) (rad * radToIndex) & SIN_MASK];
    }

    public static double cos(double rad) {
        return cos[(int) (rad * radToIndex) & SIN_MASK];
    }

    public static Vector getRandomVector() {
        double d1 = random.nextDouble() * 2.0D - 1.0D;
        double d2 = random.nextDouble() * 2.0D - 1.0D;
        double d3 = random.nextDouble() * 2.0D - 1.0D;
        return (new Vector(d1, d2, d3)).normalize();
    }

    public static Vector calculateVectorBetweenTwoPoints(Location from, Location to) {
        double dX = from.getX() - to.getX();
        double dY = from.getY() - to.getY();
        double dZ = from.getZ() - to.getZ();

        double yaw = Math.atan2(dZ, dX);

        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;

        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);

        return new Vector(x, z, y);
    }
}