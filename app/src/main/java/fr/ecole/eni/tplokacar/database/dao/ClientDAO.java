package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.ecole.eni.tplokacar.database.entity.Client;

@Dao
public interface ClientDAO extends GenericDAO<Client> {

    @Query("SELECT * FROM Client")
    List<Client> getAll();

    @Query("SELECT * FROM Client WHERE id_client = :id")
    Client findById(int id);

    @Query("DELETE FROM Client")
    void deleteAll();

    @Query(("SELECT * FROM Client WHERE nom OR prenom LIKE  :result"))
    List<Client> findByName(String result);
}
