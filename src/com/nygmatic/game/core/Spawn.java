package com.nygmatic.game.core;

import com.nygmatic.game.enemies.FastEnemy;
import com.nygmatic.game.HUD;
import com.nygmatic.game.Player;
import com.nygmatic.game.enemies.SimpleEnemy;

import static com.nygmatic.game.core.Game.HEIGHT;
import static com.nygmatic.game.core.Game.WIDTH;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private boolean initSpawn = false;

    private int scoreKeep = 0;
    private int levelUpReq = 250;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    //This method only runs once, just to spawn the first wave / player / etc.
    private void initialSpawn() {
        handler.addObject(new Player(
                100, 100, 32, 32,
                ID.Player, Utils.randomColor(), handler));

        for (int i = 0; i < 1; i++) {
            handler.addObject(new SimpleEnemy(Utils.randomLoc(WIDTH), Utils.randomLoc(HEIGHT),
                    16, 16, ID.SimpleEnemy, handler));
        }

        initSpawn = true;

    }

    public void tick() {

        if (!initSpawn)
            initialSpawn();

        scoreKeep++;

        if(scoreKeep % levelUpReq == 0) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            switch (hud.getLevel()) {

                // Only spawning 1 simple enemy per wave for the first 3 waves
                case 2:
                case 3:
                case 4:
                    handler.addObject(new SimpleEnemy(Utils.randomLoc(WIDTH), Utils.randomLoc(HEIGHT),
                            16, 16, ID.SimpleEnemy, handler));
                    break;

                // Spawning 1 fast enemy for the next 2 waves
                case 5:
                case 6:
                    handler.addObject(new FastEnemy(Utils.randomLoc(WIDTH), Utils.randomLoc(HEIGHT),
                            8, 8, ID.FastEnemy, handler));
                    break;

                // 2 simple enemies
                case 7:
                    for (int i = 0; i < 2; i++) {
                        handler.addObject(new SimpleEnemy(Utils.randomLoc(WIDTH), Utils.randomLoc(HEIGHT),
                                16, 16, ID.SimpleEnemy, handler));
                    }
                    break;

                // 2 fast
                case 8:
                    for (int i = 0; i < 2; i++) {
                        handler.addObject(new FastEnemy(Utils.randomLoc(WIDTH), Utils.randomLoc(HEIGHT),
                                8, 8, ID.FastEnemy, handler));
                    }
                    break;

                // 1 Simple 1 fast
                case 9:
                    handler.addObject(new SimpleEnemy(Utils.randomLoc(WIDTH), Utils.randomLoc(HEIGHT),
                            16, 16, ID.SimpleEnemy, handler));
                    handler.addObject(new FastEnemy(Utils.randomLoc(WIDTH), Utils.randomLoc(HEIGHT),
                            8, 8, ID.FastEnemy, handler));
                    break;
//
//                case 10:
//                    //reeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
//                    break;
            }
            }
        }
    }