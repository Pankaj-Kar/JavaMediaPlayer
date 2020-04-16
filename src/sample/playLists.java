package sample;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;


import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class playLists extends Controller implements Initializable{

    File playlist1 = new File("F:\\CSEDU\\2-1\\Java Project\\playList1.txt");
    BufferedReader br = new BufferedReader(new FileReader(playlist1));

    int i=5;
    String[] list=new String[5];
    public void addSong(javafx.event.ActionEvent event) throws Exception {
        BufferedWriter bw=new BufferedWriter(new FileWriter(playlist1,true));
        ///=> These objects are created for opening the dialog box for choosing media file  <= ///
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);

        URL mediaUrl = null;
        String mediaAddress = null;

        try {
            ///=> Getting the media address and converting it to string  <= ///
            mediaUrl = fileChooser.getSelectedFile().toURI().toURL();
            mediaAddress = mediaUrl.toString();

        } catch (MalformedURLException ex) {

            System.out.println(ex);
        }

        bw.append(mediaAddress);
        bw.newLine();
        bw.close();

    }


    public playLists() throws Exception {
        while(i!=0) {
            String s = br.readLine();
            list[i-1]=s;
            i--;
        }
        br.close();
    }

    File playlist2 = new File("F:\\CSEDU\\2-1\\New folder\\Java Project\\lastPlayed.txt");
    BufferedReader br2 = new BufferedReader(new FileReader(playlist2));
    public void addSong2(javafx.event.ActionEvent event) throws Exception {
        BufferedWriter bw=new BufferedWriter(new FileWriter(playlist2,true));
        ///=> These objects are created for opening the dialog box for choosing media file  <= ///
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        URL mediaUrl = null;
        String mediaAddress = null;

        try {
            ///=> Getting the media address and converting it to string  <= ///
            mediaUrl = fileChooser.getSelectedFile().toURI().toURL();
            mediaAddress = mediaUrl.toString();

        } catch (MalformedURLException ex) {

            System.out.println(ex);
        }

        bw.append(mediaAddress);
        bw.newLine();
        bw.close();

    }
    int i2=5;

    public playLists(String ss) throws Exception {
        while(i2!=0) {
            String s = br2.readLine();
            System.out.println(s);
            lastPlayedSongs[i2-1]=s;
            i2--;
        }
        br.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<5;i++) {
            myList.getItems().addAll(list[i]);
            lastPlayedSongsList.getItems().addAll(lastPlayedSongs[i]);
        }
    }

}
