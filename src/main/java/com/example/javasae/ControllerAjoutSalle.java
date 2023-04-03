package com.example.javasae;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class ControllerAjoutSalle {
    private ObservableList<Salle> salleObservableList;;
    @FXML private Button boutonPopupPlus;
    @FXML private TextField textField;

    String namee;


    @FXML
    public void boutonPopupPlus(ActionEvent event) {
        System.out.println("Fermer la fenètre");
        Stage stage = (Stage) boutonPopupPlus.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void confirmBoutonAjoutSalle(ActionEvent event) throws IOException {
        this.namee =textField.getText().toString();
        ajoutSalleController(namee);
        Stage stage = (Stage) boutonPopupPlus.getScene().getWindow();
        stage.close();
    }

    public void setObservableListeSalle(ObservableList<Salle> observableListeSalle){
        this.salleObservableList = observableListeSalle;
    }

    @FXML
    public void ajoutSalleController(String namee) throws IOException{
       Salle newSalle = new Salle(namee);
        this.salleObservableList.add(newSalle);

        System.out.println("salle enregistré"+" "+newSalle);

    }
}