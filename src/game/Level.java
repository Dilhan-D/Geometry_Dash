package game;
import obstacles.StopFlyRing;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import java.util.ArrayList;
import java.util.List;
import obstacles.GameObstacle;
import obstacles.Block;
import obstacles.Spike;
import obstacles.FlyRing;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


public class Level {
    private static final int GROUND_HEIGHT = 100;
    private static final double BACKGROUND_SCALE = 1.5;

    // Couleurs utilisées dans le design
    private static final Color GROUND_COLOR_TOP = Color.rgb(50, 50, 80);  // Couleur du haut du sol
    private static final Color GROUND_COLOR_BOTTOM = Color.rgb(30, 30, 50);  // Couleur du bas du sol
    private static final Color BG_COLOR_TOP = Color.rgb(25, 25, 60);  // Couleur du haut de l'arrière-plan
    private static final Color BG_COLOR_BOTTOM = Color.rgb(70, 40, 120);  // Couleur du bas de l'arrière-plan
    private static final Color GRID_COLOR = Color.rgb(255, 255, 255, 0.1);  // Couleur de la grille du sol

  
    private final Group backgroundGroup; // Groupe graphique pour l'arrière-plan
    private final Group groundGroup; // Groupe graphique pour le sol
    private final List<GameObstacle> obstacles; // Liste des obstacles dans le niveau
    private final int groundY; // Position Y du sol
    private final int levelWidth; // Largeur du niveau
    private final int levelHeight; // Hauteur du niveau
    private final int levelLength; // Longueur totale du niveau

    /**
     * Constructeur pour initialiser un niveau avec ses dimensions.
     *
     * @param width  Largeur de la fenêtre de jeu
     * @param height Hauteur de la fenêtre de jeu
     */
    public Level(int width, int height) {
        this.levelWidth = width;
        this.levelHeight = height;
        this.groundY = height - GROUND_HEIGHT; // Calcul de la position Y du sol
        this.obstacles = new ArrayList<>();
        this.levelLength = calculateLevelLength(); // Calcul de la longueur du niveau
        this.backgroundGroup = createBackground(); // Création de l'arrière-plan
        this.groundGroup = createGround(); // Création du sol
        initializeObstacles(); // Initialisation des obstacles
    }

    private void initializeObstacles() {
        try {
            
            for ( int i = 0; i < 1; i++){
                Spike spike = new Spike(3500, groundY - 40,40,Color.RED);
                spike.setLevel(this);
                obstacles.add(spike);
            }
            for ( int i = 0; i < 1; i++){
                Spike spike = new Spike(4200, groundY - 40,40,Color.RED);
                spike.setLevel(this);
                obstacles.add(spike);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(2300+(i *40), groundY - 40 , Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for ( int i = 0; i < 1; i++){
                Spike spike = new Spike(2440, groundY - 40,40,Color.RED);
                spike.setLevel(this);
                obstacles.add(spike);
            }
            for ( int i = 0; i < 1; i++){
                Spike spike = new Spike(6210, groundY - 40,40,Color.RED);
                spike.setLevel(this);
                obstacles.add(spike);
            }





            // DEBUT DU GOOD LUCK
            for (int i = 0; i < 5; i++) {
                Block block = new Block(500, groundY - 450 + (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 2; i++) {
                Block block = new Block(540, groundY - 450 + (i *160), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 2; i++) {
                Block block = new Block(580, groundY - 450 + (i *160), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 2; i++) {
                Block block = new Block(580, groundY - 350 +(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(660, groundY - 450 + (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(660 + (i *40), groundY - 450 , Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(660 + (i *40), groundY - 290 , Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 4; i++) {
                Block block = new Block(740, groundY - 450 + (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 4; i++) {
                Block block = new Block(820, groundY - 450 + (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 2; i++) {
                Block block = new Block(820 + (i *40), groundY - 450 , Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 2; i++) {
                Block block = new Block(820 + (i *40), groundY - 290 , Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(900, groundY - 450 + (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(980, groundY - 450 + (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(1020, groundY - 450, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(1060, groundY - 410+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(1020, groundY - 290 , Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1160, groundY - 450 + (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(1160 + (i *40), groundY - 290, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1320 , groundY - 450+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(1320 + (i *40), groundY - 290, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1420 , groundY - 450+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1500 , groundY - 450+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(1500+ (i *40), groundY - 450, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 3; i++) {
                Block block = new Block(1500+ (i *40), groundY - 290, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1660, groundY - 450+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1660, groundY - 450+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1660, groundY - 450+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(1660, groundY - 450+ (i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(1700, groundY - 370, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(1740, groundY - 410, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(1740, groundY - 330, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(1780, groundY - 450, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(1780, groundY - 290, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 7; i++) {
                Block block = new Block(1880, groundY - 550+(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for ( int i = 0; i < 1; i++){
                Spike spike = new Spike(1880, groundY - 220,40,Color.RED);
                spike.setLevel(this);
                obstacles.add(spike);
            }
            // FIN GOOD LUCK 



            for (int i = 0; i < 10; i++) {
                Block block = new Block(2800 + (i *40), groundY - 40, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            
            for (int i = 0; i < 6; i++) {
                Block block = new Block(2840 + (i *40), groundY - 60-(i *10), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
           
            for (int i = 0; i < 9; i++) {
                Block block = new Block(4200 + (i *300), groundY - 40, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 5; i++) {
                Block block = new Block(6000 + (i *40), groundY - 40, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 14; i++) {
                Block block = new Block(6900 + (i *40), groundY - 40, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 6; i++) {
                Block block = new Block(7100 + (i *40), groundY - 80, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 6; i++) {
                Block block = new Block(7100 + (i *40), groundY - 360, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 6; i++) {
                Block block = new Block(7500 + (i *40), groundY - 40, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 8; i++) {
                Block block = new Block(8400 + (i *40), groundY - 50, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 2; i++) {
                Block block = new Block(8560 + (i *40), groundY - 70, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 8; i++) {
                Block block = new Block(9000 + (i *40), groundY - 80, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 8; i++) {
                Block block = new Block(9200 + (i *40), groundY - 80, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 6; i++) {
                Block block = new Block(10000 + (i *40), groundY - 40, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(9740 + (i *40), groundY - 480, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(9700 + (i *40), groundY - 480, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(9500 + (i *40), groundY - 320, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(9540 + (i *40), groundY - 360, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(9580 + (i *40), groundY - 400, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(9620 + (i *40), groundY - 440, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 1; i++) {
                Block block = new Block(9660 + (i *40), groundY - 700, Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 6; i++) {
                Block block = new Block(10060+(i *40), groundY - 40-(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 4; i++) {
                Block block = new Block(10060+(i *40), groundY - 640+(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 6; i++) {
                Block block = new Block(10360+(i *40), groundY - 40-(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 4; i++) {
                Block block = new Block(10360+(i *40), groundY - 640+(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 6; i++) {
                Block block = new Block(10360+(i *40), groundY - 40-(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 4; i++) {
                Block block = new Block(10860, groundY - 640+(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for (int i = 0; i < 4; i++) {
                Block block = new Block(10860, groundY - 40-(i *40), Color.BLUE);
                block.setLevel(this);
                obstacles.add(block);
            }
            for ( int i = 0; i < 5; i++){
                Spike spike = new Spike(11400, groundY - 40-(i *40),40,Color.RED);
                spike.setLevel(this);
                obstacles.add(spike);
            }
            for ( int i = 0; i < 5; i++){
                Spike spike = new Spike(11400, groundY - 640+(i *40),40,Color.RED);
                spike.setLevel(this);
                obstacles.add(spike);
            }
            /*for (int i = 0; i < 10000; i++) {
                Spike spike = new Spike(0 + (i *40), groundY - 640,40, Color.BLUE);
                spike.setLevel(this);
                obstacles.add(spike);
            }*/
            FlyRing flyRing = new FlyRing(10000, groundY - 180, 60);
            flyRing.setLevel(this);
            obstacles.add(flyRing);
            System.out.println("FlyRing ajouté avec succès à : x=600, y=" + (groundY - 180));

            // Ajoute un mur rouge à la fin du niveau
            double wallPosition = levelLength - 100; 
            Block endWall = new Block(wallPosition, 0, Color.RED);
            endWall.setLevel(this);

            // Configure la taille du mur pour couvrir toute la hauteur du niveau
            Rectangle wallShape = (Rectangle) endWall.getShape();
            wallShape.setHeight(levelHeight);
            wallShape.setWidth(100);

            obstacles.add(endWall);

        } catch (Exception e) {
            System.out.println("Exception lors de l'initialisation des obstacles:");
            e.printStackTrace();
        }
    }

    /**
   
     *
     * @return Groupe graphique contenant l'arrière-plan.
     */
    private Group createBackground() {
        Group bg = new Group();

        try {
          
            Image backgroundImage = new Image("file:src/resources/Background.png");

           
            double imageRatio = backgroundImage.getWidth() / backgroundImage.getHeight();
            double scaledHeight = levelHeight;
            double scaledWidth = scaledHeight * imageRatio * BACKGROUND_SCALE;

    
            Rectangle background = new Rectangle(0, 0, scaledWidth, levelHeight);
            ImagePattern pattern = new ImagePattern(
                backgroundImage, 0, 0, scaledWidth, levelHeight, false
            );
            background.setFill(pattern);
            bg.getChildren().add(background);

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image d'arrière-plan: " + e.getMessage());
            bg.getChildren().add(createFallbackBackground());
        }

        return bg;
    }

    /**
     * Calcule la longueur totale du niveau en fonction de l'image d'arrière-plan.
     *
     * @return Longueur du niveau.
     */
    private int calculateLevelLength() {
        try {
            Image backgroundImage = new Image("file:src/resources/Background.png");
            double imageWidth = backgroundImage.getWidth();
            double imageHeight = backgroundImage.getHeight();
            double ratio = imageWidth / imageHeight;
            double newLength = (levelHeight * ratio * BACKGROUND_SCALE) * 0.92;

            return (int) newLength;
        } catch (Exception e) {
            System.err.println("Erreur lors du calcul de la longueur: " + e.getMessage());
            return 10000; // Longueur par défaut en cas d'échec
        }
    }

    /**
     * Crée un arrière-plan de secours avec un dégradé si l'image échoue à se charger.
     *
     * @return Rectangle représentant le dégradé.
     */
    private Rectangle createFallbackBackground() {
        Rectangle background = new Rectangle(0, 0, levelLength, levelHeight);
        LinearGradient bgGradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, BG_COLOR_TOP), new Stop(1, BG_COLOR_BOTTOM)
        );
        background.setFill(bgGradient);
        return background;
    }

    /**
     * Crée le sol du niveau avec un dégradé et une ligne supérieure visible.
     *
     * @return Groupe graphique contenant le sol.
     */
    private Group createGround() {
        Group ground = new Group();

        // Crée le rectangle principal représentant le sol
        Rectangle mainGround = new Rectangle(0, groundY, levelLength, GROUND_HEIGHT);
        LinearGradient groundGradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, GROUND_COLOR_TOP), new Stop(1, GROUND_COLOR_BOTTOM)
        );
        mainGround.setFill(groundGradient);

        Line topLine = new Line(0, groundY, levelLength, groundY);
        topLine.setStroke(Color.rgb(255, 255, 255, 0.3));
        topLine.setStrokeWidth(2);

        ground.getChildren().addAll(mainGround, topLine);
        createGroundPattern(ground); 
        return ground;
    }

    /**
     * Ajoute un motif de grille en diagonale sur le sol.
     *
     * @param ground Groupe graphique du sol.
     */
    private void createGroundPattern(Group ground) {
        double patternWidth = 100;
        for (double x = 0; x < levelLength; x += patternWidth) {
            Line pattern = new Line(x, groundY + 20, x + patternWidth / 2, groundY + GROUND_HEIGHT - 20);
            pattern.setStroke(GRID_COLOR);
            pattern.setStrokeWidth(2);
            ground.getChildren().add(pattern);
        }
    }

    // Getters pour accéder aux propriétés du niveau
    public Group getBackgroundGroup() {
        return backgroundGroup;
    }

    public Group getGroundGroup() {
        return groundGroup;
    }

    public int getGroundY() {
        return groundY;
    }

    public List<GameObstacle> getObstacles() {
        return obstacles;
    }

    public int getLevelLength() {
        return levelLength;
    }
}
