source .env

# Définir le chemin vers JUnit (maintenant dans le dossier lib)
JUNIT_PATH="lib"

# Nettoyer le dossier bin avant la compilation
rm -rf bin
mkdir -p bin/resources/fonts
mkdir -p bin/resources/music
mkdir -p bin/resources/skins
mkdir -p bin/resources/icons
mkdir -p bin/test

# Copier toutes les ressources
cp -r src/resources/music/* bin/resources/music/
cp -r src/resources/fonts/* bin/resources/fonts/
cp -r src/resources/skins/* bin/resources/skins/
cp -r src/resources/icons/* bin/resources/icons/

# Vérifier que les fichiers sont bien copiés
echo "Vérification des fichiers audio :"
ls -l bin/resources/music/

# Compilation du code principal
javac --module-path "$JAVAFX_PATH/lib" \
      --add-modules javafx.controls,javafx.fxml,javafx.media \
      -d bin \
      src/audio/AudioManager.java \
      src/menu/Menu.java \
      src/menu/EndLevelScreen.java \
      src/game/GameObject.java \
      src/game/Input.java \
      src/game/Player.java \
      src/game/Level.java \
      src/game/Trail.java \
      src/obstacles/GameObstacle.java \
      src/obstacles/Block.java \
      src/obstacles/Spike.java \
      src/obstacles/FlyRing.java \
      src/obstacles/StopFlyRing.java \
      src/Camera.java \
      src/GameEngine.java \
      src/App.java

# Compilation des tests
echo "Compilation des tests..."
javac --module-path "$JAVAFX_PATH/lib" \
      --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing \
      -cp "$JUNIT_PATH/junit-4.13.2.jar:$JUNIT_PATH/hamcrest-core-1.3.jar:bin" \
      -d bin/test \
      test/audio/AudioManagerTest.java

# Exécution des tests
echo "Exécution des tests..."
java --module-path "$JAVAFX_PATH/lib" \
     --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing \
     -cp "$JUNIT_PATH/junit-4.13.2.jar:$JUNIT_PATH/hamcrest-core-1.3.jar:bin/test:bin" \
     org.junit.runner.JUnitCore \
     audio.AudioManagerTest

# Lancement de l'application
echo "Lancement de l'application..."
java --module-path "$JAVAFX_PATH/lib" \
     --add-modules javafx.controls,javafx.fxml,javafx.media \
     -cp bin \
     App