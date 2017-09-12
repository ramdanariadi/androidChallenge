package com.example.ramdan.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ramdan.myapplication.adapter.RestaurantAdapter;
import com.example.ramdan.myapplication.data.Address;
import com.example.ramdan.myapplication.data.HttpHandler;
import com.example.ramdan.myapplication.data.IMenu;
import com.example.ramdan.myapplication.data.Menus;
import com.example.ramdan.myapplication.data.Restaurant;
import com.example.ramdan.myapplication.data.RestaurantMenus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Restaurant> restaurants;
    RestaurantAdapter restaurantAdapter;

    ProgressDialog progressDialog;

    String result;
    private final String url = "http://192.168.1.12:3000/restaurants";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.restaurant_recylist);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        initData();

    }

    private void initData(){

        Address address = new Address("Jalan melati","1111","0000");

        List<IMenu> food = new ArrayList<>();
        food.add(new RestaurantMenus("bakwan","100","murah"));
        Menus menu = new Menus();
        menu.setFoods(food);

        restaurants = new ArrayList<>();
//        restaurants.add(new Restaurant("001","spesial sambal",10,menu,address));
//
//        restaurants.add(new Restaurant("001","spesial sambal",10,menu,address));

        new GetRestaurant().execute();
        Log.e("restaurant length : ",restaurants.size()+"");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private class GetRestaurant extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(RestaurantListActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            restaurantAdapter = new RestaurantAdapter(restaurants,getApplicationContext());
            recyclerView.setAdapter(restaurantAdapter);

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHandler = new HttpHandler();
            result = httpHandler.makeServiceCall(url);

            if(result!=null){
                Log.i("result : ",result);
            }

            if(result != null){
                try {
                    Log.e("first do in background","first");
                    JSONArray arrayRestorant = new JSONArray(result);
                    int restoranLength = arrayRestorant.length();
                    Log.e("Jason array length = ",restoranLength+"");

                    for(int i = 0 ; i < restoranLength; i++ ){
                        JSONObject restorantObject = arrayRestorant.getJSONObject(i);
                        String id = restorantObject.getString("id");
                        String photo = restorantObject.getString("photo");
                        String name = restorantObject.getString("name");
                        int rate = restorantObject.getInt("rate");

                        Log.e("id : ",name+"");

                        Restaurant restaurantTemp = new Restaurant(id,photo,name,rate);

                        JSONObject JrestorantAddress = restorantObject.getJSONObject("address");
                        String street = JrestorantAddress.getString("street");
                        String lat = JrestorantAddress.getString("lat");
                        String lng = JrestorantAddress.getString("lng");

                        Log.e("id : ",street+"");

                        Address restaurantAddress = new Address(street,lat,lng);
                        restaurantTemp.setRestaurantAddress(restaurantAddress);

                        List<IMenu> foods = new ArrayList<>();
                        List<IMenu> drinks = new ArrayList<>();
                        List<IMenu> appetizer = new ArrayList<>();

                        JSONObject JmenuObject = restorantObject.getJSONObject("menu");
                        JSONArray JFodds = JmenuObject.getJSONArray("food");
                        JSONArray JDrinks = JmenuObject.getJSONArray("drink");
                        JSONArray JAppetizers = JmenuObject.getJSONArray("appetizer");

                        Log.e("id : ","menu");

                        MoveMenus(JFodds,foods);
                        MoveMenus(JDrinks,drinks);
                        MoveMenus(JAppetizers,appetizer);

                        Menus menusTemp = new Menus();
                        menusTemp.setFoods(foods);
                        menusTemp.setDrinks(drinks);
                        menusTemp.setAppetizer(appetizer);

                        restaurantTemp.setMenus(menusTemp);
                        restaurants.add(restaurantTemp);
                        Log.e("added to : ","restaurant");
                    }

                    Log.i("last do in background","restaurant length = "+restaurants.size());
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("ada exception ",e.getMessage());
                }
            }else {
                Log.e("info erro : ", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }



            return null;
        }

        private void MoveMenus(JSONArray jsonArray, List<IMenu> iMenuList){

                try{
                    for(int ii = 0 ; ii < jsonArray.length() ; ii++){
                        JSONObject JFoodsObj = jsonArray.getJSONObject(ii);
                        String menuName = JFoodsObj.getString("name");
                        String menuPhoto = JFoodsObj.getString("photo");
                        String menuPrice = JFoodsObj.getInt("price")+"";
                        String menuDescription = JFoodsObj.getString("description");
                        iMenuList.add(new RestaurantMenus(menuName,menuPhoto,menuPrice,menuDescription));
                    }

                }catch (Exception e){
                    e.printStackTrace();

                }


        }
    }
}
