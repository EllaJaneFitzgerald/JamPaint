import java.awt.*;

public class FactoryLinePanel extends FactoryDrawingPanel{
    @Override
    public DrawingPanel getDrawingPanel(int x, int y, int startX, int startY, int thickness, Color color) {
        return new LinePanel(x, y, startX, startY, thickness,color);
    }
}
