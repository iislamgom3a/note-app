import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GUIManager {
    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;
    private EditorFrame editorFrame;
    private Sketch sketchFrame;
    private User user;
    private String currentNoteTitle;


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
                Image handler = new Image();
                String saveDirectoryPath = User.USER_FOLDER_PATH+User.userName;
                String[] paths = handler.selectImage(saveDirectoryPath,currentNoteTitle);

                if (paths != null && paths.length == 2) {
                    String imagePath = paths[0];
                    String markdownPath = paths[1];
                    handler.addImage(imagePath, markdownPath);
                }
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
                updateNoteListContent();
            }
        });

    }

    private void sketchFrameActions(){

        editorFrame.notesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ensures the event fires only after the selection is finalized
                int selectedIndex = editorFrame.notesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedTitle = editorFrame.notesList.getModel().getElementAt(selectedIndex);
                    // Handle the selection
                    System.out.println("Selected Note: " + selectedTitle);
                    currentNoteTitle = selectedTitle;
                    // to do
                }
            }
        });

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
    private void updateNoteListContent() {
        // Create a DefaultListModel to hold the note titles
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Populate the list model with note titles
        for (Note note : user.notes) { // Assuming `user.notes` is a list or similar collection
            listModel.addElement(note.getTitle()); // No casting needed, assuming getTitle() returns a String
        }

        // Update the JList model
        editorFrame.notesList.setModel(listModel);

        // Ensure the scroll pane correctly displays the updated list
        editorFrame.notesListPane.setViewportView(editorFrame.notesList);
    }

}





/*

 */