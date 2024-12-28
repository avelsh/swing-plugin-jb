import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame frame;

    public MainWindow(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void addComponentToWindow(Component component) {
        this.frame.add(component);
    }

    public void showWindow() {
        frame.setVisible(true);
    }
}
