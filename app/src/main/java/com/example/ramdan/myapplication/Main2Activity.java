package com.example.ramdan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ramdan.myapplication.adapter.MenuAdapter;
import com.example.ramdan.myapplication.data.RestaurantMenus;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<RestaurantMenus> menu;
    MenuAdapter menuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.resto_list_recyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
//
        initData();
//
        //menuAdapter = new MenuAdapter(menu);
        recyclerView.setAdapter(menuAdapter);
    }

    private void initData(){
        menu = new ArrayList<>();
        menu.add(new RestaurantMenus("bakwan","2000","bakwan enak"));
        menu.add(new RestaurantMenus("bakwan1","2000","bakwan enak"));
    }
}
