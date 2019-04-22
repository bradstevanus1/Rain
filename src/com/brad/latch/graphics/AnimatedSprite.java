package com.brad.latch.graphics;

/**
 * An animated sprite has multiple sprites which are chosen
 * based on how the mob is moving.
 */
public class AnimatedSprite extends Sprite {

    private int frame = 0;
    private Sprite sprite;
    private int frameRate = 5;
    private int time = 0;
    private int length;

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
        super(sheet, width, height);
        this.length = length;
        sprite = sheet.getSprites()[0];
        if (length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long");
    }

    public void update() {
        time++;
        if (time % frameRate == 0) {
            if (frame >= length - 1) frame = 0;
            else frame++;
            sprite = sheet.getSprites()[frame];
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public void setFrame(int frame) {
        if (frame > sheet.getSprites().length - 1) {
            System.err.println("Index out of bounds in " + this);
            return;
        }
        sprite = sheet.getSprites()[frame];
    }
}
