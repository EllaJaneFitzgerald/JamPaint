import javax.swing.*;
import java.awt.*;

public class Line extends MovingTools {
    final static int ID = 2;

    public Line(int thickness, Color color, JPanel lastLayout){
        this.thickness = thickness;
        this.color = color;
        factory = new FactoryLinePanel();
        this.lastLayout = lastLayout;
    }

    public static void drawIt(int x, int y, int startX, int startY, int thickness, Color color) {
        Graphics2D[] gr = {(Graphics2D) desk.getGraphics(), (Graphics2D) bufferedImage.getGraphics()};
        for (Graphics2D g: gr) {
            g.setColor(color);
            g.setStroke(new BasicStroke(thickness));
            g.drawLine(x,y,startX,startY);
        }
    }

    @Override
    public int getId() {
        return ID;
    }
}
