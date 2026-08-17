package menu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.BlurType;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.geometry.VPos;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import java.util.function.Consumer;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class Menu {
    private Scene menuScene;
    private final int width;
    private final int height;
    private final Runnable onPlayAction;
    private Font customFont;

    // Constantes pour le style
    private static final Color PRIMARY_COLOR = Color.rgb(0, 255, 255);
    private static final Color SECONDARY_COLOR = Color.rgb(255, 0, 255);
    private static final Color BACKGROUND_COLOR = Color.rgb(26, 26, 46);

    // Variables pour le skin
    private Scene skinSelectorScene;
    private Runnable onBackToMenu;
    private final Consumer<String> onSkinChange;
    private Stage stage;
    private String currentSkin = "player.png";

    public Menu(int width, int height, Runnable onPlayAction, Consumer<String> onSkinChange, Stage stage) {
        this.width = width;
        this.height = height;
        this.onPlayAction = onPlayAction;
        this.onSkinChange = onSkinChange;
        this.stage = stage;
        this.onBackToMenu = () -> stage.setScene(menuScene);
        loadFont();
        createMenu();
        createSkinSelector();
    }

    private void loadFont() {
        try {
            customFont = Font.font("Impact", 55);
            System.out.println("Police Impact chargée");
        } catch (Exception e) {
            System.out.println("Exception lors du chargement de la police: " + e.getMessage());
            customFont = Font.font("Arial Bold", 55);
        }
    }

    private void createMenu() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: rgb(26, 26, 46);");

        VBox menuBox = new VBox(80);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(100, 20, 20, 20));

        Text title = createTitle();
        VBox buttonBox = createButtons();

        menuBox.getChildren().addAll(title, buttonBox);
        root.getChildren().add(menuBox);

        menuScene = new Scene(root, width, height);
    }

    private Text createTitle() {
        Text title = new Text("Geometry Dash");
        title.setFont(customFont);

       
        double scaleFactor = 55.0 / 33.77;
        title.setScaleX(scaleFactor);
        title.setScaleY(scaleFactor);

        
        title.setTranslateY(20);

        System.out.println("==== DEBUG TITRE ====");
        System.out.println("Taille de la police: " + customFont.getSize());
        System.out.println("Hauteur du texte: " + title.getBoundsInLocal().getHeight());
        System.out.println("Largeur du texte: " + title.getBoundsInLocal().getWidth());
        System.out.println("Scale Factor: " + scaleFactor);
        System.out.println("===================");

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, PRIMARY_COLOR),
                new Stop(0.5, SECONDARY_COLOR),
                new Stop(1, PRIMARY_COLOR)
        );
        title.setFill(gradient);

        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN,
                Color.rgb(0, 0, 0, 0.8), 10, 0.5, 0, 0);
        Glow glow = new Glow(0.8);
        shadow.setInput(glow);
        title.setEffect(shadow);

    
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(title.scaleXProperty(), scaleFactor),
                        new KeyValue(title.scaleYProperty(), scaleFactor)
                ),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(title.scaleXProperty(), scaleFactor * 1.1),
                        new KeyValue(title.scaleYProperty(), scaleFactor * 1.1)
                )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return title;
    }

    private void createSkinSelector() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: rgb(26, 26, 46);");

        VBox layout = new VBox(30);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50, 20, 50, 20));

 
        Text title = new Text("Sélecteur de Skins");
        title.setFont(Font.font(customFont.getFamily(), 40));
        title.setFill(Color.WHITE);


        javafx.scene.layout.GridPane skinGrid = new javafx.scene.layout.GridPane();
        skinGrid.setHgap(20);
        skinGrid.setVgap(20);
        skinGrid.setAlignment(Pos.CENTER);

     
        java.util.List<String> skins = game.Player.getAvailableSkins();

        int col = 0;
        int row = 0;
        for (String skin : skins) {
            StackPane skinPane = createSkinPreview(skin);
            skinGrid.add(skinPane, col, row);
          
            if (skin.equals(currentSkin)) {
                selectSkinPane(skinPane);
            }
            col++;
            if (col >= 3) {
                col = 0;
                row++;
            }
        }

        // Boutons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button saveButton = createStyledButton("SAUVEGARDER");
        Button backButton = createStyledButton("RETOUR");

        saveButton.setOnAction(e -> {
            onSkinChange.accept(currentSkin);
            onBackToMenu.run();
        });

        backButton.setOnAction(e -> {
 
            onBackToMenu.run();
        });

        buttonBox.getChildren().addAll(saveButton, backButton);

        layout.getChildren().addAll(title, skinGrid, buttonBox);
        root.getChildren().add(layout);

        skinSelectorScene = new Scene(root, width, height);
    }

    private StackPane createSkinPreview(String skinName) {
        StackPane pane = new StackPane();
        pane.setPrefSize(100, 100);

        String baseStyle = "-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 10;";
        String selectedStyle = "-fx-background-color: rgba(0, 255, 255, 0.3); -fx-background-radius: 10; -fx-border-color: #00ffff; -fx-border-width: 2; -fx-border-radius: 10;";

        pane.setStyle(baseStyle);

  
        javafx.scene.image.Image skinImage = new javafx.scene.image.Image("file:src/resources/skins/" + skinName);
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(skinImage);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);

        pane.getChildren().add(imageView);

        pane.setOnMouseEntered(e -> {
            if (!pane.getStyle().contains("border-color")) {  // Si ce n'est pas le skin sélectionné
                pane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-background-radius: 10;");
                pane.setScaleX(1.1);
                pane.setScaleY(1.1);
            }
        });

        pane.setOnMouseExited(e -> {
            if (!pane.getStyle().contains("border-color")) {  // Si ce n'est pas le skin sélectionné
                pane.setStyle(baseStyle);
                pane.setScaleX(1);
                pane.setScaleY(1);
            }
        });

       
        pane.setOnMouseClicked(e -> {
  
            pane.getParent().getChildrenUnmodifiable().forEach(node -> {
                if (node instanceof StackPane) {
                    StackPane otherPane = (StackPane) node;
                    otherPane.setStyle(baseStyle);
                    otherPane.setScaleX(1);
                    otherPane.setScaleY(1);
                }
            });

   
            pane.setStyle(selectedStyle);

       
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), pane);
            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();

            currentSkin = skinName;
        });

        return pane;
    }

    private VBox createButtons() {
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button playButton = createStyledButton("JOUER");
        Button skinsButton = createStyledButton("SKINS");
        Button quitButton = createStyledButton("QUITTER");

        playButton.setOnAction(e -> onPlayAction.run());
        skinsButton.setOnAction(e -> stage.setScene(skinSelectorScene));
        quitButton.setOnAction(e -> System.exit(0));

        buttonBox.getChildren().addAll(playButton, skinsButton, quitButton);
        return buttonBox;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(300);  
        button.setPrefHeight(60); 
        button.setFont(Font.font(customFont.getFamily(), 24));  

        String buttonStyle;
        if (text.equals("SAUVEGARDER")) {
            buttonStyle = """
        -fx-background-color: #4CAF50;
        -fx-text-fill: white;
        -fx-background-radius: 30;
        -fx-cursor: hand;
        """;
        } else if (text.equals("RETOUR")) {
            buttonStyle = """
        -fx-background-color: #757575;
        -fx-text-fill: white;
        -fx-background-radius: 30;
        -fx-cursor: hand;
        """;
        } else {
        
            buttonStyle = """
        -fx-background-color: linear-gradient(to right, #00ffff, #ff00ff);
        -fx-text-fill: white;
        -fx-background-radius: 30;
        -fx-cursor: hand;
        """;
        }
        button.setStyle(buttonStyle);


        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN,
                Color.rgb(0, 0, 0, 0.3), 5, 0.5, 0, 0);
        button.setEffect(shadow);

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

    private void selectSkinPane(StackPane pane) {
        String selectedStyle = "-fx-background-color: rgba(0, 255, 255, 0.3); -fx-background-radius: 10; -fx-border-color: #00ffff; -fx-border-width: 2; -fx-border-radius: 10;";
        pane.setStyle(selectedStyle);
    }

    public Scene getScene() {
        return menuScene;
    }
}