import processing.core.PVector;

import static processing.core.PApplet.map;

public class Direction extends PVector {
    float posX;
    float posY;
    float vectorNoiseCX;
    float vectorNoiseCY;

    public Direction(float x, float y, float posX, float posY) {
        this.x = x;
        this.y = y;
        this.posX = posX;
        this.posY = posY;
        this.vectorNoiseCX = (float) Math.random() * 10;
        this.vectorNoiseCY = (float) Math.random() * 10;
    }

    public void show(Main ctx) {
        ctx.line(this.posX, this.posY, this.posX + this.x, this.posY + this.y);
        //ctx.point(this.posX, this.posY);
    }

    public void update(float newX, float newY) {
        this.x = map(newX, 0, 1, -1, 1);
        this.y = map(newY, 0, 1, -1, 1);
        vectorNoiseCX += 0.002;
        vectorNoiseCY += 0.002;
        this.setMag(10);
    }
}
