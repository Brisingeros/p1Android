package com.example.laura.quiz;

public class Opciones {

    private static int numPreg = 10;
    private static boolean textoimagen = false;
    private static boolean imagentexto = false;
    private static boolean imagenimagen = false;

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
}
