package fr.ecole.eni.tplokacar.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;


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
public class Location {

    @PrimaryKey(autoGenerate = true)
    private int id_location;

    private String uniq_index;

    @TypeConverters({DateTypeConverter.class})
    private Date dateDepart;
    private int clientId;
    private int vehiculeId;


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

    @Override
    public String toString() {
        return "Location{" +
                "id_location=" + id_location +
                ", dateDepart=" + dateDepart +
                ", clientId=" + clientId +
                ", vehiculeId=" + vehiculeId +
                '}';
    }
}
