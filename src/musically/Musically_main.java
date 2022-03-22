package musically;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

public final class Musically_main extends javax.swing.JFrame {
    private String[] artist = new String[100];
    private String[] line = new String[100];
    private String[] play = new String[100];
    private String[] small = new String[100];
    private String[] small_artist = new String[100];
    private String[] favoritesongs = new String[100];
    private String[] results = new String[100];
    private String[] results_links = new String[100];
    private String[] results_small_links = new String[100];
    private String[] playback_path = new String[100];
    private int[] results_num = new int[100];
    private int[] song = new int[100];
    private int[] fav_artist = new int[100];
    String link1, link2, link3, user_id, user_firstname, user_lastname;
    int i = 0, j = 0, x = 0, rep = 0, pau = 0, y = 0, count = 0, playback_counter = 0, first_previous = 0, local_playback_counter = 0;
    public Connection cn;
    public Statement st;
    long clipTimePosition;
    AudioInputStream audioInput;
    Clip clip;
    
    public Musically_main() throws SQLException {
        initComponents();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/musically?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            st=cn.createStatement();
        } catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Database not connected.");
        }
        setUserID();
        setUserName();
        String name = null, link = null, small_link = null;
        String sql1 = "DELETE FROM charts;";
        st.executeUpdate(sql1);
        String url0 = "https://kworb.net/youtube/trending_music.html";      //Link for YT trending songs
        String url1 = "https://kworb.net/youtube/archive.html";
        Document doc0, doc1;
        try {
            doc0 = Jsoup.connect(url0).get();
            doc1 = Jsoup.connect(url1).get();
            Elements links = doc0.select("[href]");
            Elements links2 = doc1.select("[href]");
            String trend0 = links.toString();
            String artists = links2.toString();
            try (PrintWriter out = new PrintWriter("trends.txt")) {
                out.println(trend0);
                out.close();
            }
            try (PrintWriter out = new PrintWriter("artists.txt")) {
                out.println(artists);
                out.close();
            }
            for(int a=1; a<=18; a++){
                line[a+23] = Files.readAllLines(Paths.get("trends.txt")).get(a+23).replace("</a>","").replaceAll("\\<.*\\>","");
                song[a] = 0;
                results_num[a] = 0;
            }
            for(int a=1; a<=18; a++){
                play[a] = Files.readAllLines(Paths.get("trends.txt")).get(a+23).replace("<a href=\"/","https://www.").replace("/trending",".com").replace(".html","").replaceAll("\".*\\>","");
            }
            for(int a=1; a<=50; a++){
                small[a] = Files.readAllLines(Paths.get("trends.txt")).get(a+23).replace("<a href=\"/youtube/trending/video/","").replaceAll("\\..*\\>","");
            }
            for(int a=1; a<=36; a++){
                artist[a] = Files.readAllLines(Paths.get("artists.txt")).get(a+19).replace("</a>","").replaceAll("\\<.*\\>","");
                fav_artist[a] = 0;
            }
            labelSong1Home.setText("1. "+line[24]);
            labelSong2Home.setText("2. "+line[25]);
            labelSong3Home.setText("3. "+line[26]);
            labelArtist1Home.setText("1. "+artist[1]);
            labelArtist2Home.setText("2. "+artist[2]);
            labelArtist3Home.setText("3. "+artist[3]);
            labelSong1Charts.setText("1. "+line[24]);
            labelSong2Charts.setText("2. "+line[25]);
            labelSong3Charts.setText("3. "+line[26]);
            labelSong4Charts.setText("4. "+line[27]);
            labelSong5Charts.setText("5. "+line[28]);
            labelSong6Charts.setText("6. "+line[29]);
            labelSong7Charts.setText("7. "+line[30]);
            labelSong8Charts.setText("8. "+line[31]);
            labelSong9Charts.setText("9. "+line[32]);
            labelSong10Charts.setText("10. "+line[33]);
            labelSong11Charts.setText("11. "+line[34]);
            labelSong12Charts.setText("12. "+line[35]);
            labelSong13Charts.setText("13. "+line[36]);
            labelSong14Charts.setText("14. "+line[37]);
            labelSong15Charts.setText("15. "+line[38]);
            labelSong16Charts.setText("16. "+line[39]);
            labelSong17Charts.setText("17. "+line[40]);
            labelSong18Charts.setText("18. "+line[41]);
            labelArtist1TopArtists.setText("1. "+artist[1]);
            labelArtist2TopArtists.setText("2. "+artist[2]);
            labelArtist3TopArtists.setText("3. "+artist[3]);
            labelArtist4TopArtists.setText("4. "+artist[4]);
            labelArtist5TopArtists.setText("5. "+artist[5]);
            labelArtist6TopArtists.setText("6. "+artist[6]);
            labelArtist7TopArtists.setText("7. "+artist[7]);
            labelArtist8TopArtists.setText("8. "+artist[8]);
            labelArtist9TopArtists.setText("9. "+artist[9]);
            labelArtist10TopArtists.setText("10. "+artist[10]);
            labelArtist11TopArtists.setText("11. "+artist[11]);
            labelArtist12TopArtists.setText("12. "+artist[12]);
            labelArtist13TopArtists.setText("13. "+artist[13]);
            labelArtist14TopArtists.setText("14. "+artist[14]);
            labelArtist15TopArtists.setText("15. "+artist[15]);
            labelArtist16TopArtists.setText("16. "+artist[16]);
            labelArtist17TopArtists.setText("17. "+artist[17]);
            labelArtist18TopArtists.setText("18. "+artist[18]);
            labelArtist19TopArtists.setText("19. "+artist[19]);
            labelArtist20TopArtists.setText("20. "+artist[20]);
            labelArtist21TopArtists.setText("21. "+artist[21]);
            labelArtist22TopArtists.setText("22. "+artist[22]);
            labelArtist23TopArtists.setText("23. "+artist[23]);
            labelArtist24TopArtists.setText("24. "+artist[24]);
            labelArtist25TopArtists.setText("25. "+artist[25]);
            labelArtist26TopArtists.setText("26. "+artist[26]);
            labelArtist27TopArtists.setText("27. "+artist[27]);
            labelArtist28TopArtists.setText("28. "+artist[28]);
            labelArtist29TopArtists.setText("29. "+artist[29]);
            labelArtist30TopArtists.setText("30. "+artist[30]);
            labelArtist31TopArtists.setText("31. "+artist[31]);
            labelArtist32TopArtists.setText("32. "+artist[32]);
            labelArtist33TopArtists.setText("33. "+artist[33]);
            labelArtist34TopArtists.setText("34. "+artist[34]);
            labelArtist35TopArtists.setText("35. "+artist[35]);
            labelArtist36TopArtists.setText("36. "+artist[36]);
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        LocalTime time1 = LocalTime.now();              //Moods and messages depending on the time
        LocalTime time2 = LocalTime.parse("00:00:00");
        LocalTime time3 = LocalTime.parse("06:00:00");
        LocalTime time4 = LocalTime.parse("12:00:00");
        LocalTime time5 = LocalTime.parse("18:00:00");
        int diff = time1.compareTo(time2);
        if(diff > 0) {
            diff = time1.compareTo(time3);
            if(diff > 0) {
                diff = time1.compareTo(time4);
                if(diff > 0) {
                    diff = time1.compareTo(time5);
                    if(diff > 0) {
                        labelWelcome.setText("Good evening!");
                    } else {
                        labelWelcome.setText("Time for some relaxing music...");
                    }
                } else {
                    labelWelcome.setText("Good morning!");
                }
            } else {
                labelWelcome.setText("Good night!");
            }
        }
        try { //Inserting songs to Charts
            for (int i=24;i<124; i++){
                name = Files.readAllLines(Paths.get("trends.txt")).get(i).replace("</a>","").replaceAll("\\<.*\\>","").replace("\'","");
                link = Files.readAllLines(Paths.get("trends.txt")).get(i).replace("<a href=\"/","https://www.").replace("/trending",".com").replace(".html","").replaceAll("\".*\\>","");
                small_link = Files.readAllLines(Paths.get("trends.txt")).get(i).replace("<a href=\"/youtube/trending/video/","").replaceAll("\\..*\\>","");
                String sql2 = "INSERT INTO charts(NAME,LINK,SMALL_LINK) VALUES ('"+name+"','"+link+"','"+small_link+"');";
                String sql3 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+name+"','"+small_link+"');";
                st.executeUpdate(sql2);
                st.executeUpdate(sql3);
            }
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClickListener cl = new ClickListener();
        labelHome.addActionListener((ActionListener) cl);
        labelHome.doClick();
        labelArtistName.setEnabled(false);
        labelSongName.setEnabled(false);
        labelPlay.setEnabled(false);
        labelNext.setEnabled(false);
        labelPrevious.setEnabled(false);
        labelShuffle.setEnabled(false);
        labelRepeat.setEnabled(false);
        disableSearchLabels();
        updateTables();
        checkLikes();
    }
    
    private class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelMainDynamic.removeAll();
            panelMainDynamic.repaint();
            panelMainDynamic.revalidate();
            panelMainDynamic.add(panelHome);
            panelMainDynamic.repaint();
            panelMainDynamic.revalidate();
        }
    }
    
    public void setUserID() {
        user_id = musically.LogIn_SignUp.user_id_set;
    }
    
    public void setUserName() {
        user_firstname = musically.LogIn_SignUp.user_firstname_set;
        user_lastname = musically.LogIn_SignUp.user_lastname_set;
        labelCurrentFirstName.setText(user_firstname);
        labelCurentLatName.setText(user_lastname);
    }
    
    private void disableSearchLabels(){
        labelSongSearchResult1.setVisible(false);
        labelSongSearchResult2.setVisible(false);
        labelSongSearchResult3.setVisible(false);
        labelSongSearchResult4.setVisible(false);
        labelSongSearchResult5.setVisible(false);
        labelSongSearchResult6.setVisible(false);
        labelSongSearchResult7.setVisible(false);
        labelSongSearchResult8.setVisible(false);
        labelSongSearchResult9.setVisible(false);
        labelSongSearchResult10.setVisible(false);
        labelSongSearchResult11.setVisible(false);
        labelSongSearchResult12.setVisible(false);
        labelSongSearchResult13.setVisible(false);
        labelSongSearchResult14.setVisible(false);
        labelSongSearchResult15.setVisible(false);
        labelSongSearchResult16.setVisible(false);
        labelSongSearchResult17.setVisible(false);
        labelSearchSongViewCount1.setVisible(false);
        labelSearchSongViewCount2.setVisible(false);
        labelSearchSongViewCount3.setVisible(false);
        labelSearchSongViewCount4.setVisible(false);
        labelSearchSongViewCount5.setVisible(false);
        labelSearchSongViewCount6.setVisible(false);
        labelSearchSongViewCount7.setVisible(false);
        labelSearchSongViewCount8.setVisible(false);
        labelSearchSongViewCount9.setVisible(false);
        labelSearchSongViewCount10.setVisible(false);
        labelSearchSongViewCount11.setVisible(false);
        labelSearchSongViewCount12.setVisible(false);
        labelSearchSongViewCount13.setVisible(false);
        labelSearchSongViewCount14.setVisible(false);
        labelSearchSongViewCount15.setVisible(false);
        labelSearchSongViewCount16.setVisible(false);
        labelSearchSongViewCount17.setVisible(false);
        labelSearchSongsDate1.setVisible(false);
        labelSearchSongsDate2.setVisible(false);
        labelSearchSongsDate3.setVisible(false);
        labelSearchSongsDate4.setVisible(false);
        labelSearchSongsDate5.setVisible(false);
        labelSearchSongsDate6.setVisible(false);
        labelSearchSongsDate7.setVisible(false);
        labelSearchSongsDate8.setVisible(false);
        labelSearchSongsDate9.setVisible(false);
        labelSearchSongsDate10.setVisible(false);
        labelSearchSongsDate11.setVisible(false);
        labelSearchSongsDate12.setVisible(false);
        labelSearchSongsDate13.setVisible(false);
        labelSearchSongsDate14.setVisible(false);
        labelSearchSongsDate15.setVisible(false);
        labelSearchSongsDate16.setVisible(false);
        labelSearchSongsDate17.setVisible(false);
        labelHeart1SearchSongs.setVisible(false);
        labelHeart2SearchSongs.setVisible(false);
        labelHeart3SearchSongs.setVisible(false);
        labelHeart4SearchSongs.setVisible(false);
        labelHeart5SearchSongs.setVisible(false);
        labelHeart6SearchSongs.setVisible(false);
        labelHeart7SearchSongs.setVisible(false);
        labelHeart8SearchSongs.setVisible(false);
        labelHeart9SearchSongs.setVisible(false);
        labelHeart10SearchSongs.setVisible(false);
        labelHeart11SearchSongs.setVisible(false);
        labelHeart12SearchSongs.setVisible(false);
        labelHeart13SearchSongs.setVisible(false);
        labelHeart14SearchSongs.setVisible(false);
        labelHeart15SearchSongs.setVisible(false);
        labelHeart16SearchSongs.setVisible(false);
        labelHeart17SearchSongs.setVisible(false);
    }
    
    private void enableSearchLabels(){
        labelSongSearchResult1.setVisible(true);
        labelSongSearchResult2.setVisible(true);
        labelSongSearchResult3.setVisible(true);
        labelSongSearchResult4.setVisible(true);
        labelSongSearchResult5.setVisible(true);
        labelSongSearchResult6.setVisible(true);
        labelSongSearchResult7.setVisible(true);
        labelSongSearchResult8.setVisible(true);
        labelSongSearchResult9.setVisible(true);
        labelSongSearchResult10.setVisible(true);
        labelSongSearchResult11.setVisible(true);
        labelSongSearchResult12.setVisible(true);
        labelSongSearchResult13.setVisible(true);
        labelSongSearchResult14.setVisible(true);
        labelSongSearchResult15.setVisible(true);
        labelSongSearchResult16.setVisible(true);
        labelSongSearchResult17.setVisible(true);
        labelSearchSongViewCount1.setVisible(true);
        labelSearchSongViewCount2.setVisible(true);
        labelSearchSongViewCount3.setVisible(true);
        labelSearchSongViewCount4.setVisible(true);
        labelSearchSongViewCount5.setVisible(true);
        labelSearchSongViewCount6.setVisible(true);
        labelSearchSongViewCount7.setVisible(true);
        labelSearchSongViewCount8.setVisible(true);
        labelSearchSongViewCount9.setVisible(true);
        labelSearchSongViewCount10.setVisible(true);
        labelSearchSongViewCount11.setVisible(true);
        labelSearchSongViewCount12.setVisible(true);
        labelSearchSongViewCount13.setVisible(true);
        labelSearchSongViewCount14.setVisible(true);
        labelSearchSongViewCount15.setVisible(true);
        labelSearchSongViewCount16.setVisible(true);
        labelSearchSongViewCount17.setVisible(true);
        labelSearchSongsDate1.setVisible(true);
        labelSearchSongsDate2.setVisible(true);
        labelSearchSongsDate3.setVisible(true);
        labelSearchSongsDate4.setVisible(true);
        labelSearchSongsDate5.setVisible(true);
        labelSearchSongsDate6.setVisible(true);
        labelSearchSongsDate7.setVisible(true);
        labelSearchSongsDate8.setVisible(true);
        labelSearchSongsDate9.setVisible(true);
        labelSearchSongsDate10.setVisible(true);
        labelSearchSongsDate11.setVisible(true);
        labelSearchSongsDate12.setVisible(true);
        labelSearchSongsDate13.setVisible(true);
        labelSearchSongsDate14.setVisible(true);
        labelSearchSongsDate15.setVisible(true);
        labelSearchSongsDate16.setVisible(true);
        labelSearchSongsDate17.setVisible(true);
    }
    
    private void checkLikesSearch(){
        int row = tableFavoriteSongs.getRowCount();
        String[] names = new String[100];
        Image img2 = null;
        try {
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int a=1;a<=18;a++){
            names[a+1] = "Empty";
        }
        for(int a=0;a<row;a++){
            names[a+1] = (String) tableFavoriteSongs.getModel().getValueAt(a,0);
        }
        for(int a=1;a<=18;a++){
            if (names[a].equals(results[1])){
                results_num[1] = 1;
                labelHeart1SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[2])){
                results_num[2] = 1;
                labelHeart2SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[3])){
                results_num[3] = 1;
                labelHeart3SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[4])){
                results_num[4] = 1;
                labelHeart4SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[5])){
                results_num[5] = 1;
                labelHeart5SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[6])){
                results_num[6] = 1;
                labelHeart6SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[7])){
                results_num[7] = 1;
                labelHeart7SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[8])){
                results_num[8] = 1;
                labelHeart8SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[9])){
                results_num[9] = 1;
                labelHeart9SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[10])){
                results_num[10] = 1;
                labelHeart10SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[11])){
                results_num[11] = 1;
                labelHeart11SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[12])){
                results_num[12] = 1;
                labelHeart12SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[13])){
                results_num[13] = 1;
                labelHeart13SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[14])){
                results_num[14] = 1;
                labelHeart14SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[15])){
                results_num[15] = 1;
                labelHeart15SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[16])){
                results_num[16] = 1;
                labelHeart16SearchSongs.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(results[17])){
                results_num[17] = 1;
                labelHeart17SearchSongs.setIcon(new ImageIcon(img2));
            }
        }
    }
   
    private void checkLikes(){
        int row = tableFavoriteSongs.getRowCount();
        String[] names = new String[100];
        Image img2 = null;
        try {
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int a=1;a<=18;a++){
            names[a] = "Empty";
        }
        for(int a=0;a<row;a++){
            names[a+1] = (String) tableFavoriteSongs.getModel().getValueAt(a,0);
        }
        for(int a=1;a<=18;a++){
            if (names[a].equals(line[24])){
                song[1] = 1;
                labelHeart1Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[25])){
                song[2] = 1;
                labelHeart2Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[26])){
                song[3] = 1;
                labelHeart3Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[27])){
                song[4] = 1;
                labelHeart4Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[28])){
                song[5] = 1;
                labelHeart5Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[29])){
                song[6] = 1;
                labelHeart6Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[30])){
                song[7] = 1;
                labelHeart7Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[31])){
                song[8] = 1;
                labelHeart8Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[32])){
                song[9] = 1;
                labelHeart9Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[33])){
                song[10] = 1;
                labelHeart10Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[34])){
                song[11] = 1;
                labelHeart11Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[35])){
                song[12] = 1;
                labelHeart12Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[36])){
                song[13] = 1;
                labelHeart13Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[37])){
                song[14] = 1;
                labelHeart14Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[38])){
                song[15] = 1;
                labelHeart15Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[39])){
                song[16] = 1;
                labelHeart16Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[40])){
                song[17] = 1;
                labelHeart17Charts.setIcon(new ImageIcon(img2));
            } else if (names[a].equals(line[41])){
                song[18] = 1;
                labelHeart18Charts.setIcon(new ImageIcon(img2));
            }
        }
        int row2 = tableFavoriteArtists.getRowCount();
        String[] names2 = new String[100];
        for(int a=1;a<=36;a++){
            names2[a] = "Empty";
        }
        for(int a=0;a<row2;a++){
            names2[a+1] = (String) tableFavoriteArtists.getModel().getValueAt(a,0);
        }
        for(int a=1;a<=36;a++){
            if (names2[a].equals(artist[1])){
                fav_artist[1] = 1;
                labelHeart1Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[2])){
                fav_artist[2] = 1;
                labelHeart2Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[3])){
                fav_artist[3] = 1;
                labelHeart3Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[4])){
                fav_artist[4] = 1;
                labelHeart4Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[5])){
                fav_artist[5] = 1;
                labelHeart5Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[6])){
                fav_artist[6] = 1;
                labelHeart6Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[7])){
                fav_artist[7] = 1;
                labelHeart7Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[8])){
                fav_artist[8] = 1;
                labelHeart8Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[9])){
                fav_artist[9] = 1;
                labelHeart9Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[10])){
                fav_artist[10] = 1;
                labelHeart10Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[11])){
                fav_artist[11] = 1;
                labelHeart11Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[12])){
                fav_artist[12] = 1;
                labelHeart12Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[13])){
                fav_artist[13] = 1;
                labelHeart13Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[14])){
                fav_artist[14] = 1;
                labelHeart14Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[15])){
                fav_artist[15] = 1;
                labelHeart15Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[16])){
                fav_artist[16] = 1;
                labelHeart16Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[17])){
                fav_artist[17] = 1;
                labelHeart17Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[18])){
                fav_artist[18] = 1;
                labelHeart18Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[19])){
                fav_artist[19] = 1;
                labelHeart19Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[20])){
                fav_artist[20] = 1;
                labelHeart20Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[21])){
                fav_artist[21] = 1;
                labelHeart21Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[22])){
                fav_artist[22] = 1;
                labelHeart22Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[23])){
                fav_artist[23] = 1;
                labelHeart23Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[24])){
                fav_artist[24] = 1;
                labelHeart24Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[25])){
                fav_artist[25] = 1;
                labelHeart25Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[26])){
                fav_artist[26] = 1;
                labelHeart26Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[27])){
                fav_artist[27] = 1;
                labelHeart27Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[28])){
                fav_artist[28] = 1;
                labelHeart28Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[29])){
                fav_artist[29] = 1;
                labelHeart29Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[30])){
                fav_artist[30] = 1;
                labelHeart30Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[31])){
                fav_artist[31] = 1;
                labelHeart31Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[32])){
                fav_artist[32] = 1;
                labelHeart32Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[33])){
                fav_artist[33] = 1;
                labelHeart33Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[34])){
                fav_artist[34] = 1;
                labelHeart34Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[35])){
                fav_artist[35] = 1;
                labelHeart35Artists.setIcon(new ImageIcon(img2));
            } else if (names2[a].equals(artist[36])){
                fav_artist[36] = 1;
                labelHeart36Artists.setIcon(new ImageIcon(img2));
            }
        }
    }
    
    public void updateTables(){
        try {
            DefaultTableModel tm = (DefaultTableModel)tablePlaylistsBig.getModel();
            tm.setRowCount(0);
            String sql4 = "SELECT * FROM songs;";
            ResultSet rs = st.executeQuery(sql4);
            while(rs.next()){
                Object ii[] = {rs.getString("NAME"),rs.getString("LINK")};
                tm.addRow(ii);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DefaultTableModel tm = (DefaultTableModel)tablePlayPlaylist.getModel();
            tm.setRowCount(0);
            String sql4 = "SELECT * FROM playlists WHERE USERS_ID = '"+user_id+"';";
            ResultSet rs = st.executeQuery(sql4);
            while(rs.next()){
                Object ii[] = {rs.getString("NAME"),rs.getString("GENRE"),rs.getString("YOUTUBE_LINKS")};
                tm.addRow(ii);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DefaultTableModel tm = (DefaultTableModel)tablePlaylists.getModel();
            tm.setRowCount(0);
            String sql4 = "SELECT * FROM playlists WHERE USERS_ID = '"+user_id+"';";
            ResultSet rs = st.executeQuery(sql4);
            while(rs.next()){
                Object ii[] = {rs.getString("NAME"),rs.getString("GENRE"),rs.getString("YOUTUBE_LINKS")};
                tm.addRow(ii);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DefaultTableModel tm = (DefaultTableModel)tableFavoriteSongs.getModel();
            tm.setRowCount(0);
            String sql4 = "SELECT * FROM favorites WHERE USERS_ID = '"+user_id+"';";
            ResultSet rs = st.executeQuery(sql4);
            while(rs.next()){
                Object ii[] = {rs.getString("NAME"),rs.getString("LINK")};
                tm.addRow(ii);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DefaultTableModel tm = (DefaultTableModel)tableFavoriteArtists.getModel();
            tm.setRowCount(0);
            String sql4 = "SELECT * FROM artists WHERE USERS_ID = '"+user_id+"';";
            ResultSet rs = st.executeQuery(sql4);
            while(rs.next()){
                Object ii[] = {rs.getString("NAME"),rs.getString("LINKS")};
                tm.addRow(ii);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DefaultTableModel tm = (DefaultTableModel)tableViewCompleteCharts.getModel();
            tm.setRowCount(0);
            String sql4 = "SELECT * FROM charts;";
            ResultSet rs = st.executeQuery(sql4);
            while(rs.next()){
                Object ii[] = {rs.getString("NAME"),rs.getString("LINK")};
                tm.addRow(ii);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        int abc = tablePlayPlaylist.getRowCount();
        if(abc!=0){
            String label131 = (String) tablePlayPlaylist.getModel().getValueAt(0,0);
            link1 = (String) tablePlayPlaylist.getModel().getValueAt(0,2);
            labelPlaylist1Home.setText("1. "+label131);
            String label132 = null, label133 = null;
            try{
                label132 = (String) tablePlayPlaylist.getModel().getValueAt(1,0);
            } catch(Exception e) {
                label132 = null;
            }
            if (label132==null){
                labelPlaylist2Home.setVisible(false);
                labelPlaylist3Home.setVisible(false);
            } else {
                link2 = (String) tablePlayPlaylist.getModel().getValueAt(1,2);
                labelPlaylist2Home.setVisible(true);
                labelPlaylist2Home.setText("2. "+label132);
                try {
                    label133 = (String) tablePlayPlaylist.getModel().getValueAt(2,0);
                } catch(Exception e) {
                    label133 = null;
                }
                if (label133==null){
                    labelPlaylist3Home.setVisible(false);
                } else {
                    link3 = (String) tablePlayPlaylist.getModel().getValueAt(2,2);
                    labelPlaylist3Home.setVisible(true);
                    labelPlaylist3Home.setText("3. "+label133);
                }
             }
        } else {
            labelPlaylist1Home.setText("No playlists have been created yet.");
            labelPlaylist2Home.setVisible(false);
            labelPlaylist3Home.setVisible(false);
        }
    }
    
    public void artistsSongs(){
        Document doc2;
        try {
            String url2 = Files.readAllLines(Paths.get("artists.txt")).get(y).replace("<a href=\"","c6/youtube/").replaceAll("\".*\\>","");
            doc2 = Jsoup.connect(url2).get();
            Elements artists = doc2.select("[href]");
            String artists_songs = artists.toString();
            try (PrintWriter out = new PrintWriter("artists_songs.txt")) {
                out.println(artists_songs);
                out.close();
            }
            for(int a=1; a<=50; a++){
                small_artist[a] = Files.readAllLines(Paths.get("artists_songs.txt")).get(a+20).replace("<a href=\"../video/","").replaceAll("\\..*\\>","");
            }
            openWebPage("http://www.youtube.com/watch_videos?video_ids="+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+","+small_artist[42]+","+small_artist[43]+","+small_artist[44]+","+small_artist[45]+","+small_artist[46]+","+small_artist[47]+","+small_artist[48]+","+small_artist[49]+","+small_artist[50]);
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void fav_artistsSongs(){
        Document doc2;
        try {
            String url2 = Files.readAllLines(Paths.get("artists.txt")).get(y).replace("<a href=\"","https://kworb.net/youtube/").replaceAll("\".*\\>","");
            doc2 = Jsoup.connect(url2).get();
            Elements artists = doc2.select("[href]");
            String artists_songs = artists.toString();
            try (PrintWriter out = new PrintWriter("artists_songs.txt")) {
                out.println(artists_songs);
                out.close();
            }
            for(int a=1; a<=41; a++){
                small_artist[a] = Files.readAllLines(Paths.get("artists_songs.txt")).get(a+20).replace("<a href=\"../video/","").replaceAll("\\..*\\>","");
            }
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void beginMusic() throws UnsupportedAudioFileException, IOException{
        if(x==0){
            try {
                clip = AudioSystem.getClip();
                if(audioInput!=null){
                    try {
                        clip.open(audioInput);
                        clip.start();
                    } catch (IOException ex) {
                        Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void repeatMusic(){
        if(clip!=null){
            if(rep==1){
                clipTimePosition = clip.getMicrosecondPosition();
                clip.stop();
                clip.loop(1);
                clip.stop();
                clip.setMicrosecondPosition(clipTimePosition);
                clip.start();
            } else if(rep==0){
                clipTimePosition = clip.getMicrosecondPosition();
                clip.stop();
                clip.loop(0);
                clip.stop();
                clip.setMicrosecondPosition(clipTimePosition);
                clip.start();
            }
        }
    }
    
    public void playpauseMusic(){
        if(clip!=null){
            if(pau==1){
                clipTimePosition = clip.getMicrosecondPosition();
                clip.stop();
            } else if(pau==0) {
                clip.setMicrosecondPosition(clipTimePosition);
                clip.start();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMainStatic = new javax.swing.JPanel();
        labelMusically = new javax.swing.JLabel();
        labelEmblem = new javax.swing.JLabel();
        labelSearch = new javax.swing.JLabel();
        labelCharts = new javax.swing.JLabel();
        labelPlaylists = new javax.swing.JLabel();
        labelTopArtists = new javax.swing.JLabel();
        labelHomeImage = new javax.swing.JLabel();
        labelSearchImage = new javax.swing.JLabel();
        labelChartsImage = new javax.swing.JLabel();
        labelPlaylistsImage = new javax.swing.JLabel();
        labelTopArtistsImage = new javax.swing.JLabel();
        labelHome = new javax.swing.JButton();
        labelLocalSongsImage = new javax.swing.JLabel();
        labelLocalSong = new javax.swing.JLabel();
        scrollPaneMain = new javax.swing.JScrollPane();
        tablePlaylists = new javax.swing.JTable();
        panelMainDynamic = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        labelWelcome = new javax.swing.JLabel();
        labelFreshNewMusic = new javax.swing.JLabel();
        labelSong1Home = new javax.swing.JLabel();
        labelSong2Home = new javax.swing.JLabel();
        labelSong3Home = new javax.swing.JLabel();
        javax.swing.JLabel labelTopArtistsHome = new javax.swing.JLabel();
        labelArtist1Home = new javax.swing.JLabel();
        labelArtist2Home = new javax.swing.JLabel();
        labelArtist3Home = new javax.swing.JLabel();
        labelSavedPlaylistsHome = new javax.swing.JLabel();
        labelPlaylist1Home = new javax.swing.JLabel();
        labelPlaylist2Home = new javax.swing.JLabel();
        labelPlaylist3Home = new javax.swing.JLabel();
        buttonViewFavoriteSongs = new javax.swing.JButton();
        buttonViewFavoritesArtists = new javax.swing.JButton();
        buttonLogOut = new javax.swing.JButton();
        labelCurrentFirstName = new javax.swing.JLabel();
        labelCurentLatName = new javax.swing.JLabel();
        buttonSearchProfiles = new javax.swing.JButton();
        panelSearch = new javax.swing.JPanel();
        textFieldGenius = new javax.swing.JTextField();
        labelSearchWikipedia = new javax.swing.JLabel();
        scrollPaneSearch = new javax.swing.JScrollPane();
        editorPaneSearchResults = new javax.swing.JEditorPane();
        labelSearchGenius = new javax.swing.JLabel();
        textFieldWikipedia = new javax.swing.JTextField();
        labelBackSearch = new javax.swing.JLabel();
        labelGoBackSearch = new javax.swing.JLabel();
        panelCharts = new javax.swing.JPanel();
        labelTrendingSongs = new javax.swing.JLabel();
        labelSong1Charts = new javax.swing.JLabel();
        labelSong2Charts = new javax.swing.JLabel();
        labelSong3Charts = new javax.swing.JLabel();
        labelSong4Charts = new javax.swing.JLabel();
        labelSong5Charts = new javax.swing.JLabel();
        labelSong6Charts = new javax.swing.JLabel();
        labelSong7Charts = new javax.swing.JLabel();
        labelSong8Charts = new javax.swing.JLabel();
        labelSong9Charts = new javax.swing.JLabel();
        labelSong11Charts = new javax.swing.JLabel();
        labelSong10Charts = new javax.swing.JLabel();
        labelSong13Charts = new javax.swing.JLabel();
        labelSong14Charts = new javax.swing.JLabel();
        labelSong15Charts = new javax.swing.JLabel();
        labelSong16Charts = new javax.swing.JLabel();
        labelSong12Charts = new javax.swing.JLabel();
        labelSong17Charts = new javax.swing.JLabel();
        labelSong18Charts = new javax.swing.JLabel();
        buttonListenOnYoutube = new javax.swing.JButton();
        labelHeart1Charts = new javax.swing.JLabel();
        labelHeart2Charts = new javax.swing.JLabel();
        labelHeart3Charts = new javax.swing.JLabel();
        labelHeart4Charts = new javax.swing.JLabel();
        labelHeart5Charts = new javax.swing.JLabel();
        labelHeart6Charts = new javax.swing.JLabel();
        labelHeart7Charts = new javax.swing.JLabel();
        labelHeart8Charts = new javax.swing.JLabel();
        labelHeart9Charts = new javax.swing.JLabel();
        labelHeart10Charts = new javax.swing.JLabel();
        labelHeart11Charts = new javax.swing.JLabel();
        labelHeart12Charts = new javax.swing.JLabel();
        labelHeart13Charts = new javax.swing.JLabel();
        labelHeart14Charts = new javax.swing.JLabel();
        labelHeart15Charts = new javax.swing.JLabel();
        labelHeart16Charts = new javax.swing.JLabel();
        labelHeart17Charts = new javax.swing.JLabel();
        labelHeart18Charts = new javax.swing.JLabel();
        buttonViewCharts = new javax.swing.JButton();
        panelPlaylist = new javax.swing.JPanel();
        labelCreatePlaylist = new javax.swing.JLabel();
        formattedTextFieldPlaylistName = new javax.swing.JFormattedTextField();
        formattedTextFieldPlaylistGenre = new javax.swing.JFormattedTextField();
        scrollPanePlaylist = new javax.swing.JScrollPane();
        tablePlaylistsBig = new javax.swing.JTable();
        labelPlaylistName = new javax.swing.JLabel();
        labelPlaylistGenre = new javax.swing.JLabel();
        buttonCreatePlaylist = new javax.swing.JButton();
        labelCommend = new javax.swing.JLabel();
        formattedTextFieldSongLinks = new javax.swing.JFormattedTextField();
        buttonViewPlaylists = new javax.swing.JButton();
        panelPlayPlaylist = new javax.swing.JPanel();
        labelBackPlayPlaylist = new javax.swing.JLabel();
        scrollPanePlayPlaylist = new javax.swing.JScrollPane();
        tablePlayPlaylist = new javax.swing.JTable();
        labelSelectPlaylistToPlay = new javax.swing.JLabel();
        buttonDeleteAllPlaylists = new javax.swing.JButton();
        panelTopArtists = new javax.swing.JPanel();
        labelTopArtistsYoutube = new javax.swing.JLabel();
        labelArtist1TopArtists = new javax.swing.JLabel();
        labelArtist2TopArtists = new javax.swing.JLabel();
        labelArtist3TopArtists = new javax.swing.JLabel();
        labelArtist4TopArtists = new javax.swing.JLabel();
        labelArtist5TopArtists = new javax.swing.JLabel();
        labelArtist6TopArtists = new javax.swing.JLabel();
        labelArtist7TopArtists = new javax.swing.JLabel();
        labelArtist8TopArtists = new javax.swing.JLabel();
        labelArtist9TopArtists = new javax.swing.JLabel();
        labelArtist10TopArtists = new javax.swing.JLabel();
        labelArtist11TopArtists = new javax.swing.JLabel();
        labelArtist12TopArtists = new javax.swing.JLabel();
        labelArtist13TopArtists = new javax.swing.JLabel();
        labelArtist14TopArtists = new javax.swing.JLabel();
        labelArtist15TopArtists = new javax.swing.JLabel();
        labelArtist16TopArtists = new javax.swing.JLabel();
        labelArtist17TopArtists = new javax.swing.JLabel();
        labelArtist18TopArtists = new javax.swing.JLabel();
        labelArtist19TopArtists = new javax.swing.JLabel();
        labelArtist20TopArtists = new javax.swing.JLabel();
        labelArtist21TopArtists = new javax.swing.JLabel();
        labelArtist22TopArtists = new javax.swing.JLabel();
        labelArtist23TopArtists = new javax.swing.JLabel();
        labelArtist24TopArtists = new javax.swing.JLabel();
        labelArtist25TopArtists = new javax.swing.JLabel();
        labelArtist26TopArtists = new javax.swing.JLabel();
        labelArtist27TopArtists = new javax.swing.JLabel();
        labelArtist28TopArtists = new javax.swing.JLabel();
        labelArtist29TopArtists = new javax.swing.JLabel();
        labelArtist30TopArtists = new javax.swing.JLabel();
        labelArtist31TopArtists = new javax.swing.JLabel();
        labelArtist32TopArtists = new javax.swing.JLabel();
        labelArtist33TopArtists = new javax.swing.JLabel();
        labelArtist34TopArtists = new javax.swing.JLabel();
        labelArtist35TopArtists = new javax.swing.JLabel();
        labelArtist36TopArtists = new javax.swing.JLabel();
        labelHeart1Artists = new javax.swing.JLabel();
        labelHeart2Artists = new javax.swing.JLabel();
        labelHeart3Artists = new javax.swing.JLabel();
        labelHeart4Artists = new javax.swing.JLabel();
        labelHeart5Artists = new javax.swing.JLabel();
        labelHeart6Artists = new javax.swing.JLabel();
        labelHeart7Artists = new javax.swing.JLabel();
        labelHeart8Artists = new javax.swing.JLabel();
        labelHeart9Artists = new javax.swing.JLabel();
        labelHeart10Artists = new javax.swing.JLabel();
        labelHeart11Artists = new javax.swing.JLabel();
        labelHeart12Artists = new javax.swing.JLabel();
        labelHeart13Artists = new javax.swing.JLabel();
        labelHeart14Artists = new javax.swing.JLabel();
        labelHeart15Artists = new javax.swing.JLabel();
        labelHeart16Artists = new javax.swing.JLabel();
        labelHeart17Artists = new javax.swing.JLabel();
        labelHeart18Artists = new javax.swing.JLabel();
        labelHeart19Artists = new javax.swing.JLabel();
        labelHeart20Artists = new javax.swing.JLabel();
        labelHeart21Artists = new javax.swing.JLabel();
        labelHeart22Artists = new javax.swing.JLabel();
        labelHeart23Artists = new javax.swing.JLabel();
        labelHeart24Artists = new javax.swing.JLabel();
        labelHeart25Artists = new javax.swing.JLabel();
        labelHeart26Artists = new javax.swing.JLabel();
        labelHeart27Artists = new javax.swing.JLabel();
        labelHeart28Artists = new javax.swing.JLabel();
        labelHeart29Artists = new javax.swing.JLabel();
        labelHeart30Artists = new javax.swing.JLabel();
        labelHeart31Artists = new javax.swing.JLabel();
        labelHeart32Artists = new javax.swing.JLabel();
        labelHeart33Artists = new javax.swing.JLabel();
        labelHeart34Artists = new javax.swing.JLabel();
        labelHeart35Artists = new javax.swing.JLabel();
        labelHeart36Artists = new javax.swing.JLabel();
        panelFavoriteSongs = new javax.swing.JPanel();
        labelBackFavoriteSongs = new javax.swing.JLabel();
        labelReturnHomeFavoriteSongs = new javax.swing.JLabel();
        scrollPaneFavoriteSongs = new javax.swing.JScrollPane();
        tableFavoriteSongs = new javax.swing.JTable();
        buttonListenOnYoutubeFavoriteSongs = new javax.swing.JButton();
        panelFavoriteArtists = new javax.swing.JPanel();
        labelBckFavoriteArtists = new javax.swing.JLabel();
        labelReturnHomeFavoriteArtists = new javax.swing.JLabel();
        scrollPaneFavoriteArtists = new javax.swing.JScrollPane();
        tableFavoriteArtists = new javax.swing.JTable();
        buttonListenOnYoutubeFavoriteArtists = new javax.swing.JButton();
        panelSearchSongs = new javax.swing.JPanel();
        textFieldSearchSongs = new javax.swing.JTextField();
        labelSearchIconSearchSongs = new javax.swing.JLabel();
        buttonSearchInformation = new javax.swing.JButton();
        labelSongSearchResult1 = new javax.swing.JLabel();
        labelSongSearchResult2 = new javax.swing.JLabel();
        labelSongSearchResult3 = new javax.swing.JLabel();
        labelSongSearchResult4 = new javax.swing.JLabel();
        labelSongSearchResult5 = new javax.swing.JLabel();
        labelSongSearchResult6 = new javax.swing.JLabel();
        labelSongSearchResult7 = new javax.swing.JLabel();
        labelSongSearchResult8 = new javax.swing.JLabel();
        labelSongSearchResult9 = new javax.swing.JLabel();
        labelSongSearchResult10 = new javax.swing.JLabel();
        labelSongSearchResult11 = new javax.swing.JLabel();
        labelSongSearchResult12 = new javax.swing.JLabel();
        labelSongSearchResult13 = new javax.swing.JLabel();
        labelSongSearchResult14 = new javax.swing.JLabel();
        labelSongSearchResult15 = new javax.swing.JLabel();
        labelSongSearchResult16 = new javax.swing.JLabel();
        labelSongSearchResult17 = new javax.swing.JLabel();
        labelSearchSongViewCount1 = new javax.swing.JLabel();
        labelSearchSongViewCount2 = new javax.swing.JLabel();
        labelSearchSongViewCount3 = new javax.swing.JLabel();
        labelSearchSongViewCount4 = new javax.swing.JLabel();
        labelSearchSongViewCount5 = new javax.swing.JLabel();
        labelSearchSongViewCount6 = new javax.swing.JLabel();
        labelSearchSongViewCount7 = new javax.swing.JLabel();
        labelSearchSongViewCount8 = new javax.swing.JLabel();
        labelSearchSongViewCount9 = new javax.swing.JLabel();
        labelSearchSongViewCount10 = new javax.swing.JLabel();
        labelSearchSongViewCount11 = new javax.swing.JLabel();
        labelSearchSongViewCount12 = new javax.swing.JLabel();
        labelSearchSongViewCount13 = new javax.swing.JLabel();
        labelSearchSongViewCount14 = new javax.swing.JLabel();
        labelSearchSongViewCount15 = new javax.swing.JLabel();
        labelSearchSongViewCount16 = new javax.swing.JLabel();
        labelSearchSongViewCount17 = new javax.swing.JLabel();
        labelSearchSongsDate1 = new javax.swing.JLabel();
        labelSearchSongsDate2 = new javax.swing.JLabel();
        labelSearchSongsDate3 = new javax.swing.JLabel();
        labelSearchSongsDate4 = new javax.swing.JLabel();
        labelSearchSongsDate5 = new javax.swing.JLabel();
        labelSearchSongsDate6 = new javax.swing.JLabel();
        labelSearchSongsDate7 = new javax.swing.JLabel();
        labelSearchSongsDate8 = new javax.swing.JLabel();
        labelSearchSongsDate9 = new javax.swing.JLabel();
        labelSearchSongsDate10 = new javax.swing.JLabel();
        labelSearchSongsDate11 = new javax.swing.JLabel();
        labelSearchSongsDate12 = new javax.swing.JLabel();
        labelSearchSongsDate13 = new javax.swing.JLabel();
        labelSearchSongsDate14 = new javax.swing.JLabel();
        labelSearchSongsDate15 = new javax.swing.JLabel();
        labelSearchSongsDate16 = new javax.swing.JLabel();
        labelSearchSongsDate17 = new javax.swing.JLabel();
        labelHeart1SearchSongs = new javax.swing.JLabel();
        labelHeart2SearchSongs = new javax.swing.JLabel();
        labelHeart3SearchSongs = new javax.swing.JLabel();
        labelHeart4SearchSongs = new javax.swing.JLabel();
        labelHeart5SearchSongs = new javax.swing.JLabel();
        labelHeart6SearchSongs = new javax.swing.JLabel();
        labelHeart7SearchSongs = new javax.swing.JLabel();
        labelHeart8SearchSongs = new javax.swing.JLabel();
        labelHeart9SearchSongs = new javax.swing.JLabel();
        labelHeart10SearchSongs = new javax.swing.JLabel();
        labelHeart11SearchSongs = new javax.swing.JLabel();
        labelHeart12SearchSongs = new javax.swing.JLabel();
        labelHeart13SearchSongs = new javax.swing.JLabel();
        labelHeart14SearchSongs = new javax.swing.JLabel();
        labelHeart15SearchSongs = new javax.swing.JLabel();
        labelHeart16SearchSongs = new javax.swing.JLabel();
        labelHeart17SearchSongs = new javax.swing.JLabel();
        panelViewCompleteCharts = new javax.swing.JPanel();
        labelBackViewCompleteCharts = new javax.swing.JLabel();
        labelReturnViewCompleteCharts = new javax.swing.JLabel();
        scrollPaneViewCompleteCharts = new javax.swing.JScrollPane();
        tableViewCompleteCharts = new javax.swing.JTable();
        buttonListenOnYoutubeViewCompleteCharts = new javax.swing.JButton();
        panelSearchProfiles = new javax.swing.JPanel();
        labelBackImage = new javax.swing.JLabel();
        labelReturn = new javax.swing.JLabel();
        labelSearchIcon = new javax.swing.JLabel();
        textFieldSearch = new javax.swing.JTextField();
        labelSearchedProfile = new javax.swing.JLabel();
        labelFirstName = new javax.swing.JLabel();
        scrollPanelSearchProfiles = new javax.swing.JScrollPane();
        tableProfiles = new javax.swing.JTable();
        labelLastName = new javax.swing.JLabel();
        panelPlayer = new javax.swing.JPanel();
        sliderVolume = new javax.swing.JSlider();
        labelPlayerImage = new javax.swing.JLabel();
        labelArtistName = new javax.swing.JLabel();
        labelSongName = new javax.swing.JLabel();
        labelPlay = new javax.swing.JLabel();
        labelNext = new javax.swing.JLabel();
        labelPrevious = new javax.swing.JLabel();
        labelShuffle = new javax.swing.JLabel();
        labelRepeat = new javax.swing.JLabel();
        labelChooseLocalSong = new javax.swing.JLabel();
        labelpalette = new javax.swing.JLabel();
        labelmood = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMainStatic.setBackground(new java.awt.Color(0, 0, 0));

        labelMusically.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelMusically.setForeground(new java.awt.Color(255, 255, 255));
        labelMusically.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMusically.setText("Musically");
        labelMusically.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelEmblem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Musically.png"))); // NOI18N
        labelEmblem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelSearch.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelSearch.setForeground(new java.awt.Color(255, 255, 255));
        labelSearch.setText("Search");
        labelSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSearchMouseClicked(evt);
            }
        });

        labelCharts.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelCharts.setForeground(new java.awt.Color(255, 255, 255));
        labelCharts.setText("Charts");
        labelCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelChartsMouseClicked(evt);
            }
        });

        labelPlaylists.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelPlaylists.setForeground(new java.awt.Color(255, 255, 255));
        labelPlaylists.setText("Create/View Playlists");
        labelPlaylists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPlaylistsMouseClicked(evt);
            }
        });

        labelTopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelTopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelTopArtists.setText("Top Artists");
        labelTopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelTopArtistsMouseClicked(evt);
            }
        });

        labelHomeImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Home.png"))); // NOI18N
        labelHomeImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHomeImageMouseClicked(evt);
            }
        });

        labelSearchImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Search.png"))); // NOI18N
        labelSearchImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSearchImageMouseClicked(evt);
            }
        });

        labelChartsImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Charts.png"))); // NOI18N
        labelChartsImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelChartsImageMouseClicked(evt);
            }
        });

        labelPlaylistsImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Create_playlist.png"))); // NOI18N
        labelPlaylistsImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPlaylistsImageMouseClicked(evt);
            }
        });

        labelTopArtistsImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Top_artists.png"))); // NOI18N
        labelTopArtistsImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelTopArtistsImageMouseClicked(evt);
            }
        });

        labelHome.setBackground(new java.awt.Color(0, 0, 0));
        labelHome.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelHome.setForeground(new java.awt.Color(255, 255, 255));
        labelHome.setText("Home");
        labelHome.setBorder(null);
        labelHome.setBorderPainted(false);
        labelHome.setContentAreaFilled(false);
        labelHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelLocalSongsImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Local_song.png"))); // NOI18N
        labelLocalSongsImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLocalSongsImageMouseClicked(evt);
            }
        });

        labelLocalSong.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelLocalSong.setForeground(new java.awt.Color(255, 255, 255));
        labelLocalSong.setText("Play Local Song");
        labelLocalSong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLocalSongMouseClicked(evt);
            }
        });

        tablePlaylists.setBackground(new java.awt.Color(0, 0, 0));
        tablePlaylists.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        tablePlaylists.setForeground(new java.awt.Color(255, 255, 255));
        tablePlaylists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Genre", "YouTube Links"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablePlaylists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePlaylistsMouseClicked(evt);
            }
        });
        scrollPaneMain.setViewportView(tablePlaylists);

        javax.swing.GroupLayout panelMainStaticLayout = new javax.swing.GroupLayout(panelMainStatic);
        panelMainStatic.setLayout(panelMainStaticLayout);
        panelMainStaticLayout.setHorizontalGroup(
            panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainStaticLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelEmblem)
                    .addGroup(panelMainStaticLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSearchImage)
                            .addComponent(labelChartsImage)
                            .addComponent(labelPlaylistsImage)
                            .addComponent(labelTopArtistsImage)
                            .addComponent(labelLocalSongsImage)))
                    .addComponent(labelHomeImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMusically, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMainStaticLayout.createSequentialGroup()
                        .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTopArtists)
                            .addComponent(labelCharts)
                            .addComponent(labelSearch)
                            .addComponent(labelHome)
                            .addComponent(labelPlaylists)
                            .addComponent(labelLocalSong))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(scrollPaneMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelMainStaticLayout.setVerticalGroup(
            panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainStaticLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelEmblem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMusically, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelHomeImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelSearchImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelChartsImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelPlaylistsImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPlaylists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTopArtistsImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTopArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelMainStaticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelLocalSongsImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLocalSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        panelMainDynamic.setBackground(new java.awt.Color(28, 28, 28));
        panelMainDynamic.setPreferredSize(new java.awt.Dimension(1199, 640));

        panelHome.setBackground(new java.awt.Color(28, 28, 28));
        panelHome.setPreferredSize(new java.awt.Dimension(1017, 624));

        labelWelcome.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelWelcome.setForeground(new java.awt.Color(255, 255, 255));
        labelWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWelcome.setText("Welcome");
        labelWelcome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelFreshNewMusic.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelFreshNewMusic.setForeground(new java.awt.Color(255, 255, 255));
        labelFreshNewMusic.setText("Fresh new music");

        labelSong1Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong1Home.setForeground(new java.awt.Color(255, 255, 255));
        labelSong1Home.setText("Song 1");
        labelSong1Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong1HomeMouseClicked(evt);
            }
        });

        labelSong2Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong2Home.setForeground(new java.awt.Color(255, 255, 255));
        labelSong2Home.setText("Song 2");
        labelSong2Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong2HomeMouseClicked(evt);
            }
        });

        labelSong3Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong3Home.setForeground(new java.awt.Color(255, 255, 255));
        labelSong3Home.setText("Song 3");
        labelSong3Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong3HomeMouseClicked(evt);
            }
        });

        labelTopArtistsHome.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelTopArtistsHome.setForeground(new java.awt.Color(255, 255, 255));
        labelTopArtistsHome.setText("Top artists worldwide");

        labelArtist1Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist1Home.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist1Home.setText("Artist 1");
        labelArtist1Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist1HomeMouseClicked(evt);
            }
        });

        labelArtist2Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist2Home.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist2Home.setText("Artist 2");
        labelArtist2Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist2HomeMouseClicked(evt);
            }
        });

        labelArtist3Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist3Home.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist3Home.setText("Artist 3");
        labelArtist3Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist3HomeMouseClicked(evt);
            }
        });

        labelSavedPlaylistsHome.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        labelSavedPlaylistsHome.setForeground(new java.awt.Color(255, 255, 255));
        labelSavedPlaylistsHome.setText("Saved playlists");
        labelSavedPlaylistsHome.setToolTipText("");

        labelPlaylist1Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelPlaylist1Home.setForeground(new java.awt.Color(255, 255, 255));
        labelPlaylist1Home.setText("Playlist 1");
        labelPlaylist1Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPlaylist1HomeMouseClicked(evt);
            }
        });

        labelPlaylist2Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelPlaylist2Home.setForeground(new java.awt.Color(255, 255, 255));
        labelPlaylist2Home.setText("Playlist 2");
        labelPlaylist2Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPlaylist2HomeMouseClicked(evt);
            }
        });

        labelPlaylist3Home.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelPlaylist3Home.setForeground(new java.awt.Color(255, 255, 255));
        labelPlaylist3Home.setText("Playlist 3");
        labelPlaylist3Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPlaylist3HomeMouseClicked(evt);
            }
        });

        buttonViewFavoriteSongs.setBackground(new java.awt.Color(28, 28, 28));
        buttonViewFavoriteSongs.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonViewFavoriteSongs.setForeground(new java.awt.Color(255, 255, 255));
        buttonViewFavoriteSongs.setText("View Favorite Songs");
        buttonViewFavoriteSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonViewFavoriteSongsMouseClicked(evt);
            }
        });

        buttonViewFavoritesArtists.setBackground(new java.awt.Color(28, 28, 28));
        buttonViewFavoritesArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonViewFavoritesArtists.setForeground(new java.awt.Color(255, 255, 255));
        buttonViewFavoritesArtists.setText("View Favorite Artists");
        buttonViewFavoritesArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonViewFavoritesArtistsMouseClicked(evt);
            }
        });

        buttonLogOut.setBackground(new java.awt.Color(28, 28, 28));
        buttonLogOut.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonLogOut.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogOut.setText("Log Out");
        buttonLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLogOutMouseClicked(evt);
            }
        });

        labelCurrentFirstName.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelCurrentFirstName.setForeground(new java.awt.Color(255, 255, 255));
        labelCurrentFirstName.setText("Current firstname");

        labelCurentLatName.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelCurentLatName.setForeground(new java.awt.Color(255, 255, 255));
        labelCurentLatName.setText("Current lastname");

        buttonSearchProfiles.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonSearchProfiles.setForeground(new java.awt.Color(28, 28, 28));
        buttonSearchProfiles.setText("Search Profiles");
        buttonSearchProfiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchProfilesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHomeLayout.createSequentialGroup()
                        .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelHomeLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelHomeLayout.createSequentialGroup()
                                        .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelArtist1Home)
                                            .addComponent(labelArtist2Home)
                                            .addComponent(labelArtist3Home)
                                            .addComponent(labelPlaylist1Home)
                                            .addComponent(labelPlaylist2Home)
                                            .addComponent(labelPlaylist3Home))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHomeLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(buttonViewFavoriteSongs))))
                            .addGroup(panelHomeLayout.createSequentialGroup()
                                .addComponent(labelWelcome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelCurrentFirstName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelCurentLatName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHomeLayout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(buttonSearchProfiles)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonViewFavoritesArtists))
                            .addGroup(panelHomeLayout.createSequentialGroup()
                                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTopArtistsHome)
                                    .addComponent(labelFreshNewMusic)
                                    .addComponent(labelSavedPlaylistsHome))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panelHomeLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSong2Home)
                            .addComponent(labelSong1Home)
                            .addComponent(labelSong3Home))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelWelcome)
                    .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCurentLatName)
                        .addComponent(labelCurrentFirstName)
                        .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelFreshNewMusic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSong1Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSong2Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSong3Home)
                .addGap(18, 18, 18)
                .addComponent(labelTopArtistsHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelArtist1Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelArtist2Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelArtist3Home)
                .addGap(18, 18, 18)
                .addComponent(labelSavedPlaylistsHome)
                .addGap(20, 20, 20)
                .addComponent(labelPlaylist1Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPlaylist2Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPlaylist3Home)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(buttonViewFavoriteSongs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonViewFavoritesArtists)
                    .addComponent(buttonSearchProfiles))
                .addContainerGap())
        );

        panelSearch.setBackground(new java.awt.Color(28, 28, 28));

        textFieldGenius.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        textFieldGenius.setText("Search lyrics on Genius");
        textFieldGenius.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldGenius.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldGeniusMouseClicked(evt);
            }
        });
        textFieldGenius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldGeniusActionPerformed(evt);
            }
        });

        labelSearchWikipedia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Search.png"))); // NOI18N

        editorPaneSearchResults.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        scrollPaneSearch.setViewportView(editorPaneSearchResults);

        labelSearchGenius.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Search.png"))); // NOI18N

        textFieldWikipedia.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        textFieldWikipedia.setText("Search on Wikipedia");
        textFieldWikipedia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldWikipedia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldWikipediaMouseClicked(evt);
            }
        });
        textFieldWikipedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldWikipediaActionPerformed(evt);
            }
        });

        labelBackSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Go_back.png"))); // NOI18N
        labelBackSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackSearchMouseClicked(evt);
            }
        });

        labelGoBackSearch.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelGoBackSearch.setForeground(new java.awt.Color(255, 255, 255));
        labelGoBackSearch.setText("Go back");
        labelGoBackSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelGoBackSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSearchLayout = new javax.swing.GroupLayout(panelSearch);
        panelSearch.setLayout(panelSearchLayout);
        panelSearchLayout.setHorizontalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBackSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchLayout.createSequentialGroup()
                        .addComponent(labelGoBackSearch)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchWikipedia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldWikipedia, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchGenius)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldGenius, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPaneSearch))
                .addContainerGap())
        );
        panelSearchLayout.setVerticalGroup(
            panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSearchWikipedia, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(labelSearchGenius, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldWikipedia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldGenius, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(labelBackSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelGoBackSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelCharts.setBackground(new java.awt.Color(28, 28, 28));

        labelTrendingSongs.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelTrendingSongs.setForeground(new java.awt.Color(255, 255, 255));
        labelTrendingSongs.setText("Videos trending worldwide in the Music category | YouTube");

        labelSong1Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong1Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong1Charts.setText("Song 1");
        labelSong1Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong1ChartsMouseClicked(evt);
            }
        });

        labelSong2Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong2Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong2Charts.setText("Song 2");
        labelSong2Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong2ChartsMouseClicked(evt);
            }
        });

        labelSong3Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong3Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong3Charts.setText("Song 3");
        labelSong3Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong3ChartsMouseClicked(evt);
            }
        });

        labelSong4Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong4Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong4Charts.setText("Song 4");
        labelSong4Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong4ChartsMouseClicked(evt);
            }
        });

        labelSong5Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong5Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong5Charts.setText("Song 5");
        labelSong5Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong5ChartsMouseClicked(evt);
            }
        });

        labelSong6Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong6Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong6Charts.setText("Song 6");
        labelSong6Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong6ChartsMouseClicked(evt);
            }
        });

        labelSong7Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong7Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong7Charts.setText("Song 7");
        labelSong7Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong7ChartsMouseClicked(evt);
            }
        });

        labelSong8Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong8Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong8Charts.setText("Song 8");
        labelSong8Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong8ChartsMouseClicked(evt);
            }
        });

        labelSong9Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong9Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong9Charts.setText("Song 9");
        labelSong9Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong9ChartsMouseClicked(evt);
            }
        });

        labelSong11Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong11Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong11Charts.setText("Song 11");
        labelSong11Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong11ChartsMouseClicked(evt);
            }
        });

        labelSong10Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong10Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong10Charts.setText("Song 10");
        labelSong10Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong10ChartsMouseClicked(evt);
            }
        });

        labelSong13Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong13Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong13Charts.setText("Song 13");
        labelSong13Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong13ChartsMouseClicked(evt);
            }
        });

        labelSong14Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong14Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong14Charts.setText("Song 14");
        labelSong14Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong14ChartsMouseClicked(evt);
            }
        });

        labelSong15Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong15Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong15Charts.setText("Song 15");
        labelSong15Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong15ChartsMouseClicked(evt);
            }
        });

        labelSong16Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong16Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong16Charts.setText("Song 16");
        labelSong16Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong16ChartsMouseClicked(evt);
            }
        });

        labelSong12Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong12Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong12Charts.setText("Song 12");
        labelSong12Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong12ChartsMouseClicked(evt);
            }
        });

        labelSong17Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong17Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong17Charts.setText("Song 17");
        labelSong17Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong17ChartsMouseClicked(evt);
            }
        });

        labelSong18Charts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSong18Charts.setForeground(new java.awt.Color(255, 255, 255));
        labelSong18Charts.setText("Song 18");
        labelSong18Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSong18ChartsMouseClicked(evt);
            }
        });

        buttonListenOnYoutube.setBackground(new java.awt.Color(28, 28, 28));
        buttonListenOnYoutube.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonListenOnYoutube.setForeground(new java.awt.Color(255, 255, 255));
        buttonListenOnYoutube.setText("Listen on YouTube");
        buttonListenOnYoutube.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonListenOnYoutubeMouseClicked(evt);
            }
        });

        labelHeart1Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart1Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart1Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart1Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart1ChartsMouseClicked(evt);
            }
        });

        labelHeart2Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart2Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart2Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart2Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart2ChartsMouseClicked(evt);
            }
        });

        labelHeart3Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart3Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart3Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart3Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart3ChartsMouseClicked(evt);
            }
        });

        labelHeart4Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart4Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart4Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart4Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart4ChartsMouseClicked(evt);
            }
        });

        labelHeart5Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart5Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart5Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart5Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart5ChartsMouseClicked(evt);
            }
        });

        labelHeart6Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart6Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart6Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart6Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart6ChartsMouseClicked(evt);
            }
        });

        labelHeart7Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart7Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart7Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart7Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart7ChartsMouseClicked(evt);
            }
        });

        labelHeart8Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart8Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart8Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart8Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart8ChartsMouseClicked(evt);
            }
        });

        labelHeart9Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart9Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart9Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart9Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart9ChartsMouseClicked(evt);
            }
        });

        labelHeart10Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart10Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart10Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart10Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart10ChartsMouseClicked(evt);
            }
        });

        labelHeart11Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart11Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart11Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart11Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart11ChartsMouseClicked(evt);
            }
        });

        labelHeart12Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart12Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart12Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart12Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart12ChartsMouseClicked(evt);
            }
        });

        labelHeart13Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart13Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart13Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart13Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart13ChartsMouseClicked(evt);
            }
        });

        labelHeart14Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart14Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart14Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart14Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart14ChartsMouseClicked(evt);
            }
        });

        labelHeart15Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart15Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart15Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart15Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart15ChartsMouseClicked(evt);
            }
        });

        labelHeart16Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart16Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart16Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart16Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart16ChartsMouseClicked(evt);
            }
        });

        labelHeart17Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart17Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart17Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart17Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart17ChartsMouseClicked(evt);
            }
        });

        labelHeart18Charts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeart18Charts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart18Charts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelHeart18Charts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart18ChartsMouseClicked(evt);
            }
        });

        buttonViewCharts.setBackground(new java.awt.Color(28, 28, 28));
        buttonViewCharts.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonViewCharts.setForeground(new java.awt.Color(255, 255, 255));
        buttonViewCharts.setText("View Complete Charts");
        buttonViewCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonViewChartsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelChartsLayout = new javax.swing.GroupLayout(panelCharts);
        panelCharts.setLayout(panelChartsLayout);
        panelChartsLayout.setHorizontalGroup(
            panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChartsLayout.createSequentialGroup()
                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTrendingSongs)
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong1Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart1Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong2Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart2Charts)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                        .addComponent(buttonViewCharts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonListenOnYoutube))
                    .addGroup(panelChartsLayout.createSequentialGroup()
                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong9Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart9Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong5Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart5Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong4Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart4Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong6Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart6Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong7Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart7Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong8Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart8Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong11Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart11Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong13Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart13Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong14Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart14Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong15Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart15Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong12Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart12Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong16Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart16Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong17Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart17Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong18Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart18Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong3Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart3Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addComponent(labelSong10Charts)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart10Charts)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelChartsLayout.setVerticalGroup(
            panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelChartsLayout.createSequentialGroup()
                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(labelTrendingSongs)
                                                                                                            .addComponent(buttonListenOnYoutube, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(labelSong1Charts)
                                                                                                            .addComponent(labelHeart1Charts))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(labelSong2Charts)
                                                                                                            .addComponent(labelHeart2Charts))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(labelSong3Charts))
                                                                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                                                        .addComponent(labelHeart3Charts)))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(labelSong4Charts))
                                                                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(labelHeart4Charts)))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(labelSong5Charts, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addComponent(labelHeart5Charts, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(labelSong6Charts, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addComponent(labelHeart6Charts, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                            .addComponent(labelSong7Charts, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addComponent(labelHeart7Charts, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(labelSong8Charts))
                                                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                                        .addComponent(labelHeart8Charts)))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(labelSong9Charts))
                                                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(labelHeart9Charts)))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(labelSong10Charts))
                                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                                        .addComponent(buttonViewCharts, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(labelHeart10Charts)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(labelSong11Charts, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(labelHeart11Charts, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(labelSong12Charts))
                                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(labelHeart12Charts)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(labelSong13Charts))
                                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(labelHeart13Charts)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelSong14Charts))
                                            .addGroup(panelChartsLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(labelHeart14Charts)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelSong15Charts))
                                    .addGroup(panelChartsLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(labelHeart15Charts)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSong16Charts))
                            .addGroup(panelChartsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelHeart16Charts)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSong17Charts))
                    .addGroup(panelChartsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelHeart17Charts)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSong18Charts)
                    .addComponent(labelHeart18Charts))
                .addGap(12, 12, 12))
        );

        panelPlaylist.setBackground(new java.awt.Color(28, 28, 28));

        labelCreatePlaylist.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelCreatePlaylist.setForeground(new java.awt.Color(255, 255, 255));
        labelCreatePlaylist.setText("Create a Playlist:");

        formattedTextFieldPlaylistName.setText("Type here...");
        formattedTextFieldPlaylistName.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        formattedTextFieldPlaylistName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formattedTextFieldPlaylistNameMouseClicked(evt);
            }
        });

        formattedTextFieldPlaylistGenre.setText("Type here...");
        formattedTextFieldPlaylistGenre.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        formattedTextFieldPlaylistGenre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formattedTextFieldPlaylistGenreMouseClicked(evt);
            }
        });

        tablePlaylistsBig.setBackground(new java.awt.Color(28, 28, 28));
        tablePlaylistsBig.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        tablePlaylistsBig.setForeground(new java.awt.Color(255, 255, 255));
        tablePlaylistsBig.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title", "YouTube Link"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablePlaylistsBig.setGridColor(new java.awt.Color(255, 255, 255));
        tablePlaylistsBig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePlaylistsBigMouseClicked(evt);
            }
        });
        scrollPanePlaylist.setViewportView(tablePlaylistsBig);

        labelPlaylistName.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        labelPlaylistName.setForeground(new java.awt.Color(255, 255, 255));
        labelPlaylistName.setText("Playlist name:");

        labelPlaylistGenre.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        labelPlaylistGenre.setForeground(new java.awt.Color(255, 255, 255));
        labelPlaylistGenre.setText("Playlist genre:");

        buttonCreatePlaylist.setBackground(new java.awt.Color(28, 28, 28));
        buttonCreatePlaylist.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonCreatePlaylist.setForeground(new java.awt.Color(255, 255, 255));
        buttonCreatePlaylist.setText("Create \nPlaylist");
        buttonCreatePlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCreatePlaylistMouseClicked(evt);
            }
        });

        labelCommend.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        labelCommend.setForeground(new java.awt.Color(255, 255, 255));
        labelCommend.setText("You can edit your Playlist's links below (they must be separated with \",\"):");

        formattedTextFieldSongLinks.setText("The links will appear here...");
        formattedTextFieldSongLinks.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N

        buttonViewPlaylists.setBackground(new java.awt.Color(28, 28, 28));
        buttonViewPlaylists.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonViewPlaylists.setForeground(new java.awt.Color(255, 255, 255));
        buttonViewPlaylists.setText("View Created Playlists");
        buttonViewPlaylists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonViewPlaylistsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelPlaylistLayout = new javax.swing.GroupLayout(panelPlaylist);
        panelPlaylist.setLayout(panelPlaylistLayout);
        panelPlaylistLayout.setHorizontalGroup(
            panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlaylistLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanePlaylist)
                    .addGroup(panelPlaylistLayout.createSequentialGroup()
                        .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPlaylistLayout.createSequentialGroup()
                                .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPlaylistName)
                                    .addComponent(labelPlaylistGenre))
                                .addGap(18, 18, 18)
                                .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(formattedTextFieldPlaylistGenre, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(formattedTextFieldPlaylistName))
                                .addGap(18, 18, 18)
                                .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelCommend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(formattedTextFieldSongLinks)))
                            .addComponent(labelCreatePlaylist))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addComponent(buttonViewPlaylists)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCreatePlaylist)))
                .addContainerGap())
        );
        panelPlaylistLayout.setVerticalGroup(
            panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlaylistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPlaylistLayout.createSequentialGroup()
                        .addComponent(labelCreatePlaylist)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formattedTextFieldPlaylistName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPlaylistName)
                            .addComponent(labelCommend))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formattedTextFieldPlaylistGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPlaylistGenre)
                            .addComponent(formattedTextFieldSongLinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPlaylistLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(panelPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonViewPlaylists, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonCreatePlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(scrollPanePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelPlayPlaylist.setBackground(new java.awt.Color(28, 28, 28));

        labelBackPlayPlaylist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Go_back.png"))); // NOI18N
        labelBackPlayPlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackPlayPlaylistMouseClicked(evt);
            }
        });

        tablePlayPlaylist.setBackground(new java.awt.Color(28, 28, 28));
        tablePlayPlaylist.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        tablePlayPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        tablePlayPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Genre", "YouTube Links"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablePlayPlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePlayPlaylistMouseClicked(evt);
            }
        });
        scrollPanePlayPlaylist.setViewportView(tablePlayPlaylist);

        labelSelectPlaylistToPlay.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelSelectPlaylistToPlay.setForeground(new java.awt.Color(255, 255, 255));
        labelSelectPlaylistToPlay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSelectPlaylistToPlay.setText("Select a Playlist to begin playback");

        buttonDeleteAllPlaylists.setBackground(new java.awt.Color(28, 28, 28));
        buttonDeleteAllPlaylists.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonDeleteAllPlaylists.setForeground(new java.awt.Color(255, 255, 255));
        buttonDeleteAllPlaylists.setText("Delete all entries");
        buttonDeleteAllPlaylists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDeleteAllPlaylistsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelPlayPlaylistLayout = new javax.swing.GroupLayout(panelPlayPlaylist);
        panelPlayPlaylist.setLayout(panelPlayPlaylistLayout);
        panelPlayPlaylistLayout.setHorizontalGroup(
            panelPlayPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayPlaylistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPlayPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanePlayPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 1179, Short.MAX_VALUE)
                    .addGroup(panelPlayPlaylistLayout.createSequentialGroup()
                        .addComponent(labelBackPlayPlaylist)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelSelectPlaylistToPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonDeleteAllPlaylists)))
                .addContainerGap())
        );
        panelPlayPlaylistLayout.setVerticalGroup(
            panelPlayPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayPlaylistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPlayPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPlayPlaylistLayout.createSequentialGroup()
                        .addGroup(panelPlayPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelBackPlayPlaylist)
                            .addComponent(labelSelectPlaylistToPlay))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPlayPlaylistLayout.createSequentialGroup()
                        .addComponent(buttonDeleteAllPlaylists, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(scrollPanePlayPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTopArtists.setBackground(new java.awt.Color(28, 28, 28));

        labelTopArtistsYoutube.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelTopArtistsYoutube.setForeground(new java.awt.Color(255, 255, 255));
        labelTopArtistsYoutube.setText("Top Artists | YouTube");

        labelArtist1TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist1TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist1TopArtists.setText("Artist 1");
        labelArtist1TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist1TopArtistsMouseClicked(evt);
            }
        });

        labelArtist2TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist2TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist2TopArtists.setText("Artist 2");
        labelArtist2TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist2TopArtistsMouseClicked(evt);
            }
        });

        labelArtist3TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist3TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist3TopArtists.setText("Artist 3");
        labelArtist3TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist3TopArtistsMouseClicked(evt);
            }
        });

        labelArtist4TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist4TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist4TopArtists.setText("Artist 4");
        labelArtist4TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist4TopArtistsMouseClicked(evt);
            }
        });

        labelArtist5TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist5TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist5TopArtists.setText("Artist 5");
        labelArtist5TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist5TopArtistsMouseClicked(evt);
            }
        });

        labelArtist6TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist6TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist6TopArtists.setText("Artist 6");
        labelArtist6TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist6TopArtistsMouseClicked(evt);
            }
        });

        labelArtist7TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist7TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist7TopArtists.setText("Artist 7");
        labelArtist7TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist7TopArtistsMouseClicked(evt);
            }
        });

        labelArtist8TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist8TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist8TopArtists.setText("Artist 8");
        labelArtist8TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist8TopArtistsMouseClicked(evt);
            }
        });

        labelArtist9TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist9TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist9TopArtists.setText("Artist 9");
        labelArtist9TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist9TopArtistsMouseClicked(evt);
            }
        });

        labelArtist10TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist10TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist10TopArtists.setText("Artist 10");
        labelArtist10TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist10TopArtistsMouseClicked(evt);
            }
        });

        labelArtist11TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist11TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist11TopArtists.setText("Artist 11");
        labelArtist11TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist11TopArtistsMouseClicked(evt);
            }
        });

        labelArtist12TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist12TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist12TopArtists.setText("Artist 12");
        labelArtist12TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist12TopArtistsMouseClicked(evt);
            }
        });

        labelArtist13TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist13TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist13TopArtists.setText("Artist 13");
        labelArtist13TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist13TopArtistsMouseClicked(evt);
            }
        });

        labelArtist14TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist14TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist14TopArtists.setText("Artist 14");
        labelArtist14TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist14TopArtistsMouseClicked(evt);
            }
        });

        labelArtist15TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist15TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist15TopArtists.setText("Artist 15");
        labelArtist15TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist15TopArtistsMouseClicked(evt);
            }
        });

        labelArtist16TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist16TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist16TopArtists.setText("Artist 16");
        labelArtist16TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist16TopArtistsMouseClicked(evt);
            }
        });

        labelArtist17TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist17TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist17TopArtists.setText("Artist 17");
        labelArtist17TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist17TopArtistsMouseClicked(evt);
            }
        });

        labelArtist18TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist18TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist18TopArtists.setText("Artist 18");
        labelArtist18TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist18TopArtistsMouseClicked(evt);
            }
        });

        labelArtist19TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist19TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist19TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist19TopArtists.setText("Artist 19");
        labelArtist19TopArtists.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelArtist19TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist19TopArtistsMouseClicked(evt);
            }
        });

        labelArtist20TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist20TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist20TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist20TopArtists.setText("Artist 20");
        labelArtist20TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist20TopArtistsMouseClicked(evt);
            }
        });

        labelArtist21TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist21TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist21TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist21TopArtists.setText("Artist 21");
        labelArtist21TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist21TopArtistsMouseClicked(evt);
            }
        });

        labelArtist22TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist22TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist22TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist22TopArtists.setText("Artist 22");
        labelArtist22TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist22TopArtistsMouseClicked(evt);
            }
        });

        labelArtist23TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist23TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist23TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist23TopArtists.setText("Artist 23");
        labelArtist23TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist23TopArtistsMouseClicked(evt);
            }
        });

        labelArtist24TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist24TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist24TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist24TopArtists.setText("Artist 24");
        labelArtist24TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist24TopArtistsMouseClicked(evt);
            }
        });

        labelArtist25TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist25TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist25TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist25TopArtists.setText("Artist 25");
        labelArtist25TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist25TopArtistsMouseClicked(evt);
            }
        });

        labelArtist26TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist26TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist26TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist26TopArtists.setText("Artist 26");
        labelArtist26TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist26TopArtistsMouseClicked(evt);
            }
        });

        labelArtist27TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist27TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist27TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist27TopArtists.setText("Artist 27");
        labelArtist27TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist27TopArtistsMouseClicked(evt);
            }
        });

        labelArtist28TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist28TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist28TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist28TopArtists.setText("Artist 28");
        labelArtist28TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist28TopArtistsMouseClicked(evt);
            }
        });

        labelArtist29TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist29TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist29TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist29TopArtists.setText("Artist 29");
        labelArtist29TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist29TopArtistsMouseClicked(evt);
            }
        });

        labelArtist30TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist30TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist30TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist30TopArtists.setText("Artist 30");
        labelArtist30TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist30TopArtistsMouseClicked(evt);
            }
        });

        labelArtist31TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist31TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist31TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist31TopArtists.setText("Artist 31");
        labelArtist31TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist31TopArtistsMouseClicked(evt);
            }
        });

        labelArtist32TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist32TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist32TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist32TopArtists.setText("Artist 32");
        labelArtist32TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist32TopArtistsMouseClicked(evt);
            }
        });

        labelArtist33TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist33TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist33TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist33TopArtists.setText("Artist 33");
        labelArtist33TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist33TopArtistsMouseClicked(evt);
            }
        });

        labelArtist34TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist34TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist34TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist34TopArtists.setText("Artist 34");
        labelArtist34TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist34TopArtistsMouseClicked(evt);
            }
        });

        labelArtist35TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist35TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist35TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist35TopArtists.setText("Artist 35");
        labelArtist35TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist35TopArtistsMouseClicked(evt);
            }
        });

        labelArtist36TopArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelArtist36TopArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelArtist36TopArtists.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArtist36TopArtists.setText("Artist 36");
        labelArtist36TopArtists.setToolTipText("");
        labelArtist36TopArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtist36TopArtistsMouseClicked(evt);
            }
        });

        labelHeart1Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart1Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart1ArtistsMouseClicked(evt);
            }
        });

        labelHeart2Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart2Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart2ArtistsMouseClicked(evt);
            }
        });

        labelHeart3Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart3Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart3ArtistsMouseClicked(evt);
            }
        });

        labelHeart4Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart4Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart4ArtistsMouseClicked(evt);
            }
        });

        labelHeart5Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart5Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart5ArtistsMouseClicked(evt);
            }
        });

        labelHeart6Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart6Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart6ArtistsMouseClicked(evt);
            }
        });

        labelHeart7Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart7Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart7ArtistsMouseClicked(evt);
            }
        });

        labelHeart8Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart8Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart8ArtistsMouseClicked(evt);
            }
        });

        labelHeart9Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart9Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart9ArtistsMouseClicked(evt);
            }
        });

        labelHeart10Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart10Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart10ArtistsMouseClicked(evt);
            }
        });

        labelHeart11Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart11Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart11ArtistsMouseClicked(evt);
            }
        });

        labelHeart12Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart12Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart12ArtistsMouseClicked(evt);
            }
        });

        labelHeart13Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart13Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart13ArtistsMouseClicked(evt);
            }
        });

        labelHeart14Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart14Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart14ArtistsMouseClicked(evt);
            }
        });

        labelHeart15Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart15Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart15ArtistsMouseClicked(evt);
            }
        });

        labelHeart16Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart16Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart16ArtistsMouseClicked(evt);
            }
        });

        labelHeart17Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart17Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart17ArtistsMouseClicked(evt);
            }
        });

        labelHeart18Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart18Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart18ArtistsMouseClicked(evt);
            }
        });

        labelHeart19Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart19Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart19ArtistsMouseClicked(evt);
            }
        });

        labelHeart20Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart20Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart20ArtistsMouseClicked(evt);
            }
        });

        labelHeart21Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart21Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart21ArtistsMouseClicked(evt);
            }
        });

        labelHeart22Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart22Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart22ArtistsMouseClicked(evt);
            }
        });

        labelHeart23Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart23Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart23ArtistsMouseClicked(evt);
            }
        });

        labelHeart24Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart24Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart24ArtistsMouseClicked(evt);
            }
        });

        labelHeart25Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart25Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart25ArtistsMouseClicked(evt);
            }
        });

        labelHeart26Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart26Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart26ArtistsMouseClicked(evt);
            }
        });

        labelHeart27Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart27Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart27ArtistsMouseClicked(evt);
            }
        });

        labelHeart28Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart28Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart28ArtistsMouseClicked(evt);
            }
        });

        labelHeart29Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart29Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart29ArtistsMouseClicked(evt);
            }
        });

        labelHeart30Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart30Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart30ArtistsMouseClicked(evt);
            }
        });

        labelHeart31Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart31Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart31ArtistsMouseClicked(evt);
            }
        });

        labelHeart32Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart32Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart32ArtistsMouseClicked(evt);
            }
        });

        labelHeart33Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart33Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart33ArtistsMouseClicked(evt);
            }
        });

        labelHeart34Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart34Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart34ArtistsMouseClicked(evt);
            }
        });

        labelHeart35Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart35Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart35ArtistsMouseClicked(evt);
            }
        });

        labelHeart36Artists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart36Artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart36ArtistsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelTopArtistsLayout = new javax.swing.GroupLayout(panelTopArtists);
        panelTopArtists.setLayout(panelTopArtistsLayout);
        panelTopArtistsLayout.setHorizontalGroup(
            panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist1TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart1Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist2TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart2Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist3TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart3Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist4TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart4Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist5TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart5Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist6TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart6Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist7TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart7Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist8TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart8Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist9TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart9Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist10TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart10Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist11TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart11Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist12TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart12Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist13TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart13Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist14TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart14Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist15TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart15Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist16TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart16Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist17TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart17Artists))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addComponent(labelArtist18TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart18Artists)))
                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelArtist34TopArtists, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelArtist35TopArtists, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                                        .addComponent(labelArtist36TopArtists)))
                                .addGap(18, 18, 18)
                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelHeart34Artists)
                                        .addComponent(labelHeart35Artists, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(labelHeart36Artists, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist19TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart19Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist21TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart21Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist22TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart22Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist23TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart23Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist24TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart24Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist25TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart25Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist26TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart26Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist27TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart27Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist28TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart28Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist29TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart29Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist30TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart30Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist31TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart31Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist32TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart32Artists))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopArtistsLayout.createSequentialGroup()
                                        .addComponent(labelArtist33TopArtists)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelHeart33Artists))))
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelArtist20TopArtists)
                                .addGap(18, 18, 18)
                                .addComponent(labelHeart20Artists))))
                    .addComponent(labelTopArtistsYoutube))
                .addContainerGap())
        );
        panelTopArtistsLayout.setVerticalGroup(
            panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                                                                        .addComponent(labelTopArtistsYoutube)
                                                                                                        .addGap(18, 18, 18)
                                                                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(labelArtist19TopArtists)
                                                                                                            .addComponent(labelHeart1Artists, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(labelHeart19Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(labelArtist1TopArtists))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(labelArtist2TopArtists)
                                                                                                            .addComponent(labelHeart2Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(labelHeart20Artists, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                            .addComponent(labelArtist20TopArtists, javax.swing.GroupLayout.Alignment.LEADING))
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                            .addComponent(labelArtist3TopArtists)
                                                                                                            .addComponent(labelArtist21TopArtists)
                                                                                                            .addComponent(labelHeart21Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                    .addComponent(labelHeart3Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                    .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addComponent(labelArtist22TopArtists, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                        .addComponent(labelArtist4TopArtists))
                                                                                                    .addComponent(labelHeart22Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                            .addComponent(labelHeart4Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addComponent(labelArtist5TopArtists)
                                                                                            .addComponent(labelHeart23Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                            .addComponent(labelArtist23TopArtists)))
                                                                                    .addComponent(labelHeart5Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addComponent(labelArtist6TopArtists)
                                                                                    .addComponent(labelHeart24Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(labelArtist24TopArtists)))
                                                                            .addComponent(labelHeart6Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addComponent(labelArtist7TopArtists)
                                                                            .addComponent(labelHeart25Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(labelArtist25TopArtists)))
                                                                    .addComponent(labelHeart7Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(labelArtist8TopArtists)
                                                                    .addComponent(labelHeart26Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(labelArtist26TopArtists)))
                                                            .addComponent(labelHeart8Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(labelArtist9TopArtists)
                                                            .addComponent(labelHeart27Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(labelArtist27TopArtists)))
                                                    .addComponent(labelHeart9Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(labelArtist10TopArtists)
                                                    .addComponent(labelHeart28Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(labelArtist28TopArtists)))
                                            .addComponent(labelHeart10Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelArtist11TopArtists, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(labelHeart11Artists, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelArtist12TopArtists))
                                            .addGroup(panelTopArtistsLayout.createSequentialGroup()
                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelHeart29Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(labelArtist29TopArtists))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelHeart30Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(labelArtist30TopArtists)))))
                                    .addComponent(labelHeart12Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelArtist31TopArtists, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelArtist13TopArtists))
                                    .addComponent(labelHeart31Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelHeart13Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelArtist32TopArtists)
                            .addComponent(labelArtist14TopArtists)
                            .addComponent(labelHeart14Artists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelHeart32Artists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelArtist15TopArtists)
                            .addComponent(labelHeart33Artists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelArtist33TopArtists)))
                    .addComponent(labelHeart15Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelArtist34TopArtists)
                    .addComponent(labelArtist16TopArtists)
                    .addComponent(labelHeart16Artists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelHeart34Artists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelArtist35TopArtists)
                    .addComponent(labelArtist17TopArtists)
                    .addComponent(labelHeart17Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeart35Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelArtist18TopArtists)
                        .addComponent(labelHeart18Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelArtist36TopArtists)
                    .addComponent(labelHeart36Artists, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelFavoriteSongs.setBackground(new java.awt.Color(28, 28, 28));

        labelBackFavoriteSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Go_back.png"))); // NOI18N
        labelBackFavoriteSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackFavoriteSongsMouseClicked(evt);
            }
        });

        labelReturnHomeFavoriteSongs.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelReturnHomeFavoriteSongs.setForeground(new java.awt.Color(255, 255, 255));
        labelReturnHomeFavoriteSongs.setText("Return Home");
        labelReturnHomeFavoriteSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelReturnHomeFavoriteSongsMouseClicked(evt);
            }
        });

        tableFavoriteSongs.setBackground(new java.awt.Color(28, 28, 28));
        tableFavoriteSongs.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        tableFavoriteSongs.setForeground(new java.awt.Color(255, 255, 255));
        tableFavoriteSongs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Song Name", "YouTube Link"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableFavoriteSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFavoriteSongsMouseClicked(evt);
            }
        });
        scrollPaneFavoriteSongs.setViewportView(tableFavoriteSongs);

        buttonListenOnYoutubeFavoriteSongs.setBackground(new java.awt.Color(28, 28, 28));
        buttonListenOnYoutubeFavoriteSongs.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        buttonListenOnYoutubeFavoriteSongs.setForeground(new java.awt.Color(255, 255, 255));
        buttonListenOnYoutubeFavoriteSongs.setText("Listen on YouTube");
        buttonListenOnYoutubeFavoriteSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonListenOnYoutubeFavoriteSongsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelFavoriteSongsLayout = new javax.swing.GroupLayout(panelFavoriteSongs);
        panelFavoriteSongs.setLayout(panelFavoriteSongsLayout);
        panelFavoriteSongsLayout.setHorizontalGroup(
            panelFavoriteSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFavoriteSongsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFavoriteSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneFavoriteSongs, javax.swing.GroupLayout.DEFAULT_SIZE, 1179, Short.MAX_VALUE)
                    .addGroup(panelFavoriteSongsLayout.createSequentialGroup()
                        .addComponent(labelBackFavoriteSongs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelReturnHomeFavoriteSongs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonListenOnYoutubeFavoriteSongs)))
                .addContainerGap())
        );
        panelFavoriteSongsLayout.setVerticalGroup(
            panelFavoriteSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFavoriteSongsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFavoriteSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelBackFavoriteSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelReturnHomeFavoriteSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonListenOnYoutubeFavoriteSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneFavoriteSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelFavoriteArtists.setBackground(new java.awt.Color(28, 28, 28));

        labelBckFavoriteArtists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Go_back.png"))); // NOI18N
        labelBckFavoriteArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBckFavoriteArtistsMouseClicked(evt);
            }
        });

        labelReturnHomeFavoriteArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelReturnHomeFavoriteArtists.setForeground(new java.awt.Color(255, 255, 255));
        labelReturnHomeFavoriteArtists.setText("Return Home");
        labelReturnHomeFavoriteArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelReturnHomeFavoriteArtistsMouseClicked(evt);
            }
        });

        tableFavoriteArtists.setBackground(new java.awt.Color(28, 28, 28));
        tableFavoriteArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        tableFavoriteArtists.setForeground(new java.awt.Color(255, 255, 255));
        tableFavoriteArtists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Artist Name", "YouTube Links"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableFavoriteArtists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFavoriteArtistsMouseClicked(evt);
            }
        });
        scrollPaneFavoriteArtists.setViewportView(tableFavoriteArtists);

        buttonListenOnYoutubeFavoriteArtists.setBackground(new java.awt.Color(28, 28, 28));
        buttonListenOnYoutubeFavoriteArtists.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        buttonListenOnYoutubeFavoriteArtists.setForeground(new java.awt.Color(255, 255, 255));
        buttonListenOnYoutubeFavoriteArtists.setText("Listen on YouTube");

        javax.swing.GroupLayout panelFavoriteArtistsLayout = new javax.swing.GroupLayout(panelFavoriteArtists);
        panelFavoriteArtists.setLayout(panelFavoriteArtistsLayout);
        panelFavoriteArtistsLayout.setHorizontalGroup(
            panelFavoriteArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFavoriteArtistsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFavoriteArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneFavoriteArtists, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                    .addGroup(panelFavoriteArtistsLayout.createSequentialGroup()
                        .addComponent(labelBckFavoriteArtists)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelReturnHomeFavoriteArtists)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonListenOnYoutubeFavoriteArtists)))
                .addContainerGap())
        );
        panelFavoriteArtistsLayout.setVerticalGroup(
            panelFavoriteArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFavoriteArtistsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFavoriteArtistsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelBckFavoriteArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelReturnHomeFavoriteArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonListenOnYoutubeFavoriteArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneFavoriteArtists, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelSearchSongs.setBackground(new java.awt.Color(28, 28, 28));

        textFieldSearchSongs.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        textFieldSearchSongs.setText("Search songs & videos on the web");
        textFieldSearchSongs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        textFieldSearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldSearchSongsMouseClicked(evt);
            }
        });
        textFieldSearchSongs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchSongsActionPerformed(evt);
            }
        });

        labelSearchIconSearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Search.png"))); // NOI18N

        buttonSearchInformation.setBackground(new java.awt.Color(28, 28, 28));
        buttonSearchInformation.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        buttonSearchInformation.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchInformation.setText("Search lyrics on Genius & information on Wikipedia");
        buttonSearchInformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSearchInformationMouseClicked(evt);
            }
        });

        labelSongSearchResult1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult1.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult1.setText("Search result 1");
        labelSongSearchResult1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult1MouseClicked(evt);
            }
        });

        labelSongSearchResult2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult2.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult2.setText("Search result 2");
        labelSongSearchResult2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult2MouseClicked(evt);
            }
        });

        labelSongSearchResult3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult3.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult3.setText("Search result 3");
        labelSongSearchResult3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult3MouseClicked(evt);
            }
        });

        labelSongSearchResult4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult4.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult4.setText("Search result 4");
        labelSongSearchResult4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult4MouseClicked(evt);
            }
        });

        labelSongSearchResult5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult5.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult5.setText("Search result 5");
        labelSongSearchResult5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult5MouseClicked(evt);
            }
        });

        labelSongSearchResult6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult6.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult6.setText("Search result 6");
        labelSongSearchResult6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult6MouseClicked(evt);
            }
        });

        labelSongSearchResult7.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult7.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult7.setText("Search result 7");
        labelSongSearchResult7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult7MouseClicked(evt);
            }
        });

        labelSongSearchResult8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult8.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult8.setText("Search result 8");
        labelSongSearchResult8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult8MouseClicked(evt);
            }
        });

        labelSongSearchResult9.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult9.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult9.setText("Search result 9");
        labelSongSearchResult9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult9MouseClicked(evt);
            }
        });

        labelSongSearchResult10.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult10.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult10.setText("Search result 10");
        labelSongSearchResult10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult10MouseClicked(evt);
            }
        });

        labelSongSearchResult11.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult11.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult11.setText("Search result 11");
        labelSongSearchResult11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult11MouseClicked(evt);
            }
        });

        labelSongSearchResult12.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult12.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult12.setText("Search result 12");
        labelSongSearchResult12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult12MouseClicked(evt);
            }
        });

        labelSongSearchResult13.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult13.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult13.setText("Search result 13");
        labelSongSearchResult13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult13MouseClicked(evt);
            }
        });

        labelSongSearchResult14.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult14.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult14.setText("Search result 14");
        labelSongSearchResult14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult14MouseClicked(evt);
            }
        });

        labelSongSearchResult15.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult15.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult15.setText("Search result 15");
        labelSongSearchResult15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult15MouseClicked(evt);
            }
        });

        labelSongSearchResult16.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult16.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult16.setText("Search result 16");
        labelSongSearchResult16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult16MouseClicked(evt);
            }
        });

        labelSongSearchResult17.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSongSearchResult17.setForeground(new java.awt.Color(255, 255, 255));
        labelSongSearchResult17.setText("Search result 17");
        labelSongSearchResult17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongSearchResult17MouseClicked(evt);
            }
        });

        labelSearchSongViewCount1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount1.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount1.setText("View count 1");

        labelSearchSongViewCount2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount2.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount2.setText("View count 2");

        labelSearchSongViewCount3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount3.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount3.setText("View count 3");

        labelSearchSongViewCount4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount4.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount4.setText("View count 4");

        labelSearchSongViewCount5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount5.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount5.setText("View count 5");

        labelSearchSongViewCount6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount6.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount6.setText("View count 6");

        labelSearchSongViewCount7.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount7.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount7.setText("View count 7");

        labelSearchSongViewCount8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount8.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount8.setText("View count 8");

        labelSearchSongViewCount9.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount9.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount9.setText("View count 9");

        labelSearchSongViewCount10.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount10.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount10.setText("View count 10");

        labelSearchSongViewCount11.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount11.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount11.setText("View count 11");

        labelSearchSongViewCount12.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount12.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount12.setText("View count 12");

        labelSearchSongViewCount13.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount13.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount13.setText("View count 13");

        labelSearchSongViewCount14.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount14.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount14.setText("View count 14");

        labelSearchSongViewCount15.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount15.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount15.setText("View count 15");

        labelSearchSongViewCount16.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount16.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount16.setText("View count 16");

        labelSearchSongViewCount17.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongViewCount17.setForeground(new java.awt.Color(255, 204, 204));
        labelSearchSongViewCount17.setText("View count 17");

        labelSearchSongsDate1.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate1.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate1.setText("Date 1");

        labelSearchSongsDate2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate2.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate2.setText("Date 2");

        labelSearchSongsDate3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate3.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate3.setText("Date 3");

        labelSearchSongsDate4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate4.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate4.setText("Date 4");

        labelSearchSongsDate5.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate5.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate5.setText("Date 5");
        labelSearchSongsDate5.setToolTipText("");

        labelSearchSongsDate6.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate6.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate6.setText("Date 6");
        labelSearchSongsDate6.setToolTipText("");

        labelSearchSongsDate7.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate7.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate7.setText("Date 7");
        labelSearchSongsDate7.setToolTipText("");

        labelSearchSongsDate8.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate8.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate8.setText("Date 8");
        labelSearchSongsDate8.setToolTipText("");

        labelSearchSongsDate9.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate9.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate9.setText("Date 9");
        labelSearchSongsDate9.setToolTipText("");

        labelSearchSongsDate10.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate10.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate10.setText("Date 10");
        labelSearchSongsDate10.setToolTipText("");

        labelSearchSongsDate11.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate11.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate11.setText("Date 11");
        labelSearchSongsDate11.setToolTipText("");

        labelSearchSongsDate12.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate12.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate12.setText("Date 12");
        labelSearchSongsDate12.setToolTipText("");

        labelSearchSongsDate13.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate13.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate13.setText("Date 13");
        labelSearchSongsDate13.setToolTipText("");

        labelSearchSongsDate14.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate14.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate14.setText("Date 14");
        labelSearchSongsDate14.setToolTipText("");

        labelSearchSongsDate15.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate15.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate15.setText("Date 15");
        labelSearchSongsDate15.setToolTipText("");

        labelSearchSongsDate16.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate16.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate16.setText("Date 16");
        labelSearchSongsDate16.setToolTipText("");

        labelSearchSongsDate17.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchSongsDate17.setForeground(new java.awt.Color(204, 255, 204));
        labelSearchSongsDate17.setText("Date 17");
        labelSearchSongsDate17.setToolTipText("");

        labelHeart1SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart1SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart1SearchSongsMouseClicked(evt);
            }
        });

        labelHeart2SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart2SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart2SearchSongsMouseClicked(evt);
            }
        });

        labelHeart3SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart3SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart3SearchSongsMouseClicked(evt);
            }
        });

        labelHeart4SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart4SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart4SearchSongsMouseClicked(evt);
            }
        });

        labelHeart5SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart5SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart5SearchSongsMouseClicked(evt);
            }
        });

        labelHeart6SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart6SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart6SearchSongsMouseClicked(evt);
            }
        });

        labelHeart7SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart7SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart7SearchSongsMouseClicked(evt);
            }
        });

        labelHeart8SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart8SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart8SearchSongsMouseClicked(evt);
            }
        });

        labelHeart9SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart9SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart9SearchSongsMouseClicked(evt);
            }
        });

        labelHeart10SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart10SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart10SearchSongsMouseClicked(evt);
            }
        });

        labelHeart11SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart11SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart11SearchSongsMouseClicked(evt);
            }
        });

        labelHeart12SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart12SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart12SearchSongsMouseClicked(evt);
            }
        });

        labelHeart13SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart13SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart13SearchSongsMouseClicked(evt);
            }
        });

        labelHeart14SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart14SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart14SearchSongsMouseClicked(evt);
            }
        });

        labelHeart15SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart15SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart15SearchSongsMouseClicked(evt);
            }
        });

        labelHeart16SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart16SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart16SearchSongsMouseClicked(evt);
            }
        });

        labelHeart17SearchSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Liked_songs.png"))); // NOI18N
        labelHeart17SearchSongs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeart17SearchSongsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSearchSongsLayout = new javax.swing.GroupLayout(panelSearchSongs);
        panelSearchSongs.setLayout(panelSearchSongsLayout);
        panelSearchSongsLayout.setHorizontalGroup(
            panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchSongsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelSearchIconSearchSongs)
                    .addComponent(labelHeart1SearchSongs)
                    .addComponent(labelHeart2SearchSongs)
                    .addComponent(labelHeart3SearchSongs)
                    .addComponent(labelHeart4SearchSongs)
                    .addComponent(labelHeart5SearchSongs)
                    .addComponent(labelHeart6SearchSongs)
                    .addComponent(labelHeart7SearchSongs)
                    .addComponent(labelHeart8SearchSongs)
                    .addComponent(labelHeart9SearchSongs)
                    .addComponent(labelHeart10SearchSongs)
                    .addComponent(labelHeart11SearchSongs)
                    .addComponent(labelHeart12SearchSongs)
                    .addComponent(labelHeart13SearchSongs)
                    .addComponent(labelHeart14SearchSongs)
                    .addComponent(labelHeart15SearchSongs)
                    .addComponent(labelHeart16SearchSongs)
                    .addComponent(labelHeart17SearchSongs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(textFieldSearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSearchInformation))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult17)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount17)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate17))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult16)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount16)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate16))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult15)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount15)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate15))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult14)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount14)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate14))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult13)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount13)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate13))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult12)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount12)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate12))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult11)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount11)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate11))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult9)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount9)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate9))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult8)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount8)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate8))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult7)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount7)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate7))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult6)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount6)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate6))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult5)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount5)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate5))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult4)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount4)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate4))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult3)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount3)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate3))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult1)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount1)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate1))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult2)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount2)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate2))
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addComponent(labelSongSearchResult10)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongViewCount10)
                        .addGap(18, 18, 18)
                        .addComponent(labelSearchSongsDate10)))
                .addContainerGap(360, Short.MAX_VALUE))
        );
        panelSearchSongsLayout.setVerticalGroup(
            panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchSongsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                                                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                            .addGroup(panelSearchSongsLayout.createSequentialGroup()
                                                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                    .addComponent(labelSearchIconSearchSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                    .addComponent(textFieldSearchSongs)
                                                                                                    .addComponent(buttonSearchInformation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                    .addComponent(labelHeart1SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                    .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                        .addComponent(labelSongSearchResult1)
                                                                                                        .addComponent(labelSearchSongViewCount1)
                                                                                                        .addComponent(labelSearchSongsDate1)))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                    .addComponent(labelSongSearchResult2)
                                                                                                    .addComponent(labelSearchSongViewCount2)
                                                                                                    .addComponent(labelSearchSongsDate2)))
                                                                                            .addComponent(labelHeart2SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                            .addComponent(labelSongSearchResult3)
                                                                                            .addComponent(labelSearchSongViewCount3)
                                                                                            .addComponent(labelSearchSongsDate3)))
                                                                                    .addComponent(labelHeart3SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(labelSongSearchResult4)
                                                                                    .addComponent(labelSearchSongViewCount4)
                                                                                    .addComponent(labelSearchSongsDate4)))
                                                                            .addComponent(labelHeart4SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(labelSongSearchResult5)
                                                                            .addComponent(labelSearchSongViewCount5)
                                                                            .addComponent(labelSearchSongsDate5)))
                                                                    .addComponent(labelHeart5SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(labelSongSearchResult6)
                                                                        .addComponent(labelSearchSongViewCount6)
                                                                        .addComponent(labelSearchSongsDate6))
                                                                    .addComponent(labelHeart6SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(labelSongSearchResult7)
                                                                    .addComponent(labelSearchSongViewCount7)
                                                                    .addComponent(labelSearchSongsDate7)))
                                                            .addComponent(labelHeart7SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(labelSongSearchResult8)
                                                            .addComponent(labelSearchSongViewCount8)
                                                            .addComponent(labelSearchSongsDate8)))
                                                    .addComponent(labelHeart8SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(labelSongSearchResult9)
                                                    .addComponent(labelSearchSongViewCount9)
                                                    .addComponent(labelSearchSongsDate9)))
                                            .addComponent(labelHeart9SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelSongSearchResult10)
                                            .addComponent(labelSearchSongViewCount10)
                                            .addComponent(labelSearchSongsDate10)))
                                    .addComponent(labelHeart10SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelSongSearchResult11)
                                    .addComponent(labelSearchSongViewCount11)
                                    .addComponent(labelSearchSongsDate11)))
                            .addComponent(labelHeart11SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSongSearchResult12)
                            .addComponent(labelSearchSongViewCount12)
                            .addComponent(labelSearchSongsDate12)))
                    .addComponent(labelHeart12SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSongSearchResult13)
                        .addComponent(labelSearchSongViewCount13)
                        .addComponent(labelSearchSongsDate13))
                    .addComponent(labelHeart13SearchSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSongSearchResult14)
                        .addComponent(labelSearchSongViewCount14)
                        .addComponent(labelSearchSongsDate14))
                    .addComponent(labelHeart14SearchSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSongSearchResult15)
                        .addComponent(labelSearchSongViewCount15)
                        .addComponent(labelSearchSongsDate15))
                    .addComponent(labelHeart15SearchSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSongSearchResult16)
                        .addComponent(labelSearchSongViewCount16)
                        .addComponent(labelSearchSongsDate16))
                    .addComponent(labelHeart16SearchSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHeart17SearchSongs, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSearchSongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSongSearchResult17)
                        .addComponent(labelSearchSongViewCount17)
                        .addComponent(labelSearchSongsDate17)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        panelViewCompleteCharts.setBackground(new java.awt.Color(28, 28, 28));

        labelBackViewCompleteCharts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Go_back.png"))); // NOI18N
        labelBackViewCompleteCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackViewCompleteChartsMouseClicked(evt);
            }
        });

        labelReturnViewCompleteCharts.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelReturnViewCompleteCharts.setForeground(new java.awt.Color(255, 255, 255));
        labelReturnViewCompleteCharts.setText("Return");
        labelReturnViewCompleteCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelReturnViewCompleteChartsMouseClicked(evt);
            }
        });

        tableViewCompleteCharts.setBackground(new java.awt.Color(28, 28, 28));
        tableViewCompleteCharts.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        tableViewCompleteCharts.setForeground(new java.awt.Color(255, 255, 255));
        tableViewCompleteCharts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Song Name", "YouTube Link"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableViewCompleteCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViewCompleteChartsMouseClicked(evt);
            }
        });
        scrollPaneViewCompleteCharts.setViewportView(tableViewCompleteCharts);

        buttonListenOnYoutubeViewCompleteCharts.setBackground(new java.awt.Color(28, 28, 28));
        buttonListenOnYoutubeViewCompleteCharts.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        buttonListenOnYoutubeViewCompleteCharts.setForeground(new java.awt.Color(255, 255, 255));
        buttonListenOnYoutubeViewCompleteCharts.setText("Listen on YouTube");
        buttonListenOnYoutubeViewCompleteCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonListenOnYoutubeViewCompleteChartsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelViewCompleteChartsLayout = new javax.swing.GroupLayout(panelViewCompleteCharts);
        panelViewCompleteCharts.setLayout(panelViewCompleteChartsLayout);
        panelViewCompleteChartsLayout.setHorizontalGroup(
            panelViewCompleteChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewCompleteChartsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelViewCompleteChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneViewCompleteCharts, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                    .addGroup(panelViewCompleteChartsLayout.createSequentialGroup()
                        .addComponent(labelBackViewCompleteCharts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelReturnViewCompleteCharts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonListenOnYoutubeViewCompleteCharts)))
                .addContainerGap())
        );
        panelViewCompleteChartsLayout.setVerticalGroup(
            panelViewCompleteChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewCompleteChartsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelViewCompleteChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelBackViewCompleteCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelReturnViewCompleteCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonListenOnYoutubeViewCompleteCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneViewCompleteCharts, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelSearchProfiles.setBackground(new java.awt.Color(28, 28, 28));

        labelBackImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Go_back.png"))); // NOI18N
        labelBackImage.setText("jLabel1");
        labelBackImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackImageMouseClicked(evt);
            }
        });

        labelReturn.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelReturn.setForeground(new java.awt.Color(255, 255, 255));
        labelReturn.setText("Return");
        labelReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelReturnMouseClicked(evt);
            }
        });

        labelSearchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Search.png"))); // NOI18N
        labelSearchIcon.setText("jLabel1");

        textFieldSearch.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        textFieldSearch.setText("Search other profiles on Musically");
        textFieldSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldSearchMouseClicked(evt);
            }
        });
        textFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchActionPerformed(evt);
            }
        });

        labelSearchedProfile.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelSearchedProfile.setForeground(new java.awt.Color(255, 255, 255));
        labelSearchedProfile.setText("Searched Profile: ");

        labelFirstName.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelFirstName.setForeground(new java.awt.Color(255, 255, 255));
        labelFirstName.setText("No profile");

        tableProfiles.setBackground(new java.awt.Color(28, 28, 28));
        tableProfiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Playlist Name", "Genre", "Youtube Links"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProfiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProfilesMouseClicked(evt);
            }
        });
        scrollPanelSearchProfiles.setViewportView(tableProfiles);

        labelLastName.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        labelLastName.setForeground(new java.awt.Color(255, 255, 255));
        labelLastName.setText("Searched yet");

        javax.swing.GroupLayout panelSearchProfilesLayout = new javax.swing.GroupLayout(panelSearchProfiles);
        panelSearchProfiles.setLayout(panelSearchProfilesLayout);
        panelSearchProfilesLayout.setHorizontalGroup(
            panelSearchProfilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchProfilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchProfilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanelSearchProfiles)
                    .addGroup(panelSearchProfilesLayout.createSequentialGroup()
                        .addComponent(labelBackImage, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelSearchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)
                        .addComponent(labelSearchedProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSearchProfilesLayout.setVerticalGroup(
            panelSearchProfilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchProfilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSearchProfilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchProfilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelBackImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelReturn))
                    .addComponent(labelSearchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSearchProfilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSearchedProfile))
                    .addGroup(panelSearchProfilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(labelLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelSearchProfiles, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelMainDynamicLayout = new javax.swing.GroupLayout(panelMainDynamic);
        panelMainDynamic.setLayout(panelMainDynamicLayout);
        panelMainDynamicLayout.setHorizontalGroup(
            panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainDynamicLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelHome, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPlayPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTopArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelFavoriteSongs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainDynamicLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelFavoriteArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainDynamicLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelSearchSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainDynamicLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelViewCompleteCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainDynamicLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSearchProfiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        panelMainDynamicLayout.setVerticalGroup(
            panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHome, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelCharts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPlaylist, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPlayPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTopArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelFavoriteSongs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainDynamicLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelFavoriteArtists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainDynamicLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(panelSearchSongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(4, 4, 4)))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainDynamicLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(panelViewCompleteCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(15, 15, 15)))
            .addGroup(panelMainDynamicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainDynamicLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelSearchProfiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        panelPlayer.setBackground(new java.awt.Color(24, 24, 24));

        sliderVolume.setBackground(new java.awt.Color(24, 24, 24));
        sliderVolume.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N

        labelPlayerImage.setForeground(new java.awt.Color(255, 255, 255));
        labelPlayerImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPlayerImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Thumbnail.png"))); // NOI18N
        labelPlayerImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelPlayerImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelArtistName.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        labelArtistName.setForeground(new java.awt.Color(255, 255, 255));
        labelArtistName.setText("Artist name");
        labelArtistName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelArtistNameMouseClicked(evt);
            }
        });

        labelSongName.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        labelSongName.setForeground(new java.awt.Color(255, 255, 255));
        labelSongName.setText("Song name");
        labelSongName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSongNameMouseClicked(evt);
            }
        });

        labelPlay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Play_song.png"))); // NOI18N
        labelPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPlayMouseClicked(evt);
            }
        });

        labelNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Next_track.png"))); // NOI18N
        labelNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNextMouseClicked(evt);
            }
        });

        labelPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Previous_track.png"))); // NOI18N
        labelPrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPreviousMouseClicked(evt);
            }
        });

        labelShuffle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Shuffle.png"))); // NOI18N
        labelShuffle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelShuffleMouseClicked(evt);
            }
        });

        labelRepeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Repeat.png"))); // NOI18N
        labelRepeat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRepeatMouseClicked(evt);
            }
        });

        labelChooseLocalSong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/Add_local_song.png"))); // NOI18N
        labelChooseLocalSong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelChooseLocalSongMouseClicked(evt);
            }
        });

        labelpalette.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/color-palette (1).png"))); // NOI18N
        labelpalette.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelpaletteMouseClicked(evt);
            }
        });

        labelmood.setForeground(new java.awt.Color(255, 51, 102));
        labelmood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musically/images/angry-face.png"))); // NOI18N
        labelmood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelmoodMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelPlayerLayout = new javax.swing.GroupLayout(panelPlayer);
        panelPlayer.setLayout(panelPlayerLayout);
        panelPlayerLayout.setHorizontalGroup(
            panelPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPlayerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelSongName, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addComponent(labelArtistName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(labelShuffle)
                .addGap(18, 18, 18)
                .addComponent(labelPrevious)
                .addGap(18, 18, 18)
                .addComponent(labelPlay)
                .addGap(18, 18, 18)
                .addComponent(labelNext)
                .addGap(18, 18, 18)
                .addComponent(labelRepeat)
                .addGap(145, 145, 145)
                .addComponent(labelmood)
                .addGap(18, 18, 18)
                .addComponent(labelpalette, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelChooseLocalSong)
                .addGap(18, 18, 18)
                .addComponent(sliderVolume, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPlayerLayout.setVerticalGroup(
            panelPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayerLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(labelpalette, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelPlayerLayout.createSequentialGroup()
                .addGroup(panelPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPlayerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPrevious, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPlay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sliderVolume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPlayerImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelShuffle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelRepeat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelPlayerLayout.createSequentialGroup()
                                .addComponent(labelSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelArtistName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(labelChooseLocalSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelPlayerLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(labelmood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMainStatic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMainDynamic, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, Short.MAX_VALUE))
            .addComponent(panelPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMainDynamic, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(panelMainStatic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void labelSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSearchMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelSearchSongs);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelSearchMouseClicked

    private void labelChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelChartsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelCharts);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelChartsMouseClicked

    private void labelPlaylistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlaylistsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelPlaylist);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelPlaylistsMouseClicked

    private void labelTopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTopArtistsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelTopArtists);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelTopArtistsMouseClicked

    private void labelHomeImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHomeImageMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelHome);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelHomeImageMouseClicked

    private void labelSearchImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSearchImageMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelSearchSongs);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelSearchImageMouseClicked

    private void labelChartsImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelChartsImageMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelCharts);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelChartsImageMouseClicked

    private void labelPlaylistsImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlaylistsImageMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelPlaylist);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelPlaylistsImageMouseClicked

    private void labelTopArtistsImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTopArtistsImageMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelTopArtists);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelTopArtistsImageMouseClicked

    private void labelSong18ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong18ChartsMouseClicked
        openWebPage(play[18]);
    }//GEN-LAST:event_labelSong18ChartsMouseClicked

    private void labelSong17ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong17ChartsMouseClicked
        openWebPage(play[17]);
    }//GEN-LAST:event_labelSong17ChartsMouseClicked

    private void labelSong12ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong12ChartsMouseClicked
        openWebPage(play[12]);
    }//GEN-LAST:event_labelSong12ChartsMouseClicked

    private void labelSong16ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong16ChartsMouseClicked
        openWebPage(play[16]);
    }//GEN-LAST:event_labelSong16ChartsMouseClicked

    private void labelSong15ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong15ChartsMouseClicked
        openWebPage(play[15]);
    }//GEN-LAST:event_labelSong15ChartsMouseClicked

    private void labelSong14ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong14ChartsMouseClicked
        openWebPage(play[14]);
    }//GEN-LAST:event_labelSong14ChartsMouseClicked

    private void labelSong13ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong13ChartsMouseClicked
        openWebPage(play[13]);
    }//GEN-LAST:event_labelSong13ChartsMouseClicked

    private void labelSong10ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong10ChartsMouseClicked
        openWebPage(play[10]);
    }//GEN-LAST:event_labelSong10ChartsMouseClicked

    private void labelSong11ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong11ChartsMouseClicked
        openWebPage(play[11]);
    }//GEN-LAST:event_labelSong11ChartsMouseClicked

    private void labelSong9ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong9ChartsMouseClicked
        openWebPage(play[9]);
    }//GEN-LAST:event_labelSong9ChartsMouseClicked

    private void labelSong8ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong8ChartsMouseClicked
        openWebPage(play[8]);
    }//GEN-LAST:event_labelSong8ChartsMouseClicked

    private void labelSong7ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong7ChartsMouseClicked
        openWebPage(play[7]);
    }//GEN-LAST:event_labelSong7ChartsMouseClicked

    private void labelSong6ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong6ChartsMouseClicked
        openWebPage(play[6]);
    }//GEN-LAST:event_labelSong6ChartsMouseClicked

    private void labelSong5ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong5ChartsMouseClicked
        openWebPage(play[5]);
    }//GEN-LAST:event_labelSong5ChartsMouseClicked

    private void labelSong4ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong4ChartsMouseClicked
        openWebPage(play[4]);
    }//GEN-LAST:event_labelSong4ChartsMouseClicked

    private void labelSong3ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong3ChartsMouseClicked
        openWebPage(play[3]);
    }//GEN-LAST:event_labelSong3ChartsMouseClicked

    private void labelSong2ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong2ChartsMouseClicked
        openWebPage(play[2]);
    }//GEN-LAST:event_labelSong2ChartsMouseClicked

    private void labelSong1ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong1ChartsMouseClicked
        openWebPage(play[1]);
    }//GEN-LAST:event_labelSong1ChartsMouseClicked

    private void labelPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlayMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Play_song.png"));
            img2 = ImageIO.read(getClass().getResource("images/Pause.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i==0){
            i = 1;
            labelPlay.setIcon(new ImageIcon(img2));
            pau = 0;
            x = 1;
            labelLocalSongsImage.setEnabled(false);
            labelLocalSong.setEnabled(false);
            playpauseMusic();
        } else {
            i = 0;
            labelPlay.setIcon(new ImageIcon(img1));
            pau = 1;
            x = 0;
            labelLocalSongsImage.setEnabled(true);
            labelLocalSong.setEnabled(true);
            playpauseMusic();
        }  
    }//GEN-LAST:event_labelPlayMouseClicked

    private void textFieldGeniusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldGeniusMouseClicked
        textFieldGenius.setText("");
        textFieldWikipedia.setText("Search on Wikipedia");
        textFieldSearchSongs.setText("Search songs & videos on the web");
    }//GEN-LAST:event_textFieldGeniusMouseClicked

    private void textFieldGeniusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldGeniusActionPerformed
        editorPaneSearchResults.setEditable(false);
        String text1 = textFieldGenius.getText();
        String text2 = text1.replaceAll(" ","\\-").replaceAll("A", "a").replaceAll("B", "b").replaceAll("C", "c").replaceAll("D", "d").replaceAll("E", "e").replaceAll("F", "f").replaceAll("G", "g").replaceAll("H", "h").replaceAll("I", "i").replaceAll("J", "j").replaceAll("K", "k").replaceAll("L","l").replaceAll("M", "m").replaceAll("N", "n").replaceAll("O", "o").replaceAll("P", "p").replaceAll("Q", "q").replaceAll("R", "r").replaceAll("S", "s").replaceAll("T", "t").replaceAll("U", "u").replaceAll("V", "v").replaceAll("W", "w").replaceAll("X", "x").replaceAll("Y", "y").replaceAll("Z", "z");
        try {
            editorPaneSearchResults.setPage("https://genius.com/"+text2+"-lyrics");
        } catch (IOException e) {
            editorPaneSearchResults.setContentType("text/html");
            editorPaneSearchResults.setText("<html>Could not load</html>");
        }
    }//GEN-LAST:event_textFieldGeniusActionPerformed

    private void textFieldWikipediaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldWikipediaMouseClicked
        textFieldGenius.setText("Search lyrics on Genius");
        textFieldWikipedia.setText("");
        textFieldSearchSongs.setText("Search songs & videos on the web");
    }//GEN-LAST:event_textFieldWikipediaMouseClicked

    private void textFieldWikipediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldWikipediaActionPerformed
        editorPaneSearchResults.setEditable(false);
        String text1 = textFieldWikipedia.getText();
        String text2 = text1.replaceAll(" ","\\_").replaceAll("A", "a").replaceAll("B", "b").replaceAll("C", "c").replaceAll("D", "d").replaceAll("E", "e").replaceAll("F", "f").replaceAll("G", "g").replaceAll("H", "h").replaceAll("I", "i").replaceAll("J", "j").replaceAll("K", "k").replaceAll("L","l").replaceAll("M", "m").replaceAll("N", "n").replaceAll("O", "o").replaceAll("P", "p").replaceAll("Q", "q").replaceAll("R", "r").replaceAll("S", "s").replaceAll("T", "t").replaceAll("U", "u").replaceAll("V", "v").replaceAll("W", "w").replaceAll("X", "x").replaceAll("Y", "y").replaceAll("Z", "z");
        try {
            editorPaneSearchResults.setPage("https://en.wikipedia.org/wiki/"+text2);
        } catch (IOException e) {
            editorPaneSearchResults.setContentType("text/html");
            editorPaneSearchResults.setText("<html>Could not load</html>");
        }
    }//GEN-LAST:event_textFieldWikipediaActionPerformed

    private void formattedTextFieldPlaylistNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formattedTextFieldPlaylistNameMouseClicked
        formattedTextFieldPlaylistName.setText("");
    }//GEN-LAST:event_formattedTextFieldPlaylistNameMouseClicked

    private void formattedTextFieldPlaylistGenreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formattedTextFieldPlaylistGenreMouseClicked
        formattedTextFieldPlaylistGenre.setText("");
    }//GEN-LAST:event_formattedTextFieldPlaylistGenreMouseClicked

    private void buttonCreatePlaylistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCreatePlaylistMouseClicked
        String playlist_name = formattedTextFieldPlaylistName.getText();
        String playlist_genre = formattedTextFieldPlaylistGenre.getText();
        String playlist_links = formattedTextFieldSongLinks.getText();
        String sql5 = "INSERT INTO playlists(NAME,GENRE,YOUTUBE_LINKS,USERS_ID) VALUES ('"+playlist_name+"','"+playlist_genre+"','"+playlist_links+"','"+user_id+"');";
        try {
            st.executeUpdate(sql5);
            count=0;
            formattedTextFieldPlaylistName.setText("Type here...");
            formattedTextFieldPlaylistGenre.setText("Type here...");
            formattedTextFieldSongLinks.setText("The links will appear here...");
            JOptionPane.showMessageDialog(null, "Playlist created succesfully.");
            updateTables();
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCreatePlaylistMouseClicked

    private void labelShuffleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelShuffleMouseClicked
        
    }//GEN-LAST:event_labelShuffleMouseClicked

    private void labelRepeatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRepeatMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Repeat.png"));
            img2 = ImageIO.read(getClass().getResource("images/Repeat_on.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(j==0){
            j = 1;
            labelRepeat.setIcon(new ImageIcon(img2));
            rep = 1;
            repeatMusic();
        } else {
            j = 0;
            labelRepeat.setIcon(new ImageIcon(img1));
            rep = 0;
            repeatMusic();
        }
    }//GEN-LAST:event_labelRepeatMouseClicked

    private void labelLocalSongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLocalSongMouseClicked
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File musicPath = chooser.getSelectedFile();
        String filename = musicPath.getName().replaceAll(".*\\-\\ ","").replace(".wav","");
        String artistname = musicPath.getName().replaceAll("\\ \\-.*.wav","");
        labelSongName.setText(filename);
        labelArtistName.setText(artistname);
        try {
            audioInput = AudioSystem.getAudioInputStream(musicPath);
            playback_path[playback_counter] = musicPath.getAbsolutePath();
            playback_counter++;
            local_playback_counter = playback_counter;
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image img2 = null;
        try {
            img2 = ImageIO.read(getClass().getResource("images/Pause.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(x==0){
            try {
                beginMusic();
                x = 1;
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        i = 1;
        labelPlay.setIcon(new ImageIcon(img2));
        labelArtistName.setEnabled(true);
        labelSongName.setEnabled(true);
        labelPlay.setEnabled(true);
        labelNext.setEnabled(true);
        labelPrevious.setEnabled(true);
        labelShuffle.setEnabled(true);
        labelRepeat.setEnabled(true);
        labelLocalSongsImage.setEnabled(false);
        labelLocalSong.setEnabled(false);
        first_previous = 0;
    }//GEN-LAST:event_labelLocalSongMouseClicked

    private void labelLocalSongsImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLocalSongsImageMouseClicked
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File musicPath = chooser.getSelectedFile();
        String filename = musicPath.getName().replaceAll(".*\\-\\ ","").replace(".wav","");
        String artistname = musicPath.getName().replaceAll("\\ \\-.*.wav","");
        labelSongName.setText(filename);
        labelArtistName.setText(artistname);
        try {
            audioInput = AudioSystem.getAudioInputStream(musicPath);
            playback_path[playback_counter] = musicPath.getAbsolutePath();
            playback_counter++;
            local_playback_counter = playback_counter;
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image img2 = null;
        try {
            img2 = ImageIO.read(getClass().getResource("images/Pause.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(x==0){
            try {
                beginMusic();
                x = 1;
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        i = 1;
        labelPlay.setIcon(new ImageIcon(img2));
        labelArtistName.setEnabled(true);
        labelSongName.setEnabled(true);
        labelPlay.setEnabled(true);
        labelNext.setEnabled(true);
        labelPrevious.setEnabled(true);
        labelShuffle.setEnabled(true);
        labelRepeat.setEnabled(true);
        labelLocalSongsImage.setEnabled(false);
        labelLocalSong.setEnabled(false);
        first_previous = 0;
    }//GEN-LAST:event_labelLocalSongsImageMouseClicked

    private void buttonListenOnYoutubeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListenOnYoutubeMouseClicked
        openWebPage("http://www.youtube.com/watch_videos?video_ids="+small[1]+","+small[2]+","+small[3]+","+small[4]+","+small[5]+","+small[6]+","+small[7]+","+small[8]+","+small[9]+","+small[10]+","+small[11]+","+small[12]+","+small[13]+","+small[14]+","+small[15]+","+small[16]+","+small[17]+","+small[18]+","+small[19]+","+small[20]+","+small[21]+","+small[22]+","+small[23]+","+small[24]+","+small[25]+","+small[26]+","+small[27]+","+small[28]+","+small[29]+","+small[30]+","+small[31]+","+small[32]+","+small[33]+","+small[34]+","+small[35]+","+small[36]+","+small[37]+","+small[38]+","+small[39]+","+small[40]+","+small[41]+","+small[42]+","+small[43]+","+small[44]+","+small[45]+","+small[46]+","+small[47]+","+small[48]+","+small[49]+","+small[50]);
    }//GEN-LAST:event_buttonListenOnYoutubeMouseClicked

    private void labelHeart1ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart1ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[1]==0){
            song[1] = 1;
            labelHeart1Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[24]+"','"+small[1]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[1] = 0;
            labelHeart1Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[24]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart1ChartsMouseClicked

    private void labelHeart2ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart2ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[2]==0){
            song[2] = 1;
            labelHeart2Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[25]+"','"+small[2]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[2] = 0;
            labelHeart2Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[25]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart2ChartsMouseClicked

    private void labelHeart3ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart3ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[3]==0){
            song[3] = 1;
            labelHeart3Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[26]+"','"+small[3]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[3] = 0;
            labelHeart3Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[26]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart3ChartsMouseClicked

    private void labelHeart4ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart4ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[4]==0){
            song[4] = 1;
            labelHeart4Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[27]+"','"+small[4]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[4] = 0;
            labelHeart4Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[27]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart4ChartsMouseClicked

    private void labelHeart5ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart5ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[5]==0){
            song[5] = 1;
            labelHeart5Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[28]+"','"+small[5]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[5] = 0;
            labelHeart5Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[28]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart5ChartsMouseClicked

    private void labelHeart6ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart6ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[6]==0){
            song[6] = 1;
            labelHeart6Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[29]+"','"+small[6]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[6] = 0;
            labelHeart6Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[29]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart6ChartsMouseClicked

    private void labelHeart7ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart7ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[7]==0){
            song[7] = 1;
            labelHeart7Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[30]+"','"+small[7]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[7] = 0;
            labelHeart7Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[30]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart7ChartsMouseClicked

    private void labelHeart8ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart8ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[8]==0){
            song[8] = 1;
            labelHeart8Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[31]+"','"+small[8]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[8] = 0;
            labelHeart8Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[31]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart8ChartsMouseClicked

    private void labelHeart9ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart9ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[9]==0){
            song[9] = 1;
            labelHeart9Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[32]+"','"+small[9]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[9] = 0;
            labelHeart9Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[32]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart9ChartsMouseClicked

    private void labelHeart10ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart10ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[10]==0){
            song[10] = 1;
            labelHeart10Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[33]+"','"+small[10]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[10] = 0;
            labelHeart10Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[33]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart10ChartsMouseClicked

    private void labelHeart11ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart11ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[11]==0){
            song[11] = 1;
            labelHeart11Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[34]+"','"+small[11]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[11] = 0;
            labelHeart11Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[34]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart11ChartsMouseClicked

    private void labelHeart12ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart12ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[12]==0){
            song[12] = 1;
            labelHeart12Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[35]+"','"+small[12]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[12] = 0;
            labelHeart12Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[35]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart12ChartsMouseClicked

    private void labelHeart13ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart13ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[13]==0){
            song[13] = 1;
            labelHeart13Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[36]+"','"+small[13]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[13] = 0;
            labelHeart13Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[36]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart13ChartsMouseClicked

    private void labelHeart14ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart14ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[14]==0){
            song[14] = 1;
            labelHeart14Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[37]+"','"+small[14]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[14] = 0;
            labelHeart14Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[37]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart14ChartsMouseClicked

    private void labelHeart15ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart15ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[15]==0){
            song[15] = 1;
            labelHeart15Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[38]+"','"+small[15]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[15] = 0;
            labelHeart15Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[38]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart15ChartsMouseClicked

    private void labelHeart16ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart16ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[16]==0){
            song[16] = 1;
            labelHeart16Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[39]+"','"+small[16]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[16] = 0;
            labelHeart16Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[39]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart16ChartsMouseClicked

    private void labelHeart17ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart17ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[17]==0){
            song[17] = 1;
            labelHeart17Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[40]+"','"+small[17]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[17] = 0;
            labelHeart17Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[40]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart17ChartsMouseClicked

    private void labelHeart18ChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart18ChartsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(song[18]==0){
            song[18] = 1;
            labelHeart18Charts.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+line[41]+"','"+small[18]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            song[18] = 0;
            labelHeart18Charts.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+line[41]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart18ChartsMouseClicked

    private void labelChooseLocalSongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelChooseLocalSongMouseClicked
        AddSongsFrame.main(null);
    }//GEN-LAST:event_labelChooseLocalSongMouseClicked

    private void buttonViewPlaylistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonViewPlaylistsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelPlayPlaylist);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_buttonViewPlaylistsMouseClicked

    private void labelSong1HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong1HomeMouseClicked
        openWebPage(play[1]);
    }//GEN-LAST:event_labelSong1HomeMouseClicked

    private void labelSong2HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong2HomeMouseClicked
        openWebPage(play[2]);
    }//GEN-LAST:event_labelSong2HomeMouseClicked

    private void labelSong3HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSong3HomeMouseClicked
        openWebPage(play[3]);
    }//GEN-LAST:event_labelSong3HomeMouseClicked

    private void labelArtist1HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist1HomeMouseClicked
        y = 20;
        artistsSongs();
    }//GEN-LAST:event_labelArtist1HomeMouseClicked

    private void labelArtist2HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist2HomeMouseClicked
        y = 21;
        artistsSongs();
    }//GEN-LAST:event_labelArtist2HomeMouseClicked

    private void labelArtist3HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist3HomeMouseClicked
        y = 22;
        artistsSongs();
    }//GEN-LAST:event_labelArtist3HomeMouseClicked

    private void labelBackPlayPlaylistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackPlayPlaylistMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelPlaylist);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelBackPlayPlaylistMouseClicked

    private void tablePlayPlaylistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePlayPlaylistMouseClicked
        int row = tablePlayPlaylist.rowAtPoint(evt.getPoint());
        int col = tablePlayPlaylist.columnAtPoint(evt.getPoint());
        for(int a=0;a<=row;a++){
            if (row == a && col >= 0) {
                String links = (String) tablePlayPlaylist.getModel().getValueAt(a,2);
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+links);
            }
        }
    }//GEN-LAST:event_tablePlayPlaylistMouseClicked

    private void labelPlaylist1HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlaylist1HomeMouseClicked
        openWebPage("http://www.youtube.com/watch_videos?video_ids="+link1);
    }//GEN-LAST:event_labelPlaylist1HomeMouseClicked

    private void labelPlaylist2HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlaylist2HomeMouseClicked
        openWebPage("http://www.youtube.com/watch_videos?video_ids="+link2);
    }//GEN-LAST:event_labelPlaylist2HomeMouseClicked

    private void labelPlaylist3HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPlaylist3HomeMouseClicked
        openWebPage("http://www.youtube.com/watch_videos?video_ids="+link3);
    }//GEN-LAST:event_labelPlaylist3HomeMouseClicked

    private void tablePlaylistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePlaylistsMouseClicked
        int row = tablePlaylists.rowAtPoint(evt.getPoint());
        int col = tablePlaylists.columnAtPoint(evt.getPoint());
        for(int a=0;a<=row;a++){
            if (row == a && col >= 0) {
                String links = (String) tablePlaylists.getModel().getValueAt(a,2);
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+links);
            }
        }
    }//GEN-LAST:event_tablePlaylistsMouseClicked

    private void buttonDeleteAllPlaylistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDeleteAllPlaylistsMouseClicked
        String sql9 = "DELETE FROM playlists WHERE USERS_ID='"+user_id+"';";
        try {
            st.executeUpdate(sql9);
            updateTables();
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonDeleteAllPlaylistsMouseClicked

    private void buttonViewFavoriteSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonViewFavoriteSongsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelFavoriteSongs);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_buttonViewFavoriteSongsMouseClicked

    private void buttonViewFavoritesArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonViewFavoritesArtistsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelFavoriteArtists);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_buttonViewFavoritesArtistsMouseClicked

    private void labelBackFavoriteSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackFavoriteSongsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelHome);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelBackFavoriteSongsMouseClicked

    private void tablePlaylistsBigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePlaylistsBigMouseClicked
        int row = tablePlaylistsBig.rowAtPoint(evt.getPoint());
        int col = tablePlaylistsBig.columnAtPoint(evt.getPoint());
        for(int a=0;a<=row;a++){
            if (row == a && col >= 0) {
                String links = (String) tablePlaylistsBig.getModel().getValueAt(a,1);
                if (count==0){
                    formattedTextFieldSongLinks.setText(links);
                    count=1;
                } else {
                    try {
                        count = formattedTextFieldSongLinks.getCaretPosition();
                        formattedTextFieldSongLinks.getDocument().insertString(count,","+links, null);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_tablePlaylistsBigMouseClicked

    private void buttonListenOnYoutubeFavoriteSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListenOnYoutubeFavoriteSongsMouseClicked
        int row = tableFavoriteSongs.getRowCount();
        for(int a=0;a<row;a++){
            favoritesongs[a+1] = (String) tableFavoriteSongs.getModel().getValueAt(a,1);
        }
        switch (row) {
            case 1:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]);
                break;
            case 2:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]);
                break;
            case 3:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]);
                break;
            case 4:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]);
                break;
            case 5:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]);
                break;
            case 6:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]);
                break;
            case 7:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]);
                break;
            case 8:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]);
                break;
            case 9:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]);
                break;
            case 10:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]);
                break;
            case 11:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]);
                break;
            case 12:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]);
                break;
            case 13:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]);
                break;
            case 14:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]);
                break;
            case 15:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]);
                break;
            case 16:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]);
                break;
            case 17:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]);
                break;
            case 18:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]);
                break;
            case 19:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]);
                break;
            case 20:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]);
                break;
            case 21:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]);
                break;
            case 22:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]);
                break;
            case 23:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]);
                break;
            case 24:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]);
                break;
            case 25:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]);
                break;
            case 26:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]);
                break;
            case 27:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]);
                break;
            case 28:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]);
                break;
            case 29:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]);
                break;
            case 30:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]);
                break;
            case 31:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]);
                break;
            case 32:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]);
                break;
            case 33:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]);
                break;
            case 34:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]);
                break;
            case 35:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]);
                break;
            case 36:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]);
                break;
            case 37:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]);
                break;
            case 38:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]);
                break;
            case 39:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]);
                break;
            case 40:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]);
                break;
            case 41:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]);
                break;
            case 42:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]);
                break;
            case 43:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]);
                break;
            case 44:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]+","+favoritesongs[44]);
                break;
            case 45:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]+","+favoritesongs[44]+","+favoritesongs[45]);
                break;
            case 46:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]+","+favoritesongs[44]+","+favoritesongs[45]+","+favoritesongs[46]);
                break;
            case 47:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]+","+favoritesongs[44]+","+favoritesongs[45]+","+favoritesongs[46]+","+favoritesongs[47]);
                break;
            case 48:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]+","+favoritesongs[44]+","+favoritesongs[45]+","+favoritesongs[46]+","+favoritesongs[47]+","+favoritesongs[48]);
                break;
            case 49:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]+","+favoritesongs[44]+","+favoritesongs[45]+","+favoritesongs[46]+","+favoritesongs[47]+","+favoritesongs[48]+","+favoritesongs[49]);
                break;
            case 50:
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+favoritesongs[1]+","+favoritesongs[2]+","+favoritesongs[3]+","+favoritesongs[4]+","+favoritesongs[5]+","+favoritesongs[6]+","+favoritesongs[7]+","+favoritesongs[8]+","+favoritesongs[9]+","+favoritesongs[10]+","+favoritesongs[11]+","+favoritesongs[12]+","+favoritesongs[13]+","+favoritesongs[14]+","+favoritesongs[15]+","+favoritesongs[16]+","+favoritesongs[17]+","+favoritesongs[18]+","+favoritesongs[19]+","+favoritesongs[20]+","+favoritesongs[21]+","+favoritesongs[22]+","+favoritesongs[23]+","+favoritesongs[24]+","+favoritesongs[25]+","+favoritesongs[26]+","+favoritesongs[27]+","+favoritesongs[28]+","+favoritesongs[29]+","+favoritesongs[30]+","+favoritesongs[31]+","+favoritesongs[32]+","+favoritesongs[33]+","+favoritesongs[34]+","+favoritesongs[35]+","+favoritesongs[36]+","+favoritesongs[37]+","+favoritesongs[38]+","+favoritesongs[39]+","+favoritesongs[40]+","+favoritesongs[41]+","+favoritesongs[42]+","+favoritesongs[43]+","+favoritesongs[44]+","+favoritesongs[45]+","+favoritesongs[46]+","+favoritesongs[47]+","+favoritesongs[48]+","+favoritesongs[49]+","+favoritesongs[50]);
                break;
        }
        
    }//GEN-LAST:event_buttonListenOnYoutubeFavoriteSongsMouseClicked

    private void labelBckFavoriteArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBckFavoriteArtistsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelHome);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelBckFavoriteArtistsMouseClicked

    private void labelReturnHomeFavoriteArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReturnHomeFavoriteArtistsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelHome);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelReturnHomeFavoriteArtistsMouseClicked

    private void labelReturnHomeFavoriteSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReturnHomeFavoriteSongsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelHome);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelReturnHomeFavoriteSongsMouseClicked

    private void labelArtist36TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist36TopArtistsMouseClicked
        y = 55;
        artistsSongs();
    }//GEN-LAST:event_labelArtist36TopArtistsMouseClicked

    private void labelArtist35TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist35TopArtistsMouseClicked
        y = 54;
        artistsSongs();
    }//GEN-LAST:event_labelArtist35TopArtistsMouseClicked

    private void labelArtist34TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist34TopArtistsMouseClicked
        y = 53;
        artistsSongs();
    }//GEN-LAST:event_labelArtist34TopArtistsMouseClicked

    private void labelArtist33TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist33TopArtistsMouseClicked
        y = 52;
        artistsSongs();
    }//GEN-LAST:event_labelArtist33TopArtistsMouseClicked

    private void labelArtist32TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist32TopArtistsMouseClicked
        y = 51;
        artistsSongs();
    }//GEN-LAST:event_labelArtist32TopArtistsMouseClicked

    private void labelArtist31TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist31TopArtistsMouseClicked
        y = 50;
        artistsSongs();
    }//GEN-LAST:event_labelArtist31TopArtistsMouseClicked

    private void labelArtist30TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist30TopArtistsMouseClicked
        y = 49;
        artistsSongs();
    }//GEN-LAST:event_labelArtist30TopArtistsMouseClicked

    private void labelArtist29TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist29TopArtistsMouseClicked
        y = 48;
        artistsSongs();
    }//GEN-LAST:event_labelArtist29TopArtistsMouseClicked

    private void labelArtist28TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist28TopArtistsMouseClicked
        y = 47;
        artistsSongs();
    }//GEN-LAST:event_labelArtist28TopArtistsMouseClicked

    private void labelArtist27TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist27TopArtistsMouseClicked
        y = 46;
        artistsSongs();
    }//GEN-LAST:event_labelArtist27TopArtistsMouseClicked

    private void labelArtist26TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist26TopArtistsMouseClicked
        y = 45;
        artistsSongs();
    }//GEN-LAST:event_labelArtist26TopArtistsMouseClicked

    private void labelArtist25TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist25TopArtistsMouseClicked
        y = 44;
        artistsSongs();
    }//GEN-LAST:event_labelArtist25TopArtistsMouseClicked

    private void labelArtist24TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist24TopArtistsMouseClicked
        y = 43;
        artistsSongs();
    }//GEN-LAST:event_labelArtist24TopArtistsMouseClicked

    private void labelArtist23TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist23TopArtistsMouseClicked
        y = 42;
        artistsSongs();
    }//GEN-LAST:event_labelArtist23TopArtistsMouseClicked

    private void labelArtist22TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist22TopArtistsMouseClicked
        y = 41;
        artistsSongs();
    }//GEN-LAST:event_labelArtist22TopArtistsMouseClicked

    private void labelArtist21TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist21TopArtistsMouseClicked
        y = 40;
        artistsSongs();
    }//GEN-LAST:event_labelArtist21TopArtistsMouseClicked

    private void labelArtist20TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist20TopArtistsMouseClicked
        y = 39;
        artistsSongs();
    }//GEN-LAST:event_labelArtist20TopArtistsMouseClicked

    private void labelArtist19TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist19TopArtistsMouseClicked
        y = 38;
        artistsSongs();
    }//GEN-LAST:event_labelArtist19TopArtistsMouseClicked

    private void labelArtist18TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist18TopArtistsMouseClicked
        y = 37;
        artistsSongs();
    }//GEN-LAST:event_labelArtist18TopArtistsMouseClicked

    private void labelArtist17TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist17TopArtistsMouseClicked
        y = 36;
        artistsSongs();
    }//GEN-LAST:event_labelArtist17TopArtistsMouseClicked

    private void labelArtist16TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist16TopArtistsMouseClicked
        y = 35;
        artistsSongs();
    }//GEN-LAST:event_labelArtist16TopArtistsMouseClicked

    private void labelArtist15TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist15TopArtistsMouseClicked
        y = 34;
        artistsSongs();
    }//GEN-LAST:event_labelArtist15TopArtistsMouseClicked

    private void labelArtist14TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist14TopArtistsMouseClicked
        y = 33;
        artistsSongs();
    }//GEN-LAST:event_labelArtist14TopArtistsMouseClicked

    private void labelArtist13TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist13TopArtistsMouseClicked
        y = 32;
        artistsSongs();
    }//GEN-LAST:event_labelArtist13TopArtistsMouseClicked

    private void labelArtist12TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist12TopArtistsMouseClicked
        y = 31;
        artistsSongs();
    }//GEN-LAST:event_labelArtist12TopArtistsMouseClicked

    private void labelArtist11TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist11TopArtistsMouseClicked
        y = 30;
        artistsSongs();
    }//GEN-LAST:event_labelArtist11TopArtistsMouseClicked

    private void labelArtist10TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist10TopArtistsMouseClicked
        y = 29;
        artistsSongs();
    }//GEN-LAST:event_labelArtist10TopArtistsMouseClicked

    private void labelArtist9TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist9TopArtistsMouseClicked
        y = 28;
        artistsSongs();
    }//GEN-LAST:event_labelArtist9TopArtistsMouseClicked

    private void labelArtist8TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist8TopArtistsMouseClicked
        y = 27;
        artistsSongs();
    }//GEN-LAST:event_labelArtist8TopArtistsMouseClicked

    private void labelArtist7TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist7TopArtistsMouseClicked
        y = 26;
        artistsSongs();
    }//GEN-LAST:event_labelArtist7TopArtistsMouseClicked

    private void labelArtist6TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist6TopArtistsMouseClicked
        y = 25;
        artistsSongs();
    }//GEN-LAST:event_labelArtist6TopArtistsMouseClicked

    private void labelArtist5TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist5TopArtistsMouseClicked
        y = 24;
        artistsSongs();
    }//GEN-LAST:event_labelArtist5TopArtistsMouseClicked

    private void labelArtist4TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist4TopArtistsMouseClicked
        y = 23;
        artistsSongs();
    }//GEN-LAST:event_labelArtist4TopArtistsMouseClicked

    private void labelArtist3TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist3TopArtistsMouseClicked
        y = 22;
        artistsSongs();
    }//GEN-LAST:event_labelArtist3TopArtistsMouseClicked

    private void labelArtist2TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist2TopArtistsMouseClicked
        y = 21;
        artistsSongs();
    }//GEN-LAST:event_labelArtist2TopArtistsMouseClicked

    private void labelArtist1TopArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtist1TopArtistsMouseClicked
        y = 20;
        artistsSongs();
    }//GEN-LAST:event_labelArtist1TopArtistsMouseClicked

    private void labelHeart1ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart1ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[1]==0){
            fav_artist[1] = 1;
            y = 20;
            fav_artistsSongs();
            labelHeart1Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[1]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[1] = 0;
            labelHeart1Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[1]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart1ArtistsMouseClicked

    private void labelHeart2ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart2ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[2]==0){
            fav_artist[2] = 1;
            y = 21;
            fav_artistsSongs();
            labelHeart2Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[2]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[2] = 0;
            labelHeart2Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[2]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart2ArtistsMouseClicked

    private void labelHeart3ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart3ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[3]==0){
            fav_artist[3] = 1;
            y = 22;
            fav_artistsSongs();
            labelHeart3Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[3]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[3] = 0;
            labelHeart3Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[3]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart3ArtistsMouseClicked

    private void labelHeart4ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart4ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[4]==0){
            fav_artist[4] = 1;
            y = 23;
            fav_artistsSongs();
            labelHeart4Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[4]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[4] = 0;
            labelHeart4Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[4]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart4ArtistsMouseClicked

    private void labelHeart5ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart5ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[5]==0){
            fav_artist[5] = 1;
            y = 24;
            fav_artistsSongs();
            labelHeart5Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[5]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[5] = 0;
            labelHeart5Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[5]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart5ArtistsMouseClicked

    private void labelHeart6ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart6ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[6]==0){
            fav_artist[6] = 1;
            y = 25;
            fav_artistsSongs();
            labelHeart6Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[6]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[6] = 0;
            labelHeart6Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[6]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart6ArtistsMouseClicked

    private void labelHeart7ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart7ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[7]==0){
            fav_artist[7] = 1;
            y = 26;
            fav_artistsSongs();
            labelHeart7Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[7]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[7] = 0;
            labelHeart7Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[7]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart7ArtistsMouseClicked

    private void labelHeart8ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart8ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[8]==0){
            fav_artist[8] = 1;
            y = 27;
            fav_artistsSongs();
            labelHeart8Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[8]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[8] = 0;
            labelHeart8Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[8]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart8ArtistsMouseClicked

    private void labelHeart9ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart9ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[9]==0){
            fav_artist[9] = 1;
            y = 28;
            fav_artistsSongs();
            labelHeart9Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[9]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[9] = 0;
            labelHeart9Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[9]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart9ArtistsMouseClicked

    private void labelHeart10ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart10ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[10]==0){
            fav_artist[10] = 1;
            y = 29;
            fav_artistsSongs();
            labelHeart10Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[10]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[10] = 0;
            labelHeart10Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[10]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart10ArtistsMouseClicked

    private void labelHeart11ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart11ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[11]==0){
            fav_artist[11] = 1;
            y = 30;
            fav_artistsSongs();
            labelHeart11Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[11]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[11] = 0;
            labelHeart11Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[11]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart11ArtistsMouseClicked

    private void labelHeart12ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart12ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[12]==0){
            fav_artist[12] = 1;
            y = 31;
            fav_artistsSongs();
            labelHeart12Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[12]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[12] = 0;
            labelHeart12Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[12]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart12ArtistsMouseClicked

    private void labelHeart13ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart13ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[13]==0){
            fav_artist[13] = 1;
            y = 32;
            fav_artistsSongs();
            labelHeart13Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[13]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[13] = 0;
            labelHeart13Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[13]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart13ArtistsMouseClicked

    private void labelHeart14ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart14ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[14]==0){
            fav_artist[14] = 1;
            y = 33;
            fav_artistsSongs();
            labelHeart14Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[14]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[14] = 0;
            labelHeart14Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[14]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart14ArtistsMouseClicked

    private void labelHeart15ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart15ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[15]==0){
            fav_artist[15] = 1;
            y = 34;
            fav_artistsSongs();
            labelHeart15Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[15]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[15] = 0;
            labelHeart15Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[15]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart15ArtistsMouseClicked

    private void labelHeart16ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart16ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[16]==0){
            fav_artist[16] = 1;
            y = 35;
            fav_artistsSongs();
            labelHeart16Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[16]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[16] = 0;
            labelHeart16Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[16]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart16ArtistsMouseClicked

    private void labelHeart17ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart17ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[17]==0){
            fav_artist[17] = 1;
            y = 36;
            fav_artistsSongs();
            labelHeart17Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[17]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[17] = 0;
            labelHeart17Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[17]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart17ArtistsMouseClicked

    private void labelHeart18ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart18ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[18]==0){
            fav_artist[18] = 1;
            y = 37;
            fav_artistsSongs();
            labelHeart18Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[18]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[18] = 0;
            labelHeart18Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[18]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart18ArtistsMouseClicked

    private void labelHeart19ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart19ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[19]==0){
            fav_artist[19] = 1;
            y = 38;
            fav_artistsSongs();
            labelHeart19Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[19]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[19] = 0;
            labelHeart19Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[19]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart19ArtistsMouseClicked

    private void labelHeart20ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart20ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[20]==0){
            fav_artist[20] = 1;
            y = 39;
            fav_artistsSongs();
            labelHeart20Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[20]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[20] = 0;
            labelHeart20Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[20]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart20ArtistsMouseClicked

    private void labelHeart21ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart21ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[21]==0){
            fav_artist[21] = 1;
            y = 40;
            fav_artistsSongs();
            labelHeart21Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[21]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[21] = 0;
            labelHeart21Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[21]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart21ArtistsMouseClicked

    private void labelHeart22ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart22ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[22]==0){
            fav_artist[22] = 1;
            y = 41;
            fav_artistsSongs();
            labelHeart22Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[22]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[22] = 0;
            labelHeart22Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[22]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart22ArtistsMouseClicked

    private void labelHeart23ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart23ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[23]==0){
            fav_artist[23] = 1;
            y = 42;
            fav_artistsSongs();
            labelHeart23Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[23]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[23] = 0;
            labelHeart23Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[23]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart23ArtistsMouseClicked

    private void labelHeart24ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart24ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[24]==0){
            fav_artist[24] = 1;
            y = 43;
            fav_artistsSongs();
            labelHeart24Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[24]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[24] = 0;
            labelHeart24Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[24]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart24ArtistsMouseClicked

    private void labelHeart25ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart25ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[25]==0){
            fav_artist[25] = 1;
            y = 44;
            fav_artistsSongs();
            labelHeart25Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[25]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[25] = 0;
            labelHeart25Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[25]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart25ArtistsMouseClicked

    private void labelHeart26ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart26ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[26]==0){
            fav_artist[26] = 1;
            y = 45;
            fav_artistsSongs();
            labelHeart26Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[26]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[26] = 0;
            labelHeart26Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[26]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart26ArtistsMouseClicked

    private void labelHeart27ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart27ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[27]==0){
            fav_artist[27] = 1;
            y = 46;
            fav_artistsSongs();
            labelHeart27Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[27]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[27] = 0;
            labelHeart27Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[27]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart27ArtistsMouseClicked

    private void labelHeart28ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart28ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[28]==0){
            fav_artist[28] = 1;
            y = 47;
            fav_artistsSongs();
            labelHeart28Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[28]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[28] = 0;
            labelHeart28Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[28]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart28ArtistsMouseClicked

    private void labelHeart29ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart29ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[29]==0){
            fav_artist[29] = 1;
            y = 48;
            fav_artistsSongs();
            labelHeart29Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[29]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[29] = 0;
            labelHeart29Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[29]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart29ArtistsMouseClicked

    private void labelHeart30ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart30ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[30]==0){
            fav_artist[30] = 1;
            y = 49;
            fav_artistsSongs();
            labelHeart30Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[30]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[30] = 0;
            labelHeart30Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[30]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart30ArtistsMouseClicked

    private void labelHeart31ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart31ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[31]==0){
            fav_artist[31] = 1;
            y = 50;
            fav_artistsSongs();
            labelHeart31Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[31]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[31] = 0;
            labelHeart31Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[31]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart31ArtistsMouseClicked

    private void labelHeart32ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart32ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[32]==0){
            fav_artist[32] = 1;
            y = 51;
            fav_artistsSongs();
            labelHeart32Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[32]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[32] = 0;
            labelHeart32Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[32]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart32ArtistsMouseClicked

    private void labelHeart33ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart33ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[33]==0){
            fav_artist[33] = 1;
            y = 52;
            fav_artistsSongs();
            labelHeart33Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[33]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[33] = 0;
            labelHeart33Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[33]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart33ArtistsMouseClicked

    private void labelHeart34ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart34ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[34]==0){
            fav_artist[34] = 1;
            y = 53;
            fav_artistsSongs();
            labelHeart34Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[34]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[34] = 0;
            labelHeart34Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[34]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart34ArtistsMouseClicked

    private void labelHeart35ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart35ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[35]==0){
            fav_artist[35] = 1;
            y = 54;
            fav_artistsSongs();
            labelHeart35Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[35]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[35] = 0;
            labelHeart35Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[35]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart35ArtistsMouseClicked

    private void labelHeart36ArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart36ArtistsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(fav_artist[36]==0){
            fav_artist[36] = 1;
            y = 55;
            fav_artistsSongs();
            labelHeart36Artists.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO artists(NAME,LINKS,USERS_ID) VALUES ('"+artist[36]+"','"+small_artist[1]+","+small_artist[2]+","+small_artist[3]+","+small_artist[4]+","+small_artist[5]+","+small_artist[6]+","+small_artist[7]+","+small_artist[8]+","+small_artist[9]+","+small_artist[10]+","+small_artist[11]+","+small_artist[12]+","+small_artist[13]+","+small_artist[14]+","+small_artist[15]+","+small_artist[16]+","+small_artist[17]+","+small_artist[18]+","+small_artist[19]+","+small_artist[20]+","+small_artist[21]+","+small_artist[22]+","+small_artist[23]+","+small_artist[24]+","+small_artist[25]+","+small_artist[26]+","+small_artist[27]+","+small_artist[28]+","+small_artist[29]+","+small_artist[30]+","+small_artist[31]+","+small_artist[32]+","+small_artist[33]+","+small_artist[34]+","+small_artist[35]+","+small_artist[36]+","+small_artist[37]+","+small_artist[38]+","+small_artist[39]+","+small_artist[40]+","+small_artist[41]+"','"+user_id+"');";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            fav_artist[36] = 0;
            labelHeart36Artists.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM artists WHERE NAME='"+artist[36]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart36ArtistsMouseClicked

    private void tableFavoriteArtistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFavoriteArtistsMouseClicked
        int row = tableFavoriteArtists.rowAtPoint(evt.getPoint());
        int col = tableFavoriteArtists.columnAtPoint(evt.getPoint());
        for(int a=0;a<=row;a++){
            if (row == a && col >= 0) {
                String links = (String) tableFavoriteArtists.getModel().getValueAt(a,1);
                openWebPage("http://www.youtube.com/watch_videos?video_ids="+links);
            }
        }
    }//GEN-LAST:event_tableFavoriteArtistsMouseClicked

    private void textFieldSearchSongsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchSongsActionPerformed
        Image img1 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] view_count = new String[100];
        String[] date_published = new String[100];
        String text1 = textFieldSearchSongs.getText();
        String text2 = text1.replaceAll(" ","\\+").replaceAll("A", "a").replaceAll("B", "b").replaceAll("C", "c").replaceAll("D", "d").replaceAll("E", "e").replaceAll("F", "f").replaceAll("G", "g").replaceAll("H", "h").replaceAll("I", "i").replaceAll("J", "j").replaceAll("K", "k").replaceAll("L","l").replaceAll("M", "m").replaceAll("N", "n").replaceAll("O", "o").replaceAll("P", "p").replaceAll("Q", "q").replaceAll("R", "r").replaceAll("S", "s").replaceAll("T", "t").replaceAll("U", "u").replaceAll("V", "v").replaceAll("W", "w").replaceAll("X", "x").replaceAll("Y", "y").replaceAll("Z", "z");
        Document doc3;
        try {
            String url3 = "https://www.bing.com/videos/search?q="+text2;
            doc3 = Jsoup.connect(url3).get();
            Elements artists = doc3.select("a[href]");
            String search_results = artists.html();
            try (PrintWriter out = new PrintWriter("search_results.txt")) {
                out.println(search_results);
                out.close();
            }
            try {
                File file = new File("search_results.txt");
                Scanner scanner;
                scanner = new Scanner(file);
                int lineNum = 0;
                int resultNum = 1;
                while (scanner.hasNextLine()) {
                    String result = scanner.nextLine();
                    if(result.startsWith("   <div class=\"mc_vtvc_title b_promtxt\" title=\"")) { 
                        results[resultNum] = Files.readAllLines(Paths.get("search_results.txt")).get(lineNum).replace("   <div class=\"mc_vtvc_title b_promtxt\" title=\"","").replace("\">","");
                        resultNum++;
                    }
                    lineNum++;
                }
            } catch(FileNotFoundException e) { }
            try {
                File file = new File("search_results.txt");
                Scanner scanner;
                scanner = new Scanner(file);
                int lineNum = 0;
                int resultNum = 1;
                while (scanner.hasNextLine()) {
                    String result = scanner.nextLine();
                    if(result.startsWith("<div class=\"mc_vtvc_con_rc\" ourl=\"")) { 
                        results_links[resultNum] = Files.readAllLines(Paths.get("search_results.txt")).get(lineNum).replace("<div class=\"mc_vtvc_con_rc\" ourl=\"","").replace("\">","");
                        results_small_links[resultNum] = Files.readAllLines(Paths.get("search_results.txt")).get(lineNum).replace("<div class=\"mc_vtvc_con_rc\" ourl=\"https://www.youtube.com/watch?v=","").replace("\">","");
                        resultNum++;
                    }
                    lineNum++;
                }
            } catch(FileNotFoundException e) { }
            try {
                File file = new File("search_results.txt");
                Scanner scanner;
                scanner = new Scanner(file);
                int lineNum = 0;
                int resultNum = 1;
                while (scanner.hasNextLine()) {
                    String result = scanner.nextLine();
                    if(result.startsWith("      <span class=\"meta_vc_content\">")) { 
                        view_count[resultNum] = Files.readAllLines(Paths.get("search_results.txt")).get(lineNum).replace("      <span class=\"meta_vc_content\">","").replace("</span>","");
                        resultNum++;
                    }
                    lineNum++;
                }
            } catch(FileNotFoundException e) { }
            try {
                File file = new File("search_results.txt");
                Scanner scanner;
                scanner = new Scanner(file);
                int lineNum = 0;
                int resultNum = 1;
                while (scanner.hasNextLine()) {
                    String result = scanner.nextLine();
                    if(result.startsWith("      <span class=\"meta_pd_content\">")) { 
                        date_published[resultNum] = Files.readAllLines(Paths.get("search_results.txt")).get(lineNum).replace("      <span class=\"meta_pd_content\">","").replace("</span>","");
                        resultNum++;
                    }
                    lineNum++;
                }
            } catch(FileNotFoundException e) { }
            labelSongSearchResult1.setText(results[1]);
            labelSongSearchResult2.setText(results[2]);
            labelSongSearchResult3.setText(results[3]);
            labelSongSearchResult4.setText(results[4]);
            labelSongSearchResult5.setText(results[5]);
            labelSongSearchResult6.setText(results[6]);
            labelSongSearchResult7.setText(results[7]);
            labelSongSearchResult8.setText(results[8]);
            labelSongSearchResult9.setText(results[9]);
            labelSongSearchResult10.setText(results[10]);
            labelSongSearchResult11.setText(results[11]);
            labelSongSearchResult12.setText(results[12]);
            labelSongSearchResult13.setText(results[13]);
            labelSongSearchResult14.setText(results[14]);
            labelSongSearchResult15.setText(results[15]);
            labelSongSearchResult16.setText(results[16]);
            labelSongSearchResult17.setText(results[17]);
            labelSearchSongViewCount1.setText(view_count[1]);
            labelSearchSongViewCount2.setText(view_count[2]);
            labelSearchSongViewCount3.setText(view_count[3]);
            labelSearchSongViewCount4.setText(view_count[4]);
            labelSearchSongViewCount5.setText(view_count[5]);
            labelSearchSongViewCount6.setText(view_count[6]);
            labelSearchSongViewCount7.setText(view_count[7]);
            labelSearchSongViewCount8.setText(view_count[8]);
            labelSearchSongViewCount9.setText(view_count[9]);
            labelSearchSongViewCount10.setText(view_count[10]);
            labelSearchSongViewCount11.setText(view_count[11]);
            labelSearchSongViewCount12.setText(view_count[12]);
            labelSearchSongViewCount13.setText(view_count[13]);
            labelSearchSongViewCount14.setText(view_count[14]);
            labelSearchSongViewCount15.setText(view_count[15]);
            labelSearchSongViewCount16.setText(view_count[16]);
            labelSearchSongViewCount17.setText(view_count[17]);
            labelSearchSongsDate1.setText(date_published[1]);
            labelSearchSongsDate2.setText(date_published[2]);
            labelSearchSongsDate3.setText(date_published[3]);
            labelSearchSongsDate4.setText(date_published[4]);
            labelSearchSongsDate5.setText(date_published[5]);
            labelSearchSongsDate6.setText(date_published[6]);
            labelSearchSongsDate7.setText(date_published[7]);
            labelSearchSongsDate8.setText(date_published[8]);
            labelSearchSongsDate9.setText(date_published[9]);
            labelSearchSongsDate10.setText(date_published[10]);
            labelSearchSongsDate11.setText(date_published[11]);
            labelSearchSongsDate12.setText(date_published[12]);
            labelSearchSongsDate13.setText(date_published[13]);
            labelSearchSongsDate14.setText(date_published[14]);
            labelSearchSongsDate15.setText(date_published[15]);
            labelSearchSongsDate16.setText(date_published[16]);
            labelSearchSongsDate17.setText(date_published[17]);
            if(results_links[1].contains("https://www.youtube.com/")){
                results_num[1] = 0;
                labelHeart1SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart1SearchSongs.setVisible(true);
            } else {
                labelHeart1SearchSongs.setVisible(false);
            }
            if(results_links[2].contains("https://www.youtube.com/")){
                results_num[2] = 0;
                labelHeart2SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart2SearchSongs.setVisible(true);
            } else {
                labelHeart2SearchSongs.setVisible(false);
            }
            if(results_links[3].contains("https://www.youtube.com/")){
                results_num[3] = 0;
                labelHeart3SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart3SearchSongs.setVisible(true);
            } else {
                labelHeart3SearchSongs.setVisible(false);
            }
            if(results_links[4].contains("https://www.youtube.com/")){
                results_num[4] = 0;
                labelHeart4SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart4SearchSongs.setVisible(true);
            } else {
                labelHeart4SearchSongs.setVisible(false);
            }
            if(results_links[5].contains("https://www.youtube.com/")){
                results_num[5] = 0;
                labelHeart5SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart5SearchSongs.setVisible(true);
            } else {
                labelHeart5SearchSongs.setVisible(false);
            }
            if(results_links[6].contains("https://www.youtube.com/")){
                results_num[6] = 0;
                labelHeart6SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart6SearchSongs.setVisible(true);
            } else {
                labelHeart6SearchSongs.setVisible(false);
            }
            if(results_links[7].contains("https://www.youtube.com/")){
                results_num[7] = 0;
                labelHeart7SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart7SearchSongs.setVisible(true);
            } else {
                labelHeart7SearchSongs.setVisible(false);
            }
            if(results_links[8].contains("https://www.youtube.com/")){
                results_num[8] = 0;
                labelHeart8SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart8SearchSongs.setVisible(true);
            } else {
                labelHeart8SearchSongs.setVisible(false);
            }
            if(results_links[9].contains("https://www.youtube.com/")){
                results_num[9] = 0;
                labelHeart9SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart9SearchSongs.setVisible(true);
            } else {
                labelHeart9SearchSongs.setVisible(false);
            }
            if(results_links[10].contains("https://www.youtube.com/")){
                results_num[10] = 0;
                labelHeart10SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart10SearchSongs.setVisible(true);
            } else {
                labelHeart10SearchSongs.setVisible(false);
            }
            if(results_links[11].contains("https://www.youtube.com/")){
                results_num[11] = 0;
                labelHeart11SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart11SearchSongs.setVisible(true);
            } else {
                labelHeart11SearchSongs.setVisible(false);
            }
            if(results_links[12].contains("https://www.youtube.com/")){
                results_num[12] = 0;
                labelHeart12SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart12SearchSongs.setVisible(true);
            } else {
                labelHeart12SearchSongs.setVisible(false);
            }
            if(results_links[13].contains("https://www.youtube.com/")){
                results_num[13] = 0;
                labelHeart13SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart13SearchSongs.setVisible(true);
            } else {
                labelHeart13SearchSongs.setVisible(false);
            }
            if(results_links[14].contains("https://www.youtube.com/")){
                results_num[14] = 0;
                labelHeart14SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart14SearchSongs.setVisible(true);
            } else {
                labelHeart14SearchSongs.setVisible(false);
            }
            if(results_links[15].contains("https://www.youtube.com/")){
                results_num[15] = 0;
                labelHeart15SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart15SearchSongs.setVisible(true);
            } else {
                labelHeart15SearchSongs.setVisible(false);
            }
            if(results_links[16].contains("https://www.youtube.com/")){
                results_num[16] = 0;
                labelHeart16SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart16SearchSongs.setVisible(true);
            } else {
                labelHeart16SearchSongs.setVisible(false);
            }
            if(results_links[17].contains("https://www.youtube.com/")){
                results_num[17] = 0;
                labelHeart17SearchSongs.setIcon(new ImageIcon(img1));
                labelHeart17SearchSongs.setVisible(true);
            } else {
                labelHeart17SearchSongs.setVisible(false);
            }
            enableSearchLabels();
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkLikesSearch();
    }//GEN-LAST:event_textFieldSearchSongsActionPerformed

    private void textFieldSearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldSearchSongsMouseClicked
        textFieldGenius.setText("Search lyrics on Genius");
        textFieldWikipedia.setText("Search on Wikipedia");
        textFieldSearchSongs.setText("");
    }//GEN-LAST:event_textFieldSearchSongsMouseClicked

    private void buttonSearchInformationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchInformationMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelSearch);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_buttonSearchInformationMouseClicked

    private void labelBackSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackSearchMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelSearchSongs);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelBackSearchMouseClicked

    private void labelGoBackSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelGoBackSearchMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelSearchSongs);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelGoBackSearchMouseClicked

    private void labelSongSearchResult1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult1MouseClicked
        openWebPage(results_links[1]);
    }//GEN-LAST:event_labelSongSearchResult1MouseClicked

    private void labelSongSearchResult2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult2MouseClicked
        openWebPage(results_links[2]);
    }//GEN-LAST:event_labelSongSearchResult2MouseClicked

    private void labelSongSearchResult3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult3MouseClicked
        openWebPage(results_links[3]);
    }//GEN-LAST:event_labelSongSearchResult3MouseClicked

    private void labelSongSearchResult4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult4MouseClicked
        openWebPage(results_links[4]);
    }//GEN-LAST:event_labelSongSearchResult4MouseClicked

    private void labelSongSearchResult5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult5MouseClicked
        openWebPage(results_links[5]);
    }//GEN-LAST:event_labelSongSearchResult5MouseClicked

    private void labelSongSearchResult6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult6MouseClicked
        openWebPage(results_links[6]);
    }//GEN-LAST:event_labelSongSearchResult6MouseClicked

    private void labelSongSearchResult7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult7MouseClicked
        openWebPage(results_links[7]);
    }//GEN-LAST:event_labelSongSearchResult7MouseClicked

    private void labelSongSearchResult8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult8MouseClicked
        openWebPage(results_links[8]);
    }//GEN-LAST:event_labelSongSearchResult8MouseClicked

    private void labelSongSearchResult9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult9MouseClicked
        openWebPage(results_links[9]);
    }//GEN-LAST:event_labelSongSearchResult9MouseClicked

    private void labelSongSearchResult10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult10MouseClicked
        openWebPage(results_links[10]);
    }//GEN-LAST:event_labelSongSearchResult10MouseClicked

    private void labelSongSearchResult11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult11MouseClicked
        openWebPage(results_links[11]);
    }//GEN-LAST:event_labelSongSearchResult11MouseClicked

    private void labelSongSearchResult12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult12MouseClicked
        openWebPage(results_links[12]);
    }//GEN-LAST:event_labelSongSearchResult12MouseClicked

    private void labelSongSearchResult13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult13MouseClicked
        openWebPage(results_links[13]);
    }//GEN-LAST:event_labelSongSearchResult13MouseClicked

    private void labelSongSearchResult14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult14MouseClicked
        openWebPage(results_links[14]);
    }//GEN-LAST:event_labelSongSearchResult14MouseClicked

    private void labelSongSearchResult15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult15MouseClicked
        openWebPage(results_links[15]);
    }//GEN-LAST:event_labelSongSearchResult15MouseClicked

    private void labelSongSearchResult16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult16MouseClicked
        openWebPage(results_links[16]);
    }//GEN-LAST:event_labelSongSearchResult16MouseClicked

    private void labelSongSearchResult17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongSearchResult17MouseClicked
        openWebPage(results_links[17]);
    }//GEN-LAST:event_labelSongSearchResult17MouseClicked

    private void labelHeart1SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart1SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[1]==0){
            results_num[1] = 1;
            labelHeart1SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[1]+"','"+results_small_links[1]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[1]+"','"+results_small_links[1]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[1] = 0;
            labelHeart1SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[1]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart1SearchSongsMouseClicked

    private void labelHeart2SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart2SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[2]==0){
            results_num[2] = 1;
            labelHeart2SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[2]+"','"+results_small_links[2]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[2]+"','"+results_small_links[2]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[2] = 0;
            labelHeart2SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[2]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart2SearchSongsMouseClicked

    private void labelHeart3SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart3SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[3]==0){
            results_num[3] = 1;
            labelHeart3SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[3]+"','"+results_small_links[3]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[3]+"','"+results_small_links[3]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[3] = 0;
            labelHeart3SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[3]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart3SearchSongsMouseClicked

    private void labelHeart4SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart4SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[4]==0){
            results_num[4] = 1;
            labelHeart4SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[4]+"','"+results_small_links[4]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[4]+"','"+results_small_links[4]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[4] = 0;
            labelHeart4SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[4]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart4SearchSongsMouseClicked

    private void labelHeart5SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart5SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[5]==0){
            results_num[5] = 1;
            labelHeart5SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[5]+"','"+results_small_links[5]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[5]+"','"+results_small_links[5]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[5] = 0;
            labelHeart5SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[5]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart5SearchSongsMouseClicked

    private void labelHeart6SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart6SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[6]==0){
            results_num[6] = 1;
            labelHeart6SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[6]+"','"+results_small_links[6]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[6]+"','"+results_small_links[6]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[6] = 0;
            labelHeart6SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[6]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart6SearchSongsMouseClicked

    private void labelHeart7SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart7SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[7]==0){
            results_num[7] = 1;
            labelHeart7SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[7]+"','"+results_small_links[7]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[7]+"','"+results_small_links[7]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[7] = 0;
            labelHeart7SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[7]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart7SearchSongsMouseClicked

    private void labelHeart8SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart8SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[8]==0){
            results_num[8] = 1;
            labelHeart8SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[8]+"','"+results_small_links[8]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[8]+"','"+results_small_links[8]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[8] = 0;
            labelHeart8SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[8]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart8SearchSongsMouseClicked

    private void labelHeart9SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart9SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[9]==0){
            results_num[9] = 1;
            labelHeart9SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[9]+"','"+results_small_links[9]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[9]+"','"+results_small_links[9]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[9] = 0;
            labelHeart9SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[9]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart9SearchSongsMouseClicked

    private void labelHeart10SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart10SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[10]==0){
            results_num[10] = 1;
            labelHeart10SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[10]+"','"+results_small_links[10]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[10]+"','"+results_small_links[10]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[10] = 0;
            labelHeart10SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[10]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart10SearchSongsMouseClicked

    private void labelHeart11SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart11SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[11]==0){
            results_num[11] = 1;
            labelHeart11SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[11]+"','"+results_small_links[11]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[11]+"','"+results_small_links[11]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[11] = 0;
            labelHeart11SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[11]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart11SearchSongsMouseClicked

    private void labelHeart12SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart12SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[12]==0){
            results_num[12] = 1;
            labelHeart12SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[12]+"','"+results_small_links[12]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[12]+"','"+results_small_links[12]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[12] = 0;
            labelHeart12SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[12]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart12SearchSongsMouseClicked

    private void labelHeart13SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart13SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[13]==0){
            results_num[13] = 1;
            labelHeart13SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[13]+"','"+results_small_links[13]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[13]+"','"+results_small_links[13]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[13] = 0;
            labelHeart13SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[13]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart13SearchSongsMouseClicked

    private void labelHeart14SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart14SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[14]==0){
            results_num[14] = 1;
            labelHeart14SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[14]+"','"+results_small_links[14]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[14]+"','"+results_small_links[14]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[14] = 0;
            labelHeart14SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[14]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart14SearchSongsMouseClicked

    private void labelHeart15SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart15SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[15]==0){
            results_num[15] = 1;
            labelHeart15SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[15]+"','"+results_small_links[15]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[15]+"','"+results_small_links[15]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[15] = 0;
            labelHeart15SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[15]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart15SearchSongsMouseClicked

    private void labelHeart16SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart16SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[16]==0){
            results_num[16] = 1;
            labelHeart16SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[16]+"','"+results_small_links[16]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[16]+"','"+results_small_links[16]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[16] = 0;
            labelHeart16SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[16]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart16SearchSongsMouseClicked

    private void labelHeart17SearchSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeart17SearchSongsMouseClicked
        Image img1 = null, img2 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("images/Liked_songs.png"));
            img2 = ImageIO.read(getClass().getResource("images/Liked_songs_red.png"));
        } catch (IOException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(results_num[17]==0){
            results_num[17] = 1;
            labelHeart17SearchSongs.setIcon(new ImageIcon(img2));
            String sql8 = "INSERT IGNORE INTO favorites(NAME,LINK,USERS_ID) VALUES ('"+results[17]+"','"+results_small_links[17]+"','"+user_id+"');";
            String sql9 = "INSERT IGNORE INTO songs(NAME,LINK) VALUES ('"+results[17]+"','"+results_small_links[17]+"');";
            try {
                st.executeUpdate(sql8);
                st.executeUpdate(sql9);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            results_num[17] = 0;
            labelHeart17SearchSongs.setIcon(new ImageIcon(img1));
            String sql8 = "DELETE FROM favorites WHERE NAME='"+results[17]+"' AND USERS_ID='"+user_id+"';";
            try {
                st.executeUpdate(sql8);
                updateTables();
            } catch (SQLException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_labelHeart17SearchSongsMouseClicked

    private void labelArtistNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelArtistNameMouseClicked
        String songname = labelArtistName.getText().replaceAll(" ","\\_").replaceAll("A", "a").replaceAll("B", "b").replaceAll("C", "c").replaceAll("D", "d").replaceAll("E", "e").replaceAll("F", "f").replaceAll("G", "g").replaceAll("H", "h").replaceAll("I", "i").replaceAll("J", "j").replaceAll("K", "k").replaceAll("L","l").replaceAll("M", "m").replaceAll("N", "n").replaceAll("O", "o").replaceAll("P", "p").replaceAll("Q", "q").replaceAll("R", "r").replaceAll("S", "s").replaceAll("T", "t").replaceAll("U", "u").replaceAll("V", "v").replaceAll("W", "w").replaceAll("X", "x").replaceAll("Y", "y").replaceAll("Z", "z");
        openWebPage("https://en.wikipedia.org/wiki/"+songname);
    }//GEN-LAST:event_labelArtistNameMouseClicked

    private void labelSongNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSongNameMouseClicked
        String artistname = labelSongName.getText().replaceAll(" ","\\_");
        openWebPage("https://en.wikipedia.org/wiki/"+artistname);
    }//GEN-LAST:event_labelSongNameMouseClicked

    private void labelPreviousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPreviousMouseClicked
        if(local_playback_counter!=0){
            clip.stop();
            x = 0;
            if(first_previous==0){
                if(local_playback_counter!=1){
                    local_playback_counter = local_playback_counter - 2;
                } else {
                    local_playback_counter--;
                }
            } else {
                local_playback_counter--;
            }
            first_previous = 1;
            File musicPath = new File(playback_path[local_playback_counter]);
            String filename = musicPath.getName().replaceAll(".*\\-\\ ","").replace(".wav","");
            String artistname = musicPath.getName().replaceAll("\\ \\-.*.wav","");
            labelSongName.setText(filename);
            labelArtistName.setText(artistname);
            try {
                audioInput = AudioSystem.getAudioInputStream(musicPath);
            } catch (UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image img2 = null;
            try {
                img2 = ImageIO.read(getClass().getResource("images/Pause.png"));
            } catch (IOException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(x==0){
                try {
                    beginMusic();
                    x = 1;
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pau = 0;
            i = 1;
            labelPlay.setIcon(new ImageIcon(img2));
            labelLocalSongsImage.setEnabled(false);
            labelLocalSong.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "There are no more previous tracks.");
        }
    }//GEN-LAST:event_labelPreviousMouseClicked

    private void labelNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNextMouseClicked
        if(local_playback_counter==(playback_counter-1) || local_playback_counter==(playback_counter)){
            JOptionPane.showMessageDialog(null, "There are no next tracks.");
        } else {
            clip.stop();
            x = 0;
            local_playback_counter++;
            File musicPath = new File(playback_path[local_playback_counter]);
            String filename = musicPath.getName().replaceAll(".*\\-\\ ","").replace(".wav","");
            String artistname = musicPath.getName().replaceAll("\\ \\-.*.wav","");
            labelSongName.setText(filename);
            labelArtistName.setText(artistname);
            try {
                audioInput = AudioSystem.getAudioInputStream(musicPath);
            } catch (UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image img2 = null;
            try {
                img2 = ImageIO.read(getClass().getResource("images/Pause.png"));
            } catch (IOException ex) {
                Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(x==0){
                try {
                    beginMusic();
                    x = 1;
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pau = 0;
            i = 1;
            labelPlay.setIcon(new ImageIcon(img2));
            labelLocalSongsImage.setEnabled(false);
            labelLocalSong.setEnabled(false);
        }
    }//GEN-LAST:event_labelNextMouseClicked

    private void labelBackViewCompleteChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackViewCompleteChartsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelCharts);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelBackViewCompleteChartsMouseClicked

    private void labelReturnViewCompleteChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReturnViewCompleteChartsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelCharts);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_labelReturnViewCompleteChartsMouseClicked

    private void tableViewCompleteChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViewCompleteChartsMouseClicked
        int row = tableViewCompleteCharts.rowAtPoint(evt.getPoint());
        int col = tableViewCompleteCharts.columnAtPoint(evt.getPoint());
        for(int a=0;a<=row;a++){
            if (row == a && col >= 0) {
                String link = (String) tableViewCompleteCharts.getModel().getValueAt(a,1);
                openWebPage(link);
            }
        }
    }//GEN-LAST:event_tableViewCompleteChartsMouseClicked

    private void buttonViewChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonViewChartsMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelViewCompleteCharts);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_buttonViewChartsMouseClicked

    private void buttonListenOnYoutubeViewCompleteChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListenOnYoutubeViewCompleteChartsMouseClicked
        openWebPage("http://www.youtube.com/watch_videos?video_ids="+small[1]+","+small[2]+","+small[3]+","+small[4]+","+small[5]+","+small[6]+","+small[7]+","+small[8]+","+small[9]+","+small[10]+","+small[11]+","+small[12]+","+small[13]+","+small[14]+","+small[15]+","+small[16]+","+small[17]+","+small[18]+","+small[19]+","+small[20]+","+small[21]+","+small[22]+","+small[23]+","+small[24]+","+small[25]+","+small[26]+","+small[27]+","+small[28]+","+small[29]+","+small[30]+","+small[31]+","+small[32]+","+small[33]+","+small[34]+","+small[35]+","+small[36]+","+small[37]+","+small[38]+","+small[39]+","+small[40]+","+small[41]+","+small[42]+","+small[43]+","+small[44]+","+small[45]+","+small[46]+","+small[47]+","+small[48]+","+small[49]+","+small[50]);
    }//GEN-LAST:event_buttonListenOnYoutubeViewCompleteChartsMouseClicked

    private void tableFavoriteSongsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFavoriteSongsMouseClicked
        int row = tableFavoriteSongs.rowAtPoint(evt.getPoint());
        int col = tableFavoriteSongs.columnAtPoint(evt.getPoint());
        for(int a=0;a<=row;a++){
            if (row == a && col >= 0) {
                String link = (String) tableFavoriteSongs.getModel().getValueAt(a,1);
                openWebPage("http://www.youtube.com/video/"+link);
            }
        }
    }//GEN-LAST:event_tableFavoriteSongsMouseClicked

    private void buttonLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLogOutMouseClicked
        setVisible(false);
        dispose();
        LogIn_SignUp.main(null);
    }//GEN-LAST:event_buttonLogOutMouseClicked

    private void labelBackImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackImageMouseClicked
        panelSearchProfiles.removeAll();
        panelSearchProfiles.repaint();
        panelSearchProfiles.revalidate();
        panelSearchProfiles.add(panelMainDynamic);
        panelSearchProfiles.repaint();
        panelSearchProfiles.revalidate();
    }//GEN-LAST:event_labelBackImageMouseClicked

    private void labelReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReturnMouseClicked
        panelSearchProfiles.removeAll();
        panelSearchProfiles.repaint();
        panelSearchProfiles.revalidate();
        panelSearchProfiles.add(panelMainDynamic);
        panelSearchProfiles.repaint();
        panelSearchProfiles.revalidate();
    }//GEN-LAST:event_labelReturnMouseClicked

    private void tableProfilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProfilesMouseClicked
        String searched_user = textFieldSearch.getText();
        String sql6 = "SELECT * FROM users WHERE FIRSTNAME = '"+searched_user+"'";
        String searched_user_id;
        try {
            ResultSet rs = st.executeQuery(sql6);
            if(rs.next() == false){
                JOptionPane.showMessageDialog(null, "User does not exist. Search using only a profile's first name.");
            } else {
                labelFirstName.setText(rs.getString("FIRSTNAME"));
                labelLastName.setText(rs.getString("LASTNAME"));
                searched_user_id = rs.getString("USERS_ID");
                try {
                    DefaultTableModel tm = (DefaultTableModel)tableProfiles.getModel();
                    tm.setRowCount(0);
                    String sql7 = "SELECT * FROM playlists WHERE USERS_ID = '"+searched_user_id+"';";
                    ResultSet rs2 = st.executeQuery(sql7);
                    while(rs2.next()){
                        Object ii[] = {rs2.getString("NAME"),rs2.getString("GENRE"),rs2.getString("YOUTUBE_LINKS")};
                        tm.addRow(ii);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableProfilesMouseClicked

    private void textFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchActionPerformed
        String searched_user = textFieldSearch.getText();
        String sql6 = "SELECT * FROM users WHERE FIRSTNAME = '"+searched_user+"'";
        String searched_user_id;
        try {
            ResultSet rs = st.executeQuery(sql6);
            if(rs.next() == false){
                JOptionPane.showMessageDialog(null, "User does not exist. Search using only a profile's first name.");
            } else {
                labelFirstName.setText(rs.getString("FIRSTNAME"));
                labelLastName.setText(rs.getString("LASTNAME"));
                searched_user_id = rs.getString("USERS_ID");
                try {
                    DefaultTableModel tm = (DefaultTableModel)tableProfiles.getModel();
                    tm.setRowCount(0);
                    String sql7 = "SELECT * FROM playlists WHERE USERS_ID = '"+searched_user_id+"';";
                    ResultSet rs2 = st.executeQuery(sql7);
                    while(rs2.next()){
                        Object ii[] = {rs2.getString("NAME"),rs2.getString("GENRE"),rs2.getString("YOUTUBE_LINKS")};
                        tm.addRow(ii);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_textFieldSearchActionPerformed

    private void buttonSearchProfilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSearchProfilesMouseClicked
        panelMainDynamic.removeAll();
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
        panelMainDynamic.add(panelSearchProfiles);
        panelMainDynamic.repaint();
        panelMainDynamic.revalidate();
    }//GEN-LAST:event_buttonSearchProfilesMouseClicked

    private void textFieldSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldSearchMouseClicked
        textFieldSearch.setText("");
    }//GEN-LAST:event_textFieldSearchMouseClicked

    private void labelpaletteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelpaletteMouseClicked
       
        String[] options = {"Reading","Chill","Party","Workout","Sad","Default"};
        ImageIcon icon = new ImageIcon("src/musically/images/Change_color_large.png");
        String n = (String)JOptionPane.showInputDialog(null, "Select one of the colors below to change the app's user interface.", "Change User-Interface Colour", JOptionPane.QUESTION_MESSAGE, icon, options, options[2]);
        switch(n){
            case "Reading":
              //(new Color(31, 191, 87));
                panelHome.setBackground(new Color(22, 140, 64));
                panelSearch.setBackground(new Color(22, 140, 64));
                panelCharts.setBackground(new Color(22, 140, 64));
                panelPlaylist.setBackground(new Color(22, 140, 64));
                panelPlayPlaylist.setBackground(new Color(22, 140, 64));
                panelTopArtists.setBackground(new Color(22, 140, 64));
                panelFavoriteSongs.setBackground(new Color(22, 140, 64));
                panelFavoriteArtists.setBackground(new Color(22, 140, 64));
                panelSearchSongs.setBackground(new Color(22, 140, 64));
                panelViewCompleteCharts.setBackground(new Color(22, 140, 64));
                panelSearchProfiles.setBackground(new Color(22, 140, 64));
                panelMainDynamic.setBackground(new Color(22, 140, 64));
                
                
                //ta upoloipa
                panelPlayer.setBackground(new Color(13, 13, 13));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                
                
                 
                break;
            case "Chill":
                  panelHome.setBackground(new Color(51, 69, 166));
                panelSearch.setBackground(new Color(51, 69, 166));
                panelCharts.setBackground(new Color(51, 69, 166));
                panelPlaylist.setBackground(new Color(51, 69, 166));
                panelPlayPlaylist.setBackground(new Color(51, 69, 166));
                panelTopArtists.setBackground(new Color(51, 69, 166));
                panelFavoriteSongs.setBackground(new Color(51, 69, 166));
                panelFavoriteArtists.setBackground(new Color(51, 69, 166));
                panelSearchSongs.setBackground(new Color(51, 69, 166));
                panelViewCompleteCharts.setBackground(new Color(51, 69, 166));
                panelSearchProfiles.setBackground(new Color(51, 69, 166));
                panelMainDynamic.setBackground(new Color(51, 69, 166));
                
                
                //ta upoloipa
                panelPlayer.setBackground(new Color(41, 52, 115));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                break;
            case "Party":
                      panelHome.setBackground(new Color(191, 17, 17));
                panelSearch.setBackground(new Color(191, 17, 17));
                panelCharts.setBackground(new Color(191, 17, 17));
                panelPlaylist.setBackground(new Color(191, 17, 17));
                panelPlayPlaylist.setBackground(new Color(191, 17, 17));
                panelTopArtists.setBackground(new Color(191, 17, 17));
                panelFavoriteSongs.setBackground(new Color(191, 17, 17));
                panelFavoriteArtists.setBackground(new Color(191, 17, 17));
                panelSearchSongs.setBackground(new Color(191, 17, 17));
                panelViewCompleteCharts.setBackground(new Color(191, 17, 17));
                panelSearchProfiles.setBackground(new Color(191, 17, 17));
                panelMainDynamic.setBackground(new Color(191, 17, 17));
                
                
                //ta upoloipa
                panelPlayer.setBackground(new Color(89, 11, 11));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                break;
            case "Workout":
                    panelHome.setBackground(new Color(61, 121, 242));
                panelSearch.setBackground(new Color(61, 121, 242));
                panelCharts.setBackground(new Color(61, 121, 242));
                panelPlaylist.setBackground(new Color(61, 121, 242));
                panelPlayPlaylist.setBackground(new Color(61, 121, 242));
                panelTopArtists.setBackground(new Color(61, 121, 242));
                panelFavoriteSongs.setBackground(new Color(61, 121, 242));
                panelFavoriteArtists.setBackground(new Color(61, 121, 242));
                panelSearchSongs.setBackground(new Color(61, 121, 242));
                panelViewCompleteCharts.setBackground(new Color(61, 121, 242));
                panelSearchProfiles.setBackground(new Color(61, 121, 242));
                panelMainDynamic.setBackground(new Color(61, 121, 242));
                
                //242, 148, 65    
                //ta upoloipa
                panelPlayer.setBackground(new Color(48, 97, 242));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                break;
            case "Sad":
                panelHome.setBackground(new Color(100, 98, 115));
                panelSearch.setBackground(new Color(100, 98, 115));
                panelCharts.setBackground(new Color(100, 98, 115));
                panelPlaylist.setBackground(new Color(100, 98, 115));
                panelPlayPlaylist.setBackground(new Color(100, 98, 115));
                panelTopArtists.setBackground(new Color(100, 98, 115));
                panelFavoriteSongs.setBackground(new Color(100, 98, 115));
                panelFavoriteArtists.setBackground(new Color(100, 98, 115));
                panelSearchSongs.setBackground(new Color(100, 98, 115));
                panelViewCompleteCharts.setBackground(new Color(100, 98, 115));
                panelSearchProfiles.setBackground(new Color(100, 98, 115));
                panelMainDynamic.setBackground(new Color(100, 98, 115));
                
                //242, 148, 65
                //ta upoloipa
                panelPlayer.setBackground(new Color(50, 49, 64));
                panelMainStatic.setBackground(new Color(30, 28, 38));
                break;
                case "Default":
              //(new Color(31, 191, 87));
                panelHome.setBackground(new Color(28,28,28));
                panelSearch.setBackground(new Color(28,28,28));
                panelCharts.setBackground(new Color(28,28,28));
                panelPlaylist.setBackground(new Color(28,28,28));
                panelPlayPlaylist.setBackground(new Color(28,28,28));
                panelTopArtists.setBackground(new Color(28,28,28));
                panelFavoriteSongs.setBackground(new Color(28,28,28));
                panelFavoriteArtists.setBackground(new Color(28,28,28));
                panelSearchSongs.setBackground(new Color(28,28,28));
                panelViewCompleteCharts.setBackground(new Color(28,28,28));
                panelSearchProfiles.setBackground(new Color(28,28,28));
                panelMainDynamic.setBackground(new Color(28,28,28));
                
                
                //ta upoloipa
                panelPlayer.setBackground(new Color(24,24,24));
                panelMainStatic.setBackground(new Color(0,0,0));
                
                
                 
                break;
          
        }
            


    }//GEN-LAST:event_labelpaletteMouseClicked

    private void labelmoodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelmoodMouseClicked
      String[] options = {"Reading","Chill","Party","Workout","Sad","Default"};
        ImageIcon icon = new ImageIcon("src/musically/images/Change_color_large.png");
        String n = (String)JOptionPane.showInputDialog(null, "Select your mood.", "Change User-Interface Colour", JOptionPane.QUESTION_MESSAGE, icon, options, options[2]);
        switch(n){
            case "Reading":
              //(new Color(31, 191, 87));
                openWebPage("https://www.youtube.com/watch?v=r5yaoMjaAmE&list=PLj6hQzOG6piDxWxG6PJCXsJBVtqz_4Fm9");
                labelmood.setIcon(new ImageIcon("C:\\Users\\onoma\\Documents\\NetBeansProjects\\Musically\\reading.png"));
                panelHome.setBackground(new Color(22, 140, 64));
                panelSearch.setBackground(new Color(22, 140, 64));
                panelCharts.setBackground(new Color(22, 140, 64));
                panelPlaylist.setBackground(new Color(22, 140, 64));
                panelPlayPlaylist.setBackground(new Color(22, 140, 64));
                panelTopArtists.setBackground(new Color(22, 140, 64));
                panelFavoriteSongs.setBackground(new Color(22, 140, 64));
                panelFavoriteArtists.setBackground(new Color(22, 140, 64));
                panelSearchSongs.setBackground(new Color(22, 140, 64));
                panelViewCompleteCharts.setBackground(new Color(22, 140, 64));
                panelSearchProfiles.setBackground(new Color(22, 140, 64));
                panelMainDynamic.setBackground(new Color(22, 140, 64));
                
                
                //ta upoloipa
                panelPlayer.setBackground(new Color(13, 13, 13));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                
                
                 
                break;
            case "Chill":
                  openWebPage("https://www.youtube.com/watch?v=D-YDEyuDxWU&list=PLgzTt0k8mXzEpH7-dOCHqRZOsakqXmzmG");
                  labelmood.setIcon(new ImageIcon("C:\\Users\\onoma\\Documents\\NetBeansProjects\\Musically\\sun.png"));
                  panelHome.setBackground(new Color(51, 69, 166));
                panelSearch.setBackground(new Color(51, 69, 166));
                panelCharts.setBackground(new Color(51, 69, 166));
                panelPlaylist.setBackground(new Color(51, 69, 166));
                panelPlayPlaylist.setBackground(new Color(51, 69, 166));
                panelTopArtists.setBackground(new Color(51, 69, 166));
                panelFavoriteSongs.setBackground(new Color(51, 69, 166));
                panelFavoriteArtists.setBackground(new Color(51, 69, 166));
                panelSearchSongs.setBackground(new Color(51, 69, 166));
                panelViewCompleteCharts.setBackground(new Color(51, 69, 166));
                panelSearchProfiles.setBackground(new Color(51, 69, 166));
                panelMainDynamic.setBackground(new Color(51, 69, 166));
                
                
                //ta upoloipa
                panelPlayer.setBackground(new Color(41, 52, 115));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                break;
            case "Party":
                 openWebPage("https://www.youtube.com/watch?v=YVkUvmDQ3HY&list=PL39z-AAkkats9VE4V8gdQyIjqp21nao9p");
                  labelmood.setIcon(new ImageIcon("C:\\Users\\onoma\\Documents\\NetBeansProjects\\Musically\\party-blower.png"));
                      panelHome.setBackground(new Color(191, 17, 17));
                panelSearch.setBackground(new Color(191, 17, 17));
                panelCharts.setBackground(new Color(191, 17, 17));
                panelPlaylist.setBackground(new Color(191, 17, 17));
                panelPlayPlaylist.setBackground(new Color(191, 17, 17));
                panelTopArtists.setBackground(new Color(191, 17, 17));
                panelFavoriteSongs.setBackground(new Color(191, 17, 17));
                panelFavoriteArtists.setBackground(new Color(191, 17, 17));
                panelSearchSongs.setBackground(new Color(191, 17, 17));
                panelViewCompleteCharts.setBackground(new Color(191, 17, 17));
                panelSearchProfiles.setBackground(new Color(191, 17, 17));
                panelMainDynamic.setBackground(new Color(191, 17, 17));
                
                
                //ta upoloipa
                panelPlayer.setBackground(new Color(89, 11, 11));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                break;
            case "Workout":
                openWebPage("https://www.youtube.com/watch?v=5qm8PH4xAss&list=PLxA687tYuMWih3dMICezqR_Y7hXBKM5qn");
                 labelmood.setIcon(new ImageIcon("C:\\Users\\onoma\\Documents\\NetBeansProjects\\Musically\\yoga.png"));
                    panelHome.setBackground(new Color(61, 121, 242));
                panelSearch.setBackground(new Color(61, 121, 242));
                panelCharts.setBackground(new Color(61, 121, 242));
                panelPlaylist.setBackground(new Color(61, 121, 242));
                panelPlayPlaylist.setBackground(new Color(61, 121, 242));
                panelTopArtists.setBackground(new Color(61, 121, 242));
                panelFavoriteSongs.setBackground(new Color(61, 121, 242));
                panelFavoriteArtists.setBackground(new Color(61, 121, 242));
                panelSearchSongs.setBackground(new Color(61, 121, 242));
                panelViewCompleteCharts.setBackground(new Color(61, 121, 242));
                panelSearchProfiles.setBackground(new Color(61, 121, 242));
                panelMainDynamic.setBackground(new Color(61, 121, 242));
                
                //242, 148, 65    
                //ta upoloipa
                panelPlayer.setBackground(new Color(48, 97, 242));
                panelMainStatic.setBackground(new Color(13, 13, 13));
                break;
            case "Sad":
                  openWebPage("https://www.youtube.com/watch?v=V1Pl8CzNzCw&list=PLgzTt0k8mXzHcKebL8d0uYHfawiARhQja");
                   labelmood.setIcon(new ImageIcon("C:\\Users\\onoma\\Documents\\NetBeansProjects\\Musically\\sad.png"));
                panelHome.setBackground(new Color(100, 98, 115));
                panelSearch.setBackground(new Color(100, 98, 115));
                panelCharts.setBackground(new Color(100, 98, 115));
                panelPlaylist.setBackground(new Color(100, 98, 115));
                panelPlayPlaylist.setBackground(new Color(100, 98, 115));
                panelTopArtists.setBackground(new Color(100, 98, 115));
                panelFavoriteSongs.setBackground(new Color(100, 98, 115));
                panelFavoriteArtists.setBackground(new Color(100, 98, 115));
                panelSearchSongs.setBackground(new Color(100, 98, 115));
                panelViewCompleteCharts.setBackground(new Color(100, 98, 115));
                panelSearchProfiles.setBackground(new Color(100, 98, 115));
                panelMainDynamic.setBackground(new Color(100, 98, 115));
                
                //242, 148, 65
                //ta upoloipa
                panelPlayer.setBackground(new Color(50, 49, 64));
                panelMainStatic.setBackground(new Color(30, 28, 38));
                break;
               
                 case "Default":
                 labelmood.setIcon(new ImageIcon("C:\\Users\\onoma\\Documents\\NetBeansProjects\\Musically\\angry-face.png"));
            break;
        }
    }//GEN-LAST:event_labelmoodMouseClicked

    public void openWebPage(String url){
        try {         
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
    catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Musically_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Musically_main().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Musically_main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCreatePlaylist;
    private javax.swing.JButton buttonDeleteAllPlaylists;
    private javax.swing.JButton buttonListenOnYoutube;
    private javax.swing.JButton buttonListenOnYoutubeFavoriteArtists;
    private javax.swing.JButton buttonListenOnYoutubeFavoriteSongs;
    private javax.swing.JButton buttonListenOnYoutubeViewCompleteCharts;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonSearchInformation;
    private javax.swing.JButton buttonSearchProfiles;
    private javax.swing.JButton buttonViewCharts;
    private javax.swing.JButton buttonViewFavoriteSongs;
    private javax.swing.JButton buttonViewFavoritesArtists;
    private javax.swing.JButton buttonViewPlaylists;
    private javax.swing.JEditorPane editorPaneSearchResults;
    private javax.swing.JFormattedTextField formattedTextFieldPlaylistGenre;
    private javax.swing.JFormattedTextField formattedTextFieldPlaylistName;
    private javax.swing.JFormattedTextField formattedTextFieldSongLinks;
    private javax.swing.JLabel labelArtist10TopArtists;
    private javax.swing.JLabel labelArtist11TopArtists;
    private javax.swing.JLabel labelArtist12TopArtists;
    private javax.swing.JLabel labelArtist13TopArtists;
    private javax.swing.JLabel labelArtist14TopArtists;
    private javax.swing.JLabel labelArtist15TopArtists;
    private javax.swing.JLabel labelArtist16TopArtists;
    private javax.swing.JLabel labelArtist17TopArtists;
    private javax.swing.JLabel labelArtist18TopArtists;
    private javax.swing.JLabel labelArtist19TopArtists;
    private javax.swing.JLabel labelArtist1Home;
    private javax.swing.JLabel labelArtist1TopArtists;
    private javax.swing.JLabel labelArtist20TopArtists;
    private javax.swing.JLabel labelArtist21TopArtists;
    private javax.swing.JLabel labelArtist22TopArtists;
    private javax.swing.JLabel labelArtist23TopArtists;
    private javax.swing.JLabel labelArtist24TopArtists;
    private javax.swing.JLabel labelArtist25TopArtists;
    private javax.swing.JLabel labelArtist26TopArtists;
    private javax.swing.JLabel labelArtist27TopArtists;
    private javax.swing.JLabel labelArtist28TopArtists;
    private javax.swing.JLabel labelArtist29TopArtists;
    private javax.swing.JLabel labelArtist2Home;
    private javax.swing.JLabel labelArtist2TopArtists;
    private javax.swing.JLabel labelArtist30TopArtists;
    private javax.swing.JLabel labelArtist31TopArtists;
    private javax.swing.JLabel labelArtist32TopArtists;
    private javax.swing.JLabel labelArtist33TopArtists;
    private javax.swing.JLabel labelArtist34TopArtists;
    private javax.swing.JLabel labelArtist35TopArtists;
    private javax.swing.JLabel labelArtist36TopArtists;
    private javax.swing.JLabel labelArtist3Home;
    private javax.swing.JLabel labelArtist3TopArtists;
    private javax.swing.JLabel labelArtist4TopArtists;
    private javax.swing.JLabel labelArtist5TopArtists;
    private javax.swing.JLabel labelArtist6TopArtists;
    private javax.swing.JLabel labelArtist7TopArtists;
    private javax.swing.JLabel labelArtist8TopArtists;
    private javax.swing.JLabel labelArtist9TopArtists;
    private javax.swing.JLabel labelArtistName;
    private javax.swing.JLabel labelBackFavoriteSongs;
    private javax.swing.JLabel labelBackImage;
    private javax.swing.JLabel labelBackPlayPlaylist;
    private javax.swing.JLabel labelBackSearch;
    private javax.swing.JLabel labelBackViewCompleteCharts;
    private javax.swing.JLabel labelBckFavoriteArtists;
    private javax.swing.JLabel labelCharts;
    private javax.swing.JLabel labelChartsImage;
    private javax.swing.JLabel labelChooseLocalSong;
    private javax.swing.JLabel labelCommend;
    private javax.swing.JLabel labelCreatePlaylist;
    private javax.swing.JLabel labelCurentLatName;
    private javax.swing.JLabel labelCurrentFirstName;
    private javax.swing.JLabel labelEmblem;
    private javax.swing.JLabel labelFirstName;
    private javax.swing.JLabel labelFreshNewMusic;
    private javax.swing.JLabel labelGoBackSearch;
    private javax.swing.JLabel labelHeart10Artists;
    private javax.swing.JLabel labelHeart10Charts;
    private javax.swing.JLabel labelHeart10SearchSongs;
    private javax.swing.JLabel labelHeart11Artists;
    private javax.swing.JLabel labelHeart11Charts;
    private javax.swing.JLabel labelHeart11SearchSongs;
    private javax.swing.JLabel labelHeart12Artists;
    private javax.swing.JLabel labelHeart12Charts;
    private javax.swing.JLabel labelHeart12SearchSongs;
    private javax.swing.JLabel labelHeart13Artists;
    private javax.swing.JLabel labelHeart13Charts;
    private javax.swing.JLabel labelHeart13SearchSongs;
    private javax.swing.JLabel labelHeart14Artists;
    private javax.swing.JLabel labelHeart14Charts;
    private javax.swing.JLabel labelHeart14SearchSongs;
    private javax.swing.JLabel labelHeart15Artists;
    private javax.swing.JLabel labelHeart15Charts;
    private javax.swing.JLabel labelHeart15SearchSongs;
    private javax.swing.JLabel labelHeart16Artists;
    private javax.swing.JLabel labelHeart16Charts;
    private javax.swing.JLabel labelHeart16SearchSongs;
    private javax.swing.JLabel labelHeart17Artists;
    private javax.swing.JLabel labelHeart17Charts;
    private javax.swing.JLabel labelHeart17SearchSongs;
    private javax.swing.JLabel labelHeart18Artists;
    private javax.swing.JLabel labelHeart18Charts;
    private javax.swing.JLabel labelHeart19Artists;
    private javax.swing.JLabel labelHeart1Artists;
    private javax.swing.JLabel labelHeart1Charts;
    private javax.swing.JLabel labelHeart1SearchSongs;
    private javax.swing.JLabel labelHeart20Artists;
    private javax.swing.JLabel labelHeart21Artists;
    private javax.swing.JLabel labelHeart22Artists;
    private javax.swing.JLabel labelHeart23Artists;
    private javax.swing.JLabel labelHeart24Artists;
    private javax.swing.JLabel labelHeart25Artists;
    private javax.swing.JLabel labelHeart26Artists;
    private javax.swing.JLabel labelHeart27Artists;
    private javax.swing.JLabel labelHeart28Artists;
    private javax.swing.JLabel labelHeart29Artists;
    private javax.swing.JLabel labelHeart2Artists;
    private javax.swing.JLabel labelHeart2Charts;
    private javax.swing.JLabel labelHeart2SearchSongs;
    private javax.swing.JLabel labelHeart30Artists;
    private javax.swing.JLabel labelHeart31Artists;
    private javax.swing.JLabel labelHeart32Artists;
    private javax.swing.JLabel labelHeart33Artists;
    private javax.swing.JLabel labelHeart34Artists;
    private javax.swing.JLabel labelHeart35Artists;
    private javax.swing.JLabel labelHeart36Artists;
    private javax.swing.JLabel labelHeart3Artists;
    private javax.swing.JLabel labelHeart3Charts;
    private javax.swing.JLabel labelHeart3SearchSongs;
    private javax.swing.JLabel labelHeart4Artists;
    private javax.swing.JLabel labelHeart4Charts;
    private javax.swing.JLabel labelHeart4SearchSongs;
    private javax.swing.JLabel labelHeart5Artists;
    private javax.swing.JLabel labelHeart5Charts;
    private javax.swing.JLabel labelHeart5SearchSongs;
    private javax.swing.JLabel labelHeart6Artists;
    private javax.swing.JLabel labelHeart6Charts;
    private javax.swing.JLabel labelHeart6SearchSongs;
    private javax.swing.JLabel labelHeart7Artists;
    private javax.swing.JLabel labelHeart7Charts;
    private javax.swing.JLabel labelHeart7SearchSongs;
    private javax.swing.JLabel labelHeart8Artists;
    private javax.swing.JLabel labelHeart8Charts;
    private javax.swing.JLabel labelHeart8SearchSongs;
    private javax.swing.JLabel labelHeart9Artists;
    private javax.swing.JLabel labelHeart9Charts;
    private javax.swing.JLabel labelHeart9SearchSongs;
    private javax.swing.JButton labelHome;
    private javax.swing.JLabel labelHomeImage;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel labelLocalSong;
    private javax.swing.JLabel labelLocalSongsImage;
    private javax.swing.JLabel labelMusically;
    private javax.swing.JLabel labelNext;
    private javax.swing.JLabel labelPlay;
    private javax.swing.JLabel labelPlayerImage;
    private javax.swing.JLabel labelPlaylist1Home;
    private javax.swing.JLabel labelPlaylist2Home;
    private javax.swing.JLabel labelPlaylist3Home;
    private javax.swing.JLabel labelPlaylistGenre;
    private javax.swing.JLabel labelPlaylistName;
    private javax.swing.JLabel labelPlaylists;
    private javax.swing.JLabel labelPlaylistsImage;
    private javax.swing.JLabel labelPrevious;
    private javax.swing.JLabel labelRepeat;
    private javax.swing.JLabel labelReturn;
    private javax.swing.JLabel labelReturnHomeFavoriteArtists;
    private javax.swing.JLabel labelReturnHomeFavoriteSongs;
    private javax.swing.JLabel labelReturnViewCompleteCharts;
    private javax.swing.JLabel labelSavedPlaylistsHome;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JLabel labelSearchGenius;
    private javax.swing.JLabel labelSearchIcon;
    private javax.swing.JLabel labelSearchIconSearchSongs;
    private javax.swing.JLabel labelSearchImage;
    private javax.swing.JLabel labelSearchSongViewCount1;
    private javax.swing.JLabel labelSearchSongViewCount10;
    private javax.swing.JLabel labelSearchSongViewCount11;
    private javax.swing.JLabel labelSearchSongViewCount12;
    private javax.swing.JLabel labelSearchSongViewCount13;
    private javax.swing.JLabel labelSearchSongViewCount14;
    private javax.swing.JLabel labelSearchSongViewCount15;
    private javax.swing.JLabel labelSearchSongViewCount16;
    private javax.swing.JLabel labelSearchSongViewCount17;
    private javax.swing.JLabel labelSearchSongViewCount2;
    private javax.swing.JLabel labelSearchSongViewCount3;
    private javax.swing.JLabel labelSearchSongViewCount4;
    private javax.swing.JLabel labelSearchSongViewCount5;
    private javax.swing.JLabel labelSearchSongViewCount6;
    private javax.swing.JLabel labelSearchSongViewCount7;
    private javax.swing.JLabel labelSearchSongViewCount8;
    private javax.swing.JLabel labelSearchSongViewCount9;
    private javax.swing.JLabel labelSearchSongsDate1;
    private javax.swing.JLabel labelSearchSongsDate10;
    private javax.swing.JLabel labelSearchSongsDate11;
    private javax.swing.JLabel labelSearchSongsDate12;
    private javax.swing.JLabel labelSearchSongsDate13;
    private javax.swing.JLabel labelSearchSongsDate14;
    private javax.swing.JLabel labelSearchSongsDate15;
    private javax.swing.JLabel labelSearchSongsDate16;
    private javax.swing.JLabel labelSearchSongsDate17;
    private javax.swing.JLabel labelSearchSongsDate2;
    private javax.swing.JLabel labelSearchSongsDate3;
    private javax.swing.JLabel labelSearchSongsDate4;
    private javax.swing.JLabel labelSearchSongsDate5;
    private javax.swing.JLabel labelSearchSongsDate6;
    private javax.swing.JLabel labelSearchSongsDate7;
    private javax.swing.JLabel labelSearchSongsDate8;
    private javax.swing.JLabel labelSearchSongsDate9;
    private javax.swing.JLabel labelSearchWikipedia;
    private javax.swing.JLabel labelSearchedProfile;
    private javax.swing.JLabel labelSelectPlaylistToPlay;
    private javax.swing.JLabel labelShuffle;
    private javax.swing.JLabel labelSong10Charts;
    private javax.swing.JLabel labelSong11Charts;
    private javax.swing.JLabel labelSong12Charts;
    private javax.swing.JLabel labelSong13Charts;
    private javax.swing.JLabel labelSong14Charts;
    private javax.swing.JLabel labelSong15Charts;
    private javax.swing.JLabel labelSong16Charts;
    private javax.swing.JLabel labelSong17Charts;
    private javax.swing.JLabel labelSong18Charts;
    private javax.swing.JLabel labelSong1Charts;
    private javax.swing.JLabel labelSong1Home;
    private javax.swing.JLabel labelSong2Charts;
    private javax.swing.JLabel labelSong2Home;
    private javax.swing.JLabel labelSong3Charts;
    private javax.swing.JLabel labelSong3Home;
    private javax.swing.JLabel labelSong4Charts;
    private javax.swing.JLabel labelSong5Charts;
    private javax.swing.JLabel labelSong6Charts;
    private javax.swing.JLabel labelSong7Charts;
    private javax.swing.JLabel labelSong8Charts;
    private javax.swing.JLabel labelSong9Charts;
    private javax.swing.JLabel labelSongName;
    private javax.swing.JLabel labelSongSearchResult1;
    private javax.swing.JLabel labelSongSearchResult10;
    private javax.swing.JLabel labelSongSearchResult11;
    private javax.swing.JLabel labelSongSearchResult12;
    private javax.swing.JLabel labelSongSearchResult13;
    private javax.swing.JLabel labelSongSearchResult14;
    private javax.swing.JLabel labelSongSearchResult15;
    private javax.swing.JLabel labelSongSearchResult16;
    private javax.swing.JLabel labelSongSearchResult17;
    private javax.swing.JLabel labelSongSearchResult2;
    private javax.swing.JLabel labelSongSearchResult3;
    private javax.swing.JLabel labelSongSearchResult4;
    private javax.swing.JLabel labelSongSearchResult5;
    private javax.swing.JLabel labelSongSearchResult6;
    private javax.swing.JLabel labelSongSearchResult7;
    private javax.swing.JLabel labelSongSearchResult8;
    private javax.swing.JLabel labelSongSearchResult9;
    private javax.swing.JLabel labelTopArtists;
    private javax.swing.JLabel labelTopArtistsImage;
    private javax.swing.JLabel labelTopArtistsYoutube;
    private javax.swing.JLabel labelTrendingSongs;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JLabel labelmood;
    private javax.swing.JLabel labelpalette;
    private javax.swing.JPanel panelCharts;
    private javax.swing.JPanel panelFavoriteArtists;
    private javax.swing.JPanel panelFavoriteSongs;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelMainDynamic;
    private javax.swing.JPanel panelMainStatic;
    private javax.swing.JPanel panelPlayPlaylist;
    private javax.swing.JPanel panelPlayer;
    private javax.swing.JPanel panelPlaylist;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JPanel panelSearchProfiles;
    private javax.swing.JPanel panelSearchSongs;
    private javax.swing.JPanel panelTopArtists;
    private javax.swing.JPanel panelViewCompleteCharts;
    private javax.swing.JScrollPane scrollPaneFavoriteArtists;
    private javax.swing.JScrollPane scrollPaneFavoriteSongs;
    private javax.swing.JScrollPane scrollPaneMain;
    private javax.swing.JScrollPane scrollPanePlayPlaylist;
    private javax.swing.JScrollPane scrollPanePlaylist;
    private javax.swing.JScrollPane scrollPaneSearch;
    private javax.swing.JScrollPane scrollPaneViewCompleteCharts;
    private javax.swing.JScrollPane scrollPanelSearchProfiles;
    private javax.swing.JSlider sliderVolume;
    private javax.swing.JTable tableFavoriteArtists;
    private javax.swing.JTable tableFavoriteSongs;
    private javax.swing.JTable tablePlayPlaylist;
    private javax.swing.JTable tablePlaylists;
    private javax.swing.JTable tablePlaylistsBig;
    private javax.swing.JTable tableProfiles;
    private javax.swing.JTable tableViewCompleteCharts;
    private javax.swing.JTextField textFieldGenius;
    private javax.swing.JTextField textFieldSearch;
    private javax.swing.JTextField textFieldSearchSongs;
    private javax.swing.JTextField textFieldWikipedia;
    // End of variables declaration//GEN-END:variables
}