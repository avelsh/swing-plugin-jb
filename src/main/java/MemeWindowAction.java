import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.*;

public class MemeWindowAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        SwingUtilities.invokeLater(() -> {

            // set up your preferred UI window component here
            MemePanelInputConfig inputMemePanelConfig = new MemePanelInputConfig(
                    "meme.jpg",
                    "loading-spinner.gif",
                    "loading-meme.jpg",
                    0.25,
                    1.0,
                    0.001
            );

            MemePanel panel = new MemePanel(inputMemePanelConfig);

            // you can replace this component in the future
            MainWindow window = new MainWindow("Main Window", 800, 600);

            window.addComponentToWindow(panel);

            window.showWindow();
        });
    }
}
