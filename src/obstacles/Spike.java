package obstacles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;
import game.Player;


public class Spike extends GameObstacle {
    
    private static final double DEFAULT_SIZE = Player.SIZE * 1.5;
    private static final double STROKE_WIDTH = 2.0;

    private static final Color SPIKE_COLOR = Color.RED;
    private static final Color STROKE_COLOR = Color.BLACK;

  
    private static final double[][] SPIKE_POINTS = {
            {0.0, 1.0},    // Point bas gauche
            {0.5, 0.0},    // Point haut
            {1.0, 1.0}     // Point bas droite
    };

    private final double size;

    /**
     * Crée un nouveau pic
     * @param x Position X du pic
     * @param y Position Y du pic
     * @param size Taille du pic (utilise la taille par défaut si <= 0)
     */
    public Spike(double x, double y, double size, Color color) {
        super(x, y);
        this.size = validateSize(size);
        this.collisionType = CollisionType.SPIKE;
        initializeShape();
    }

    private double validateSize(double size) {
        return size > 0 ? size : DEFAULT_SIZE;
    }

    @Override
    protected Shape createShape() {
        Polygon spike = createSpikePolygon();
        configureSpikeAppearance(spike);
        return spike;
    }

    private Polygon createSpikePolygon() {
        Polygon spike = new Polygon();
        for (double[] point : SPIKE_POINTS) {
            spike.getPoints().addAll(
                    point[0] * size,  // X
                    point[1] * size   // Y
            );
        }
        return spike;
    }

    private void configureSpikeAppearance(Polygon spike) {
        spike.setFill(SPIKE_COLOR);
        spike.setStroke(STROKE_COLOR);
        spike.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    protected boolean handleCollision(Player player, Bounds playerBounds, Bounds spikeBounds) {
        return true;  // Collision toujours létale
    }

    /**
     * Retourne la taille du pic
     * @return La taille du pic
     */
    public double getSize() {
        return size;
    }

    /**
     * Retourne la taille par défaut des pics
     * @return La taille par défaut
     */
    public static double getDefaultSize() {
        return DEFAULT_SIZE;
    }
}