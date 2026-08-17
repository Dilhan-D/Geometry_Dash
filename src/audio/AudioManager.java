package audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class AudioManager {
    private static AudioManager instance;
    private MediaPlayer menuMusic;
    private MediaPlayer gameMusic;
    private MediaPlayer currentMusic;
    private boolean isMuted = false;

    private AudioManager() {
        initializeAudio();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    private void initializeAudio() {
        try {
            URL menuMusicUrl = getClass().getResource("/resources/music/menu.mp3");
            if (menuMusicUrl != null) {
                Media menuMedia = new Media(menuMusicUrl.toString());
                menuMusic = new MediaPlayer(menuMedia);
                menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
            }

            URL gameMusicUrl = getClass().getResource("/resources/music/game.mp3");
            if (gameMusicUrl != null) {
                Media gameMedia = new Media(gameMusicUrl.toString());
                gameMusic = new MediaPlayer(gameMedia);
                gameMusic.setCycleCount(MediaPlayer.INDEFINITE);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des fichiers audio : " + e.getMessage());
        }
    }

    public void playMenuMusic() {
        stopCurrentMusic();
        if (menuMusic != null) {
            menuMusic.play();
            currentMusic = menuMusic;
        }
    }

    public void playGameMusic() {
        stopCurrentMusic();
        if (gameMusic != null) {
            gameMusic.play();
            currentMusic = gameMusic;
        }
    }

    public void restartGameMusic() {
        if (gameMusic != null) {
            gameMusic.stop();
            gameMusic.play();
        }
    }

    private void stopCurrentMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
    }

    public void stop() {
        if (menuMusic != null) {
            menuMusic.dispose();
        }
        if (gameMusic != null) {
            gameMusic.dispose();
        }
    }

    public void toggleMute() {
        isMuted = !isMuted;
        if (menuMusic != null) {
            menuMusic.setMute(isMuted);
        }
        if (gameMusic != null) {
            gameMusic.setMute(isMuted);
        }
    }

    public boolean isMuted() {
        return isMuted;
    }
}