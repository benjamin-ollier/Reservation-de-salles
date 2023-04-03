package com.example.javasae;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Reservation {
    private String Prof;
    DayOfWeek Jour;
    int JourInt;
    int HeureDepart;
    int HeureFin;
    LocalDate date;
    int nombreTotalDePersonne;
    Salle salle;

    @FXML
    Text text;


    /**
     *
     * Cette classe agi à la création d'une salle
     */
    public Reservation(LocalDate date, String Prof, int HeureDepart, int HeureFin, int nombreTotalDePersonne){
        /*if(HeureDepart<8 || HeureFin>18){
            System.out.println("Good");
            return;
        }
        else{
            System.out.println("Not Good");

        }*/
        this.HeureDepart=HeureDepart-7;
        this.HeureFin=HeureFin-7;
        this.nombreTotalDePersonne=nombreTotalDePersonne;
        this.Prof=Prof;
        this.Jour=date.getDayOfWeek();
        System.out.println("jour:"+Jour);

        if(this.Jour.toString()=="MONDAY"){
            JourInt=1;
        }
        else if(this.Jour.toString()=="TUESDAY"){
            JourInt=2;
        }
        else if(this.Jour.toString()=="WEDNESDAY"){
            JourInt=3;
        }
        else if(this.Jour.toString()=="THURSDAY"){
            JourInt=4;
        }
        else if(this.Jour.toString()=="FRIDAY") {
            JourInt =5;
        }
        else{
            JourInt=0;
        }
    }


}
