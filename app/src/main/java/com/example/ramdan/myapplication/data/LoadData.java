package com.example.ramdan.myapplication.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by ramdan on 12/09/17.
 */

public class LoadData {

    public static class GetMenu extends AsyncTask<String,Void,RestaurantMenus>{
        JSONObject jsonObject;
        List<RestaurantMenus> restaurantMenuses;
        public void GetMenu(List<RestaurantMenus> restaurantMenuses, JSONObject jsonObject){
            this.restaurantMenuses = restaurantMenuses;
        }

        @Override
        protected RestaurantMenus doInBackground(String... menuCategory) {
            try {
                JSONArray menuArr = jsonObject.getJSONArray(menuCategory[0]);
                for(int i = 0; i < menuArr.length(); i++){
                    JSONObject menuObj = menuArr.getJSONObject(i);
                    restaurantMenuses.add(new RestaurantMenus(
                            menuObj.getString("name"),
                            menuObj.getString("photo"),
                            menuObj.getString("price"),
                            menuObj.getString("description")
                            ));
                }
            }catch (Exception e){
                Log.e("load menu exception : ",e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(RestaurantMenus restaurantMenus) {
            super.onPostExecute(restaurantMenus);

        }
    }

    public static class GetImage extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public GetImage(ImageView imageView){
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String sUrl = urls[0];
            Bitmap restIcon = null;


            try{
                URL imageUrl = new URL(sUrl);
                InputStream in = imageUrl.openStream();

                restIcon = BitmapFactory.decodeStream(in);

            }catch (Exception e){

            }
            return restIcon;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
