package musically;

import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AddSongsFrame extends javax.swing.JFrame {
    String song_name, artist, album, genre, path;
    public Connection cn;
    public Statement st;
     
    public AddSongsFrame() {
        initComponents();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/musically?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st=cn.createStatement();
        } catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database not connected.");
        }
        try {
            DefaultTableModel tm = (DefaultTableModel)TableSong.getModel();
            tm.setRowCount(0);
            String sql1 = "SELECT * FROM songs;";
            ResultSet rs = st.executeQuery(sql1);
            while(rs.next()){
                Object i[] = {rs.getInt("ID"),rs.getString("NAME"),rs.getString("NAME_ARTIST"),rs.getString("NAME_ALBUM"),rs.getString("GENRE"),rs.getString("LINK")};
                tm.addRow(i);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(AddSongsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        GenreLabel = new javax.swing.JLabel();
        PathLabel = new javax.swing.JLabel();
        SongTextField = new javax.swing.JTextField();
        ArtistTextField = new javax.swing.JTextField();
        AlbumTextField = new javax.swing.JTextField();
        GenreTextField = new javax.swing.JTextField();
        PathTextField = new javax.swing.JTextField();
        scrollPaneTable = new javax.swing.JScrollPane();
        TableSong = new javax.swing.JTable();
        AddSongsLabel = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        ChooseButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        NameLabel = new javax.swing.JLabel();
        ArtistLabel = new javax.swing.JLabel();
        AlbumLabel = new javax.swing.JLabel();
        buttonExit = new javax.swing.JButton();
        labelSongsSavedInDatabase = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(28, 28, 28));

        GenreLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        GenreLabel.setForeground(new java.awt.Color(255, 255, 255));
        GenreLabel.setText("Genre");

        PathLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        PathLabel.setForeground(new java.awt.Color(255, 255, 255));
        PathLabel.setText("Path");

        SongTextField.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        SongTextField.setText("Enter song name...");
        SongTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SongTextFieldMouseClicked(evt);
            }
        });

        ArtistTextField.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        ArtistTextField.setText("Enter artist name...");
        ArtistTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArtistTextFieldMouseClicked(evt);
            }
        });

        AlbumTextField.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        AlbumTextField.setText("Enter album name...");
        AlbumTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AlbumTextFieldMouseClicked(evt);
            }
        });

        GenreTextField.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        GenreTextField.setText("Enter genre name...");
        GenreTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenreTextFieldMouseClicked(evt);
            }
        });

        PathTextField.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        PathTextField.setText("Click \"Choose Songs\"...");

        TableSong.setBackground(new java.awt.Color(28, 28, 28));
        TableSong.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        TableSong.setForeground(new java.awt.Color(255, 255, 255));
        TableSong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Song ID", "Song Name", "Artist", "Album", "Genre", "Path"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneTable.setViewportView(TableSong);

        AddSongsLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        AddSongsLabel.setForeground(new java.awt.Color(255, 255, 255));
        AddSongsLabel.setText("Add your songs to Musically");

        SaveButton.setBackground(new java.awt.Color(28, 28, 28));
        SaveButton.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        SaveButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        ChooseButton.setBackground(new java.awt.Color(28, 28, 28));
        ChooseButton.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        ChooseButton.setForeground(new java.awt.Color(255, 255, 255));
        ChooseButton.setText("Choose Songs");
        ChooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseButtonActionPerformed(evt);
            }
        });

        BackButton.setBackground(new java.awt.Color(28, 28, 28));
        BackButton.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        BackButton.setForeground(new java.awt.Color(255, 255, 255));
        BackButton.setText("Go Back");
        BackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BackButtonMouseClicked(evt);
            }
        });

        NameLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        NameLabel.setForeground(new java.awt.Color(255, 255, 255));
        NameLabel.setText("Song Name");

        ArtistLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        ArtistLabel.setForeground(new java.awt.Color(255, 255, 255));
        ArtistLabel.setText("Artist");

        AlbumLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        AlbumLabel.setForeground(new java.awt.Color(255, 255, 255));
        AlbumLabel.setText("Album");

        buttonExit.setBackground(new java.awt.Color(28, 28, 28));
        buttonExit.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        buttonExit.setForeground(new java.awt.Color(255, 255, 255));
        buttonExit.setText("X");
        buttonExit.setBorder(null);
        buttonExit.setBorderPainted(false);
        buttonExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonExitMouseClicked(evt);
            }
        });

        labelSongsSavedInDatabase.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelSongsSavedInDatabase.setForeground(new java.awt.Color(255, 255, 255));
        labelSongsSavedInDatabase.setText("Songs already stored in the database:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AddSongsLabel)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(ArtistLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ArtistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(NameLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SongTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(PathLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(AlbumLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AlbumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(GenreLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(GenreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ChooseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(SaveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BackButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelSongsSavedInDatabase)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddSongsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SongTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ArtistLabel)
                            .addComponent(ArtistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AlbumLabel)
                            .addComponent(AlbumTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GenreLabel)
                            .addComponent(GenreTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PathLabel)
                            .addComponent(PathTextField))
                        .addGap(18, 18, 18)
                        .addComponent(labelSongsSavedInDatabase)
                        .addGap(207, 207, 207))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ChooseButton)
                        .addGap(18, 18, 18)
                        .addComponent(SaveButton)
                        .addGap(18, 18, 18)
                        .addComponent(BackButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(278, Short.MAX_VALUE)
                    .addComponent(scrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackButtonMouseClicked
        setVisible(false);
        this.dispose();
    }//GEN-LAST:event_BackButtonMouseClicked

    private void ChooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseButtonActionPerformed
        PathTextField.setText("");
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        PathTextField.setText(filename);
    }//GEN-LAST:event_ChooseButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        try {
            song_name = SongTextField.getText();
            artist = ArtistTextField.getText();
            album = AlbumTextField.getText();
            genre = GenreTextField.getText();
            path = PathTextField.getText();
            String sql1 = "INSERT IGNORE INTO songs (NAME, NAME_ARTIST, NAME_ALBUM, GENRE, LINK) values ('"+song_name+"','"+artist+"','"+album+"','"+genre+"','"+path+"')";
            st.executeUpdate(sql1);
            JOptionPane.showMessageDialog(null, "Song entered successfully.");
            setVisible(false);
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error");        
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void buttonExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExitMouseClicked
        setVisible(false);
        dispose();
    }//GEN-LAST:event_buttonExitMouseClicked

    private void SongTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SongTextFieldMouseClicked
        SongTextField.setText("");
    }//GEN-LAST:event_SongTextFieldMouseClicked

    private void ArtistTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArtistTextFieldMouseClicked
        ArtistTextField.setText("");
    }//GEN-LAST:event_ArtistTextFieldMouseClicked

    private void AlbumTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlbumTextFieldMouseClicked
        AlbumTextField.setText("");
    }//GEN-LAST:event_AlbumTextFieldMouseClicked

    private void GenreTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenreTextFieldMouseClicked
        GenreTextField.setText("");
    }//GEN-LAST:event_GenreTextFieldMouseClicked

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
            java.util.logging.Logger.getLogger(AddSongsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSongsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSongsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSongsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddSongsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddSongsLabel;
    private javax.swing.JLabel AlbumLabel;
    private javax.swing.JTextField AlbumTextField;
    private javax.swing.JLabel ArtistLabel;
    private javax.swing.JTextField ArtistTextField;
    private javax.swing.JButton BackButton;
    private javax.swing.JButton ChooseButton;
    private javax.swing.JLabel GenreLabel;
    private javax.swing.JTextField GenreTextField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel PathLabel;
    private javax.swing.JTextField PathTextField;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField SongTextField;
    private javax.swing.JTable TableSong;
    private javax.swing.JButton buttonExit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelSongsSavedInDatabase;
    private javax.swing.JScrollPane scrollPaneTable;
    // End of variables declaration//GEN-END:variables
}
