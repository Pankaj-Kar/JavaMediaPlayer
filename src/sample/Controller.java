package sample;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;
import javax.swing.*;
import java.util.ResourceBundle;

public class Controller implements  Initializable {
    boolean playing=false;


   private File lastPlay = new File("F:\\CSEDU\\2-1\\New folder\\Java Project\\lastPlayed.txt");
   private BufferedReader br = new BufferedReader(new FileReader(lastPlay));


    ///=> Several Functions are created for media player <= ///
    Media media;
    MediaPlayer mediaPlayer;
    @FXML
    private
    MediaView mediaView;
    ///=> Volume Seeker <= ///
    @FXML
    Slider volumeSlider;
    ///=> Time Seeker <= ///
    @FXML
    Slider seek;
    @FXML
    public ListView<String> myList;
    @FXML
    public ListView<String> lastPlayedSongsList;

    public Controller() throws Exception {
    }

    String[] lastPlayedSongs=new String[5];
    ///=> A method for opening the file using OPENFILE button <= ///
    public void openFile(javafx.event.ActionEvent actionEvent) throws IOException {
        ///=> These objects are created for opening the dialog box for choosing media file  <= ///
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);

        URL mediaUrl = null;
        String mediaAddress = null;

        try {
            ///=> Getting the media address and converting it to string  <= ///
            mediaUrl = fileChooser.getSelectedFile().toURI().toURL();
            mediaAddress = mediaUrl.toString();
            if(playing){
                mediaPlayer.stop();
            }

        } catch (MalformedURLException ex) {

            System.out.println(ex);
        }

        ///=> Creating Media Objects <= ///
        System.out.println(mediaAddress);
        BufferedWriter bw=new BufferedWriter(new FileWriter(lastPlay));
        PrintWriter pw = new PrintWriter(bw);
      pw.println(mediaAddress);
        System.out.println(mediaAddress+"oopen");
      pw.close();
        media = new Media(mediaAddress);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        playing=true;
        ///=> Playing the media file after opening <= ///
        mediaPlayer.play();
        ///=> Fitting the media resolution <= ///
        DoubleProperty height = mediaView.fitHeightProperty();
        DoubleProperty width = mediaView.fitWidthProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));


        ///=> Volume slider/seeker properties <= ///
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });


        ///=> Time seeker properties <= ///
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                seek.setValue(newValue.toSeconds());
                seek.setMax(mediaPlayer.getTotalDuration().toSeconds());
            }
        });
        seek.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                mediaPlayer.seek(Duration.seconds(seek.getValue()));
            }
        });
    }

    int i2=5;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*System.out.println("");
        while(i2!=0) {
            String s = null;
            try {
              //  s = br.readLine();
                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            lastPlayedSongs[i2-1]=s;
            i2--;
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<5;i++){
            lastPlayedSongsList.getItems().addAll(lastPlayedSongs[i]);
        }*/
    }

    ///=> Play method for playing the media <= ///
    public void play(javafx.event.ActionEvent actionEvent) {
        mediaPlayer.play();
    }

    ///=> Pause method for pausing the media <= ///
    public void pause(javafx.event.ActionEvent actionEvent) {
        mediaPlayer.pause();
    }

    ///=> Restarting method. Using this method the media will restart <= ///
    public void restart(javafx.event.ActionEvent actionEvent) {
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();
    }
    public void Stop(javafx.event.ActionEvent actionEvent){
        mediaPlayer.stop();
    }
    public void lastPlayed(javafx.event.ActionEvent actionEvent) throws IOException {

        String lastSongPlayed=br.readLine();
        br.close();
        media = new Media(lastSongPlayed);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        playing=true;
        ///=> Playing the media file after opening <= ///
        mediaPlayer.play();
        ///=> Fitting the media resolution <= ///
        DoubleProperty height = mediaView.fitHeightProperty();
        DoubleProperty width = mediaView.fitWidthProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));


        ///=> Volume slider/seeker properties <= ///
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });


        ///=> Time seeker properties <= ///
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                seek.setValue(newValue.toSeconds());
                seek.setMax(mediaPlayer.getTotalDuration().toSeconds());
            }
        });
        seek.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                mediaPlayer.seek(Duration.seconds(seek.getValue()));
            }
        });

    }
    ///play from list
    public void add(ActionEvent event) throws IOException {
        if(playing){
            mediaPlayer.stop();
        }
        BufferedWriter bw=new BufferedWriter(new FileWriter(lastPlay));
        PrintWriter pw = new PrintWriter(bw);

        String s=null, s2=null;
        s=myList.getSelectionModel().getSelectedItem();
        pw.println(s);
        pw.close();
        System.out.println(s+" from list");
        media=new Media(s);
        playing=true;
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        DoubleProperty height = mediaView.fitHeightProperty();
        DoubleProperty width = mediaView.fitWidthProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));


        ///=> Volume slider/seeker properties <= ///
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });


        ///=> Time seeker properties <= ///
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                seek.setValue(newValue.toSeconds());
                seek.setMax(mediaPlayer.getTotalDuration().toSeconds());
            }
        });
        seek.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                mediaPlayer.seek(Duration.seconds(seek.getValue()));
            }
        });
    }
    ///=>For playLists<=///


        //mediaPlayer.stop();
    }








