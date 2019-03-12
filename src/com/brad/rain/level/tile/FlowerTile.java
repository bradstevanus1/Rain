package com.brad.rain.level.tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;

public class FlowerTile extends Tile {

    public FlowerTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeExp2(), y << Tile.getTileSizeExp2(), this);
    }
}
