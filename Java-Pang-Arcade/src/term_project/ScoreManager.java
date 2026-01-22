package term_project;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScoreManager {

    private static final String SCORE_FILE = "data/scores.csv";

    public static void saveScore(String username, int score) {
    	
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SCORE_FILE, true))) {
            String date = LocalDate.now().toString();
            
            String time = LocalTime.now().withNano(0).toString();
            bw.write(username + "," + score + "," + date + "," + time);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Skor dosyasına yazılamadı.");
        }
    }
}
