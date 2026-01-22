package term_project;



import javax.swing.*;
import java.awt.*;

public class LoginDialog {

    public static void show(UserManager userManager, boolean isRegister) {
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null, panel,
        		
                isRegister ? "Register" : "Login", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            boolean success;
            
            if (isRegister) {
                success = userManager.register(username, password);
                JOptionPane.showMessageDialog(null, success ? "Kayıt başarılı" : "Bu kullanıcı adı zaten var!");
            } else {
                success = userManager.login(username, password);
                if (!success) {
                    JOptionPane.showMessageDialog(null, "Hatalı giriş!");
                }

            }
        }
    }
}
