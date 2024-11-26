package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;


public class Trail {
    private static final double INITIAL_OPACITY = 0.7;
    private static final double FADE_SPEED = 0.03;
    private static final double MIN_SIZE = 4.0;
    private static final double SIZE_VARIATION = 4.0;
    private static final double POSITION_X_VARIATION = 3.0;
    private static final double POSITION_Y_VARIATION = 5.0;

    private static final int BASE_RED = 150;
    private static final int RED_VARIATION = 50;
    private static final int BASE_GREEN = 0;
    private static final int GREEN_VARIATION = 50;
    private static final int BLUE = 255;

    private static final Random RANDOM = new Random();

    private final Rectangle shape;
    private double opacity;

    /**
     * Crée une nouvelle particule de traînée
     * @param x Position X de base
     * @param y Position Y de base
     * @param rotation Rotation actuelle du joueur
     * @param maxY Position Y maximum autorisée
     */
    public Trail(double x, double y, double rotation, double maxY) {
        this.opacity = INITIAL_OPACITY;
        this.shape = createTrailParticle(x, y);
    }

    private Rectangle createTrailParticle(double x, double y) {
        double size = MIN_SIZE + RANDOM.nextDouble() * SIZE_VARIATION;
        Rectangle particle = new Rectangle(size, size);


        particle.setX(x + randomOffset(POSITION_X_VARIATION));
        particle.setY(y + randomOffset(POSITION_Y_VARIATION));

 
        particle.setFill(createRandomPurpleColor());

        return particle;
    }

    private Color createRandomPurpleColor() {
        return Color.rgb(
                BASE_RED + RANDOM.nextInt(RED_VARIATION),
                BASE_GREEN + RANDOM.nextInt(GREEN_VARIATION),
                BLUE,
                opacity
        );
    }

    private double randomOffset(double range) {
        return RANDOM.nextDouble() * range * 2 - range;
    }

    /**
     * Met à jour l'état de la particule
     * @return true si la particule doit être supprimée, false sinon
     */
    public boolean update() {
        opacity -= FADE_SPEED;
        shape.setOpacity(opacity);
        return opacity <= 0;
    }

    /**
     * Retourne la forme graphique de la particule
     * @return Rectangle représentant la particule
     */
    public Rectangle getShape() {
        return shape;
    }

    /**
     * Retourne l'opacité actuelle de la particule
     * @return valeur d'opacité entre 0 et 1
     */
    public double getOpacity() {
        return opacity;
    }
}