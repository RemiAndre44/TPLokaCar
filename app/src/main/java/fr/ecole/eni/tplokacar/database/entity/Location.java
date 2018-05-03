package fr.ecole.eni.tplokacar.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;


import java.util.Date;

import fr.ecole.eni.tplokacar.database.DateTypeConverter;


@Entity(
        indices = {@Index("uniq_index"),
                @Index(value = {"clientId", "vehiculeId", "dateDepart", "id_location"})},
        foreignKeys = {
        @ForeignKey(entity = Client.class,
                 parentColumns = "id_client",
                 childColumns = "clientId"),
        @ForeignKey(entity = Vehicule.class,
                parentColumns = "id_vehicule",
                childColumns = "vehiculeId")
})
public class Location implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id_location;

    private String uniq_index;

    @TypeConverters({DateTypeConverter.class})
    private Date dateDepart;
    private int clientId;
    private int vehiculeId;
    private int duree;

    public Location(Parcel in) {
        id_location = in.readInt();
        uniq_index = in.readString();
        clientId = in.readInt();
        vehiculeId = in.readInt();
        duree = in.readInt();
    }

    public Location(){
        
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }


    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public String getUniq_index() {
        return uniq_index;
    }

    public void setUniq_index(String uniq_index) {
        this.uniq_index = uniq_index;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id_location=" + id_location +
                ", uniq_index='" + uniq_index + '\'' +
                ", dateDepart=" + dateDepart +
                ", clientId=" + clientId +
                ", vehiculeId=" + vehiculeId +
                ", duree=" + duree +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_location);
        dest.writeString(uniq_index);
        dest.writeInt(clientId);
        dest.writeInt(vehiculeId);
        dest.writeInt(duree);
        dest.writeLong(dateDepart.getTime());
    }
}
