package com.example.ramdan.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.ramdan.myapplication.data.Address;
import com.example.ramdan.myapplication.data.HttpHandler;
import com.example.ramdan.myapplication.data.IMenu;
import com.example.ramdan.myapplication.data.JsonURL;
import com.example.ramdan.myapplication.data.Menus;
import com.example.ramdan.myapplication.data.Restaurant;
import com.example.ramdan.myapplication.data.RestaurantMenus;
import com.example.ramdan.myapplication.restaurant.detail.fragments.RestaurantApperizersFragment;
import com.example.ramdan.myapplication.restaurant.detail.fragments.RestaurantDrinksFragment;
import com.example.ramdan.myapplication.restaurant.detail.fragments.RestaurantFoodsFragment;
import com.example.ramdan.myapplication.restaurant.detail.fragments.RestaurantHomeFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    Intent intent;
    Restaurant restaurant;
    ProgressDialog progressDialog;

    RestaurantHomeFragment restaurantHomeFragment;
    RestaurantDrinksFragment restaurantDrinksFragment;
    RestaurantFoodsFragment restaurantFoodsFragment;
    RestaurantApperizersFragment restaurantApperizersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        if(getIntent()!=null){
            intent = getIntent();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tab_ayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setUpViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager mViewPager){


        new GetRestaurant().execute();

        restaurantHomeFragment = RestaurantHomeFragment.newInstance(restaurant);
        restaurantHomeFragment.setRestaurant(restaurant);

        restaurantFoodsFragment = new RestaurantFoodsFragment();
        restaurantDrinksFragment = new RestaurantDrinksFragment();
        restaurantApperizersFragment = new RestaurantApperizersFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(restaurantHomeFragment,"Home");
        viewPagerAdapter.addFragment(restaurantFoodsFragment,"Foods");
        viewPagerAdapter.addFragment(restaurantDrinksFragment,"Drinks");
        viewPagerAdapter.addFragment(restaurantApperizersFragment,"Appetizers");
        mViewPager.setAdapter(viewPagerAdapter);
    }

    public static class FragmentDataContainer{
        public static Restaurant homeDataContainer = null;
        public static List<IMenu> foodsDataContainer = null;
        public static List<IMenu> drinksDataContainer = null;
        public static List<IMenu> appetizersDataContainer = null;
    }

    class GetRestaurant extends AsyncTask<Void,Void,Restaurant>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(RestaurantActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Restaurant rs) {
            super.onPostExecute(rs);

            FragmentDataContainer.homeDataContainer = rs;


            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

        }

        @Override
        protected Restaurant doInBackground(Void... voids) {
            HttpHandler httpHandler = new HttpHandler();
            String result = httpHandler.makeServiceCall(JsonURL.restaurantUrl+intent.getStringExtra("id"));

            if(result != null){
                Log.e("res activity : ",result);
                try {
                    JSONObject restObject = new JSONObject(result);
                    restaurant = new Restaurant(
                            restObject.getString("id"),
                            restObject.getString("photo"),
                            restObject.getString("name"),
                            restObject.getInt("rate"));

                    JSONObject restAddressObject = restObject.getJSONObject("address");
                    Address restAddressTemp = new Address(restAddressObject.getString("street"),
                            restAddressObject.getString("lat"),
                            restAddressObject.getString("lng"));

                    restaurant.setRestaurantAddress(restAddressTemp);

                    JSONObject restMenuObject = restObject.getJSONObject("menu");
                    JSONArray restFoodsArray = restMenuObject.getJSONArray("food");
                    JSONArray restDrinksArray = restMenuObject.getJSONArray("drink");
                    JSONArray restAppetizer = restMenuObject.getJSONArray("appetizer");

                    Menus restMenuTemp = new Menus();
                    List<IMenu> foods = new ArrayList<>();
                    int i;
                    for( i = 0 ; i < restFoodsArray.length() ; i++){
                        JSONObject JFoodsObject = restFoodsArray.getJSONObject(i);
                        foods.add(new RestaurantMenus(
                                JFoodsObject.getString("name"),
                                JFoodsObject.getString("photo"),
                                JFoodsObject.getString("price"),
                                JFoodsObject.getString("description")
                        ));
                    }

                    List<IMenu> drinks = new ArrayList<>();

                    for( i = 0; i < restDrinksArray.length(); i++){
                        JSONObject JDrinkObject = restDrinksArray.getJSONObject(i);
                        drinks.add(new RestaurantMenus(
                                JDrinkObject.getString("name"),
                                JDrinkObject.getString("photo"),
                                JDrinkObject.getString("price"),
                                JDrinkObject.getString("description")
                        ));
                    }

                    List<IMenu> appetizers = new ArrayList<>();

                    for (i = 0 ; i < restAppetizer.length(); i++){
                        JSONObject JAppetizer = restAppetizer.getJSONObject(i);
                        appetizers.add(new RestaurantMenus(
                                JAppetizer.getString("name"),
                                JAppetizer.getString("photo"),
                                JAppetizer.getString("price"),
                                JAppetizer.getString("description")
                        ));
                    }

                    restMenuTemp.setFoods(foods);
                    restMenuTemp.setDrinks(drinks);
                    restMenuTemp.setAppetizer(appetizers);
                    FragmentDataContainer.foodsDataContainer = foods;
                    FragmentDataContainer.drinksDataContainer = drinks;
                    FragmentDataContainer.appetizersDataContainer = appetizers;
                    if(restMenuTemp == null){
                        Log.e("error "," restaurant temp is null");
                    }

                }catch (Exception e){
                    Log.e("error : ",e.getMessage());
                }
            }else{
                Log.e("error","result empty");
                Log.e("error",JsonURL.restaurantUrl+intent.getStringExtra("id"));
            }

            FragmentDataContainer.homeDataContainer = restaurant;

            return restaurant;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        List<Fragment> mFragmentList = new ArrayList<>();
        List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}
