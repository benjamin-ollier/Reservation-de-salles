package com.example.javasae;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


public class ControllerSuppressionSalle {

@FXML private Button boutonPopupMoins;
@FXML private ComboBox comb;
@FXML ObservableList<Salle> listeSallee;


    /**
     * Cette methode permet de fermer la fenetre quand le bouton est pressé
     */
    @FXML
    public void fermerFenetre(ActionEvent event) {
        System.out.println("Fermer la fenètre");
        Stage stage = (Stage) boutonPopupMoins.getScene().getWindow();
        stage.close();
    }

    /**
     * Cette méthode permet de récupérer les salles et de placer celles ci dans la combobox
     */
    public void setListeSalle(ObservableList<Salle> listeSallee){
        this.listeSallee = listeSallee;
        comb.getItems().removeAll(comb.getItems());
        comb.getItems().addAll(this.listeSallee);
    }

    /**
     * Le bouton event confirmerBouton permet de prendre les éléments entrés et agi à la suppression de la salle voulu
     */
    @FXML
    public void confirmBouton(ActionEvent event) {
        Salle s = (Salle)comb.getSelectionModel().getSelectedItem();
        this.listeSallee.remove(s);
        System.out.println("Salle supprimée"+s);
        Stage stage = (Stage) boutonPopupMoins.getScene().getWindow();
        stage.close();
    }

}
