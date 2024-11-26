package game;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe représentant le joueur et gérant sa physique, son apparence et ses animations
 */
public class Player implements GameObject {

    private boolean isMoving = true;
    private double currentSpeed = MOVEMENT_SPEED;

    // Constantes de configuration
    public static final int SIZE = 40;
    private static final double GRAVITY = 2400.0;
    private static final double JUMP_FORCE = -800.0;
    private static final double MOVEMENT_SPEED = 400.0;
    private static final double ROTATION_SPEED = 360.0;
    private static final double FLY_FORCE = -400.0;
    private static final double TARGET_ROTATION = 180.0;
    private static final double LOG_INTERVAL = 0.3;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private double totalRotation = 0;
    // Constantes de style
    private static final Color PLAYER_COLOR_START = Color.rgb(100, 0, 255);
    private static final Color PLAYER_COLOR_MID = Color.rgb(180, 50, 255);
    private static final Color PLAYER_COLOR_END = Color.rgb(255, 100, 255);
    private static final Color EYE_COLOR = Color.rgb(0, 255, 255);
    private static final double STROKE_WIDTH = 2.0;
    private static final double GLOW_INTENSITY = 0.8;
    private static final double SHADOW_RADIUS = 10.0;

    // Constantes des trails
    private static final int TRAIL_SPAWN_RATE = 1;
    private static final int PARTICLES_PER_SPAWN = 3;

    // Composants visuels
    private final Rectangle shape;
    private final ImageView playerImage;
    private final List<Trail> trails;
    private Pane gameRoot;

    // État du joueur
    private double velocityY;
    private double rotation;
    private boolean canJump;
    private boolean flyMode;
    private boolean isRotating;
    private Level currentLevel;
    private int trailCounter;
    private long lastLogTime;

    // Gestion du skin
    private static final String SKINS_PATH = "file:src/resources/skins/";
    private String currentSkin = "player.png";



    /**
     * Crée un nouveau joueur
     * @param startX Position X initiale
     * @param groundY Position Y du sol
     * @param gameRoot Conteneur principal du jeu
     */
    public Player(int startX, int groundY, Pane gameRoot) {
        this.gameRoot = gameRoot;// Stocker la largeur
        this.trails = new ArrayList<>();

        this.shape = createPlayerShape(startX, groundY);
        this.playerImage = createPlayerImage(startX, groundY);

        if (playerImage != null) {
            gameRoot.getChildren().add(playerImage);
        }

        reset(startX, groundY);
    }

    private ImageView createPlayerImage(int startX, int groundY) {
        try {
            Image image = new Image(SKINS_PATH + currentSkin);
            ImageView imageView = new ImageView(image);

            // Définir la taille
            imageView.setFitWidth(SIZE);
            imageView.setFitHeight(SIZE);

            // Positionner l'image
            imageView.setX(startX);
            imageView.setY(groundY - SIZE);

            // Définir le point de rotation au centre
            imageView.setPreserveRatio(true);
            imageView.setRotationAxis(javafx.scene.transform.Rotate.Z_AXIS);

            return imageView;
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image du joueur : " + e.getMessage());
            return null;
        }
    }


    @Override
    public void update(double deltaTime) {
        updatePhysics(deltaTime);
        updateRotation(deltaTime);
        updateTrail();
        logDebugInfo();
    }

    private void updatePhysics(double deltaTime) {
        if (!isMoving) {
            velocityY = 0;
            return;
        }

     
        if (flyMode) {
            if (Input.isKeyPressed(KeyCode.SPACE)) {
                velocityY = FLY_FORCE;  
            } else {

                velocityY += GRAVITY * deltaTime * 0.4; 
            }
        } else {
         
            velocityY += GRAVITY * deltaTime;
        }
        
   
        double maxFallSpeed = 1000.0;
        if (velocityY > maxFallSpeed) {
            velocityY = maxFallSpeed;
        }

  
        double newY = shape.getY() + velocityY * deltaTime;
        shape.setY(newY);
        shape.setX(shape.getX() + currentSpeed * deltaTime);

    
        if (playerImage != null) {
            playerImage.setY(newY);
            playerImage.setX(shape.getX());
        }
    }

    public void stopMovement() {
        isMoving = false;
        currentSpeed = 0;
        velocityY = 0;
        canJump = false;  
    }

    public void startMovement() {
        isMoving = true;
        currentSpeed = MOVEMENT_SPEED;
        canJump = true;
    }

    private void updateRotation(double deltaTime) {
        if (isRotating) {
            double rotationThisFrame = ROTATION_SPEED * deltaTime;
            rotation += rotationThisFrame;
            
            if (rotation >= TARGET_ROTATION) {
                // Compléter la rotation à exactement 90 degrés
                rotation = 0;
                isRotating = false;
                totalRotation = (totalRotation + 180) % 360;
            }
            
            if (playerImage != null) {
                // Appliquer la rotation actuelle plus la rotation totale
                playerImage.setRotate(totalRotation + rotation);
            }
        }
    }

    private void updateTrail() {
        if (++trailCounter >= TRAIL_SPAWN_RATE) {
            spawnTrailParticles();
            trailCounter = 0;
        }

        updateExistingTrails();
    }

    private void spawnTrailParticles() {
        for (int i = 0; i < PARTICLES_PER_SPAWN; i++) {
            Trail trail = new Trail(shape.getX(), shape.getY() + SIZE, rotation, shape.getY() + SIZE);
            trails.add(trail);
            gameRoot.getChildren().add(trail.getShape());
        }
    }

    private void updateExistingTrails() {
        Iterator<Trail> it = trails.iterator();
        while (it.hasNext()) {
            Trail trail = it.next();
            if (trail.update()) {
                gameRoot.getChildren().remove(trail.getShape());
                it.remove();
            }
        }
    }

    private Rectangle createPlayerShape(int startX, int groundY) {
        Rectangle playerShape = new Rectangle(SIZE, SIZE);
        playerShape.setX(startX);
        playerShape.setY(groundY - SIZE);
        playerShape.setOpacity(0);
        return playerShape;
    }

    public void jump() {
        if (canJump) {
            logJump();
            velocityY = JUMP_FORCE;
            canJump = false;
            isRotating = true;
            rotation = 0;  
        }
    }

    public void checkCollision(Level level) {
        if (shape.getY() + SIZE > level.getGroundY()) {
            landOnGround(level.getGroundY());
        }

        if (shape.getX() + SIZE >= level.getLevelLength()) {
            shape.setX(level.getLevelLength() - SIZE);
            if (playerImage != null) {
                playerImage.setX(level.getLevelLength() - SIZE);
            }
            stopMovement();
        }
    }

    private void landOnGround(int groundY) {
        shape.setY(groundY - SIZE);
        velocityY = 0;
        canJump = true;
        
        if (isRotating) {
            totalRotation = (Math.round(totalRotation / 90) * 90) % 360;
            rotation = 0;
            isRotating = false;
            if (playerImage != null) {
                playerImage.setRotate(totalRotation);
            }
        }
    }

    private void logDebugInfo() {
       
    }

    private void logJump() {
    }

    private void printDebugInfo() {
    }

    private void reset(double x, double y) {
        shape.setX(x);
        shape.setY(y - SIZE);
        if (playerImage != null) {
            playerImage.setX(x);
            playerImage.setY(y - SIZE);
            totalRotation = 0;
            playerImage.setRotate(0);
        }
        velocityY = 0;
        canJump = true;
        isRotating = false;
        flyMode = false;  
        trailCounter = 0;
        lastLogTime = 0;
        startMovement();
    }

    public boolean isMoving() {
        return isMoving;
    }

    // Getters & Setters
    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public double getX() {
        return shape.getX();
    }

    public void setFlyMode(boolean flyMode) {
        this.flyMode = flyMode;
    }

    public void setPosition(double x, double y) {
        reset(x, y);  // Enlever les logs
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public void resetRotation() {
        rotation = 0;
        totalRotation = 0;  // Réinitialiser aussi la rotation totale
        if (playerImage != null) {
            playerImage.setRotate(0);
        }
        isRotating = false;
    }

    public void changeSkin(String skinName) {
        this.currentSkin = skinName;
        // Recharger l'image avec le nouveau skin
        Image newImage = new Image(SKINS_PATH + currentSkin);
        playerImage.setImage(newImage);
    }

    public static List<String> getAvailableSkins() {
        List<String> skins = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            skins.add(i == 1 ? "player.png" : "player" + i + ".png");
        }
        return skins;
    }

    public void setGameRoot(Pane newGameRoot) {
        if (this.gameRoot != null) {
            if (playerImage != null) {
                this.gameRoot.getChildren().remove(playerImage);
            }
            if (shape != null) {
                this.gameRoot.getChildren().remove(shape);
            }
            // Retirer tous les trails
            for (Trail trail : trails) {
                this.gameRoot.getChildren().remove(trail.getShape());
            }
        }

        // Définir le nouveau gameRoot
        this.gameRoot = newGameRoot;

        // Ajouter tous les éléments au nouveau gameRoot
        if (this.gameRoot != null) {
            if (playerImage != null) {
                this.gameRoot.getChildren().add(playerImage);
            }
            if (shape != null) {
                this.gameRoot.getChildren().add(shape);
            }
            for (Trail trail : trails) {
                this.gameRoot.getChildren().add(trail.getShape());
            }
        }
    }
}