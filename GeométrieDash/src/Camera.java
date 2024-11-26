import game.Player;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

/**
 * Classe gérant la caméra qui suit le joueur
 */
public class Camera extends Node {
    private static final double CAMERA_OFFSET_X = 300.0;

    private double positionX;
    private double positionY;

    public Camera() {
        this.positionX = 0.0;
        this.positionY = 0.0;
    }

    /**
     * Met à jour la position de la caméra en fonction du joueur
     */
    public void update(Player player, Pane gameRoot) {
        if (player == null || gameRoot == null) return;

        positionX = -(player.getX() - CAMERA_OFFSET_X);
        gameRoot.setTranslateX(positionX);
    }

    /**
     * Réinitialise la position de la caméra
     */
    public void reset() {
        positionX = 0.0;
        positionY = 0.0;

        if (getParent() instanceof Pane parentPane) {
            parentPane.setTranslateX(0.0);
            parentPane.setTranslateY(0.0);
        }
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
}