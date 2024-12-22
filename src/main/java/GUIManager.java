import java.awt.event.ActionEvent;
import javax.swing.*;

public class GUIManager {
    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;
    private EditorFrame editorFrame;
    private Sketch sketchFrame;
    private User user;

    public GUIManager() {
        loginFrame = new LoginFrame();
        registerFrame = new RegisterFrame();
        editorFrame = new EditorFrame();
        sketchFrame = new Sketch();
        user = new User();
        loginActions();
        RegisterActions();
        editorFrameActions();
    }
    // login Actions
    private void loginActions() {
        // Register button Action
        LoginFrame.RegisterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginFrame.dispose();
                // Create and show the register frame
                showRegisterFrame();
            }
        });

        // login Button Action
        loginFrame.loginButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = loginFrame.userNameTextField.getText();
                String password = new String(loginFrame.passwordFiled.getPassword());
                try {
                    String userPath = user.logIn(userName, password);
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    showEditorFrame();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

    }

    // Register Actoins
    private void RegisterActions() {
        registerFrame.registerButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = registerFrame.userNameTextField.getText();
                String password = new String(registerFrame.passwrodField.getPassword());
                String confirmedPassword = new String(registerFrame.confirmPasswordField.getPassword());

                try {
                    String userPath = user.register(userName, password, confirmedPassword);
                    JOptionPane.showMessageDialog(null, "Register Successful!");
                } catch (PasswordException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                } catch (WeakPasswordException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }

        });
        registerFrame.backToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerFrame.dispose();
                showLoginFrame();
            }
        });
    }

    // Editor Frame Actions
    private void editorFrameActions(){
        editorFrame.logOutButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorFrame.dispose();
                showLoginFrame();
            }
        });
        editorFrame.addSketchButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSketchFrame();
            }
        });
        editorFrame.addNoteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // to do
            }
        });
        editorFrame.addImageButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // to do
            }
        });

        editorFrame.addNoteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Username= User.userName ;
                // title * password
                String title = JOptionPane.showInputDialog("Enter Note Title: ");
                SecureNote note = new SecureNote(title);
                String password = note.setPassword(JOptionPane.showInputDialog("Enter Note Password"));
            }
        });

    }

    private void sketchFrameActions(){

    }

    public void showLoginFrame() {
        // Hide all frames first to avoid overlap
        editorFrame.setVisible(false);
        sketchFrame.setVisible(false);
        loginFrame.setVisible(true);
    }

    public void showRegisterFrame() {
        // Hide all frames first
        loginFrame.setVisible(false);
        editorFrame.setVisible(false);
        sketchFrame.setVisible(false);
        registerFrame.setVisible(true);
    }

    public void showEditorFrame() {
        // Hide all frames first
        loginFrame.setVisible(false);
        registerFrame.setVisible(false);
        sketchFrame.setVisible(false);
        editorFrame.setVisible(true);
    }

    public void showSketchFrame() {
        // Hide all frames first
        loginFrame.setVisible(false);
        registerFrame.setVisible(false);
        sketchFrame.setVisible(true);
    }
}

/*

 */