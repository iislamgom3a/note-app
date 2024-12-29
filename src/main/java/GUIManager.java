import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CancellationException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUIManager {
    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;
    private EditorFrame editorFrame;
    private Sketch sketchFrame;
    private User user;
    private String currntUserName;
    private String currentNoteTitle;
    private List<String> titles;
    private SecureNote note;
    private HashMap<String, List<String>> imagesPaths;



    public GUIManager() {
        loginFrame = new LoginFrame();
        registerFrame = new RegisterFrame();
        editorFrame = new EditorFrame();
        sketchFrame = new Sketch();
        user = new User();
        imagesPaths = new HashMap<>();
        loginActions();
        RegisterActions();
        editorFrameActions();
        sketchFrameActions();
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
                    editorFrame.currnetUserName = userName;
                    System.out.println(userName);
                    loadTitleList();
                    loadImagesHash();
                    if (user != null) {
                        showEditorFrame();
                        updateList();
                        updateImagePanel();
                        editorFrame.textArea.repaint();
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
                    User.register(userName, password, confirmedPassword);
                    currntUserName = userName;
                    System.out.println();
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
        editorFrame.logOutButton.addActionListener(e -> {
            editorFrame.dispose();
            showLoginFrame();
        });

        editorFrame.addNoteButton.addActionListener(e -> {
            try {
                String title = JOptionPane.showInputDialog("Enter Note Title: ");
                if (title == null || title.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                note = new SecureNote(title);
                String password = JOptionPane.showInputDialog("Enter Note Password:");
                if (password == null || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                note.createNote(currntUserName, password);
                loadTitleList();
                updateList();
                JOptionPane.showMessageDialog(null, "Note created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Add MouseListener for double-click functionality
        editorFrame.notesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click detected
                    int index = editorFrame.notesList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        String selectedTitle = editorFrame.notesList.getModel().getElementAt(index);
                        currentNoteTitle = selectedTitle;

                        // Prompt for password to unlock the note
                        String password = JOptionPane.showInputDialog("Enter password to unlock note:");
                        try {
                            note = new SecureNote(selectedTitle);
                            note.unlock(currntUserName, password); // Attempt to unlock
                            JOptionPane.showMessageDialog(null, "Note unlocked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            // Load and display content only after successful unlock
                            currentNoteTitle = selectedTitle;
                            editorFrame.currentNote = selectedTitle;
                            editorFrame.loadNoteContent(selectedTitle, currntUserName);
                            updateImagePanel();
                            if (!editorFrame.isRawMode) {
                                editorFrame.updatePreview();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        editorFrame.addSketchButton.addActionListener(e -> {
            showSketchFrame();
            try {
                saveImagesHash();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            updateImagePanel();
        });

        editorFrame.addImageButton.addActionListener(e-> {
            Images imageHandler = new Images();
            String imagePath = imageHandler.addImage(currntUserName);
            imagesPaths.computeIfAbsent(currentNoteTitle, k -> new ArrayList<>()).add(imagePath);
            System.out.println(imagePath+ "Added successfully to imagesPaths map");
            try {
                saveImagesHash();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            updateImagePanel();
            System.out.println("image copyed succesfully to "+imagePath);
        });

        loadTitleList();
        updateList();
    }

    private void sketchFrameActions(){
        sketchFrame.saveButton.addActionListener(e -> {
            try {
                String imagePath = sketchFrame.saveImage(sketchFrame.drawingPanel, currntUserName);
                imagesPaths.computeIfAbsent(currentNoteTitle, k -> new ArrayList<>()).add(imagePath);
                saveImagesHash();
                System.out.println("Sketch  saved successfully! to "+imagePath);
                sketchFrame.setVisible(false);
                sketchFrame.strokes.clear();
                sketchFrame.drawingPanel.repaint();
                updateImagePanel();
            } catch (Exception ex) {
                System.out.println("Failed to save the image");
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
        updateImagePanel();
        // Hide all frames first
        loginFrame.setVisible(false);
        registerFrame.setVisible(false);
        sketchFrame.setVisible(true);
    }

    private void showImageSelctionFrame(){
        Images image = new Images();
        String imagePath = image.addImage(currntUserName);
        updateImagePanel();

    }

    private void updateList() {
        updateNoteListContent(titles);
    }

    public void updateNoteListContent(List<String> titles) {
        DefaultListModel<String> model = (DefaultListModel<String>) editorFrame.notesList.getModel();
        model.clear(); // Clear existing items in the list

        if (titles == null || titles.isEmpty()) {
            model.addElement("No notes available.");
        } else {
            for (String title : titles) {
                if (title != null && !title.isEmpty()) {
                    model.addElement(title);
                    System.out.println(title);
                } else {
                    System.out.println("Note has no title.");
                }
            }
        }

        // Ensure the list is repainted with the updated model
        editorFrame.notesList.repaint();
    }

    private void updateImagePanel() {
        editorFrame.imagePanel.removeAll(); // Clear existing components
        List<String> paths = imagesPaths.get(currentNoteTitle);

        if (paths != null && !paths.isEmpty()) {
            try {
                for (String path : paths) {
                    // Load the image using ImageIO
                    BufferedImage image = ImageIO.read(new File(path));

                    if (image != null) { // Check if image loaded successfully
                        int panelWidth = editorFrame.imagePanel.getWidth();
                        int panelHeight = editorFrame.imagePanel.getHeight();

                        int scaledWidth = image.getWidth();
                        int scaledHeight = image.getHeight();

                        if (panelWidth > 0 && panelHeight > 0) { // check if panel has size
                            if (scaledWidth > panelWidth) {
                                scaledWidth = panelWidth;
                                scaledHeight = (int) (image.getHeight() * ((double) panelWidth / image.getWidth()));
                            }
                            if (scaledHeight > panelHeight) {
                                scaledHeight = panelHeight;
                                scaledWidth = (int) (image.getWidth() * ((double) panelHeight / image.getHeight()));
                            }
                        }

                        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        JLabel imageLabel = new JLabel(scaledIcon);
                        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        imageLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                JOptionPane.showMessageDialog(null, new ImageIcon(path), "Image Viewer", JOptionPane.PLAIN_MESSAGE);
                            }
                        });
                        editorFrame.imagePanel.add(Box.createVerticalStrut(10));
                        JScrollPane scrollPane = new JScrollPane(imageLabel);
                        editorFrame.imagePanel.add(scrollPane);
                    } else {
                        System.err.println("Could not load image: " + path);
                        JLabel errorLabel = new JLabel("Could not load image: " + new File(path).getName());
                        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        editorFrame.imagePanel.add(errorLabel);
                    }
                }
            } catch (IOException ex) {
                System.err.println("Error loading image: " + ex.getMessage());
            }
        } else {
            editorFrame.imagePanel.add(new JLabel("No images available."));
        }

        editorFrame.imagePanel.revalidate();
        editorFrame.imagePanel.repaint();
    }

    private String imagesHashpath = User.USERs_FOLDER_PATH + File.separator + currntUserName + File.separator + "imagesPaths.txt";

    private void loadImagesHash() throws IOException, ClassNotFoundException {
        String path = User.USERs_FOLDER_PATH + File.separator + currntUserName + File.separator + "imagesPaths.txt";
        ensureDirectoryExists(path);
        if (FileManager.createFileIfNotExists(path)) {
            imagesPaths = new HashMap<>();
        } else {
            imagesPaths = (HashMap<String, List<String>>) FileManager.loadFromFile(path);
        }
    }

    private void saveImagesHash() throws IOException {
        String path = User.USERs_FOLDER_PATH + File.separator + currntUserName + File.separator + "imagesPaths.txt";
        ensureDirectoryExists(path);
        FileManager.saveToFile(path, imagesPaths);
    }

    private void ensureDirectoryExists(String filePath) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + parentDir);
        }
    }

    private  void loadTitleList(){
            String userFolderPath = User.USERs_FOLDER_PATH + File.separator + currntUserName;
            File titlesFile = new File(userFolderPath, "TitlesAndPasswords.txt");
            HashMap<String, String> titlesAndPasswords = readTitlesAndPasswords(titlesFile.getPath());
            titles = new ArrayList<>(titlesAndPasswords.keySet());
        }

    private HashMap<String, String> readTitlesAndPasswords(String filePath) {
        HashMap<String, String> map = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            map = (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Titles and passwords file not found. A new one will be created.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading titles and passwords file: " + e.getMessage());
        }
        return map == null ? new HashMap<>() : map;
    }
}
