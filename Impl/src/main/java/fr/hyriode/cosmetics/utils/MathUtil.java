package fr.hyriode.cosmetics.utils;

import org.bukkit.util.Vector;

import java.security.SecureRandom;
import java.util.Random;

public class MathUtil {

    private final static Random random = new SecureRandom();


    public static Vector rotateAroundAxisY(Vector vector, double d) {
        final double cos = Math.cos(d);
        final double sin = Math.sin(d);
        final double x = vector.getX() * cos + vector.getZ() * sin;
        final double z = vector.getX() * -sin + vector.getZ() * cos;
        return vector.setX(x).setZ(z);
    }
}