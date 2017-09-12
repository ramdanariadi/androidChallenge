package com.example.ramdan.myapplication.restaurant.detail.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramdan.myapplication.R;
import com.example.ramdan.myapplication.RestaurantActivity;
import com.example.ramdan.myapplication.adapter.MenuAdapter;
import com.example.ramdan.myapplication.data.IMenu;
import com.example.ramdan.myapplication.data.RestaurantMenus;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFoodsFragment extends Fragment {

    TextView textView;
    RecyclerView recyclerView;

    public RestaurantFoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_foods, container, false);
        recyclerView = view.findViewById(R.id.foods_list_recyclerview);

        if(RestaurantActivity.FragmentDataContainer.foodsDataContainer != null){
            IMenu restaurantMenus = (RestaurantMenus) RestaurantActivity.FragmentDataContainer.foodsDataContainer.get(0);
            //textView.setText(restaurantMenus.getName());
            MenuAdapter menuAdapter = new MenuAdapter(RestaurantActivity.FragmentDataContainer.foodsDataContainer,getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(menuAdapter);
        }else{
            //textView.setText("restaurants food empty");
        }

        return view;
    }

}
