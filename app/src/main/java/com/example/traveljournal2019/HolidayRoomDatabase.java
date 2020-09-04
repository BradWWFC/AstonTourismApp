package com.example.traveljournal2019;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.traveljournal2019.model.Holiday;

@Database(entities = {Holiday.class}, version = 1, exportSchema = false)
public abstract class HolidayRoomDatabase extends RoomDatabase {

    public abstract HolidayDao holidayDao();
    private static HolidayRoomDatabase INSTANCE;

    static HolidayRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HolidayRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HolidayRoomDatabase.class, "holiday_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    /*
     * asyncTask to delete all existing entries and repopulating the database with the contents of string array
     * */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final HolidayDao mDao;
        String[] holidays = {"Paris", "London", "Tokyo"};

        PopulateDbAsync(HolidayRoomDatabase db) {
            mDao = db.holidayDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            for (int i = 0; i <= holidays.length - 1; i++) {
                Holiday holiday = new Holiday(holidays[i]);
                mDao.insert(holiday);
            }
            return null;
        }
    }
}
