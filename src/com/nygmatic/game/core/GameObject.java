package com.nygmatic.game.core;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected int width, height;
    protected int velocityX, velocityY;
    protected ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public GameObject(int x, int y, int width, int height, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);
    public abstract Rectangle getBounds();

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setID(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }
    public int getY() { return y; }
    public ID getID() {
        return id;
    }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setVelocityX(int velocity) { this.velocityX = velocity; }
    public void setVelocityY(int velocity) { this.velocityY = velocity; }

    public int getVelocityX() {
        return velocityX;
    }
    public int getVelocityY() {
        return velocityY;
    }
}
