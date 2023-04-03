package com.example.javasae;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerModifierSalle {

    /**
     * Salle à modifier
     */

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML Text nom;

    @FXML TextField name_field;
    @FXML ComboBox comboboxModifier;

    @FXML TextField TextfieldData;

    @FXML
    Text infoText;
    @FXML private Button confirmermodification;
    @FXML
    Button boutonFermerModifierSalle;
    @FXML ObservableList<Salle> listeSallee;
    @FXML Label label;
    @FXML
    AnchorPane AnchorpaneModifierSalle;


    /**
     * Bouton de fermeture de la fenetre
     */
    @FXML
    public void FermerFenetreModifierSalle(ActionEvent event) {
        System.out.println("Fermer la fenètre");
        Stage stage = (Stage) boutonFermerModifierSalle.getScene().getWindow();
        stage.close();
    }

    /**
     *Récupération des liste de salles
     */
    public void setListeSalle(ObservableList<Salle> listeSallee){
        this.listeSallee = listeSallee;
        comboboxModifier.getItems().removeAll(comboboxModifier.getItems());
        comboboxModifier.getItems().addAll(this.listeSallee);
    }

    /**
      * Methode action: bouton de confirmation permet de récupérer les information et modifier la salle ou
     * renvoyer une erreur
     */
    public void confirmermodification(ActionEvent event) {
        try {
            Salle SalleAvantModification = (Salle)comboboxModifier.getSelectionModel().getSelectedItem();
            Salle SalleAModifier= (Salle)comboboxModifier.getSelectionModel().getSelectedItem();
            String nomAModifier= String.valueOf(TextfieldData.getText());
            SalleAModifier.setName(nomAModifier);

            modifierSalleController(SalleAvantModification,SalleAModifier);
            String reponse="Modification effectué";
            label.setText(reponse);
            label.setStyle("-fx-text-fill:GREEN;");

            //AnchorpaneModifierSalle
        } catch (NumberFormatException e) {
            infoText.setText("Le champ capacité doit comporter uniquement un nombre entier");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Enregistrement des informations dans le controller principal
     */
    public void modifierSalleController(Salle SalleAvantModification,Salle SalleAModifier) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Controller c = fxmlLoader.getController();
        c.setListeSalle(SalleAvantModification,SalleAModifier);
    }
}
