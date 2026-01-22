package term_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Player {

    private int x, y;
    private int speed = 10;

    private Image imageLeft, imageRight, imageDead;
    private boolean facingRight = true;
    private boolean isDead = false;

    private Weapon weapon;

    public Player() {
    	
        x = 400;
        y = 480;

        imageLeft = loadImage("images/player-left.png");
        imageRight = loadImage("images/player-right.png");
        imageDead = loadImage("images/player-dead.png");

        weapon = new Weapon();
    }

    private Image loadImage(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Görsel bulunamadı: " + path);
            return null;
        }
        return new ImageIcon(url).getImage();
    }

    public void draw(Graphics g) {
        if (isDead && imageDead != null) {
            g.drawImage(imageDead, x, y, null);
        } else if (facingRight) {
            g.drawImage(imageRight, x, y, null);
        } else {
            g.drawImage(imageLeft, x, y, null);
        }

        weapon.draw(g);
    }

    public void move(int dx) {
        x += dx;
        facingRight = dx > 0;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            move(-speed);
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            move(speed);
        }

        if (key == KeyEvent.VK_SPACE) {
            weapon.fire(x, y);
        }
    }

    public void update() {
        weapon.update();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 5);
    }

    public void resetPosition() {
        x = 400;
        y = 480;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
