package com.nygmatic.game;

import com.nygmatic.game.core.*;

import java.awt.*;

public class Player extends GameObject {

    static int health;
    private int speed;
    private int damageAmount;
    private Handler handler;
    private Color color;

    public Player(int x, int y, int width, int height, ID id, Color color, Handler handler) {
        super(x, y, width, height, id);
        this.handler = handler;
        this.color = color;
        health = 100;
        speed = 5;
        damageAmount = 2;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        x = Utils.clamp(x, 0, Game.WIDTH - (width + 5));
        y = Utils.clamp(y, 0, Game.HEIGHT - (height + 30));
        collisionDetection();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.black);
        graphics.drawRect(x, y, width, height);
    }

    private void collisionDetection() {
        //loop through all objects currently alive
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject selected = handler.getObjects().get(i);

            //Check to see if the object is tagged with an enemy tag
            if(selected.getID() == ID.SimpleEnemy || selected.getID() == ID.FastEnemy) {

                //collision detection
                if (getBounds().intersects(selected.getBounds())) {
                    health -= damageAmount;
                }
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
}
