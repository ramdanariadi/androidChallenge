package com.example.ramdan.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramdan.myapplication.R;
import com.example.ramdan.myapplication.RestaurantActivity;
import com.example.ramdan.myapplication.data.LoadData;
import com.example.ramdan.myapplication.data.Restaurant;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by ramdan on 11/09/17.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolderRestaurant>{
    List<Restaurant> restaurantList;
    Context aplicationContex;

    public RestaurantAdapter(List<Restaurant> restaurantList, Context aplicationContex) {
        this.restaurantList = restaurantList;
        this.aplicationContex = aplicationContex;
    }

    @Override
    public ViewHolderRestaurant onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_row,parent,false);
        return new ViewHolderRestaurant(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRestaurant holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.name.setText(restaurant.getName());
        holder.rate.setText(restaurant.getRate()+"");
        holder.address.setText(restaurant.getRestaurantAddress().getStreet());

        new LoadData.GetImage(holder.photo).execute(restaurant.getPhoto());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    private void onClick(int position){
        Restaurant restaurant = restaurantList.get(position);
        Toast.makeText(aplicationContex,""+position,Toast.LENGTH_SHORT).show();

        Intent ii = new Intent(aplicationContex,RestaurantActivity.class);
        ii.putExtra("id",""+restaurant.getId());


        Toast.makeText(aplicationContex,"name : "+restaurant.getName(),Toast.LENGTH_SHORT).show();
        aplicationContex.startActivity(ii);
    }



    class ViewHolderRestaurant extends RecyclerView.ViewHolder{
        TextView name,address,rate;
        ImageView photo;

        public ViewHolderRestaurant(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.restaurant_name);
            address = (TextView) itemView.findViewById(R.id.restaurant_address);
            rate = (TextView) itemView.findViewById(R.id.restaurant_rate);
            photo = (ImageView) itemView.findViewById(R.id.resto_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestaurantAdapter.this.onClick(getAdapterPosition());
                }
            });
        }
    }

    class LoadPhoto extends AsyncTask<String,Void,Bitmap>{
        ImageView imageView;

        public LoadPhoto(ImageView imageView){
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
