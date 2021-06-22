package com.github.martinfrank.endlessbuilding.gui;

public class ScaleFactor {

    public static final ScaleFactor SMALL = new ScaleFactor(7.5, 31, 31);
    public static final ScaleFactor MEDIUM = new ScaleFactor(15, 60, 60);
    public static final ScaleFactor BIG = new ScaleFactor(30, 120, 120);

    public final double scaleFactor;
    public final double tileWidth;
    public final double tileHeight;

    private ScaleFactor(double scaleFactor, int tileWidth, int tileHeight) {
        this.scaleFactor = scaleFactor;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }
}
