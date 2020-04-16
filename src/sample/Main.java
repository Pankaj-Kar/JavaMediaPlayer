package sample;
import java.io.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class Main extends Application {


    public Main() throws Exception {

    }

    public static void main(String[] args) {
        launch(args);


    }


    playLists list=new playLists();
    playLists last=new playLists("hello");



    ///=> The Display Section. FXML file is used for showing the media file <= ///
    @Override
    public void start(Stage stage) throws IOException {
       // VBox root=new VBox();
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
       // root.getChildren().addAll(view);
        Scene scene=new Scene(root);
        stage.setTitle("MediaBox");

     ///=> A method is used for getting full screen by double clicking <= ///
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent doubleClicked) {
                if(doubleClicked.getClickCount()==2) stage.setFullScreen(true);
            }
        });
        stage.setScene(scene);
        stage.show();

    }

}
