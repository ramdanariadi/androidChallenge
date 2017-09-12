package com.example.ramdan.myapplication;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ramdan.myapplication.adapter.RestaurantAdapter;
import com.example.ramdan.myapplication.data.Address;
import com.example.ramdan.myapplication.data.IMenu;
import com.example.ramdan.myapplication.data.Menus;
import com.example.ramdan.myapplication.data.Restaurant;
import com.example.ramdan.myapplication.data.RestaurantMenus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Restaurant> restaurants;
    RestaurantAdapter restaurantAdapter;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        /**Navigation Drawer */


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer_toggle,R.string.close_drawer_toggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListener());

        /**recycler*/


        recyclerView = (RecyclerView) findViewById(R.id.resto_list_recyclerView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

       // initData();
//
//        restaurantAdapter = new RestaurantAdapter(restaurants,this,progressDialog);
//        recyclerView.setAdapter(restaurantAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.drawer_home:
                    Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    return true;
                case R.id.drawer_history:
                    Toast.makeText(getApplicationContext(),"history",Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                    return false;
                case R.id.drawer_list:
                    Toast.makeText(getApplicationContext(),"list",Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(MainActivity.this,RestaurantListActivity.class);
                    startActivity(ii);
                    drawerLayout.closeDrawers();
                    return true;
                default:
                    return false;
            }
        }
    }


    private void initData(){

        Address address = new Address("Jalan melati","1111","0000");

        List<IMenu> food = new ArrayList<>();
        food.add(new RestaurantMenus("bakwan","100","murah"));
        Menus menu = new Menus();
        menu.setFoods(food);

        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("001","spesial sambal",10,menu,address));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);

        MenuItem searchItem = menu.findItem(R.id.search_action_menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()){
            case R.id.capture_image:
                Toast.makeText(this,"Trying capture image",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
