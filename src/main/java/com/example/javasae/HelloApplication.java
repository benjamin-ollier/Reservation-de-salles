package com.example.javasae;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    @FXML public ListView<Salle> listeDeSalles;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 810, 500);
        stage.setTitle("Calendar APP!");


        //control.ajoutlisteDeSalles(Salle1);


        Controller c= new Controller();
        Salle[] salle = new Salle[100];

        Salle[] Salle;
        Salle = new Salle[100];

        Salle[0]= new Salle("sallunul");
        Salle[1]= new Salle("salle1");

        Salle salle3= new Salle("salle3");
        //c.ajoutViewSalle("salle3");
        //c.ajoutViewSalle("salle1");



        //c.createSalle(salle4);

        //c.createSalle(salle4);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}