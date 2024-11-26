package obstacles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;
import game.Player;

/**
 * Classe représentant un anneau qui donne temporairement la capacité de voler au joueur
 */
public class FlyRing extends GameObstacle {
    // Constantes de configuration
    private static final double DEFAULT_SIZE = Player.SIZE * 1.5;
    private static final double POWER_DURATION = 30.0;
    private static final double STROKE_WIDTH = 3.0;

    // Couleurs
    private static final Color RING_COLOR = Color.YELLOW;
    private static final Color RING_FILL = Color.TRANSPARENT;
    private static final Color ACTIVE_COLOR = Color.GREEN;

    // État de l'anneau
    private final double radius;
    private double powerupTimeLeft;
    private boolean isActive;

    /**
     * Crée un nouvel anneau de vol
     * @param x Position X de l'anneau
     * @param y Position Y de l'anneau
     * @param radius Rayon de l'anneau (utilise la taille par défaut si <= 0)
     */
    public FlyRing(double x, double y, double radius) {
        super(x, y);
        this.radius = validateRadius(radius);
        this.collisionType = CollisionType.POWERUP;
        initializeShape();
        reset();
    }

    private double validateRadius(double radius) {
        return radius > 0 ? radius : DEFAULT_SIZE;
    }

    @Override
    protected Shape createShape() {
        Circle ring = new Circle(radius);
        configureRingAppearance(ring);
        return ring;
    }

    private void configureRingAppearance(Circle ring) {
        ring.setFill(RING_FILL);
        ring.setStroke(RING_COLOR);
        ring.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    protected boolean handleCollision(Player player, Bounds playerBounds, Bounds ringBounds) {
        if (!isActive) {
            System.out.println("FlyRing collision detected!");
            activatePowerup(player);
            return false; 
        }
        return false;
    }

    private void activatePowerup(Player player) {
        if (player == null) {
            System.out.println("Error: Player is null!");
            return;
        }
        
        isActive = true;
        powerupTimeLeft = POWER_DURATION;
        System.out.println("Activating fly mode for player");
        player.setFlyMode(true);
        updateRingAppearance(ACTIVE_COLOR);
    }

    private void deactivatePowerup(Player player) {
        isActive = false;
        powerupTimeLeft = 0;
        player.setFlyMode(false);
        updateRingAppearance(RING_COLOR);
    }

    private void updateRingAppearance(Color color) {
        if (getShape() instanceof Circle ring) {
            ring.setStroke(color);
        }
    }

    @Override
    public void update(double deltaTime) {
        if (!isActive) return;

        powerupTimeLeft -= deltaTime;
        if (powerupTimeLeft <= 0) {
            Player player = getCurrentPlayer();
            if (player != null) {
                System.out.println("Deactivating fly mode");
                deactivatePowerup(player);
            }
        }
    }

    /**
     * Réinitialise l'état de l'anneau
     */
    public void reset() {
        isActive = false;
        powerupTimeLeft = 0;
        updateRingAppearance(RING_COLOR);
    }

    /**
     * Vérifie si l'anneau est actif
     * @return true si le powerup est actif, false sinon
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Retourne le temps restant du powerup
     * @return temps restant en secondes
     */
    public double getRemainingTime() {
        return powerupTimeLeft;
    }

    // Cette méthode devrait être implémentée dans GameObstacle
    @Override
    protected Player getCurrentPlayer() {
        return currentPlayer;  // Use the currentPlayer reference from GameObstacle
    }
}