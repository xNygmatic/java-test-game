package com.nygmatic.game.core;

import com.nygmatic.game.HUD;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    public static final String title = "Placeholder";
    private Thread thread;
    private Handler handler;
    private HUD hud;
    private Spawn spawn;
    private boolean running = false;

    public static void main(String[] args) { new Game(); }

    public Game() {
        new Window(WIDTH, HEIGHT, title, this);
        start();
    }

    public synchronized void start() {
        if (running) return;
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        if (!running) return;
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        handler = new Handler();
        hud = new HUD();
        spawn = new Spawn(handler, hud);
        this.addKeyListener(new KeyInput(handler));
        this.requestFocus();
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        double tickAmount = 60.0;
        double ns = 1000000000 / tickAmount;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        //Update all currently alive game objects
        handler.tick();

        //Update the player hud
        hud.tick();

        //Update the spawning
        spawn.tick();
    }

    private void render() {
        BufferStrategy buffer = this.getBufferStrategy();

        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = buffer.getDrawGraphics();

        // Drawing the background
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0,0, WIDTH, HEIGHT);

        //Rendering all game objects
        handler.render(graphics);

        //Rendering HUD on top of all objects in the game
        hud.render(graphics);

        graphics.dispose();
        buffer.show();

    }
}
