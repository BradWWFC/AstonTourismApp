package com.example.traveljournal2019;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.traveljournal2019.model.Holiday;

import java.util.List;

/*
 * DAO (Data Access Object) class; Here, SQL queries are declared and method calls are associated with it.
 * All queries to be executed on a worker thread.
 * DAO must be an abstract class or an interface
 * */

/*
 * @Dao annotation used to tell Room that this is DAO class
 * */
@Dao
public interface HolidayDao {

    /*
     * method to insert one holiday
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Holiday holiday);

    /*
     * method to delete all items from the table; since there is no generic method, a query has to be made
     * */
    @Query("DELETE FROM holiday_table")
    void deleteAll();

    /*
     * get all the holidays from the table in List object
     * LiveData is used for data observation and making app responsive to data changes
     * */
    @Query("SELECT * FROM holiday_table ORDER BY NAME ASC")
    LiveData<List<Holiday>> getAllHolidays();
}
