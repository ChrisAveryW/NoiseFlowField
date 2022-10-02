import processing.core.PApplet;

public class Main extends PApplet {
    final static int windowSize = 600;
    final static int fieldSpace = 20;
    final static int fieldSize = windowSize / fieldSpace;
    final static int particlesCount = 300;
    int millisCount = 0;
    static Direction [][]  fields = new Direction[fieldSize][fieldSize];
    Particle[] particles = new Particle[particlesCount];

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    @Override
    public void settings() {
        size(windowSize, windowSize);
    }

    @Override
    public void setup() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                fields[i][j] = new Direction(random(-1, 1), random(-1, 1), i * fieldSpace, j * fieldSpace);
            }
        }
        for(int i = 0; i < particles.length; i ++){
            particles[i] = new Particle(random(windowSize), random(windowSize));
        }
    }

    @Override
    public void draw() {
        background(255);
        strokeWeight(1);
        stroke(0);
        updateAllDirections();
        //showAllDirections(this);
        updateAllParticles();
        //showAllParticles(this);
    }

    private void showAllParticles(Main main) {
        for(int i = 0; i < particles.length; i++){
            particles[i].show(main, 0);
        }
    }

    private void updateAllParticles() {
        for(int i = 0; i < particles.length; i++){
            particles[i].update(this);
        }
    }

    private void showAllDirections(Main main) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                fields[i][j].show(main);
            }
        }
    }

    private void updateAllDirections() {
        //if (millisCount < millis()) {
            millisCount = millis();
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    fields[i][j].update(noise(fields[i][j].vectorNoiseCX), noise(fields[i][j].vectorNoiseCY));
                }
            }
        //}
    }
}