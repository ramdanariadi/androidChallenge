package com.example.ramdan.myapplication.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by ramdan on 12/09/17.
 */

class LoadImage extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;

    public LoadImage(ImageView imageView){
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
