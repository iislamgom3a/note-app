import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class GUIManager {
    private static final String FILE_NAME = "DataBase.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIManager::createLoginFrame);
    }

    private static void createLoginFrame() {
        JFrame loginFrame = new JFrame("Log In");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(null);

        // Components for login
        JLabel labelLogin = new JLabel("Log In");
        labelLogin.setBounds(170, 20, 80, 20);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 60, 80, 20);
        JTextField userNameField = new JTextField();
        userNameField.setBounds(150, 60, 150, 20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 20);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 20);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(100, 150, 80, 30);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 150, 100, 30);

        // Add components to login frame
        loginFrame.add(labelLogin);
        loginFrame.add(userLabel);
        loginFrame.add(userNameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);
        loginFrame.add(registerButton);

        loginFrame.setVisible(true);

        // Event listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    if (logIn(userName, password)) {
                        JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                        openFolderInFrame(userName); // Open the folder in a new frame
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(loginFrame, "Error: " + ex.getMessage());
                }
            }
        });

        // Event listener for register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose(); // Close login frame
                openRegistrationFrame(); // Open registration frame
            }
        });
    }

    private static void openRegistrationFrame() {
        JFrame registerFrame = new JFrame("Register");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(400, 250);
        registerFrame.setLayout(null);

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(170, 20, 80, 20);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 60, 80, 20);
        JTextField userNameField = new JTextField();
        userNameField.setBounds(150, 60, 150, 20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 20);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 20);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 150, 100, 30);

        registerFrame.add(registerLabel);
        registerFrame.add(userLabel);
        registerFrame.add(userNameField);
        registerFrame.add(passwordLabel);
        registerFrame.add(passwordField);
        registerFrame.add(registerButton);

        registerFrame.setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                String password = new String(passwordField.getPassword());
                String folderPath = register(userName, password);

                if (folderPath != null) {
                    JOptionPane.showMessageDialog(registerFrame, "Registered Successfully! Folder: " + folderPath);
                    openFolderInFrame(userName); // Open the folder in a new frame
                    registerFrame.dispose();
                    createLoginFrame(); // Return to log in frame
                } else {
                    JOptionPane.showMessageDialog(registerFrame, "Username already exists!");
                }
            }
        });
    }

    // Backend methods (same as provided)
    public static String register(String userName, String password) {
        HashMap<String, String> map1 = readHashMapFromFile(FILE_NAME);
        String folderPath = "Users\\" + userName;
        if (!map1.containsKey(userName)) {
            map1.put(userName, password);
            writeHashMapToFile(map1, FILE_NAME);
            File folder = new File(folderPath);
            if (folder.mkdir()) {
                return folder.getAbsolutePath();
            }
        }
        System.out.println("Username already exists");
        return null;
    }

    private static void writeHashMapToFile(HashMap<String, String> map, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (HashMap.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + "-->" + entry.getValue());
                writer.newLine();
            }
            System.out.println("HashMap has been written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing HashMap to file: " + e.getMessage());
        }
    }

    private static HashMap<String, String> readHashMapFromFile(String filePath) {
        HashMap<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-->", 2);
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading HashMap from file: " + e.getMessage());
        }
        return map;
    }

    public static boolean logIn(String userName, String password) throws Exception {
        HashMap<String, String> map = readHashMapFromFile(FILE_NAME);
        if (map.containsKey(userName)) {
            if (map.get(userName).equals(password)) {
                return true;
            } else {
                throw new Exception("Wrong password");
            }
        }
        throw new Exception("Username doesn't exist");
    }

    // Helper method to display folder contents in a JFrame
    private static void openFolderInFrame(String userName) {
        String folderPath = "Users\\" + userName;
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            JFrame folderFrame = new JFrame("Folder: " + userName);
            folderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            folderFrame.setSize(500, 400);

            // Get list of files in the folder
            String[] files = folder.list();
            if (files == null) files = new String[]{};

            JList<String> fileList = new JList<>(files);
            JScrollPane scrollPane = new JScrollPane(fileList);

            folderFrame.add(scrollPane);
            folderFrame.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Folder does not exist: " + folderPath);
        }
    }
}



    /*
     - login panel or register if the user is not found

     --- if the user exists: open a panel which the notes at the left and the edit panel at the right
     - when clicking a note it will ask for a password
     - passed: the notes will be shown at the right, and it can be changed from the editing mode to view and vice versa
     - when a note closed it will be locked after a time
     - a button to add new note and asking for its new password

     --- user isn't exits : new user
     - same but there is no old notes there

    */