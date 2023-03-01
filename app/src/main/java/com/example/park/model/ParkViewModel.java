package com.example.park.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ParkViewModel extends ViewModel {
    private static final MutableLiveData<Data> selectedPark = new MutableLiveData<>();
    private final MutableLiveData<List<Data>> selectedParks = new MutableLiveData<>();
    private final MutableLiveData<String> code = new MutableLiveData<>();

    public static void setSelectedPark(Data park) {
        selectedPark.setValue(park);
    }

    public LiveData<Data> getSelectedPark() {
        return selectedPark;
    }

    public void setSelectedParks(List<Data> parks) {
        selectedParks.setValue(parks);
    }

    public LiveData<List<Data>> getParks() {
        return selectedParks;
    }


    public LiveData<String> getCode() {
        return code;
    }

    public void selectCode(String c) {
        code.setValue(c);
    }

    {


    }
}
