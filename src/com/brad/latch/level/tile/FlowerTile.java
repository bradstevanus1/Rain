package com.brad.latch.level.tile;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;

public class FlowerTile extends Tile {

    public FlowerTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeSqrt(), y << Tile.getTileSizeSqrt(), this);
    }
}
