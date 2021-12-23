package com.nygmatic.game;

import com.nygmatic.game.core.Utils;

import java.awt.*;

public class HUD {

    private int greenVal = 255;
    private int score = 0;
    private int level = 1;

    public void tick() {
        Player.health = Utils.clamp(Player.health, 0, 100);
        greenVal = Utils.clamp(greenVal, 0, 255);
        greenVal = Player.health*2;

        score++;
    }

    public void render(Graphics graphics) {

        graphics.setColor(new Color(75, greenVal, 0));
        graphics.fillRect(15, 15, Player.health * 2, 32);

        //That "crisp" black outline
        graphics.setColor(Color.black);
        graphics.drawRect(15, 15, Player.health*2, 32);

        graphics.drawString("Score: " + score, 15, 70);
        graphics.drawString("Wave: " + level, 16, 90);
    }

    public void score(int score) { this.score = score; }
    public void setLevel(int level) { this.level = level; }
    public int getScore() { return this.score; }
    public int getLevel() { return this.level; }
}
