package com.nygmatic.game.enemies;

import com.nygmatic.game.Trail;
import com.nygmatic.game.core.*;

import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;
    private boolean trail;
    private Color color = Color.yellow;

    public FastEnemy(int x, int y, int width, int height, ID id, Handler handler) {
        super(x, y, width, height, id);
        this.handler = handler;
        velocityX = 5;
        velocityY = 8;
        trail = true;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        if (trail)
            handler.addObject(new Trail(x, y, width, height, ID.Trail, color, 0.065f, handler));

        if (y <= 0 || y >= Game.HEIGHT - (height + 30)) {
            velocityY *= -1;
        }
        if (x <= 0 || x >= Game.WIDTH - (width + 5)) {
            velocityX *= -1;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
