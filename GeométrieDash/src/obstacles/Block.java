package obstacles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;
import game.Player;

/**
 * Classe représentant un bloc obstacle.
 * Le joueur peut atterrir sur le dessus du bloc, mais meurt s'il touche les côtés.
 */
public class Block extends GameObstacle {
    // Constantes de configuration
    private static final double BLOCK_SIZE = 40.0;
    private static final double VERTICAL_TOLERANCE = 10.0;
    private static final double HORIZONTAL_TOLERANCE = 2.0;
    private static final double LOWER_THIRD_RATIO = 2.0/3.0;

    private final Color color;

    /**
     * Crée un nouveau bloc
     * @param x Position X du bloc
     * @param y Position Y du bloc
     * @param color Couleur du bloc
     */
    public Block(double x, double y, Color color) {
        super(x, y);
        this.color = color;
        this.collisionType = CollisionType.BLOCK;
        initializeShape();
    }

    @Override
    protected Shape createShape() {
        Rectangle block = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        block.setFill(color);
        return block;
    }

    @Override
    protected boolean handleCollision(Player player, Bounds playerBounds, Bounds blockBounds) {
        if (isTopCollision(playerBounds, blockBounds)) {
            handleTopCollision(player, blockBounds);
            return false;
        }

        if (isInLowerThird(playerBounds, blockBounds)) {
            return true; // Collision létale
        }

        return false; // Ignorer les autres collisions
    }

    /**
     * Vérifie si le joueur entre en collision par le haut du bloc
     */
    private boolean isTopCollision(Bounds playerBounds, Bounds blockBounds) {
        return isCollidingFromTop(playerBounds, blockBounds) &&
                isWithinHorizontalBounds(playerBounds, blockBounds);
    }

    /**
     * Vérifie si le joueur entre en collision verticalement avec le bloc
     */
    private boolean isCollidingFromTop(Bounds playerBounds, Bounds blockBounds) {
        return playerBounds.getMaxY() > blockBounds.getMinY() &&
                playerBounds.getMinY() < blockBounds.getMinY();
    }

    /**
     * Vérifie si le joueur est aligné horizontalement avec le bloc
     */
    private boolean isWithinHorizontalBounds(Bounds playerBounds, Bounds blockBounds) {
        return playerBounds.getMaxX() > blockBounds.getMinX() + HORIZONTAL_TOLERANCE &&
                playerBounds.getMinX() < blockBounds.getMaxX() - HORIZONTAL_TOLERANCE;
    }

    /**
     * Vérifie si le joueur est dans la partie basse du bloc
     */
    private boolean isInLowerThird(Bounds playerBounds, Bounds blockBounds) {
        double blockLowerThird = blockBounds.getMinY() + (blockBounds.getHeight() * LOWER_THIRD_RATIO);
        return playerBounds.getMaxY() > blockLowerThird;
    }

    /**
     * Gère la collision par le haut du bloc
     */
    private void handleTopCollision(Player player, Bounds blockBounds) {
        player.setPosition(player.getX(), blockBounds.getMinY());
        player.resetRotation();
    }

    /**
     * Retourne la taille du bloc
     * @return La taille du bloc
     */
    public static double getSize() {
        return BLOCK_SIZE;
    }
}