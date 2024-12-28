import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

public class MemePanel extends JPanel implements MouseMotionListener, MouseListener {
    private final Image loadingMeme;
    private final Image finalMeme;
    private final Image loadingSpinner;
    private boolean isFullyInitialized = false;
    private Timer initializationTimer;

    private Point panelPosition = null;
    private double panelScale;
    private Point lastMousePosition = null;

    private final double minScale;
    private final double maxScale;
    private final double scaleSpeedRatio;

    private String enteredFrom;

    public MemePanel(MemePanelInputConfig config) {
        this.loadingMeme = new ImageIcon(Objects.requireNonNull(getClass().getResource(config.tmpLoadingContent()))).getImage();
        this.finalMeme = new ImageIcon(Objects.requireNonNull(getClass().getResource(config.memePath()))).getImage();
        this.loadingSpinner = new ImageIcon(Objects.requireNonNull(getClass().getResource(config.loadingSpinnerPath()))).getImage();
        this.minScale = config.minScale();
        this.maxScale = config.maxScale();
        this.scaleSpeedRatio = config.scaleSpeedRatio();
        this.panelScale = minScale;

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    private void simulateInitialization() {
        initializationTimer = new Timer(1000, e -> {
            isFullyInitialized = true;
            initializationTimer.stop();
            repaint();
        });
        initializationTimer.setRepeats(false);
        initializationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (panelPosition != null) {
            int panelWidth = (int) (getImage().getWidth(null) * panelScale);
            int panelHeight = (int) (getImage().getHeight(null) * panelScale);
            g.drawImage(getImage(), panelPosition.x - panelWidth / 2, panelPosition.y - panelHeight / 2, panelWidth, panelHeight, this);

            if (!isFullyInitialized) {
                int spinnerSize = panelWidth / 4;
                g.drawImage(loadingSpinner, panelPosition.x - spinnerSize / 2, panelPosition.y - spinnerSize / 2, spinnerSize, spinnerSize, this);
            }
        }
    }

    private Image getImage() {
        return isFullyInitialized ? finalMeme : loadingMeme;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (panelPosition == null) {
            return;
        }

        panelPosition = new Point(e.getPoint());

        // Calculate horizontal and vertical movement
        double deltaX = e.getX() - lastMousePosition.getX();
        double deltaY = e.getY() - lastMousePosition.getY();


        // Update meme panel scaling
        if (deltaX != 0 || deltaY != 0) {
            double deltaDistance = Math.sqrt(deltaX * deltaX + deltaY * deltaY) * scaleSpeedRatio;

            boolean scaleUp = isScaleUp(deltaX, deltaY);

            if (scaleUp && panelScale < maxScale) {
                panelScale += deltaDistance;
            } else if (!scaleUp && panelScale > minScale) {
                panelScale -= deltaDistance;
            }
        }

        lastMousePosition = e.getPoint();
        repaint();
    }

    private boolean isScaleUp(double deltaX, double deltaY) {
        boolean isHorizontalDominant = Math.abs(deltaX) > Math.abs(deltaY);
        return switch (enteredFrom) {
            case "SouthWest" -> isHorizontalDominant ? deltaX > 0 : deltaY < 0;
            case "NorthEast" -> isHorizontalDominant ? deltaX < 0 : deltaY > 0;
            case "SouthEast" -> isHorizontalDominant ? deltaX < 0 : deltaY < 0;
            case "NorthWest" -> isHorizontalDominant ? deltaX > 0 : deltaY > 0;
            default -> false;
        };
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        simulateInitialization();
        // Determine the entered quadrant
        boolean isLeft = e.getX() < getImage().getWidth(null) / 2;
        boolean isTop = e.getY() < getImage().getHeight(null) / 2;
        enteredFrom = (isTop ? "North" : "South") + (isLeft ? "West" : "East");

        panelPosition = e.getPoint();
        panelScale = minScale;
        lastMousePosition = e.getPoint();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panelPosition = null;
        isFullyInitialized = false;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Not used
    }
}
