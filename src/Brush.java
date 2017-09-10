import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Brush implements Tool {
    static final int ID = 1;
    private int x;
    private int y;
    private int lastX;
    private int lastY;
    private int thickness;
    private Color color;
    JPanel lastLayout;

    public Brush(int thickness, Color color, JPanel lastLayout){
        this.thickness = thickness;
        this.color = color;
        this.lastLayout = lastLayout;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public void mouseDragged(MouseEvent ev) {
        x = ev.getX();
        y = ev.getY();
        try {
            client.allClientsDrawIt(x,y,lastX,lastY,thickness,color,ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        lastX = x;
        lastY = y;
    }

    @Override
    public void mousePressed(MouseEvent ev) {
        lastX = ev.getX();
        lastY = ev.getY();
    }

    @Override
    public void mouseClicked(MouseEvent ev) {
        try {
            x = ev.getX();
            y = ev.getY();
            client.allClientsDrawIt(x,y,x,y,thickness,color,Dot.ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent ev) {}

    public static void drawIt(int x, int y, int startX, int startY, int thickness, Color color) {
        Graphics2D[] gr = {(Graphics2D) desk.getGraphics(), (Graphics2D) bufferedImage.getGraphics()};
        for (Graphics2D g: gr) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawLine(startX,startY,x,y);
        }
    }
}
