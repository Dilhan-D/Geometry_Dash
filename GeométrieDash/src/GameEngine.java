import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.List;
import game.Player;
import obstacles.*;
import javafx.scene.input.KeyCode;
import game.Input;
import game.Level;
import game.Trail;
import javafx.scene.Group;
import javafx.stage.Stage;
import menu.EndLevelScreen;
import menu.Menu;
import javafx.scene.shape.Rectangle;
import audio.AudioManager;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;


/**
 * Classe qui gère la logique principale du jeu
 */
public class GameEngine {
    private final AudioManager audioManager;
    private String currentSkin = "player.png";

    private static final int PLAYER_START_X = 100;
    private static final String LOG_SEPARATOR = "===========================";

    private final int width;
    private final int height;
    private final Camera camera;

    private Player player;
    private Level currentLevel;
    private boolean isGameRunning;
    private Stage stage;
    private EndLevelScreen endLevelScreen;
    private AnimationTimer gameLoop;

    private Button muteButton;


    public Player getPlayer() {
        return player;
    }

    public GameEngine(int width, int height) {
        this.width = width;
        this.height = height;
        this.camera = new Camera();
        
        this.isGameRunning = false;
        this.endLevelScreen = new EndLevelScreen(width, height);
        this.audioManager = AudioManager.getInstance();
        initializeGame();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        setupEndLevelScreen();
        Menu menu = new Menu(
            width,
            height,
            () -> {
                resetGame();
                stage.setScene(createScene());
            },
            skinName -> {
                currentSkin = skinName;
                if (player != null) {
                    player.changeSkin(skinName);
                }
            },
            stage
        );
    }

    private void setupEndLevelScreen() {
        endLevelScreen.setOnRestart(() -> {
            if (gameLoop != null) {
                gameLoop.stop();
            }
            isGameRunning = false;
            audioManager.playGameMusic();
            resetGame();
        });

        endLevelScreen.setOnMenu(() -> {
            if (gameLoop != null) {
                gameLoop.stop();
            }
            isGameRunning = false;
            audioManager.playMenuMusic();
            Menu menu = new Menu(
                    width,
                    height,
                    () -> {
                        resetGame();
                        stage.setScene(createScene());
                    },
                    skinName -> player.changeSkin(skinName),
                    stage
            );
            stage.setScene(menu.getScene());
        });
    }

    private void initializeGame() {
        this.currentLevel = new Level(width, height);
        Pane tempRoot = new Pane();
        this.player = new Player(PLAYER_START_X, height, tempRoot);
        if (currentSkin != null) {
            player.changeSkin(currentSkin);
        }
    }

    public Scene createScene() {
        logGameState("Début création de la scène");

        Pane gameRoot = new Pane();
        Pane cameraGroup = new Pane();

        gameRoot.getChildren().add(currentLevel.getBackgroundGroup());
        gameRoot.getChildren().add(currentLevel.getGroundGroup());
        addObstaclesToScene(gameRoot);
        initializeGameElements(gameRoot);

        cameraGroup.getChildren().add(gameRoot);
        Scene scene = createAndConfigureScene(cameraGroup);

        setupControls(scene);
        startGameLoop(gameRoot);

        audioManager.playGameMusic();

        logGameState("Fin création de la scène");
        return scene;
    }

    private void initializeGameElements(Pane gameRoot) {
        if (player != null) {
            player.setGameRoot(gameRoot);
        }
    }

    private Scene createAndConfigureScene(Pane root) {
        StackPane mainRoot = new StackPane();
        mainRoot.getChildren().add(root);

        // Créer le bouton mute
        createMuteButton();

        // Créer un conteneur pour le bouton en haut à droite
        HBox buttonContainer = new HBox(muteButton);
        buttonContainer.setAlignment(Pos.TOP_RIGHT);
        buttonContainer.setPadding(new javafx.geometry.Insets(10));

        // Ajouter le conteneur du bouton par-dessus le jeu
        mainRoot.getChildren().add(buttonContainer);

        Scene scene = new Scene(mainRoot, width, height);
        scene.setFill(Color.BLACK);
        return scene;
    }

    private void createMuteButton() {
        muteButton = new Button();
        muteButton.setStyle("-fx-background-color: transparent; -fx-padding: 5;");

        // Charger les icônes
        Image soundOnImage = new Image(getClass().getResourceAsStream("/resources/icons/sound-on.png"));
        Image soundOffImage = new Image(getClass().getResourceAsStream("/resources/icons/sound-off.png"));

        ImageView imageView = new ImageView(soundOnImage);
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        muteButton.setGraphic(imageView);

        // Empêcher le bouton de prendre le focus
        muteButton.setFocusTraversable(false);

        muteButton.setOnAction(e -> {
            audioManager.toggleMute();
            if (audioManager.isMuted()) {
                imageView.setImage(soundOffImage);
            } else {
                imageView.setImage(soundOnImage);
            }
            // Consommer l'événement pour qu'il ne se propage pas
            e.consume();
        });
    }

    private void setupControls(Scene scene) {
        scene.setOnKeyPressed(event -> {
            Input.setKeyPressed(event.getCode(), true);
            if (event.getCode() == KeyCode.SPACE) {
                player.jump();
            }
        });
        scene.setOnKeyReleased(event -> Input.setKeyPressed(event.getCode(), false));
    }

    private void startGameLoop(Pane gameRoot) {
        if (gameLoop != null) {
            gameLoop.stop();
        }

        isGameRunning = true;
        final long[] lastUpdate = {System.nanoTime()};
        final double MAX_DELTA_TIME = 1.0 / 30.0; 
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isGameRunning) return;

                double deltaTime = (now - lastUpdate[0]) / 1_000_000_000.0;
                deltaTime = Math.min(deltaTime, MAX_DELTA_TIME); // Limite le deltaTime
                lastUpdate[0] = now;

                updateGameState(gameRoot, deltaTime);
            }
        };
        
        gameLoop.start();
    }

    private void updateGameState(Pane gameRoot, double deltaTime) {
        // Mise à jour de la position avec deltaTime
        player.update(deltaTime);
        
        // Les collisions doivent être vérifiées après la mise à jour de la position
        checkCollisions();
        
        // Mise à jour de la caméra après tout le reste
        camera.update(player, gameRoot);
    }

    private void checkCollisions() {
  
        player.checkCollision(currentLevel);
        
        // Vérification des collisions avec les obstacles
        for (GameObstacle obstacle : currentLevel.getObstacles()) {
            if (obstacle.checkCollision(player)) {
                if (obstacle instanceof Block && 
                    ((Rectangle)obstacle.getShape()).getFill() == Color.RED) {
                    handleLevelComplete();
                } else {
                    resetGameAfterCollision();
                }
                return;
            }
        }
    }

    private void handleLevelComplete() {
        isGameRunning = false;
        if (gameLoop != null) {
            gameLoop.stop();
        }
        audioManager.playMenuMusic();
        if (stage != null) {
            Menu menu = new Menu(
                width,
                height,
                () -> {
                    resetGame();
                    stage.setScene(createScene());
                },
                skinName -> {
                    currentSkin = skinName;
                    if (player != null) {
                        player.changeSkin(skinName);
                    }
                },
                stage
            );
            stage.setScene(menu.getScene());
        }
    }

    private void resetGameAfterCollision() {
        logGameState("GAME OVER - RESET");
        if (gameLoop != null) {
            gameLoop.stop();
        }

        audioManager.restartGameMusic();

        // Réinitialiser la position et la rotation du joueur
        player.setPosition(PLAYER_START_X, height);
        player.resetRotation();
        camera.reset();

        // Réinitialiser tous les obstacles
        for (GameObstacle obstacle : currentLevel.getObstacles()) {
            if (obstacle instanceof FlyRing) {
                ((FlyRing) obstacle).reset();
            }
        }

   
        Scene newScene = createScene();
        if (stage != null) {
            stage.setScene(newScene);
        }
    }

    private void resetGame() {
        logGameState("GAME OVER - RESET");
        if (gameLoop != null) {
            gameLoop.stop();
        }
        isGameRunning = false;

        audioManager.restartGameMusic();

        // Réinitialiser complètement le jeu
        initializeGame();

        // Réinitialiser la position et la rotation
        player.setPosition(PLAYER_START_X, height);
        player.resetRotation();
        camera.reset();

        // Réinitialiser tous les obstacles
        for (GameObstacle obstacle : currentLevel.getObstacles()) {
            if (obstacle instanceof FlyRing) {
                ((FlyRing) obstacle).reset();
            }
        }

        // Créer et configurer une nouvelle scène
        Scene newScene = createScene();
        if (stage != null) {
            stage.setScene(newScene);
        }
    }

    private void logGameState(String message) {
        if (message.contains("GAME OVER") || message.contains("Exception")) {
            System.out.println("\n" + LOG_SEPARATOR);
            System.out.println(message);
            System.out.println(LOG_SEPARATOR);
        }
    }

    private void addObstaclesToScene(Pane gameRoot) {
        List<GameObstacle> obstacles = currentLevel.getObstacles();
        
        for (GameObstacle obstacle : obstacles) {
            try {
                if (obstacle == null || obstacle.getShape() == null) {
                    continue;
                }
                gameRoot.getChildren().add(obstacle.getShape());
            } catch (Exception e) {
                System.out.println("Exception lors de l'ajout d'un obstacle:");
                e.printStackTrace();
            }
        }
    }
}