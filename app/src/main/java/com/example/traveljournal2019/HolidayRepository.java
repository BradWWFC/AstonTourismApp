package com.example.traveljournal2019;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.traveljournal2019.model.Holiday;

import java.util.List;

public class HolidayRepository {

    private HolidayDao mHolidayDao;
    private LiveData<List<Holiday>> mAllHolidays;

    public HolidayRepository(Application application) {
        HolidayRoomDatabase db = HolidayRoomDatabase.getDatabase(application);
        mHolidayDao = db.holidayDao();
        mAllHolidays = mHolidayDao.getAllHolidays();
    }

    public LiveData<List<Holiday>> getAllHolidays() {
        return mAllHolidays;
    }

    public void insert (Holiday holiday) {
        new insertAsyncTask(mHolidayDao).execute(holiday);
    }

    private static class insertAsyncTask extends AsyncTask<Holiday, Void, Void> {

        private HolidayDao mAsyncTaskDao;

        insertAsyncTask(HolidayDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Holiday... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
