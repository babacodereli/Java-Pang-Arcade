package term_project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Bubble {

    public enum Size { XL, L, M, S }

    private int x, y;
    private int dx = 2; // sağa sola hareket hızı
    private int dy = -10; // zıplama yüksekliği
    private int gravity = 1;

    private Size size;
    private Image image;

    public Bubble(int x, int y, Size size) {
    	
        this.x = x;
        this.y = y;
        this.size = size;
        
        loadImage();
    }

    private void loadImage() {
    	
        String fileName = switch (size) {
            case XL -> "baloons-xl.png";
            case L  -> "baloons-l.png";
            case M  -> "baloons-m.png";
            case S  -> "baloons-s.png";
        };
        URL url = getClass().getResource("images/" + fileName);
        if (url == null) {
        	
            System.out.println("Balon görseli bulunamadı: " + fileName);
            return;
        }
        
        image = new ImageIcon(url).getImage();
    }

    public void update() {
    	
        x += dx;
        y += dy;
        
        dy += gravity;

        // Kenarlardan sekme
        if (x < 0 || x > 760) dx *= -1;

        // Zemine çarpınca yukarı zıpla
        if (y > 480) {
            y = 480;
            dy = -15;
        }
    }

    public void draw(Graphics g) {
    	
        if (image != null) {
            g.drawImage(image, x, y, null);
        }
    }

    public Rectangle getBounds() {
    	
        return new Rectangle(x, y, 40, 40); // basit çarpışma alanı
    }
    public Bubble.Size getSize() {
    	
        return size;
    }

    public int getX() {
    	
        return x;
    }

    public int getY() {
    	
        return y;
    }

}
