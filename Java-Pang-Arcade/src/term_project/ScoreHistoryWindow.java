package term_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class ScoreHistoryWindow extends JFrame {

    public ScoreHistoryWindow() {
    	
        setTitle("Score History");
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"Username", "Score", "Date", "Time"};
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        

        try (BufferedReader br = new BufferedReader(new FileReader("data/scores.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                Vector<String> row = new Vector<>();
                
                for (String val : values) {
                    row.add(val);
                }
                tableModel.addRow(row);
            }
        } catch (IOException e) {
        	
            JOptionPane.showMessageDialog(this, "Skor dosyası okunamadı.");
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }
}
