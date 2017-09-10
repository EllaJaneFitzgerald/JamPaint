import java.awt.*;

public abstract class FactoryDrawingPanel {
    public abstract DrawingPanel getDrawingPanel(int x, int y, int startX, int startY, int thickness, Color color);
}
