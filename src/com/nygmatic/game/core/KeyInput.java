package com.nygmatic.game.core;

import com.nygmatic.game.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyPressed = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        for (int i = 0; i < keyPressed.length; i++) {
            keyPressed[i] = false;
        }
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        for(int i = 0; i < handler.getObjects().size(); i++) {
            GameObject selected = handler.getObjects().get(i);

            if (selected.getID() == ID.Player) {
                Player player = (Player) selected;

                switch (key) {

                    case (KeyEvent.VK_W):
                        selected.setVelocityY(-player.getSpeed());
                        keyPressed[0] = true;
                        break;

                    case (KeyEvent.VK_S):
                        selected.setVelocityY(player.getSpeed());
                        keyPressed[1] = true;
                        break;

                    case (KeyEvent.VK_A):
                        selected.setVelocityX(-player.getSpeed());
                        keyPressed[2] = true;
                        break;

                    case (KeyEvent.VK_D):
                        selected.setVelocityX(player.getSpeed());
                        keyPressed[3] = true;
                        break;

                    default:
                        break;
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();

        for(int i = 0; i < handler.getObjects().size(); i++) {
            GameObject selected = handler.getObjects().get(i);

            if (selected.getID() == ID.Player) {
                switch (key) {

                    case (KeyEvent.VK_W):
                        keyPressed[0] = false;
                        break;

                    case (KeyEvent.VK_S):
                        keyPressed[1] = false;
                        break;

                    case (KeyEvent.VK_A):
                        keyPressed[2] = false;
                        break;

                    case (KeyEvent.VK_D):
                        keyPressed[3] = false;
                        break;
                }

                //Vertical movement
                if (!keyPressed[0] && !keyPressed[1]) selected.setVelocityY(0);

                //Horizontal
                if (!keyPressed[2] && !keyPressed[3]) selected.setVelocityX(0);
            }
        }
    }

}
