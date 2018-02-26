package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


/**
 * Created by roman on 09-Jun-17.
 * Edited by David on 15-Jun-17.
 */

public class RootUIController extends Application{


    @FXML
    private Button btnFB;
    @FXML
    private Button btnMock;

    public static  int type = 0;
    public static final int MOCKUP = 1;
    public static final int FASTBILL = 2;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("fxml/root.fxml"));


        primaryStage.setTitle("JakubServices GmbH");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    @FXML
    private void onButtonClick(ActionEvent e) throws IOException {
        Stage stage2;
        if(e.getSource()==btnFB) { type = RootUIController.FASTBILL; }

        if(e.getSource()==btnMock){ type = RootUIController.MOCKUP; }

        stage2 = (Stage) btnMock.getScene().getWindow();
        FXMLLoader loader2 = new FXMLLoader();
        Parent client = loader2.load(getClass().getResource("fxml/Client.fxml"));

        Scene scene2 = new Scene(client);
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.show();

    }


    public void initialize() {
    }



    public static void main(String[] args) {
        launch(args);
    }
}