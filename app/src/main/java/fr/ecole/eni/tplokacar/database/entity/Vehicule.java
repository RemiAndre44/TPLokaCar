package fr.ecole.eni.tplokacar.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Vehicule {


    @PrimaryKey(autoGenerate = true)
    private int id_vehicule;

    private float prix;
    private String plaque;
    private boolean louee;
    private String carburant;
    private int nbrePlaces;


    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public boolean isLouee() {
        return louee;
    }

    public void setLouee(boolean louee) {
        this.louee = louee;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public int getNbrePlaces() {
        return nbrePlaces;
    }

    public void setNbrePlaces(int nbrePlaces) {
        this.nbrePlaces = nbrePlaces;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id_vehicule=" + id_vehicule +
                ", prix=" + prix +
                ", plaque='" + plaque + '\'' +
                ", louee=" + louee +
                ", carburant='" + carburant + '\'' +
                ", nbrePlaces=" + nbrePlaces +
                '}';
    }
}
