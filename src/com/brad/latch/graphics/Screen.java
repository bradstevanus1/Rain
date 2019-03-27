package com.brad.latch.graphics;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.entity.mob.Straggler;
import com.brad.latch.entity.mob.Traveller;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.level.tile.Tile;

import java.util.Random;

/**
 * Screen is responsible for handling how each type of object is rendered in the game.
 * Its methods fill the pixels array with RGB colours. This data is then handed back to
 * the Game class to be rendered by rasterized buffered image \s.
 */
public class Screen {

    // TODO Refactor to make all objects be rendered by renderSprite

    public int width, height;
    public int[] pixels;
    public int xOffset, yOffset;
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        // The array to be cast to and rendered as a buffered image. If you want to render anything,
        // simply fill this array with the correct pixel colours.
        pixels = new int[width * height];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xOrigin, int yOrigin, Sprite sprite, boolean fixed) {
        // The origin variables represent the top left coordinate of the screen
        if (fixed) {
            xOrigin -= xOffset;
            yOrigin -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int yRelToScreen = y + yOrigin;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xRelToScreen = x + xOrigin;
                if (xRelToScreen < 0 || xRelToScreen >= width || yRelToScreen < 0 || yRelToScreen >= height) continue;
                pixels[xRelToScreen + yRelToScreen * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }

    /*
    public void renderSheet(int xOrigin, int yOrigin, SpriteSheet sheet, boolean fixed) {
        // The origin variables represent the top left coordinate of the screen
        if (fixed) {
            xOrigin -= xOffset;
            yOrigin -= yOffset;
        }
        for (int y = 0; y < sheet.HEIGHT; y++) {
            int yRelToScreen = y + yOrigin;
            for (int x = 0; x < sheet.WIDTH; x++) {
                int xRelToScreen = x + xOrigin;
                if (xRelToScreen < 0 || xRelToScreen >= width || yRelToScreen < 0 || yRelToScreen >= height) continue;
                pixels[xRelToScreen + yRelToScreen * width] = sheet.pixels[x + y * sheet.WIDTH];
            }
        }
    }
    */

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yDelta = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xDelta = x + xp;
                if (xDelta < -tile.sprite.SIZE || xDelta >= width || yDelta < 0 || yDelta >= height) break;
                if (xDelta < 0) xDelta = 0;
                pixels[xDelta + yDelta * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }


    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++) {
            int yDelta = y + yp;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xDelta = x + xp;
                if (xDelta < -p.getSpriteSize() || xDelta >= width || yDelta < 0 || yDelta >= height) break;
                if (xDelta < 0) xDelta = 0;
                int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
                if (col != 0xffff00ff) pixels[xDelta + yDelta * width] = col;
            }
        }
    }

    /*
     * This method renders the player onto the screen.
     * xs is x but flipped depending on the direction to
     * show reversed sprite direction.
     */
    // TODO Merge this method (used for player) with other renderMob
    @SuppressWarnings("Duplicates")
    public void renderMob(int xp, int yp, Sprite sprite, int flip) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < Player.getSize(); y++) {
            int yDelta = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) {
                ys = (Player.getSize() - 1) - y;
            }
            for (int x = 0; x < Player.getSize(); x++) {
                int xDelta = x + xp;
                int xs = x;
                if (flip == 1 || flip == 3) {
                    xs = (Player.getSize() - 1) - x;
                }
                if (xDelta < -Player.getSize() || xDelta >= width || yDelta < 0 || yDelta >= height) break;
                if (xDelta < 0) xDelta = 0;
                int col = sprite.pixels[xs + ys * Player.getSize()];
                if (col != 0xffff00ff) pixels[xDelta + yDelta * width] = col;
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void renderMob(int xOrigin, int yOrigin, Mob mob) {
        xOrigin -= xOffset;
        yOrigin -= yOffset;
        for (int y = 0; y < Player.getSize(); y++) {
            int yRelToScreen = y + yOrigin;
            for (int x = 0; x < Player.getSize(); x++) {
                int xRelToScreen = x + xOrigin;
                if (xRelToScreen < -Player.getSize() || xRelToScreen >= width || yRelToScreen < 0 || yRelToScreen >= height) break;
                if (xRelToScreen < 0) xRelToScreen = 0;
                int colour = mob.getSprite().pixels[x + y * Player.getSize()];
                if (mob instanceof Traveller && colour == 0xFF2084CC) colour = 0xFF36B72A;
                else if (mob instanceof Straggler && colour == 0xFF2084CC) colour = 0xFFF70E1A;
                if (colour != 0xffff00ff) pixels[xRelToScreen + yRelToScreen * width] = colour;
            }
        }
    }


    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}