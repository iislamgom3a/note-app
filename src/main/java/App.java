import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;

public class App {
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatAtomOneDarkIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Launch your GUI here
      javax.swing.SwingUtilities.invokeLater(() -> {
            GUIManager guiManager = new GUIManager();
            // Show the login frame initially
            guiManager.showLoginFrame();
     });
    }
}
