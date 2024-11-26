import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import menu.Menu;
import audio.AudioManager;

/**
 * Configuration globale du jeu
 */
final class GameConfig {
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;

    private GameConfig() {} 
}

/**
 * Classe principale qui lance le jeu
 */
public class App extends Application {
    private Stage primaryStage;
    private GameEngine gameEngine;
    private Menu menu;
    private AudioManager audioManager;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            this.audioManager = AudioManager.getInstance();
            initializeGame();


            menu = new Menu(
                    GameConfig.WINDOW_WIDTH,
                    GameConfig.WINDOW_HEIGHT,
                    this::startGame,
                    skinName -> gameEngine.getPlayer().changeSkin(skinName),
                    primaryStage
            );

            primaryStage.setTitle("Geometry Dash");
            primaryStage.setScene(menu.getScene());
            primaryStage.setResizable(false);
            primaryStage.show();

            audioManager.playMenuMusic();

        } catch (Exception e) {
            System.err.println("Erreur lors du démarrage du jeu : " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initializeGame() {
        gameEngine = new GameEngine(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        gameEngine.setStage(primaryStage);
    }

    private void startGame() {
        Scene gameScene = gameEngine.createScene();
        primaryStage.setScene(gameScene);
        audioManager.playGameMusic();
    }

    @Override
    public void stop() {
        audioManager.stop();
        System.out.println("Fermeture du jeu");
    }

    public static void main(String[] args) {
        launch(args);
    }
}