package term_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel {
	

    private Player player;
    private List<Bubble> bubbles;
    
    private Timer timer;
    private Timer gameTimer;
    private Timer invincibleTimer;

    private int lives = 3;
    
    private int timeLeft = 90;
    private int score = 0;

    private boolean invincible = false;

    private Image backgroundImage;

    public GamePanel() {
        setFocusable(true);
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        // zorluk seviyesine göre arka plan seç
        String bgFile = switch (GameSettings.getDifficulty()) {
        
            case NOVICE -> "images/Backgrounds-1.png";
            case INTERMEDIATE -> "images/Backgrounds-2.png";
            case ADVANCED -> "images/Backgrounds-3.png";
        };

        backgroundImage = loadImage(bgFile);
        
        player = new Player();
        initializeBubbles();

        addKeyListener(new KeyAdapter() {
        	
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }
            
        });

        timer = new Timer(16, e -> {
        	
            player.update();

            Iterator<Bubble> it = bubbles.iterator();
            List<Bubble> newBubbles = new ArrayList<>();

            while (it.hasNext()) {
                Bubble bubble = it.next();
                
                bubble.update();

                if (!invincible && bubble.getBounds().intersects(player.getBounds())) {
                    lives--;

                    if (lives <= 0) {
                        ScoreManager.saveScore(UserManager.getCurrentUsername(), score);
                        stopGame();
                        JOptionPane.showMessageDialog(this, "Canların bitti! Oyun sona erdi.");
                        return;
                    }

                    player.resetPosition();
                    
                    player.setDead(true);
                    invincible = true;

                    invincibleTimer = new Timer(1000, ev -> {
                        invincible = false;
                        player.setDead(false);
                    });
                    invincibleTimer.setRepeats(false);
                    invincibleTimer.start();

                    break;
                }

                
                
                if (player.getWeapon().isActive() &&
                    bubble.getBounds().intersects(player.getWeapon().getBounds())) {

                    score += 10;
                    it.remove();

                    Bubble.Size size = bubble.getSize();
                    if (size != Bubble.Size.S) {
                        Bubble.Size nextSize = switch (size) {
                            case XL -> Bubble.Size.L;
                            case L -> Bubble.Size.M;
                            case M -> Bubble.Size.S;
                            
                            default -> null;
                        };

                        if (nextSize != null) {
                        	
                            newBubbles.add(new Bubble(bubble.getX() - 20, bubble.getY(), nextSize));
                            newBubbles.add(new Bubble(bubble.getX() + 20, bubble.getY(), nextSize));
                        }
                    }

                    player.getWeapon().setActive(false);
                }
                
            }

            bubbles.addAll(newBubbles);
            repaint();
        });

        timer.start();

        gameTimer = new Timer(1000, e -> {
            if (timeLeft > 0) {
                timeLeft--;
            } else {
                ScoreManager.saveScore(UserManager.getCurrentUsername(), score);
                stopGame();
                JOptionPane.showMessageDialog(this, "Süre doldu! Oyun bitti.");
            }
        });

        gameTimer.start();
    }

    private void initializeBubbles() {
        bubbles = new ArrayList<>();
        GameSettings.Difficulty level = GameSettings.getDifficulty();

        int count = switch (level) {
            case NOVICE -> 1;
            case INTERMEDIATE -> 2;
            case ADVANCED -> 3;
        };

        for (int i = 0; i < count; i++) {
            bubbles.add(new Bubble(100 + i * 100, 100, Bubble.Size.XL));
        }
    }

    private Image loadImage(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Görsel bulunamadı: " + path);
            return null;
        }
        return new ImageIcon(url).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int hudHeight = 30;
        int usableHeight = getHeight() - hudHeight;

        // Arka plan görselini yukarıya çiz
        if (backgroundImage != null) {
        	
            g.drawImage(backgroundImage, 0, 0, getWidth(), usableHeight, null);
        }

        player.draw(g);
        for (Bubble bubble : bubbles) {
            bubble.draw(g);
        }

        // Siyah alt bar (HUD)
        g.setColor(Color.BLACK);
        g.fillRect(0, usableHeight, getWidth(), hudHeight);

        // Bilgi yazıları
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        
        g.drawString("Lives: " + lives, 20, usableHeight + 30);
        g.drawString("Score: " + score, getWidth() / 2 - 40, usableHeight + 30);
        g.drawString("Time: " + timeLeft + "s", getWidth() - 120, usableHeight + 30);
    }

    public void stopGame() {
        if (timer != null) timer.stop();
        
        if (gameTimer != null) timer.stop();
        if (invincibleTimer != null) invincibleTimer.stop();
    }
}

