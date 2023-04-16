package fr.hyriode.cosmetics.algorithm;

import fr.hyriode.cosmetics.utils.RGBValue;

import java.awt.*;

public class RegularHSV {
    private static final RGBValue r = new RGBValue(255);
    private static final RGBValue g = new RGBValue(0);
    private static final RGBValue b = new RGBValue(0);
    private static final int power = 5;

    public static Color run() {
        if (r.value() == 255 && b.value() == 0) {
            g.increase(power);
        }

        if (g.value() == 255 && b.value() == 0) {
            r.decrease(power);
        }

        if (g.value() == 255 && r.value() == 0) {
            b.increase(power);
        }

        if (b.value() == 255 && r.value() == 0) {
            g.decrease(power);
        }

        if (b.value() == 255 && g.value() == 0) {
            r.increase(power);
        }

        if (r.value() == 255 && g.value() == 0) {
            b.decrease(power);
        }

        return new Color(r.value(), g.value(), b.value());
    }
}
