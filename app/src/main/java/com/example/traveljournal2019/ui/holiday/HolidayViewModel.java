package com.example.traveljournal2019.ui.holiday;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.traveljournal2019.HolidayRepository;
import com.example.traveljournal2019.model.Holiday;

import java.util.List;

public class HolidayViewModel extends AndroidViewModel {

    private HolidayRepository mRepository;

    private LiveData<List<Holiday>> mAllHolidays;

    public HolidayViewModel(Application application) {
        super(application);
        mRepository = new HolidayRepository(application);
        mAllHolidays = mRepository.getAllHolidays();
    }

    LiveData<List<Holiday>> getAllHolidays() {
        return mAllHolidays; }


    public void insert(Holiday holiday) {
        mRepository.insert(holiday);
    }
}
