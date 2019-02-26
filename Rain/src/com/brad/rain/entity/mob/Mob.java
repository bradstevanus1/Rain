package com.brad.rain.entity.mob;

import com.brad.rain.entity.Entity;
import com.brad.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;

    public void move(int xa, int ya) {
        // TODO If else if's do not work, replace all with if's
        if (xa > 0) dir = 1;
        else if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        else if (ya < 0) dir = 0;

        if (!collision()) {
            x += xa;
            y += ya;
        }
    }

    public void update() {

    }

    private boolean collision() {
        return false;
    }

    public void render() {

    }

}
