package com.nygmatic.game.core;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    private LinkedList<GameObject> objects = new LinkedList<>();

    public void tick() {
        // DON'T TRUST THE IDE, IT LIES! NO NO NO NO FOREACH, BAD BAD BAD!!!
        for (int i = 0; i < objects.size(); i++) {
            GameObject selected = objects.get(i);
            selected.tick();
        }
    }

    public void render(Graphics graphics) {
        // DON'T TRUST THE IDE, IT LIES! NO NO NO NO FOREACH, BAD BAD BAD!!!
        for (int i = 0; i < objects.size(); i++) {
            GameObject selected = objects.get(i);
            selected.render(graphics);
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

    public LinkedList<GameObject> getObjects() {
        return this.objects;
    }
}
