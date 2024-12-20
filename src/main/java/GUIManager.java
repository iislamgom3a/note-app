import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUIManager extends JFrame{

    private static void createLoginFrame  () {
        User user = new User();
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
                    user.logIn(userName, password);
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                    openFolderInFrame(userName); // Open the folder in a new frame
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
        User user = new User();
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
                String folderPath = null;
                try {
                    folderPath = user.register(userName, password);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                if (folderPath != null) {
                    JOptionPane.showMessageDialog(registerFrame, "Registered Successfully! Folder: " + folderPath);
                    openFolderInFrame(userName); // Open the folder in a new frame
                    registerFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(registerFrame, "Username already exists!");
                }
            }
        });
    }

    // Backend methods (same as provided)

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