package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.ecole.eni.tplokacar.database.entity.Client;

@Dao
public interface ClientDAO {

    @Insert
    void insert(Client client);

    @Insert
    void insert(List<Client> clients);

    @Update
    void update(Client client);

    @Delete
    void delete(Client client);
}
