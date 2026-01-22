package term_project;

import javax.swing.*;

public class GameWindow extends JFrame {

    private UserManager userManager;

    public GameWindow() {
        setTitle("Pang Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        userManager = new UserManager();
        setJMenuBar(MenuBarCreator.createMenuBar(this, userManager));

        // Kullanıcı login değilse, başta zorunlu giriş yap
        
        if (UserManager.getCurrentUsername().equals("guest")) {

            Object[] options = {"Login", "Register"};
            int choice = JOptionPane.showOptionDialog(
                this,
                "Giriş yapmak mı yoksa yeni hesap oluşturmak mı istersiniz?",
                "Oturum Aç",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 0) {
                LoginDialog.show(userManager, false);
            } else if (choice == 1) {
                LoginDialog.show(userManager, true);
            }

            //  Giriş başarılıysa GamePanel başlat
            
            if (!UserManager.getCurrentUsername().equals("guest")) {
                getContentPane().removeAll();
                add(new GamePanel());
                revalidate();
                repaint();
            } else {
                // Kullanıcı giriş yapmazsa sadece boş ekran göster
            	
            	
                add(new JPanel());
            }
        } else {
            // Kullanıcı zaten giriş yapmışsa doğrudan oyun başlat
        	
            add(new GamePanel());
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow());
    }
}
