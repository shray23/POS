package com.example.medpay;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    String amount;
    String todaysValue = "\u20B9 0.0", yesterdaysValue = "\u20B9 0.0";


    private MainRepository mRepository;

    public LiveData<List<MainEntity>> allEntities;

    public MainActivityViewModel(Application application) {
        super(application);
        mRepository = new MainRepository(application);
        allEntities = mRepository.getAllEntities();
    }

    public LiveData<List<MainEntity>> getAllEntities() {
        return allEntities;
    }

    public void insert(MainEntity entity) {
        mRepository.insert(entity);
    }

    public String getAmount() {
        return amount;
    }

}
