package com.example.laura.quiz;

public class Opciones {

    private static int numPreg;
    private static boolean textoimagen;
    private static boolean imagentexto;
    private static boolean imagenimagen;
    private static boolean videotexto;

    public static int getNumPreg() {
        return numPreg;
    }

    public static void setNumPreg(int numPreg) {
        Opciones.numPreg = numPreg;
    }

    public static boolean isTextoimagen() {
        return textoimagen;
    }

    public static void setTextoimagen(boolean textoimagen) {
        Opciones.textoimagen = textoimagen;
    }

    public static boolean isImagentexto() {
        return imagentexto;
    }

    public static void setImagentexto(boolean imagentexto) {
        Opciones.imagentexto = imagentexto;
    }

    public static boolean isImagenimagen() {
        return imagenimagen;
    }

    public static void setImagenimagen(boolean imagenimagen) {
        Opciones.imagenimagen = imagenimagen;
    }

    public static boolean isVideotexto() {
        return videotexto;
    }

    public static void setVideotexto(boolean videotexto) {
        Opciones.videotexto = videotexto;
    }
}
