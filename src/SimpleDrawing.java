import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SimpleDrawing {
    static JFrame frame;
    static JPanel desk;
    static BufferedImage bufferedImage = null;
    static DrawingClient client;
    JSlider thicknessSlider;
    JColorChooser colorChooser;
    int thickness = 1;
    Color color = Color.black;
    PreviewPanel preview;
    Tool tool = new NoTool();
    JPanel lastLayout; //отмена
    Color backColor = new Color(180, 187, 198);
    Font font = new Font ("Courier", Font.PLAIN, 16);

    public static void main(String[] args) {
        new SimpleDrawing().buildGUI();
    }

    private void buildGUI() {
        frame = new JFrame("Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel background = new JPanel(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        desk = new Desk();
        background.add(BorderLayout.CENTER,desk);
        desk.addMouseListener(new DrawingMouseListener());
        desk.addMouseMotionListener(new DrawingMouseMotionListener());
        desk.setLayout(new CardLayout());


        JPanel toolsBox = new JPanel();
        toolsBox.setLayout(new BoxLayout(toolsBox, BoxLayout.Y_AXIS));
        toolsBox.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        thicknessSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        thicknessSlider.setMajorTickSpacing(1);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setSnapToTicks(true);
        thicknessSlider.addChangeListener(new ThicknessChangeListener());
        thicknessSlider.setBackground(backColor);
        thicknessSlider.setForeground(Color.white);

        colorChooser = new JColorChooser(Color.black);
        colorChooser.getSelectionModel().addChangeListener(new ColorChangeListener());
        for (AbstractColorChooserPanel p: colorChooser.getChooserPanels()) {
            if (!"RGB".equals(p.getDisplayName())) {
                colorChooser.removeChooserPanel(p);
            } else {
                int num = 0;
                for (Component j: p.getComponents()) {
                    switch (num) {
                        case 0: j.setVisible(false); break;
                        case 1:
                        case 2: p.remove(j); break;
                    }
                    num++;
                }
                p.setBackground(Color.white);
                p.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        }
        colorChooser.setPreviewPanel(new JPanel());

        for (Component j: colorChooser.getComponents()) {
            j.setBackground(backColor);
        }

        preview = new PreviewPanel();

        JButton bBrush = new JButton();
        bBrush.setBackground(Color.white);
        bBrush.setIcon(new ImageIcon("images/iconBrush.png"));
        bBrush.setPreferredSize(new Dimension(80,80));
        bBrush.addActionListener(new BrushActionListener());
        bBrush.setBorder(BorderFactory.createRaisedBevelBorder());

        JButton bLine = new JButton();
        bLine.setBackground(Color.white);
        bLine.setIcon(new ImageIcon("images/iconLine.png"));
        bLine.setPreferredSize(new Dimension(80,80));
        bLine.addActionListener(new LineActionListener());
        bLine.setBorder(BorderFactory.createRaisedBevelBorder());

        JButton bSquare = new JButton();
        bSquare.setBackground(Color.white);
        bSquare.setIcon(new ImageIcon("images/iconSquare.png"));
        bSquare.setPreferredSize(new Dimension(80,80));
        bSquare.addActionListener(new SquareActionListener());
        bSquare.setBorder(BorderFactory.createRaisedBevelBorder());

        toolsBox.add(preview);
        toolsBox.add(Box.createRigidArea(new Dimension(0, 10)));
        toolsBox.add(colorChooser);
        toolsBox.add(Box.createRigidArea(new Dimension(0, 10)));
        toolsBox.add(thicknessSlider);
        toolsBox.add(Box.createRigidArea(new Dimension(0, 10)));
        toolsBox.add(bBrush);
        toolsBox.add(Box.createRigidArea(new Dimension(0, 10)));
        toolsBox.add(bLine);
        toolsBox.add(Box.createRigidArea(new Dimension(0, 10)));
        toolsBox.add(bSquare);
        toolsBox.add(Box.createRigidArea(new Dimension(0, 370)));
        toolsBox.setOpaque(false);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);
        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.setFont(font);
        saveMenu.addActionListener(new SaveActionListener());
        fileMenu.add(saveMenu);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        background.setBackground(backColor);
        background.add(BorderLayout.WEST,toolsBox);
        frame.setContentPane(background);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.addComponentListener(new SizeComponentListener());
        try {
            client = new DrawingClient();
            client.create();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class Desk extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            if (bufferedImage==null) {
                createImage();
            }
            g.drawImage(bufferedImage, 0, 0, this);
            //this.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        private void createImage(){
            bufferedImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics g = bufferedImage.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
        }
    }

    class PreviewPanel extends JPanel {
        public PreviewPanel() {
            setPreferredSize(new Dimension(40,40));
            setBorder(BorderFactory.createRaisedBevelBorder());

        }
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(color);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
        }
    }

    class DrawingMouseListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent ev) {
            tool.mousePressed(ev);
        }
        public void mouseClicked(MouseEvent ev) {
            tool.mouseClicked(ev);
        }
        public void mouseReleased(MouseEvent ev) {
            tool.mouseReleased(ev);

        }
        public void mouseEntered(MouseEvent ev) {}
        public void mouseExited(MouseEvent e) {}
    }

    class DrawingMouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent ev) {
            tool.mouseDragged(ev);
        }
        @Override
        public void mouseMoved(MouseEvent ev) {}
    }

    class ThicknessChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent ev) {
            thickness = thicknessSlider.getValue();
            tool.setThickness(thickness);
        }
    }

    class ColorChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent ev) {
            color = colorChooser.getColor();
            preview.repaint();
            tool.setColor(color);
        }
    }

    class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileSave = new JFileChooser();
            int ret = fileSave.showDialog(null, "Save");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileSave.getSelectedFile();
                try {
                    ImageIO.write(bufferedImage, "png", file);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class SizeComponentListener implements ComponentListener {
        @Override
        public void componentResized(ComponentEvent e) {
            desk.repaint();
        }
        @Override
        public void componentMoved(ComponentEvent e) {}
        public void componentShown(ComponentEvent e) {}
        public void componentHidden(ComponentEvent e) {}
    }

    class BrushActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tool = new Brush(thickness,color,lastLayout);
        }
    }

    private class NoToolActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tool = new NoTool();
        }
    }

    private class LineActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tool = new Line(thickness,color,lastLayout);
        }
    }

    private class SquareActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tool = new Square(thickness,color,lastLayout);
        }
    }
}
