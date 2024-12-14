import javax.swing.*;
import java.awt.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        MarkdownEditor editor = new MarkdownEditor();
        editor.setVisible(true);
    });
}   