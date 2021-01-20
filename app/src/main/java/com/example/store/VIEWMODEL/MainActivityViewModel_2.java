package com.example.store.VIEWMODEL;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.store.Models.data_accses;
import com.example.store.Models.orderItems;
import com.example.store.data_2;

import java.util.ArrayList;

public class MainActivityViewModel_2 extends ViewModel {
    MutableLiveData<data_2> data=new MutableLiveData<>();
    MutableLiveData<data_accses> change_Data=new MutableLiveData<>();
    MutableLiveData<ArrayList<orderItems>> orderItems=new MutableLiveData<>();
    MutableLiveData<Integer> Count=new MutableLiveData<>();

    public MutableLiveData<data_accses> getChange_Data() {
        return change_Data;
    }

    public MutableLiveData<ArrayList<orderItems>> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(MutableLiveData<ArrayList<orderItems>> orderItems) {
        this.orderItems = orderItems;
    }

    public void setChange_Data(MutableLiveData<data_accses> change_Data) {
        this.change_Data = change_Data;
    }

    public MutableLiveData<Integer> getCount() {
        return Count;
    }

    public void setCount(MutableLiveData<Integer> count) {
        Count = count;
    }

    public MutableLiveData<data_2> getData() {
        return data;
    }

    public void setData(MutableLiveData<data_2> data) {
        this.data = data;
    }
}
