package audio;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import javafx.embed.swing.JFXPanel;

public class AudioManagerTest {

    private AudioManager audioManager;

    @BeforeClass
    public static void initJFX() {
        new JFXPanel();
    }

    @Before
    public void setUp() {
        audioManager = AudioManager.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        AudioManager instance1 = AudioManager.getInstance();
        AudioManager instance2 = AudioManager.getInstance();

        assertNotNull("L'instance ne devrait pas être null", instance1);
        assertSame("getInstance() devrait toujours retourner la même instance", instance1, instance2);
    }

    @Test
    public void testMuteToggle() {
        boolean initialMuteState = audioManager.isMuted();

        audioManager.toggleMute();
        assertTrue("Le son devrait être coupé après toggle", audioManager.isMuted());

        audioManager.toggleMute();
        assertFalse("Le son devrait être réactivé après second toggle", audioManager.isMuted());
    }

    @Test
    public void testMusicTransition() {
        // Test de la transition menu -> jeu
        audioManager.playMenuMusic();
        audioManager.playGameMusic();
        // Vérifier que la musique a bien changé (on peut seulement vérifier que ça ne cause pas d'erreur)
        assertTrue("La transition menu->jeu devrait se faire sans erreur", true);

        // Test de la transition jeu -> menu
        audioManager.playGameMusic();
        audioManager.playMenuMusic();
        assertTrue("La transition jeu->menu devrait se faire sans erreur", true);
    }

    @Test
    public void testRestartGameMusic() {
        audioManager.playGameMusic();
        audioManager.restartGameMusic();
        assertTrue("Le restart de la musique du jeu devrait se faire sans erreur", true);
    }

    @Test
    public void testMuteStatePreservedAcrossTransitions() {
        // Activer le mute
        audioManager.toggleMute();
        assertTrue("Le son devrait être coupé", audioManager.isMuted());

        // Changer de musique
        audioManager.playGameMusic();
        assertTrue("Le son devrait rester coupé après changement de musique", audioManager.isMuted());

        audioManager.playMenuMusic();
        assertTrue("Le son devrait rester coupé après retour au menu", audioManager.isMuted());

        // Remettre le son pour les autres tests
        audioManager.toggleMute();
    }
}