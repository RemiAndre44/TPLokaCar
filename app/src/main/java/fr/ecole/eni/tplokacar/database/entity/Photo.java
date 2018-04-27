package fr.ecole.eni.tplokacar.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        indices = {@Index("uniq_index"),
                @Index(value = {"locationId", "vehiculeId", "id_photo"})},
        foreignKeys = {
        @ForeignKey(entity = Vehicule.class,
                parentColumns = "id_vehicule",
                childColumns = "vehiculeId"),
        @ForeignKey(entity = Location.class,
                parentColumns = "id_location",
                childColumns = "locationId")
})
public class Photo {

    @PrimaryKey(autoGenerate = true)
    private int id_photo;

    private String uniq_index;

    private String name;
    private boolean presentation;
    private int locationId;
    private int vehiculeId;




    public int getId_photo() {
        return id_photo;
    }

    public void setId_photo(int id_photo) {
        this.id_photo = id_photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPresentation() {
        return presentation;
    }

    public void setPresentation(boolean presentation) {
        this.presentation = presentation;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
        return "Photo{" +
                "id_photo=" + id_photo +
                ", uniq_index='" + uniq_index + '\'' +
                ", name='" + name + '\'' +
                ", presentation=" + presentation +
                ", locationId=" + locationId +
                ", vehiculeId=" + vehiculeId +
                '}';
    }
}
