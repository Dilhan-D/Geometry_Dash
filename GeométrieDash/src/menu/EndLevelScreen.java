package menu;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.BlurType;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.geometry.Insets;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

public class EndLevelScreen {
    private Scene scene;
    private final int width;
    private final int height;
    private Runnable onRestart;
    private Runnable onMenu;
    private Font customFont;

    // Constantes de style reprises du Menu
    private static final Color PRIMARY_COLOR = Color.rgb(0, 255, 255);
    private static final Color SECONDARY_COLOR = Color.rgb(255, 0, 255);
    private static final Color BACKGROUND_COLOR = Color.rgb(26, 26, 46);

    public EndLevelScreen(int width, int height) {
        this.width = width;
        this.height = height;
        loadFont();
        createScene();
    }

    private void loadFont() {
        try {
            customFont = Font.font("Impact", 55);
        } catch (Exception e) {
            System.out.println("Exception lors du chargement de la police: " + e.getMessage());
            customFont = Font.font("Arial Bold", 55);
        }
    }

    private void createScene() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: rgb(26, 26, 46);");

        VBox content = new VBox(80);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(100, 20, 20, 20));

        // Titre stylisé comme dans le menu
        VBox titleContainer = createTitle();

        // Boutons dans un VBox
        VBox buttonBox = createButtons();

        content.getChildren().addAll(titleContainer, buttonBox);
        root.getChildren().add(content);

        scene = new Scene(root, width, height);
    }

    private VBox createTitle() {
        // Container pour les deux textes
        VBox titleContainer = new VBox(20);  // 20 pixels d'espacement
        titleContainer.setAlignment(Pos.CENTER);

        // GG! en plus grand
        Text ggText = new Text("GG !");
        ggText.setFont(Font.font(customFont.getFamily(), 80));  // Plus grand que le titre normal

        // Niveau Terminé
        Text completedText = new Text("NIVEAU TERMINÉ !");
        completedText.setFont(Font.font(customFont.getFamily(), 55));

        // Même gradient pour les deux textes
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, PRIMARY_COLOR),
                new Stop(0.5, SECONDARY_COLOR),
                new Stop(1, PRIMARY_COLOR)
        );
        ggText.setFill(gradient);
        completedText.setFill(gradient);

        // Effets pour les deux textes
        addTextEffects(ggText);
        addTextEffects(completedText);

        // Animation de pulsation pour "GG!"
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(ggText.scaleXProperty(), 1),
                        new KeyValue(ggText.scaleYProperty(), 1)
                ),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(ggText.scaleXProperty(), 1.1),
                        new KeyValue(ggText.scaleYProperty(), 1.1)
                )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        titleContainer.getChildren().addAll(ggText, completedText);
        return titleContainer;
    }

    private void addTextEffects(Text text) {
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN,
                Color.rgb(0, 0, 0, 0.8), 10, 0.5, 0, 0);
        Glow glow = new Glow(0.8);
        shadow.setInput(glow);
        text.setEffect(shadow);
    }

    private VBox createButtons() {
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button restartButton = createStyledButton("RECOMMENCER");
        Button menuButton = createStyledButton("MENU PRINCIPAL");

        restartButton.setOnAction(e -> {
            if (onRestart != null) onRestart.run();
        });

        menuButton.setOnAction(e -> {
            if (onMenu != null) onMenu.run();
        });

        buttonBox.getChildren().addAll(restartButton, menuButton);
        return buttonBox;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(60);
        button.setFont(Font.font(customFont.getFamily(), 24));

        // Style identique aux boutons du menu
        String buttonStyle = """
            -fx-background-color: linear-gradient(to right, #00ffff, #ff00ff);
            -fx-text-fill: white;
            -fx-background-radius: 30;
            -fx-cursor: hand;
        """;
        button.setStyle(buttonStyle);

        // Effet d'ombre
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN,
                Color.rgb(0, 0, 0, 0.3), 5, 0.5, 0, 0);
        button.setEffect(shadow);

        // Animations de survol
        button.setOnMouseEntered(e -> {
            button.setScaleX(1.05);
            button.setScaleY(1.05);
        });

        button.setOnMouseExited(e -> {
            button.setScaleX(1);
            button.setScaleY(1);
        });

        return button;
    }

    public Scene getScene() {
        return scene;
    }

    public void setOnRestart(Runnable onRestart) {
        this.onRestart = onRestart;
    }

    public void setOnMenu(Runnable onMenu) {
        this.onMenu = onMenu;
    }
}