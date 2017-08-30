package pl.oskarpolak.weatherapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import   java.io.InputStream;

/**
 * Created by Lenovo on 21.08.2017.
 */
public class Utils {
    public static String readWebsiteContext(String url){
        StringBuilder builder = new StringBuilder();

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream i = httpURLConnection.getInputStream();
            int response;
            while ((response = i.read()) != -1){
                builder.append((char)response);
            }

            i.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
