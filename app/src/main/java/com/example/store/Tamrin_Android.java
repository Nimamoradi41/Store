package com.example.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Tamrin_Android extends AppCompatActivity {
     RecyclerView recy_sugess;
     Adapter_Categpry categpry;
     Adapter_Categpry_3 categpry_3;
     EditText editTextTextPersonName2;
     ImageView imageView14;
     ArrayList<MODEL_ITEM> items;
     List<MyTABLE> datas;
     Button add;
     Button ref;
     EditText data_input;
     RoomDb db;
     Tamrin_AndroidViewModel viewModel;
     BottomNavigationView bottomNavigationView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamrin__android_2);
        viewModel= new ViewModelProvider(this).get(Tamrin_AndroidViewModel.class);

        viewModel.data.setValue("Android");
        bottomNavigationView2=findViewById(R.id.bottomNavigationView2);



        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.m1)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_4,new BlankFragment_test_2()).commit();
                }





                if (item.getItemId()==R.id.m2)
                {
                  getSupportFragmentManager().beginTransaction().replace(R.id.container_4,new BlankFragment_test_1()).commit();
                }
                return false;
            }
        });







    }

}