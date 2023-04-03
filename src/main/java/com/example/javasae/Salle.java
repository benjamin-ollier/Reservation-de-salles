package com.example.javasae;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe agi à la création d'une salle
 */
public class Salle {
    private String nom;
    @FXML
    private GridPane tableau;

    List<Reservation> listReservations = new ArrayList<Reservation>();



    public Salle(String name){
        this.nom=name;
    }


    public void ajoutNom(String name){
        this.nom=name;
    }


    public void ajoutResa(Reservation reservation){
        listReservations.add(reservation);
        System.out.println(Arrays.toString(listReservations.toArray()));
    }

    public String getName() {
        return this.nom;
    }

    public void setName(String name) { this.nom = name; }


    @Override
    public String toString() {
        return nom;
    }




}
