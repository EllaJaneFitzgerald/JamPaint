import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class MovingTools implements Tool{
    int x;
    int y;
    int startX;
    int startY;
    int thickness;
    Color color;
    JPanel helpLayout = null;
    JPanel helpLayout2 = null;
    JPanel helpLayout3 = null;
    JPanel lastLayout;
    FactoryDrawingPanel factory;
    int flag = 0;


    @Override
    public void mousePressed(MouseEvent ev) {
        startX = ev.getX();
        startY = ev.getY();
        x = startX;
        y = startY;

        helpLayout = factory.getDrawingPanel(x, y, startX, startY, thickness,color);
        helpLayout.setOpaque(false);
        helpLayout.setVisible(false);
        desk.add(helpLayout);
        helpLayout2 = factory.getDrawingPanel(x, y, startX, startY, thickness,color);
        helpLayout2.setOpaque(false);
        helpLayout2.setVisible(false);
        desk.add(helpLayout2);
        helpLayout3 = factory.getDrawingPanel(x, y, startX, startY, thickness,color);
        helpLayout3.setOpaque(false);
        helpLayout3.setVisible(false);
        desk.add(helpLayout3);
}

    @Override
    public void mouseDragged(MouseEvent ev) {
        x = ev.getX();
        y = ev.getY();

        if (Math.floorMod(flag,3)==0) {
            helpLayout.setVisible(false);
            desk.remove(helpLayout);
            helpLayout2.setVisible(true);

            helpLayout3 = factory.getDrawingPanel(x, y, startX, startY, thickness,color);
            helpLayout3.setOpaque(false);
            helpLayout3.setVisible(false);
            desk.add(helpLayout3);
        } else if (Math.floorMod(flag,3)==1) {
            helpLayout2.setVisible(false);
            desk.remove(helpLayout2);
            helpLayout3.setVisible(true);

            helpLayout = factory.getDrawingPanel(x, y, startX, startY, thickness,color);
            helpLayout.setOpaque(false);
            helpLayout.setVisible(false);
            desk.add(helpLayout);
        } else {
            helpLayout3.setVisible(false);
            desk.remove(helpLayout3);
            helpLayout.setVisible(true);

            helpLayout2 = factory.getDrawingPanel(x, y, startX, startY, thickness,color);
            helpLayout2.setOpaque(false);
            helpLayout2.setVisible(false);
            desk.add(helpLayout2);
        }
        flag++;
    }

    @Override
    public void mouseClicked(MouseEvent ev) {
        try {
            x = ev.getX();
            y = ev.getY();
            client.allClientsDrawIt(x,y,x,y,thickness,color,Dot.ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent ev) {

        flag--;
            if (Math.floorMod(flag,3)==0) {
                helpLayout2.setVisible(true);
                helpLayout.setVisible(false);
                helpLayout3.setVisible(false);
                desk.remove(helpLayout);
                desk.remove(helpLayout3);
                lastLayout = helpLayout2;
            } else if (Math.floorMod(flag,3)==1) {
                helpLayout3.setVisible(true);
                helpLayout.setVisible(false);
                helpLayout2.setVisible(false);
                desk.remove(helpLayout);
                desk.remove(helpLayout2);
                lastLayout = helpLayout3;
            } else {
                helpLayout.setVisible(true);
                helpLayout2.setVisible(false);
                helpLayout3.setVisible(false);
                desk.remove(helpLayout2);
                desk.remove(helpLayout3);
                lastLayout = helpLayout;
            }

        lastLayout.setVisible(false);

        try {
            client.allClientsDrawIt(x,y,startX,startY,thickness,color,this.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        flag = 0;
    }

    @Override
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
