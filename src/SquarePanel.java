import java.awt.*;

public class SquarePanel extends DrawingPanel {
    int x;
    int y;
    int startX;
    int startY;
    int thickness;
    Color color;

    public SquarePanel (int x, int y, int startX, int startY, int thickness, Color color) {
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.startY = startY;
        this.thickness = thickness;
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        int[] xCoord = {startX,x,x,startX};
        int[] yCoord = {startY,startY,y,y};
        g2.drawPolygon(xCoord,yCoord,4);
    }

}
