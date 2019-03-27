package com.brad.latch.entity.mob;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;

public class Traveller extends Mob {

    public Traveller(int x, int y) {
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), 0.8);
        animatedSprite = SpriteCollection.player_down;
        size = 32;
    }

    @Override
    public void update() {
        updateNPCMovement(
                SpriteCollection.straggler_up,
                SpriteCollection.straggler_down,
                SpriteCollection.straggler_left,
                SpriteCollection.straggler_right
        );
    }

    @Override
    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) (x - size/2), (int) (y - size/2), this);
    }

}