package fr.hyriode.cosmetics.algorythm;

import fr.hyriode.cosmetics.utils.RGBValue;

import java.awt.*;

public class PowerConsciousHSV {
    private static final RGBValue r = new RGBValue(255);
    private static final RGBValue g = new RGBValue(0);
    private static final RGBValue b = new RGBValue(0);
    private static final int power = 5;

    public static Color run() {
        if (b.value() <= 1) {
            r.decrease(power);
            g.increase(power);
        }

        if (r.value() <= 1) {
            g.decrease(power);
            b.increase(power);
        }

        if (g.value() <= 1) {
            b.decrease(power);
            r.increase(power);
        }
        return new Color(r.value(), g.value(), b.value());
    }
}
