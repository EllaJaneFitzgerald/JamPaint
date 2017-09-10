import java.awt.*;
import java.awt.event.MouseEvent;

public class NoTool implements Tool {
    static int id=0;
    @Override
    public void mouseDragged(MouseEvent ev) {}
    public void mousePressed(MouseEvent ev) {}
    public void mouseClicked(MouseEvent ev) {}
    public void mouseReleased(MouseEvent ev) {}
    public void setThickness(int thickness) {}
    public void setColor(Color color) {}

    @Override
    public int getId() {
        return id;
    }
}
