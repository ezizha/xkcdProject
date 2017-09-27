package com.example.ezihan.xkdcproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.image_default);
        TextView crDate = (TextView) findViewById(R.id.cr_date);

//
//
//
//        HttpURLConnection connection;
//        try {
//            URL url=new URL("http://xkcd.com/info.0.json ");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.connect(); //from here we are connected to server
//
//            InputStream stream = connection.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stream)); //reading our stream line by line in text format
//            StringBuffer buffer = new StringBuffer();
//            String line="";
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You clicked on image ,'-)", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.app_bar_search:
                Toast.makeText(getApplicationContext(), "Search a spesific xkcd", Toast.LENGTH_SHORT).show();
        }
        switch (item.getItemId()){
            case R.id.back:
                Toast.makeText(getApplicationContext(), "Previous xkcd", Toast.LENGTH_SHORT).show();
        }
        switch (item.getItemId()){
            case R.id.refresh:
                Toast.makeText(getApplicationContext(), "Random xkcd", Toast.LENGTH_SHORT).show();
        }
        switch (item.getItemId()){
            case R.id.next:
                Toast.makeText(getApplicationContext(), "Next xkcd", Toast.LENGTH_SHORT).show();
        }
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(getApplicationContext(), "Share this xkcd", Toast.LENGTH_SHORT).show();
        }
        switch (item.getItemId()){
            case R.id.go_to_xkcd:
                Toast.makeText(getApplicationContext(), "Go to xkcd.com", Toast.LENGTH_SHORT).show();
        }
        switch (item.getItemId()){
            case R.id.go_to_xkcd_explain:
                Toast.makeText(getApplicationContext(), "Go to explain xkcd", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
