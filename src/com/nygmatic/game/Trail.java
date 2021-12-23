package com.nygmatic.game;

import com.nygmatic.game.core.GameObject;
import com.nygmatic.game.core.Handler;
import com.nygmatic.game.core.ID;

import java.awt.*;

public class Trail extends GameObject {

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private float life;

    // Life lasts longer the smaller the number is = 0.001 - 0.1

    public Trail(int x, int y, int width, int height, ID id, Color color, float life, Handler handler) {
        super (x, y, width, height, id);
        this.handler = handler;
        this.color = color;
        this.life = life;
    }

    @Override
    public void tick() {
        if (alpha > life) {
            alpha -= (life - 0.001f);
        } else {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setComposite(makeTransparent(alpha));
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
        graphics2D.setComposite(makeTransparent(1));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }
}
