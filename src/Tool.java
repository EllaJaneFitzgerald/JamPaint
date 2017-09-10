import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public interface Tool {
    static BufferedImage bufferedImage =  SimpleDrawing.bufferedImage;
    static JPanel desk = SimpleDrawing.desk;
    static DrawingClient client = SimpleDrawing.client;

    public void mouseDragged(MouseEvent ev);
    public void mousePressed(MouseEvent ev);
    public void mouseClicked(MouseEvent ev);
    public void mouseReleased(MouseEvent ev);
    public void setThickness(int thickness);
    public void setColor(Color color);
    public int getId();
    //public void drawIt(int x, int y, int startX, int startY);

}

