package main.java.com.game.core;

public class GameSettings {

    private static String idioma = "es"; // por defecto
    private static boolean narracionPorVoz = false; // por defecto

    public static String getLanguage() {
        return idioma;
    }

    public static void setLanguage(String language) {
        GameSettings.idioma = language;
    }

    public static boolean isVoiceNarrationEnabled() {
        return narracionPorVoz;
    }

    public static void setVoiceNarrationEnabled(boolean enabled) {
        GameSettings.narracionPorVoz = enabled;
    }
}
