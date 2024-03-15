package vista;
import javax.swing.*;
import java.awt.*;

public class RotatedPanel extends JPanel {

    private JComponent content;

    public RotatedPanel(JComponent content) {
        this.content = content;
        setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(90), getWidth() / 2.0, getHeight() / 2.0);
        super.paintComponent(g2d);
        g2d.dispose();
    }
}