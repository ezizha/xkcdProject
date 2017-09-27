package com.example.ezihan.xkdcproject;

import android.os.AsyncTask;

import com.example.ezihan.xkdcproject.Model.XkcdModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by EziHAn on 27.09.2017.
 */

public class GetImageTask extends AsyncTask<String, String, String> {

    private ImageTaskListener listener;

    public GetImageTask(ImageTaskListener mListener) {
        listener = mListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//            dialog.show();
//            progressBar.setVisibility(View.VISIBLE); // as our Progress bar is set to be hidden by defaut, we need to make it visible first, and aster task is finished, hidden.
        listener.preTask();
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null; //we need to open an HTTP connection we initialise our connection to whole doc
        BufferedReader reader = null; // we made reader object accessable for whole doc
        try {
            URL url = new URL(params[0]); //the URL we need to access is written here, our URL is now in params
            connection = (HttpURLConnection) url.openConnection(); // here we open the URL connection
            connection.connect();//after this poitn we will be connecting directly to server
            //what we get in response is infinite stream. we have to store this infinite stream and do thing with it

            InputStream stream = connection.getInputStream(); //it means the received stream is stored into our stream object
            reader = new BufferedReader(new InputStreamReader(stream)); //it helps us to read data in our stream line by line
            StringBuffer buffer = new StringBuffer();
            //code for reading buffer line by line
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJson = buffer.toString(); //this is the final Json that we get, we need to convert it into a readable file
            JSONObject parentObject = new JSONObject(finalJson);
            XkcdModel xkcdModel = new XkcdModel(); //we are getting our JSON objects that are in our model
            xkcdModel.setImg(parentObject.getString("img"));
            xkcdModel.setAlt(parentObject.getString("alt"));
            xkcdModel.setDay(parentObject.getInt("day"));
            xkcdModel.setLink(parentObject.getString("link"));
            xkcdModel.setMonth(parentObject.getInt("month"));
            xkcdModel.setNews(parentObject.getString("news"));
            xkcdModel.setNum(parentObject.getInt("num"));
            xkcdModel.setSafe_title(parentObject.getString("safe_title"));
            xkcdModel.setTitle(parentObject.getString("title"));
            xkcdModel.setTranscript(parentObject.getString("transcript"));
            xkcdModel.setYear(parentObject.getInt("year"));

//            StringBuffer description = new StringBuffer();
//            StringBuffer cr_Date = new StringBuffer();
//            StringBuffer xkcd_No = new StringBuffer();

//            String safeTitle = parentObject.getString("safe_title");
//            String transcript = parentObject.getString("transcript");
//            String alt = parentObject.getString("alt");
//            String title = parentObject.getString("title");
//            String img = parentObject.getString("img");
//            int year = parentObject.getInt("year");
//            int month = parentObject.getInt("month");
//            int day = parentObject.getInt("day");
//            int num = parentObject.getInt("num");

//            description.append(title + safeTitle + "\n" + transcript + "\n" + alt); //contains our joke
//            cr_Date.append(year+"."+month+"."+day);
//            xkcd_No.append(num);
//            return cr_Date.toString(); //returns to joke
//            return description.toString(); //returns to joke
//            return buffer.toString();  //if we were able to get data it will return to convert buffer to sring, and pass the result to 's' in onPostExecute
            return finalJson;


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) { //our connection can be null from start
                connection.disconnect();
            }//to disconnect it we need to initialise with null
            try {
                if (reader != null) { //our buffer can be null also
                    reader.close();
                }//it means if connection or reader is not null then disconnect
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null; //it means if we were not able to get data, it will return to null
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.postTask(s);
    }
}