import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class GUIManager {
    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;
    private EditorFrame editorFrame;
    private Sketch sketchFrame;
    private User user;
    String currntUserName;
    private String currentNoteTitle;


    public GUIManager() {
        loginFrame = new LoginFrame();
        registerFrame = new RegisterFrame();
        editorFrame = new EditorFrame(currentNoteTitle);
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
        loginFrame.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = loginFrame.userNameTextField.getText();
                String password = new String(loginFrame.passwordFiled.getPassword());
                try {
                    User.logIn(userName, password);
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    currntUserName = userName;
                    loadUserObject(User.USERs_FOLDER_PATH + File.separator + userName); // Load user data before proceeding
                    if (user != null) {
                        showEditorFrame();
                        System.out.println(user.toString());
                        updateList();
                    } else {
                        JOptionPane.showMessageDialog(null, "User data could not be loaded.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
    }

    // Register Actions
    private void RegisterActions() {
        registerFrame.registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = registerFrame.userNameTextField.getText();
                String password = new String(registerFrame.passwrodField.getPassword());
                String confirmedPassword = new String(registerFrame.confirmPasswordField.getPassword());

                try {
                    user.register(userName, password, confirmedPassword);
                    saveUserToFile(user);
                    JOptionPane.showMessageDialog(null, "Register Successful!");
                    registerFrame.dispose();
                    showLoginFrame();
                } catch (PasswordException | WeakPasswordException ex) {
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
    private void editorFrameActions() {
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
                updateList();
            }
        });

        editorFrame.addImageButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image handler = new Image();
                String saveDirectoryPath = User.USERs_FOLDER_PATH + currntUserName;
                String[] paths = handler.selectImage(saveDirectoryPath, currentNoteTitle);

                if (paths != null && paths.length == 2) {
                    String imagePath = paths[0];
                    String markdownPath = paths[1];
                    handler.addImage(imagePath, markdownPath);
                    updateList();
                }
            }
        });

        editorFrame.addNoteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = JOptionPane.showInputDialog("Enter Note Title: ");
                    if (title == null || title.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    SecureNote note = new SecureNote(title);
                    String password = JOptionPane.showInputDialog("Enter Note Password");
                    if (password == null || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    note.createNote(currntUserName, password);
                    user.notes.add(note);
                    updateList();
                    JOptionPane.showMessageDialog(null, "Note created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (WeakPasswordException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Weak Password", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error writing the note file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void sketchFrameActions() {
        editorFrame.notesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ensures the event fires only after the selection is finalized
                int selectedIndex = editorFrame.notesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedTitle = editorFrame.notesList.getModel().getElementAt(selectedIndex);
                    // Handle the selection
                    System.out.println("Selected Note: " + selectedTitle);
                    currentNoteTitle = selectedTitle;
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

    private void updateList() {
        updateNoteListContent(user.notes);
    }

    public void updateNoteListContent(java.util.List<Note> notes) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Check if the notes list is empty
        if (notes == null || notes.isEmpty()) {
            listModel.addElement("No notes available.");
        } else {
            // Add all note titles to the list model
            for (Note note : notes) {
                listModel.addElement(note.getTitle());
            }
            for (Note note : notes) {
                System.out.println("Note Title: " + note.getTitle());
            }
        }
        editorFrame.notesList.setModel(listModel);
        editorFrame.notesList.repaint(); // Ensure the JList updates visually
    }

    private void saveUserToFile(User user) {
        // Define the directory and file path for the user data
        String userDirectoryPath = User.USERs_FOLDER_PATH + File.separator + currntUserName;
        String userFilePath = userDirectoryPath + File.separator + "user.txt";

        try {
            // Ensure the parent directory exists
            File parentDir = new File(User.USERs_FOLDER_PATH);
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // Create parent directories if they don't exist
                System.out.println("Created parent directory: " + User.USERs_FOLDER_PATH);
            }

            // Create the user directory if it doesn't exist
            File userDir = new File(userDirectoryPath);
            if (!userDir.exists()) {
                userDir.mkdirs(); // Create the directory if it doesn't exist
                System.out.println("Created user directory: " + userDirectoryPath);
            }

            // Create the user file if it doesn't exist
            File userFile = new File(userFilePath);
            if (!userFile.exists()) {
                userFile.createNewFile(); // Create the file if it doesn't exist
                System.out.println("Created user file: " + userFilePath);
            }

            // Write the user object to the file using ObjectOutputStream
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
                oos.writeObject(user);
                System.out.println("User data saved to: " + userFilePath);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save user data: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    protected static User loadUserObject(String userPath) {
        String userFilePath = userPath + File.separator + "user.txt";
        File file = new File(userFilePath);

        try {
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                User user = (User) ois.readObject();
                ois.close();

                System.out.println("User data loaded successfully from: " + userFilePath);
                if (user != null) {
                    System.out.println("User loaded: " + user.toString());
                }
                return user;
            } else {
                System.out.println("User data file not found at: " + userFilePath);
                return null; // Or return a default User object if required
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading user data: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null; // Or return a default User object if required
        }
    }
}
