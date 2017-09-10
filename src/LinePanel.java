import java.awt.*;

public class LinePanel extends DrawingPanel{
    int x;
    int y;
    int startX;
    int startY;
    int thickness;
    Color color;

    public LinePanel (int x, int y, int startX, int startY, int thickness, Color color) {
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
        g2.drawLine(startX,startY,x,y);
    }

}
