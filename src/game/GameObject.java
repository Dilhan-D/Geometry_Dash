package game;

import javafx.scene.shape.Shape;


public interface GameObject {
    /**
    
     * @return La forme JavaFX représentant l'objet dans le jeu
     */
    Shape getShape();

    /**
    
     * @param deltaTime Temps écoulé depuis la dernière frame en secondes
     */
    void update(double deltaTime);

    /**
     
     * @return La coordonnée X de l'objet
     */
    default double getX() {
        Shape shape = getShape();
        return shape != null ? shape.getTranslateX() : 0.0;
    }

    /**
    
     * @return La coordonnée Y de l'objet
     */
    default double getY() {
        Shape shape = getShape();
        return shape != null ? shape.getTranslateY() : 0.0;
    }
}