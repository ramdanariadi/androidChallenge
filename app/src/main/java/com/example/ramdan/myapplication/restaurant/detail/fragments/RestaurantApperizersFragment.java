package com.example.ramdan.myapplication.restaurant.detail.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramdan.myapplication.R;
import com.example.ramdan.myapplication.RestaurantActivity;
import com.example.ramdan.myapplication.adapter.MenuAdapter;
import com.example.ramdan.myapplication.data.IMenu;
import com.example.ramdan.myapplication.data.RestaurantMenus;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantApperizersFragment extends Fragment {
    RecyclerView recyclerView;

    public RestaurantApperizersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_apperizers, container, false);
        recyclerView = view.findViewById(R.id.appetizers_list_recyclerview);

        if(RestaurantActivity.FragmentDataContainer.appetizersDataContainer != null){
            IMenu restaurantMenus = (RestaurantMenus) RestaurantActivity.FragmentDataContainer.foodsDataContainer.get(0);
            //textView.setText(restaurantMenus.getName());
            MenuAdapter menuAdapter = new MenuAdapter(RestaurantActivity.FragmentDataContainer.appetizersDataContainer,getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(menuAdapter);
        }else{
            //textView.setText("restaurants food empty");
        }

        return view;
    }

}
