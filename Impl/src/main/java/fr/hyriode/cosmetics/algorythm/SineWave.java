
package fr.hyriode.cosmetics.algorythm;

import fr.hyriode.cosmetics.utils.RGBValue;

import java.awt.*;

public class SineWave {
    private static final RGBValue r = new RGBValue(0);
    private static final RGBValue g = new RGBValue(0);
    private static final RGBValue b = new RGBValue(0);
    private static double colorFrequency = .05;
    private static int tickForLoop = 0;

    public static Color run() {
        r.setValue((int) getWithLimit(Math.sin(colorFrequency * tickForLoop + 0) * 127 + 128));
        g.setValue((int) getWithLimit(Math.sin(colorFrequency * tickForLoop + 2) * 127 + 128));
        b.setValue((int) getWithLimit( Math.sin(colorFrequency * tickForLoop + 4) * 127 + 128));

        if (++tickForLoop == 255) {
            tickForLoop = 0;
        }

        return new Color(r.value(), g.value(), b.value());
    }

    private static double getWithLimit(double n) {
        return Math.min(255, Math.max(0, n));
    }
}