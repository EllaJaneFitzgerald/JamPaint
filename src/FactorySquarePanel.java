import java.awt.*;

public class FactorySquarePanel extends FactoryDrawingPanel {
    @Override
    public DrawingPanel getDrawingPanel(int x, int y, int startX, int startY, int thickness, Color color) {
        return new SquarePanel(x, y, startX, startY, thickness,color);
    }
}
