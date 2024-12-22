import java.awt.*;

public class RegisterFrame extends javax.swing.JFrame {

    public RegisterFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        logoPanel = new javax.swing.JPanel();
        programNameLabel = new java.awt.Label();
        loginPanel = new javax.swing.JPanel();
        registerButton = new javax.swing.JButton();
        confirmPasswordField = new javax.swing.JPasswordField();
        logo = new java.awt.Label();
        passwordLabel = new java.awt.Label();
        registerWelcomeLablel = new java.awt.Label();
        passwrodField = new javax.swing.JPasswordField();
        confirmPasswordLabel = new java.awt.Label();
        userNameTextField = new javax.swing.JTextField();
        backToLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Register Frame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(927, 376));
        setType(java.awt.Window.Type.POPUP);

        logoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        programNameLabel.setAlignment(java.awt.Label.CENTER);
        programNameLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        programNameLabel.setFont(new java.awt.Font("Perpetua Titling MT", 1, 48)); // NOI18N
        programNameLabel.setName("programName"); // NOI18N
        programNameLabel.setText("AnnotateIt");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(programNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(programNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        loginPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        registerButton.setText("Register");
        confirmPasswordField.setText("Password");

        logo.setAlignment(java.awt.Label.CENTER);
        logo.setName("logo"); // NOI18N
        logo.setAlignment(Label.CENTER);
        logo.setText("Username");

        passwordLabel.setAlignment(java.awt.Label.CENTER);
        passwordLabel.setName("logo"); // NOI18N
        passwordLabel.setText("Password");

        registerWelcomeLablel.setAlignment(java.awt.Label.CENTER);
        registerWelcomeLablel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registerWelcomeLablel.setFont(new java.awt.Font("Perpetua Titling MT", 1, 18)); // NOI18N
        registerWelcomeLablel.setName("programName"); // NOI18N
        registerWelcomeLablel.setText("welcome To AnnotateIt");

        passwrodField.setText("Password");

        confirmPasswordLabel.setAlignment(java.awt.Label.CENTER);
        confirmPasswordLabel.setName("logo"); // NOI18N
        confirmPasswordLabel.setText("Confirm Password");

        backToLogin.setText("I already have account");
        

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwrodField, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(confirmPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addComponent(registerWelcomeLablel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(backToLogin)
                .addGap(156, 156, 156))
        );

        loginPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {confirmPasswordField, passwrodField});

        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(registerWelcomeLablel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwrodField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backToLogin)
                .addContainerGap())
        );

        loginPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {confirmPasswordField, passwrodField});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loginPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton backToLogin;
    protected javax.swing.JPasswordField confirmPasswordField;
    private java.awt.Label confirmPasswordLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel logoPanel;
    private java.awt.Label passwordLabel;
    protected javax.swing.JPasswordField passwrodField;
    private java.awt.Label programNameLabel;
    protected javax.swing.JButton registerButton;
    private java.awt.Label registerWelcomeLablel;
    protected javax.swing.JTextField userNameTextField;
    private java.awt.Label logo;
    // End of variables declaration//GEN-END:variables
}
