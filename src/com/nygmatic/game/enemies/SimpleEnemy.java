package com.nygmatic.game.enemies;

import com.nygmatic.game.Trail;
import com.nygmatic.game.core.*;

import java.awt.*;

public class SimpleEnemy extends GameObject {

    private Color color = Color.RED;
    private Handler handler;
    private boolean trail;

    public SimpleEnemy(int x, int y, int width, int height, ID id, Handler handler) {
        super(x, y, width, height, id);
        this.handler = handler;
        velocityX = 3;
        velocityY = 3;
        trail = true;
    }

    //Trails are the same color as the enemy that they spawn on/for/whatever-you-call-it
    public SimpleEnemy(int x, int y, int width, int height, ID id, Handler handler, boolean trail) {
        super(x, y, width, height, id);
        this.handler = handler;
        this.trail = trail;
        velocityX = 3;
        velocityY = 3;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;

        if (trail)
            handler.addObject(new Trail(x, y, width, height, ID.Trail, color, 0.03f, handler));

        if (y <= 0 || y >= Game.HEIGHT - (height + 30)) {
            velocityY *= -1;
            color = Utils.randomColor();
        }
        if (x <= 0 || x >= Game.WIDTH - (width + 5)) {
            velocityX *= -1;
            color = Utils.randomColor();
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
