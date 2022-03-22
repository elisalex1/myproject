package musically;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;


public class LogIn_SignUp extends javax.swing.JFrame {
    public static String user_id_set = null;
    public static String user_firstname_set = null;
    public static String user_lastname_set = null;
    public Connection cn;
    public Statement st;
    
    public LogIn_SignUp() {
        initComponents();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/musically?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st=cn.createStatement();
        } catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database not connected.");
        }
        panelMain.removeAll();
        panelMain.repaint();
        panelMain.revalidate();
        panelMain.add(panelLogIn);
        panelMain.repaint();
        panelMain.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelLogIn = new javax.swing.JPanel();
        labelEmblemLogIn = new javax.swing.JLabel();
        labelMusicallyLogIn = new javax.swing.JLabel();
        labelWelcome = new javax.swing.JLabel();
        labelUsernameIconLogIn = new javax.swing.JLabel();
        textFieldUsernameLogIn = new javax.swing.JTextField();
        labelPasswordIconLogIn = new javax.swing.JLabel();
        passwordFieldLogIn = new javax.swing.JPasswordField();
        buttonLogIn = new javax.swing.JButton();
        labelForgotPassword = new javax.swing.JLabel();
        buttonCreateAccount = new javax.swing.JButton();
        labelAboutLogIn = new javax.swing.JLabel();
        labelPrivacyLogIn = new javax.swing.JLabel();
        labelContactLogIn = new javax.swing.JLabel();
        labelCopywriteLogIn = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        panelSignUp = new javax.swing.JPanel();
        labelEmblemSignUp = new javax.swing.JLabel();
        labelMusicallySignUp = new javax.swing.JLabel();
        labelCreate = new javax.swing.JLabel();
        labelFirstName = new javax.swing.JLabel();
        labelLastName = new javax.swing.JLabel();
        textFieldFirstName = new javax.swing.JTextField();
        textFieldLastName = new javax.swing.JTextField();
        labelUsernameSignUp = new javax.swing.JLabel();
        labelPasswordSignUp = new javax.swing.JLabel();
        labelRetype = new javax.swing.JLabel();
        textFieldUsernameSignUp = new javax.swing.JTextField();
        textFieldPasswordSignUp = new javax.swing.JTextField();
        textFieldRetype = new javax.swing.JTextField();
        buttonCreate = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        labelAboutSignUp = new javax.swing.JLabel();
        labelPrivacySignUp = new javax.swing.JLabel();
        labelContactSignUp = new javax.swing.JLabel();
        labelCorywriteSignUp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMain.setPreferredSize(new java.awt.Dimension(854, 480));

        panelLogIn.setBackground(new java.awt.Color(28, 28, 28));

        labelEmblemLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Musically.png"))); // NOI18N

        labelMusicallyLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelMusicallyLogIn.setForeground(new java.awt.Color(255, 255, 255));
        labelMusicallyLogIn.setText("Musically");

        labelWelcome.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelWelcome.setForeground(new java.awt.Color(255, 255, 255));
        labelWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWelcome.setText("Welcome to Musically");

        labelUsernameIconLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Username.png"))); // NOI18N

        textFieldUsernameLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        textFieldUsernameLogIn.setText("Username");
        textFieldUsernameLogIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldUsernameLogInMouseClicked(evt);
            }
        });

        labelPasswordIconLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Password.png"))); // NOI18N

        passwordFieldLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        passwordFieldLogIn.setText("Password");
        passwordFieldLogIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldLogInMouseClicked(evt);
            }
        });
        passwordFieldLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldLogInActionPerformed(evt);
            }
        });

        buttonLogIn.setBackground(new java.awt.Color(0, 0, 255));
        buttonLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        buttonLogIn.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogIn.setText("Log In");
        buttonLogIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLogInMouseClicked(evt);
            }
        });

        labelForgotPassword.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelForgotPassword.setForeground(new java.awt.Color(255, 255, 255));
        labelForgotPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelForgotPassword.setText("Forgotten password?");
        labelForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelForgotPasswordMouseClicked(evt);
            }
        });

        buttonCreateAccount.setBackground(new java.awt.Color(51, 255, 0));
        buttonCreateAccount.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        buttonCreateAccount.setText("Create New Account");
        buttonCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCreateAccountMouseClicked(evt);
            }
        });

        labelAboutLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelAboutLogIn.setForeground(new java.awt.Color(255, 255, 255));
        labelAboutLogIn.setText("About Musically");

        labelPrivacyLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelPrivacyLogIn.setForeground(new java.awt.Color(255, 255, 255));
        labelPrivacyLogIn.setText("Privacy Policy");

        labelContactLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelContactLogIn.setForeground(new java.awt.Color(255, 255, 255));
        labelContactLogIn.setText("Contact Us");

        labelCopywriteLogIn.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelCopywriteLogIn.setForeground(new java.awt.Color(255, 255, 255));
        labelCopywriteLogIn.setText("Musically© 2021-2022");

        buttonExit.setBackground(new java.awt.Color(28, 28, 28));
        buttonExit.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        buttonExit.setForeground(new java.awt.Color(255, 255, 255));
        buttonExit.setText("X");
        buttonExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLogInLayout = new javax.swing.GroupLayout(panelLogIn);
        panelLogIn.setLayout(panelLogInLayout);
        panelLogInLayout.setHorizontalGroup(
            panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogInLayout.createSequentialGroup()
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLogInLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelLogInLayout.createSequentialGroup()
                                .addComponent(labelEmblemLogIn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelMusicallyLogIn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonExit))))
                    .addGroup(panelLogInLayout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelPasswordIconLogIn)
                            .addComponent(labelUsernameIconLogIn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLogInLayout.createSequentialGroup()
                                .addComponent(labelForgotPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(buttonLogIn))
                            .addComponent(textFieldUsernameLogIn)
                            .addComponent(passwordFieldLogIn)
                            .addComponent(buttonCreateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(307, 307, 307))
                    .addGroup(panelLogInLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelAboutLogIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPrivacyLogIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelContactLogIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCopywriteLogIn)))
                .addContainerGap())
        );
        panelLogInLayout.setVerticalGroup(
            panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelEmblemLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMusicallyLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelWelcome)
                .addGap(18, 18, 18)
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textFieldUsernameLogIn)
                    .addComponent(labelUsernameIconLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordFieldLogIn)
                    .addComponent(labelPasswordIconLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelForgotPassword))
                .addGap(18, 18, 18)
                .addComponent(buttonCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(panelLogInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAboutLogIn)
                    .addComponent(labelPrivacyLogIn)
                    .addComponent(labelContactLogIn)
                    .addComponent(labelCopywriteLogIn))
                .addContainerGap())
        );

        panelSignUp.setBackground(new java.awt.Color(28, 28, 28));

        labelEmblemSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Musically.png"))); // NOI18N

        labelMusicallySignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelMusicallySignUp.setForeground(new java.awt.Color(255, 255, 255));
        labelMusicallySignUp.setText("Musically");

        labelCreate.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelCreate.setForeground(new java.awt.Color(255, 255, 255));
        labelCreate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCreate.setText("Create New Account");

        labelFirstName.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelFirstName.setForeground(new java.awt.Color(255, 255, 255));
        labelFirstName.setText("First Name:");

        labelLastName.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelLastName.setForeground(new java.awt.Color(255, 255, 255));
        labelLastName.setText("Last Name:");

        textFieldFirstName.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        textFieldFirstName.setText("Type here...");
        textFieldFirstName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldFirstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldFirstNameMouseClicked(evt);
            }
        });

        textFieldLastName.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        textFieldLastName.setText("Type here...");
        textFieldLastName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldLastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldLastNameMouseClicked(evt);
            }
        });

        labelUsernameSignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelUsernameSignUp.setForeground(new java.awt.Color(255, 255, 255));
        labelUsernameSignUp.setText("Username:");

        labelPasswordSignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelPasswordSignUp.setForeground(new java.awt.Color(255, 255, 255));
        labelPasswordSignUp.setText("Password:");

        labelRetype.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelRetype.setForeground(new java.awt.Color(255, 255, 255));
        labelRetype.setText("Re-type password:");

        textFieldUsernameSignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        textFieldUsernameSignUp.setText("Type here...");
        textFieldUsernameSignUp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldUsernameSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldUsernameSignUpMouseClicked(evt);
            }
        });

        textFieldPasswordSignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        textFieldPasswordSignUp.setText("Type here...");
        textFieldPasswordSignUp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldPasswordSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldPasswordSignUpMouseClicked(evt);
            }
        });

        textFieldRetype.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        textFieldRetype.setText("Type here...");
        textFieldRetype.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldRetype.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldRetypeMouseClicked(evt);
            }
        });

        buttonCreate.setBackground(new java.awt.Color(0, 255, 0));
        buttonCreate.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        buttonCreate.setText("Create Account");
        buttonCreate.setToolTipText("");
        buttonCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCreateMouseClicked(evt);
            }
        });

        buttonCancel.setBackground(new java.awt.Color(28, 28, 28));
        buttonCancel.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        buttonCancel.setForeground(new java.awt.Color(255, 255, 255));
        buttonCancel.setText("Cancel");
        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        labelAboutSignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelAboutSignUp.setForeground(new java.awt.Color(255, 255, 255));
        labelAboutSignUp.setText("About Musically");

        labelPrivacySignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelPrivacySignUp.setForeground(new java.awt.Color(255, 255, 255));
        labelPrivacySignUp.setText("Privacy Policy");

        labelContactSignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelContactSignUp.setForeground(new java.awt.Color(255, 255, 255));
        labelContactSignUp.setText("Contact Us");

        labelCorywriteSignUp.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelCorywriteSignUp.setForeground(new java.awt.Color(255, 255, 255));
        labelCorywriteSignUp.setText("Musically© 2021-2022");

        javax.swing.GroupLayout panelSignUpLayout = new javax.swing.GroupLayout(panelSignUp);
        panelSignUp.setLayout(panelSignUpLayout);
        panelSignUpLayout.setHorizontalGroup(
            panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSignUpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSignUpLayout.createSequentialGroup()
                        .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSignUpLayout.createSequentialGroup()
                                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelFirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(316, 316, 316)
                                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelUsernameSignUp, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelPasswordSignUp, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSignUpLayout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(labelRetype)))
                        .addGap(18, 18, 18)
                        .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldUsernameSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(textFieldPasswordSignUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(textFieldRetype, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addGroup(panelSignUpLayout.createSequentialGroup()
                        .addComponent(labelEmblemSignUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMusicallySignUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 606, Short.MAX_VALUE)
                        .addComponent(buttonCancel))
                    .addGroup(panelSignUpLayout.createSequentialGroup()
                        .addComponent(labelAboutSignUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPrivacySignUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelContactSignUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCorywriteSignUp)))
                .addContainerGap())
            .addGroup(panelSignUpLayout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addComponent(buttonCreate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSignUpLayout.setVerticalGroup(
            panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSignUpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelMusicallySignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEmblemSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCreate)
                .addGap(18, 18, 18)
                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsernameSignUp)
                    .addComponent(textFieldUsernameSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLastName)
                    .addComponent(textFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldPasswordSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPasswordSignUp))
                .addGap(18, 18, 18)
                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldRetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRetype))
                .addGap(54, 54, 54)
                .addComponent(buttonCreate)
                .addGap(150, 150, 150)
                .addGroup(panelSignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAboutSignUp)
                    .addComponent(labelPrivacySignUp)
                    .addComponent(labelContactSignUp)
                    .addComponent(labelCorywriteSignUp))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelLogIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelSignUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCreateAccountMouseClicked
        panelMain.removeAll();
        panelMain.repaint();
        panelMain.revalidate();
        panelMain.add(panelSignUp);
        panelMain.repaint();
        panelMain.revalidate();
    }//GEN-LAST:event_buttonCreateAccountMouseClicked

    private void buttonExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_buttonExitMouseClicked

    private void buttonLogInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogInMouseClicked
        String login_username = textFieldUsernameLogIn.getText();
        String login_password = new String(passwordFieldLogIn.getPassword());
        String sql1 = "SELECT * FROM users WHERE USERNAME ='"+login_username+"' AND PASSWORD ='"+login_password+"'";
        ResultSet rs;
        try {
            rs = st.executeQuery(sql1);
            if (rs.next()){
                user_id_set = rs.getString("USERS_ID");
                user_firstname_set = rs.getString("FIRSTNAME");
                user_lastname_set = rs.getString("LASTNAME");
                setVisible(false);
                dispose();
                Musically_main.main(null);
            } else {
                JOptionPane.showMessageDialog(null, "Login un-succesful.");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonLogInMouseClicked

    private void labelForgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelForgotPasswordMouseClicked
        String login_username = textFieldUsernameLogIn.getText();
        String sql3 = "SELECT PASSWORD FROM users WHERE USERNAME ='"+login_username+"'";
        ResultSet resultSet;
        try {
            resultSet = st.executeQuery(sql3);
            if (resultSet.next()){
                String password = resultSet.getString(1);
                JOptionPane.showMessageDialog(null, "The password is: '" +password+ "'");
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid Username.");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_labelForgotPasswordMouseClicked

    private void textFieldUsernameLogInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldUsernameLogInMouseClicked
        textFieldUsernameLogIn.setText("");
    }//GEN-LAST:event_textFieldUsernameLogInMouseClicked

    private void passwordFieldLogInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldLogInMouseClicked
        passwordFieldLogIn.setText("");
    }//GEN-LAST:event_passwordFieldLogInMouseClicked

    private void textFieldFirstNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldFirstNameMouseClicked
        textFieldFirstName.setText("");
    }//GEN-LAST:event_textFieldFirstNameMouseClicked

    private void textFieldLastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldLastNameMouseClicked
        textFieldLastName.setText("");
    }//GEN-LAST:event_textFieldLastNameMouseClicked

    private void textFieldUsernameSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldUsernameSignUpMouseClicked
        textFieldUsernameSignUp.setText("");
    }//GEN-LAST:event_textFieldUsernameSignUpMouseClicked

    private void textFieldPasswordSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldPasswordSignUpMouseClicked
        textFieldPasswordSignUp.setText("");
    }//GEN-LAST:event_textFieldPasswordSignUpMouseClicked

    private void textFieldRetypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldRetypeMouseClicked
        textFieldRetype.setText("");
    }//GEN-LAST:event_textFieldRetypeMouseClicked

    private void buttonCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCreateMouseClicked
        try{
            String firstname = textFieldFirstName.getText();
            String lastname = textFieldLastName.getText();
            String username = textFieldUsernameSignUp.getText();
            String password = textFieldPasswordSignUp.getText();
            String repassword = textFieldRetype.getText();
            String sql2 = "INSERT INTO users(FIRSTNAME, LASTNAME, USERNAME, PASSWORD) VALUES ('"+firstname+"','"+lastname+"','"+username+"','"+password+"');";
                if(checkUsername(username)){
                   JOptionPane.showMessageDialog(null, "This username is already taken, please enter a different one.");
                } else {
                    try {
                        if(verifyFields()==true){
                            if(password == null ? repassword == null : password.equals(repassword)){
                                if(st.executeUpdate(sql2)!=0){
                                    JOptionPane.showMessageDialog(null, "Your account has been created.");
                                    textFieldFirstName.setText("Type here...");
                                    textFieldLastName.setText("Type here...");
                                    textFieldUsernameSignUp.setText("Type here...");
                                    textFieldPasswordSignUp.setText("Type here...");
                                    textFieldRetype.setText("Type here...");
                                    panelMain.removeAll();
                                    panelMain.repaint();
                                    panelMain.revalidate();
                                    panelMain.add(panelLogIn);
                                    panelMain.repaint();
                                    panelMain.revalidate();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error creating your account.");
                                }
                            } else {
                                    JOptionPane.showMessageDialog(null, "The passwords don't match.");
                            }
                        }
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
            } catch (HeadlessException ex) {
                   java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_buttonCreateMouseClicked

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
        panelMain.removeAll();
        panelMain.repaint();
        panelMain.revalidate();
        panelMain.add(panelLogIn);
        panelMain.repaint();
        panelMain.revalidate();
        textFieldFirstName.setText("Type here...");
        textFieldLastName.setText("Type here...");
        textFieldUsernameSignUp.setText("Type here...");
        textFieldPasswordSignUp.setText("Type here...");
        textFieldRetype.setText("Type here...");
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void passwordFieldLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldLogInActionPerformed
        String login_username = textFieldUsernameLogIn.getText();
        String login_password = new String(passwordFieldLogIn.getPassword());
        String sql1 = "SELECT * FROM users WHERE USERNAME ='"+login_username+"' AND PASSWORD ='"+login_password+"'";
        ResultSet rs;
        try {
            rs = st.executeQuery(sql1);
            if (rs.next()){
                user_id_set = rs.getString("USERS_ID");
                user_firstname_set = rs.getString("FIRSTNAME");
                user_lastname_set = rs.getString("LASTNAME");
                setVisible(false);
                dispose();
                Musically_main.main(null);
            } else {
                JOptionPane.showMessageDialog(null, "Login un-succesful.");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_passwordFieldLogInActionPerformed

    public boolean checkUsername(String user) {
        ResultSet rs;
        String username = textFieldFirstName.getText();
        boolean userexist = false;
        try {
            String query = "SELECT * FROM users WHERE USERNAME = '"+username+"'";
            rs = st.executeQuery(query);
            if(rs.next()){
                userexist = true;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return userexist; 
    }
    
    public boolean verifyFields() {
        String firstname = textFieldFirstName.getText();
        String lastname = textFieldLastName.getText();
        String username = textFieldUsernameSignUp.getText();
        String password = textFieldPasswordSignUp.getText();
        String repassword = textFieldRetype.getText();
        if(firstname.trim().equals("")  || firstname.trim().equals("Type here...")){
            JOptionPane.showMessageDialog(null, "Please enter your First Name.");
            return false;
        } else if(lastname.trim().equals("") || lastname.trim().equals("Type here...")){
            JOptionPane.showMessageDialog(null, "Please enter your Last Name.");
            return false;
        } else if(username.trim().equals("")  || username.trim().equals("Type here...")){
            JOptionPane.showMessageDialog(null, "Please enter a Username.");
            return false;
        } else if(password.trim().equals("")  || password.trim().equals("Type here...")){
            JOptionPane.showMessageDialog(null, "Please enter a Password.");
            return false;
        } else if(repassword.trim().equals("")  || repassword.trim().equals("Type here...")){
            JOptionPane.showMessageDialog(null, "Please enter re-type your Password.");
            return false;
        } 
         else {
            return true;
        }
    }
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn_SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn_SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JButton buttonCreateAccount;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonLogIn;
    private javax.swing.JLabel labelAboutLogIn;
    private javax.swing.JLabel labelAboutSignUp;
    private javax.swing.JLabel labelContactLogIn;
    private javax.swing.JLabel labelContactSignUp;
    private javax.swing.JLabel labelCopywriteLogIn;
    private javax.swing.JLabel labelCorywriteSignUp;
    private javax.swing.JLabel labelCreate;
    private javax.swing.JLabel labelEmblemLogIn;
    private javax.swing.JLabel labelEmblemSignUp;
    private javax.swing.JLabel labelFirstName;
    private javax.swing.JLabel labelForgotPassword;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelMusicallyLogIn;
    private javax.swing.JLabel labelMusicallySignUp;
    private javax.swing.JLabel labelPasswordIconLogIn;
    private javax.swing.JLabel labelPasswordSignUp;
    private javax.swing.JLabel labelPrivacyLogIn;
    private javax.swing.JLabel labelPrivacySignUp;
    private javax.swing.JLabel labelRetype;
    private javax.swing.JLabel labelUsernameIconLogIn;
    private javax.swing.JLabel labelUsernameSignUp;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JPanel panelLogIn;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelSignUp;
    private javax.swing.JPasswordField passwordFieldLogIn;
    private javax.swing.JTextField textFieldFirstName;
    private javax.swing.JTextField textFieldLastName;
    private javax.swing.JTextField textFieldPasswordSignUp;
    private javax.swing.JTextField textFieldRetype;
    private javax.swing.JTextField textFieldUsernameLogIn;
    private javax.swing.JTextField textFieldUsernameSignUp;
    // End of variables declaration//GEN-END:variables
}
