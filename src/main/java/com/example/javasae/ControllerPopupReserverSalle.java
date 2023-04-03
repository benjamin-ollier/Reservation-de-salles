package com.example.javasae;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerPopupReserverSalle implements Initializable {

    //private Controller c;

    final ObservableList<Salle> listPerson =  FXCollections.observableArrayList();

    @FXML private ComboBox comb;
    @FXML private TextField heureDeDepart;
    @FXML private TextField heureDeFin;
    @FXML private TextField professeur;
    @FXML private TextField nomPersonneQuiReserve;
    @FXML private TextField nombreTotalDePersonne;
    @FXML private DatePicker datePicker;
    @FXML private Button boutonCancel;
    Controller c;
    @FXML ObservableList<Salle> listeSallee;
    @FXML AnchorPane AnchorPane;
    @FXML Label label;
    private ObservableList<Reservation> ObservableResa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableResa = FXCollections.observableArrayList();

    }


    /**
     * La méthode confirmation récupère les informations et essaie de réserver une salle si il n'y a pas d'erreurs.
     * Cette methode se sert de checkResa et casErreur pour les éléments selectionnés.
     */
    @FXML
    public void confirmer(ActionEvent e) {
        int heureDepart= Integer.parseInt(heureDeDepart.getText());
        int heureFin= Integer.parseInt(heureDeFin.getText());

        int nb=Integer.parseInt(nombreTotalDePersonne.getText());
        String prof= String.valueOf(professeur.getText());
        Salle s = (Salle)comb.getSelectionModel().getSelectedItem();
        LocalDate date = LocalDate.from(datePicker.getValue());
        //System.out.println(date.getDayOfWeek());
        //System.out.println(date);
        System.out.println(heureDepart+" "+heureFin+" "+prof+" "+s+" "+date);
        Reservation r=new Reservation(date,prof,heureDepart,heureFin,nb);

        //Cas d'erreurs:
        String reponse=casErreur(r);
        System.out.println(reponse);
        String bonneR="Réservation enregistrée";
        if(reponse.equals(bonneR)){
            label.setStyle("-fx-text-fill:GREEN;");
            System.out.println("Réservation effectué");
        }
        else{
            label.setStyle("-fx-text-fill:RED;");
            label.setText(reponse);
            return;
        }
        label.setText(reponse);

        r.salle=s;//Ajout du nom de la salle à la reservation
        ObservableResa.add(r);//ajout à la liste reservation
        s.ajoutResa(r);
    }

    /**
     * La methode boutonCancel permet de fermer la fenetre.
     */
    @FXML
    public void boutonCancel(ActionEvent event) {
        System.out.println("Fermer la fenètre");
        Stage stage = (Stage) boutonCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * SetListeSalle permet de recupérer la liste des salles dans listSallee et de les enregistrer
     * dans le combobox.
     */
    public void setListeSalle(ObservableList<Salle> listeSallee){
        this.listeSallee = listeSallee;
        comb.getItems().removeAll(comb.getItems());
        comb.getItems().addAll(this.listeSallee);
    }

    /**
     * setListReservation permet de recupérer la liste des reservation pour ensuite de faire des verifications
     * dans d'autres méthodes
     */
    public void setListereservation(ObservableList<Reservation> ObservableResa){
        this.ObservableResa = ObservableResa;
    }

    /**
     * Cette methode est appelé pour vérifier des cas d'erreur sur les informations selectionnées
     */
    String casErreur(Reservation r){
        int heureDepart=r.HeureDepart;
        System.out.println("heure depart"+heureDepart);
        int heureFin=r.HeureFin;
        int nbTtPersonne=r.nombreTotalDePersonne;
        DayOfWeek jour=r.Jour;
        LocalDate date=r.date;
        String message;
        int check=checkResa(r,ObservableResa);

        if((heureDepart<1) || (heureFin>18) || (heureFin<heureDepart)){
            message="Les horaires ne correspondent pas";
        }
        else if(nbTtPersonne>25){
            message="plus de place disponible";
        }
        else if(nbTtPersonne<1){
            message="Le nombre de place total ne peut pas etre inferieur à 1";
        } else if (check==0) {
            message="Il existe déjà une réservation de ce type";
        } else{
            message="Réservation enregistrée";
        }
        return message;
    }

    /**
     * Cette méthode permet de regarder si une reservation existe déjà dans une salle
     */
    public int checkResa(Reservation r,ObservableList<Reservation> ObservableResa){
        int rep;
        for (int i=0; i<ObservableResa.size();i++) {
            Reservation rr = ObservableResa.get(i);
            if(r.Jour== rr.Jour) {
                return rep = 0;
            }
            if((r.JourInt==rr.JourInt)||(r.HeureDepart>=rr.HeureDepart)||(r.HeureFin<= rr.HeureFin)||(r.salle==rr.salle)) {
                    return rep = 0;
                }
            }
        return rep=1;
    }
}
