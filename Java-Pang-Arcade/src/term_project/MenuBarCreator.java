package term_project;

import javax.swing.*;

public class MenuBarCreator {

    public static JMenuBar createMenuBar(JFrame frame, UserManager userManager) {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGame = new JMenuItem("New");
        
        newGame.addActionListener(e -> {
            if (UserManager.getCurrentUsername().equals("guest")) {
                JOptionPane.showMessageDialog(frame, "Lütfen oyuna başlamadan önce giriş yapın.");
                LoginDialog.show(userManager, false);
                if (UserManager.getCurrentUsername().equals("guest")) {
                    return;
                }
            }

            // önceki oyun varsa durdur
            
            if (frame.getContentPane().getComponentCount() > 0 &&
                frame.getContentPane().getComponent(0) instanceof GamePanel gp) {
                gp.stopGame();
            }

            // yeni oyun başlat
            
            GamePanel gamePanel = new GamePanel();
            frame.getContentPane().removeAll();
            frame.getContentPane().add(gamePanel);
            frame.revalidate();
            frame.repaint();

            
            // odak (focus) ayarla
            
            SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
        });

        JMenuItem register = new JMenuItem("Register");
        register.addActionListener(e -> LoginDialog.show(userManager, true));

        JMenuItem login = new JMenuItem("Login");
        login.addActionListener(e -> LoginDialog.show(userManager, false));

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(e -> System.exit(0));

        gameMenu.add(newGame);
        gameMenu.add(register);
        gameMenu.add(login);
        gameMenu.addSeparator();
        gameMenu.add(quit);

        JMenu optionsMenu = new JMenu("Options");

        JMenuItem historyItem = new JMenuItem("History");
        historyItem.addActionListener(e -> new ScoreHistoryWindow());

        JMenuItem highScoreItem = new JMenuItem("High Score");
        highScoreItem.addActionListener(e -> new HighScoreWindow());

        JMenu difficultyMenu = new JMenu("Difficulty");

        JMenuItem novice = new JMenuItem("Novice");
        novice.addActionListener(e -> GameSettings.setDifficulty(GameSettings.Difficulty.NOVICE));

        JMenuItem intermediate = new JMenuItem("Intermediate");
        intermediate.addActionListener(e -> GameSettings.setDifficulty(GameSettings.Difficulty.INTERMEDIATE));

        JMenuItem advanced = new JMenuItem("Advanced");
        advanced.addActionListener(e -> GameSettings.setDifficulty(GameSettings.Difficulty.ADVANCED));

        difficultyMenu.add(novice);
        difficultyMenu.add(intermediate);
        difficultyMenu.add(advanced);


        optionsMenu.add(historyItem);
        optionsMenu.add(highScoreItem);
        optionsMenu.add(difficultyMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(
                frame,
                "Developer: BERK DERELİ\nSchool No: 20220702093\nEmail: berk.dereli2@std.yeditepe.edu.tr ",
                "About",
                
                JOptionPane.INFORMATION_MESSAGE
                
        ));
        helpMenu.add(about);

        menuBar.add(gameMenu);
        
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}
