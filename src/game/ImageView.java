/* package game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageView {
    public void setBackground(Pane pane) {
        String imagePath = getClass().getResource("/assets/fdd.png").toExternalForm();
        
        Image backgroundImage = new Image(imagePath);


        ImageView backgroundImageView = new ImageView(backgroundImage);


        backgroundImageView.setFitWidth(pane.getPrefWidth());
        backgroundImageView.setFitHeight(pane.getPrefHeight());


        pane.getChildren().add(0, backgroundImageView);  // Ajouter en premier (au fond)
    }
}
