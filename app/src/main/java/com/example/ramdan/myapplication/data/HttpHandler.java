package com.example.ramdan.myapplication.data;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ramdan on 11/09/17.
 */

public class HttpHandler {
    public static final String TAG = HttpHandler.class.getName();

    public HttpHandler(){}

    public String makeServiceCall(String pUrl){
        String response = null;
        try {
            URL url = new URL(pUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            response = convertStreamToString(in);
        }catch (Exception e){
            Log.e(TAG,"service erro");
        }
        return response;
    }

    private String convertStreamToString(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try{
            while ((line = reader.readLine())!=null){
                stringBuilder.append(line).append('\n');
            }
        }catch (Exception e){
            Log.e(TAG,"converting error");
        }finally {
            try {
                inputStream.close();
            }catch (Exception e){
                Log.e(TAG,"error closing");
            }
        }
        return stringBuilder.toString();
    }
}
