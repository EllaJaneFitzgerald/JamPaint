import javax.swing.*;
import java.awt.*;

public class Square extends MovingTools implements Tool {
    final static int ID = 3;

    public Square(int thickness, Color color, JPanel lastLayout){
        this.thickness = thickness;
        this.color = color;
        this.factory = new FactorySquarePanel();
        this.lastLayout = lastLayout;
    }

    public static void drawIt(int x, int y, int startX, int startY, int thickness, Color color) {
        Graphics2D[] gr = {(Graphics2D) desk.getGraphics(), (Graphics2D) bufferedImage.getGraphics()};
        for (Graphics2D g: gr) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            int[] xCoord = {startX,x,x,startX};
            int[] yCoord = {startY,startY,y,y};
            g2.drawPolygon(xCoord,yCoord,4);
        }
    }

    @Override
    public int getId() {
        return ID;
    }
}
