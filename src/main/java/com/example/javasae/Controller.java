package com.example.javasae;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;


public class Controller implements Initializable{


    @FXML private ListView<Salle> listeDeSalles;
    //public ArrayList<Salle> listSalle = new ArrayList();
    @FXML private GridPane tableau;
    @FXML private TextField textField;
    @FXML private ObservableList<Salle> salleObservableList;
    @FXML private ObservableList<Reservation> ObservableResa;

    //private static final System.Logger LOGGER = (System.Logger) Logger.getLogger( Controller.class. getName() );





    public Controller(){
        //list = new ArrayList<Salle>();
    }


    /**
     * initialize permet de générer les salles et la vue lors du lancement de l'application
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        salleObservableList = FXCollections.observableArrayList();
        Salle salle1= new Salle("salle1");
        salleObservableList.add(salle1);
        Salle salle2= new Salle("salle2");
        salleObservableList.add(salle2);
        Salle salle3= new Salle("salle3");
        salleObservableList.add(salle3);
        listeDeSalles.setItems(salleObservableList);


        tableau.setGridLinesVisible(true);
    }

    /**
     *  Bouton ModifierSalle permet d'ouvrir la fenetre de modification de salles
     */
    @FXML //Fenetre ajouter une salle
    public void ajoutSalle(ActionEvent e) throws IOException{
        System.out.println("PopupViewPlus");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popupAjoutSalle.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ControllerAjoutSalle controllerPlus = fxmlLoader.getController();
        controllerPlus.setObservableListeSalle(this.salleObservableList);
        Stage stage = new Stage();
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     *  Bouton popupMoins permet d'ouvrir la fenetre pour supprimer une salle
     */
    @FXML
    public void suppressionSalle(ActionEvent e) throws IOException {
        System.out.println("popupMoins");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popupViewMoins.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ControllerSuppressionSalle controllerMoins = fxmlLoader.getController();
        controllerMoins.setListeSalle(this.salleObservableList);
        Stage stage = new Stage();
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     *  Bouton ModifierSalle permet d'ouvrir la fenetre de modification de salles
     */
    public void modifierSalle(ActionEvent e) throws IOException {
        //ConsoleHandler handler = new ConsoleHandler();
        //LOGGER.addHandler(handler);
        //LOGGER.log(Level.INFO, "Creation de la salle");

        System.out.println("Afficher popup ModifierSalle");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popupModifierSalle.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ControllerModifierSalle controllerPopupmodifier = fxmlLoader.getController();
        controllerPopupmodifier.setListeSalle(this.salleObservableList);
        Stage stage = new Stage();
        stage.setTitle("Modifier une salle");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    /**
     *  Methode qui permet d'afficher la salle en fonction de l'objet(salle) selectionné dans la liste(listview)
     */
    @FXML //Pressed Action sur la listview
    public void tabPressed(MouseEvent arg0){
        System.out.println("une ligne selectionné" + listeDeSalles.getSelectionModel().getSelectedItem());
        afficherTab(listeDeSalles.getSelectionModel().getSelectedItem().listReservations);
    }

    /**
     *  Methode utilisé par tabPressed pour afficher les salle sur le gridview(tableau) principal
     */
    @FXML //recupere la liste de reservation de l'objet et les afficher sur le calendrier
    public void afficherTab(List<Reservation> listReservations){
        this.tableau.getChildren().removeIf(node -> node.getClass() == VBox.class);
        for (int i=0; i<listReservations.size();i++) {
            int heureD=listReservations.get(i).HeureDepart;
            int heureF=listReservations.get(i).HeureFin;
            int JourInt=listReservations.get(i).JourInt;
            int nbP=listReservations.get(i).nombreTotalDePersonne;
            System.out.println("Jourint controller:"+JourInt);
            System.out.println("jour tostring controller:"+listReservations.get(i).Jour.toString());

            //rectangle
            int heurefinale=heureF-heureD;
            for(int b=heureD;b<heureF;b++) {
                VBox rec = new VBox();
                rec.setAlignment(Pos.CENTER);
                rec.setStyle(
                        "-fx-background-color: rgba(255, 93, 58 );" +
                                "-fx-border-style: solid inside;" +
                                "-fx-border-color: black;"+
                                "-fx-border-width: 1;" );
                Label label1=new Label("Nombre d'utilisateurs");
                rec.getChildren().add(label1);
                Label label2=new Label(""+nbP);
                rec.getChildren().add(label2);

                tableau.add(rec, JourInt,heureD,1,(heureF-heureD)+1);

            }
        }
    }

    /**
     * Bouton pour ouvrir la fenetre de reservation de salles
     */
     @FXML
     public void reservSalleAction(ActionEvent e) throws IOException{
        System.out.println("Reserver une salle");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopupReserverSalle.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ControllerPopupReserverSalle controllerResa = fxmlLoader.getController();

        controllerResa.setListeSalle(this.salleObservableList);

        Stage stage = new Stage();
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     *Envoyer la liste des salles dans le controllerModifierSalle
     */
    public void setListeSalle(Salle salleAModifier, Salle Salle){
        this.salleObservableList.remove(salleAModifier);
        this.salleObservableList.add(Salle);
        this.listeDeSalles.getItems().clear();
        this.listeDeSalles.setItems(salleObservableList);
        this.listeDeSalles.refresh();
    }
}