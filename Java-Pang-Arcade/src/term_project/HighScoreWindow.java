package term_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;




public class HighScoreWindow extends JFrame {

    public HighScoreWindow() {
    	
        setTitle("Top 10 High Scores");
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"Username", "Score", "Date", "Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        List<String[]> allScores = new ArrayList<>();

        File file = new File("data/scores.csv");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Henüz hiç skor kaydedilmedi.");
            
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    allScores.add(parts);
                }
            }

            // skor değerine göre azalan sırala
            
            allScores.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

            // ilk 10 taneyi ekle
            
            for (int i = 0; i < Math.min(10, allScores.size()); i++) {
                tableModel.addRow(allScores.get(i));
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Skorlar okunurken hata oluştu.");
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }
}
