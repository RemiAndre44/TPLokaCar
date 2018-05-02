package fr.ecole.eni.tplokacar.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Vehicule implements Parcelable{


    @PrimaryKey(autoGenerate = true)
    private int id_vehicule;

    private float prix;
    private String plaque;
    private boolean louee;
    private String carburant;
    private int nbrePlaces;
    private String marque;
    private String modele;


    public Vehicule(Parcel in) {
        id_vehicule = in.readInt();
        prix = in.readFloat();
        plaque = in.readString();
        louee = in.readByte() != 0;
        carburant = in.readString();
        nbrePlaces = in.readInt();
        marque = in.readString();
        modele = in.readString();
    }

    public static final Creator<Vehicule> CREATOR = new Creator<Vehicule>() {
        @Override
        public Vehicule createFromParcel(Parcel in) {
            return new Vehicule(in);
        }

        @Override
        public Vehicule[] newArray(int size) {
            return new Vehicule[size];
        }
    };

    public Vehicule() {
        
    }

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

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_vehicule);
        dest.writeFloat(prix);
        dest.writeString(plaque);
        dest.writeByte((byte) (louee ? 1 : 0));
        dest.writeString(carburant);
        dest.writeInt(nbrePlaces);
        dest.writeString(marque);
        dest.writeString(modele);
    }
}
