package com.example.ramdan.myapplication.restaurant.detail.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramdan.myapplication.R;
import com.example.ramdan.myapplication.RestaurantActivity;
import com.example.ramdan.myapplication.data.LoadData;
import com.example.ramdan.myapplication.data.Restaurant;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantHomeFragment extends Fragment {

    TextView txtRestName;
    TextView txtRestRate;
    TextView txtRestAddr;
    ImageView imgRestImg;

    static Restaurant restaurant;
    Intent intent;

    public RestaurantHomeFragment() {
        // Required empty public constructor
        Log.e("info : ","constructor");
    }

    public static RestaurantHomeFragment newInstance(Restaurant rest) {
        RestaurantHomeFragment fragment = new RestaurantHomeFragment();
        restaurant = rest;
        Log.e("info : ","destructor static");
        if(restaurant != null){
            Log.e("info : ","restaurant exist");
        }else{
            Log.e("info : ","restaurant doesn't exist");
        }
        return fragment;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;

        if(this.restaurant != null){
            Log.e("restaurant info : ",this.restaurant.getName());
        }else{
            Log.e("restaurant info : ","restaurant doesn't exist");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("info : ","onCreate");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_home, container, false);
        txtRestName = view.findViewById(R.id.rest_desc_name);
        txtRestRate = view.findViewById(R.id.rest_dec_rate);
        txtRestAddr = view.findViewById(R.id.rest_desc_address);
        imgRestImg = view.findViewById(R.id.rest_desc_photo);
        Log.e("info : ","onCreate view");

        if(RestaurantActivity.FragmentDataContainer.homeDataContainer != null){
            Log.e("info from on create : ","restaurant exist");
            Restaurant tmp = RestaurantActivity.FragmentDataContainer.homeDataContainer;
            txtRestName.setText(tmp.getName());
            txtRestRate.setText(tmp.getRate()+"");
            txtRestAddr.setText(tmp.getRestaurantAddress().getStreet());
            new LoadData.GetImage(imgRestImg).execute(tmp.getPhoto());
        }else{
            Log.e("info from on create: ","restaurant doesn't exist");
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("info : ","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("info : ","resume");
    }
}
