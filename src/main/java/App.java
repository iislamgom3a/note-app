
import javax.swing.*;
import java.awt.*;

public class App extends JFrame{
public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true); // Replace with your main frame
        });
    }
}