package com.toreytaylor.utacaterering.model.DataAccessObj;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.toreytaylor.utacaterering.model.Objects.Event;

import java.util.List;

@Dao
public interface DaoEvent {
    @Query("SELECT * FROM Event ")
    List<Event> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingleEvent(Event  event);

    @Query("SELECT * FROM Event where customerId LIKE :customerId")
    List<Event> findCustomerEvents(String customerId);

    @Query("SELECT * FROM Event where approval LIKE :approval")
    List<Event> findCatererEvents(Boolean approval);

}
