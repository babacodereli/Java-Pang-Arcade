package term_project;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static final String USER_FILE = "data/users.csv";
    private Map<String, String> users = new HashMap<>();
    public static String currentUsername = "guest";

    public UserManager() {
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Kullanıcı dosyası bulunamadı. Yeni dosya oluşturulacak.");
        }
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, password);
        saveUsers();
        return true;
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUsername = username;
            return true;
            
        }
        return false;
    }

    private void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Kullanıcı dosyasına yazılamadı.");
        }
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }
}
