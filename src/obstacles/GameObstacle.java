package obstacles;

import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;
import game.GameObject;
import game.Player;
import game.Level;


public abstract class GameObstacle implements GameObject {
   
    public enum CollisionType {
      
        BLOCK,

        SPIKE,
   
        POWERUP
    }

    // Position initiale de l'obstacle
    protected final double initialX;
    protected final double initialY;

    // Références aux objets du jeu
    protected Shape shape;
    protected Level currentLevel;
    protected Player currentPlayer;

    // État de l'obstacle
    protected CollisionType collisionType;
    protected boolean isActive;

    /**
     * Crée un nouvel obstacle
     * @param x Position X initiale
     * @param y Position Y initiale
     */
    protected GameObstacle(double x, double y) {
        this.initialX = x;
        this.initialY = y;
        this.isActive = true;
    }

    protected void initializeShape() {
        if (this.shape != null) return;

        this.shape = createShape();
        if (this.shape != null) {
            positionShape();
        }
    }


    protected void positionShape() {
        shape.setTranslateX(initialX);
        shape.setTranslateY(initialY);
    }

    /**
     * Crée la forme spécifique de l'obstacle
     * @return La forme créée
     */
    protected abstract Shape createShape();

    /**
     * Gère la collision spécifique avec le joueur
     * @param player Le joueur
     * @param playerBounds Les limites du joueur
     * @param obstacleBounds Les limites de l'obstacle
     * @return true si la collision est létale, false sinon
     */
    protected abstract boolean handleCollision(Player player, Bounds playerBounds, Bounds obstacleBounds);

    @Override
    public Shape getShape() {
        if (shape == null) {
            initializeShape();
        }
        return shape;
    }

    @Override
    public void update(double deltaTime) {
        // Par défaut, les obstacles sont statiques
    }

    /**
     * Vérifie et gère la collision avec le joueur
     * @param player Le joueur à vérifier
     * @return true si la collision est létale, false sinon
     */
    public boolean checkCollision(Player player) {
        if (!isActive || player == null || shape == null) {
            return false;
        }

        Bounds playerBounds = player.getShape().getBoundsInParent();
        Bounds obstacleBounds = shape.getBoundsInParent();

        if (playerBounds.intersects(obstacleBounds)) {
            this.currentPlayer = player;  // Garde une référence au joueur actuel
            return handleCollision(player, playerBounds, obstacleBounds);
        }

        return false;
    }

    /**
     * Définit le niveau contenant l'obstacle
     * @param level Le niveau
     */
    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    /**
     * Retourne le joueur actuellement en interaction
     * @return Le joueur actuel ou null si aucun
     */
    protected Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Active ou désactive l'obstacle
     * @param active true pour activer, false pour désactiver
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * Vérifie si l'obstacle est actif
     * @return true si l'obstacle est actif, false sinon
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Retourne le type de collision de l'obstacle
     * @return Le type de collision
     */
    public CollisionType getCollisionType() {
        return collisionType;
    }
}