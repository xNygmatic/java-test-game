package com.nygmatic.game.core;

import java.awt.*;
import java.util.Random;

public class Utils {

    private static Random random = new Random();

    public static Color randomColor() {
        return Color.getHSBColor(random.nextFloat(), 1f, 1f);
    }

    public static int randomLoc(int bounds) {
        return random.nextInt(bounds - 50);
    }

    public static int clamp(int variable, int minimum, int maximum) {
        return Math.max(minimum, Math.min(maximum, variable));
    }
}
