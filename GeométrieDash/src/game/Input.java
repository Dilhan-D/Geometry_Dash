package game;

import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public final class Input {
    private static final Map<KeyCode, Boolean> PRESSED_KEYS = new ConcurrentHashMap<>();


    private Input() {}

    /**
     * Définit l'état d'une touche (pressée ou relâchée)
     * @param key La touche concernée
     * @param pressed true si la touche est pressée, false si relâchée
     */
    public static void setKeyPressed(KeyCode key, boolean pressed) {
        if (key != null) {
            PRESSED_KEYS.put(key, pressed);
        }
    }

    /**
     * Vérifie si une touche est actuellement pressée
     * @param key La touche à vérifier
     * @return true si la touche est pressée, false sinon
     */
    public static boolean isKeyPressed(KeyCode key) {
        return key != null && PRESSED_KEYS.getOrDefault(key, false);
    }

    public static void reset() {
        PRESSED_KEYS.clear();
    }

    /**
     * Vérifie si au moins une touche est pressée
     * @return true si au moins une touche est pressée, false sinon
     */
    public static boolean isAnyKeyPressed() {
        return PRESSED_KEYS.containsValue(true);
    }
}