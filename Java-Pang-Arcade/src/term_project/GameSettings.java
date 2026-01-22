package term_project;

public class GameSettings {

    public enum Difficulty {
        NOVICE, INTERMEDIATE, ADVANCED
    }

    private static Difficulty difficulty = Difficulty.NOVICE;

    public static void setDifficulty(Difficulty d) {
        difficulty = d;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }
}
