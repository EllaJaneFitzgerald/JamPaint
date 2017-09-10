import java.awt.*;
import java.awt.event.MouseEvent;

public class Dot implements Tool{
    static final int ID = -1;

    @Override
    public void mouseDragged(MouseEvent ev) {}

    @Override
    public void mousePressed(MouseEvent ev) {}

    @Override
    public void mouseClicked(MouseEvent ev) {}

    @Override
    public void mouseReleased(MouseEvent ev) {}

    @Override
    public void setThickness(int thickness) {}

    @Override
    public void setColor(Color color) {}

    @Override
    public int getId() {
        return ID;
    }

    public static void drawIt(int x, int y, int startX, int StartY, int thickness, Color color) {
        Graphics2D[] gr = {(Graphics2D) desk.getGraphics(), (Graphics2D) bufferedImage.getGraphics()};
        for (Graphics2D g: gr) {
            g.setColor(color);
            g.fillOval(x-thickness/2, y-thickness/2, thickness, thickness);
        }
    }

}
