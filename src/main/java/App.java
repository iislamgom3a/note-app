import javax.swing.*;
import java.awt.*;

public class App{
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        MarkdownEditor editor = new MarkdownEditor();
        editor.setVisible(true);
        
    });
}   
}