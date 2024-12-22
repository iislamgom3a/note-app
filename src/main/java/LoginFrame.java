import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;


public class LoginFrame extends javax.swing.JFrame  {

    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jFrame1 = new javax.swing.JFrame();
        textField1 = new java.awt.TextField();
        logoPanel = new javax.swing.JPanel();
        programNameLabel1 = new java.awt.Label();
        loginPanel = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        passwordFiled = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        userNameTextField = new javax.swing.JTextField();
        usernameLabel = new java.awt.Label();
        logo3 = new java.awt.Label();
        Label welcomeLabel = new Label();
        showPasswordToggle = new javax.swing.JToggleButton();
        RegisterButton = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
                jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
                jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AnnotateIt");
        setAlwaysOnTop(false);
        setBackground(new java.awt.Color(204, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Calibri", 0, 18));
        setForeground(java.awt.Color.darkGray);
        setIconImages(null);
        setName("login Frame");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 500));
        setType(Type.NORMAL);

        // Logo Panel
        logoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        programNameLabel1.setAlignment(java.awt.Label.CENTER);
        programNameLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        programNameLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 48));
        programNameLabel1.setName("programName");
        programNameLabel1.setText("AnnotateIt");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
                logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(logoPanelLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(programNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(59, Short.MAX_VALUE))
        );
        logoPanelLayout.setVerticalGroup(
                logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(logoPanelLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(programNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(73, Short.MAX_VALUE))
        );

        // Login Panel
        loginPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        loginButton.setText("Log In");
        passwordFiled.setText("");

        userNameTextField.setColumns(20);
        userNameTextField.setText("");
        userNameTextField.setAutoscrolls(false);
        jScrollPane2.setViewportView(userNameTextField);

        usernameLabel.setAlignment(java.awt.Label.CENTER);
        usernameLabel.setName("logo");
        usernameLabel.setText("Username");

        logo3.setAlignment(java.awt.Label.CENTER);
        logo3.setName("logo");
        logo3.setText("Password");

        welcomeLabel.setAlignment(java.awt.Label.CENTER);
        welcomeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        welcomeLabel.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18));
        welcomeLabel.setName("programName");
        welcomeLabel.setText("welcome Back");

        showPasswordToggle.setText("show");
        showPasswordToggle.setBorderPainted(false);
        showPasswordToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordToggleActionPerformed(evt);
            }
        });

        RegisterButton.setFont(new java.awt.Font("Dialog", 1, 12));
        RegisterButton.setText("I don't have Account");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(logo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addComponent(passwordFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(showPasswordToggle))
                                        .addComponent(jScrollPane2))
                                .addGap(63, 63, 63))
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(169, 169, 169)
                                                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addGap(3, 3, 3))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(190, 190, 190)
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(24, 24, 24))
                                                        .addComponent(RegisterButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                                                .addGap(10, 10, 10)))
                                .addGap(192, 192, 192))
        );
        loginPanelLayout.setVerticalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordFiled)
                                        .addComponent(logo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(showPasswordToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84))
        );

        loginPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane2, passwordFiled, showPasswordToggle});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 6, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void showPasswordToggleActionPerformed(java.awt.event.ActionEvent evt) {
        if (showPasswordToggle.isSelected()) {
            passwordFiled.setEchoChar((char) 0);
            showPasswordToggle.setText("Hide");
        } else {
            passwordFiled.setEchoChar('*');
            showPasswordToggle.setText("Show");
        }
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.JButton  RegisterButton;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JButton loginButton;
    private javax.swing.JPanel loginPanel;
    private java.awt.Label logo3;
    private javax.swing.JPanel logoPanel;
    protected javax.swing.JPasswordField passwordFiled;
    private java.awt.Label programNameLabel1;
    private javax.swing.JToggleButton showPasswordToggle;
    private java.awt.TextField textField1;
    protected javax.swing.JTextField userNameTextField;
    private java.awt.Label usernameLabel;
    // End of variables declaration//GEN-END:variables
}