import processing.core.PVector;

import java.util.ArrayList;

public class Particle extends PVector {
    float posX;
    float posY;
    ArrayList<Particle> previous;

    public Particle(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        this.previous = new ArrayList<>();
    }

    public void show(Main ctx, int rgb) {
        ctx.stroke(rgb);
        ctx.point(this.posX, this.posY);
    }

    public void update(Main ctx) {
        setMag(1);
        this.posX += this.x;
        this.posY += this.y;
        if ((int) Math.floor(posX / Main.fieldSize) < Main.fieldSize && (int) Math.floor(posY / Main.fieldSize) < Main.fieldSize
                && (int) Math.floor(posX / Main.fieldSize) >= 0 && (int) Math.floor(posY / Main.fieldSize) >= 0) {
            PVector nearest = Main.fields[(int) Math.floor(posX / Main.fieldSize)][(int) Math.floor(posY / Main.fieldSize)];
            this.x = (nearest.x + this.x) / 2;
            this.y = (nearest.y + this.y) / 2;
            //previous handling

            previous.add(0, this.cloneForPrevious());
            if (previous.size() > 256) {
                previous.remove(256);
            }
            for (int i = 0; i < previous.size(); i++) {
                previous.get(i).show(ctx, i);
            }
        }
    }


    public Particle cloneForPrevious() {
        return new Particle(this.posX, this.posY);
    }
}
