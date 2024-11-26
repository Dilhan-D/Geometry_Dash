package obstacles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;
import game.Player;

public class StopFlyRing extends GameObstacle {
    private static final double STROKE_WIDTH = 3.0;
    private static final Color RING_COLOR = Color.RED;
    private static final Color RING_FILL = Color.TRANSPARENT;

    public StopFlyRing(double x, double y, double size) {
        super(x, y);
        this.collisionType = CollisionType.POWERUP;
        initializeShape();
    }

    @Override
    protected Shape createShape() {
        Circle ring = new Circle(30);
        ring.setFill(RING_FILL);
        ring.setStroke(RING_COLOR);
        ring.setStrokeWidth(STROKE_WIDTH);
        return ring;
    }

    @Override
    protected boolean handleCollision(Player player, Bounds playerBounds, Bounds obstacleBounds) {
        if (!isActive) {
            System.out.println("StopFlyRing collision detected!");
            player.setFlyMode(false);
            isActive = true;
        }
        return false;
    }
} 