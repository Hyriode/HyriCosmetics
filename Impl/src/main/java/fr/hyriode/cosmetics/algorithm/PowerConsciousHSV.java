package fr.hyriode.cosmetics.algorithm;

import fr.hyriode.cosmetics.utils.RGBValue;

import java.awt.*;

public class PowerConsciousHSV {

    private static final int power = 5;

    private final RGBValue r = new RGBValue(255);
    private final RGBValue g = new RGBValue(0);
    private final RGBValue b = new RGBValue(0);

    public Color run() {
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
