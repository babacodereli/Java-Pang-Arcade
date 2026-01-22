package term_project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Weapon {

    private int x, y;
    private int speed = 10;
    private boolean active = false;

    private Image bulletImage;

    public Weapon() {
        bulletImage = loadImage("images/weapon-bullet.png");
    }

    private Image loadImage(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Görsel bulunamadı: " + path);
            return null;
        }
        return new ImageIcon(url).getImage();
    }

    public void fire(int startX, int startY) {
        if (!active) {
            x = startX + 20; // oyuncunun ortasına hizala
            y = startY;
            active = true;
        }
    }

    public void update() {
        if (active) {
            y -= speed;
            if (y < 0) {
                active = false;
            }
        }
    }

    public void draw(Graphics g) {
        if (active && bulletImage != null) {
            g.drawImage(bulletImage, x, y, null);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 20); // çarpışma kutusu
    }
}
