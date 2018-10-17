package com.example.laura.quiz;

public class Opciones {

    private static int numPreg;
    private static String difficulty;

    public static int getNumPreg() {
        return numPreg;
    }

    public static void setNumPreg(int numPreg) {
        Opciones.numPreg = numPreg;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        Opciones.difficulty = difficulty;
    }
}
