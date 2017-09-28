package com.example.ezihan.xkdcproject;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezihan.xkdcproject.Model.XkcdModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private SwipeRefreshLayout swipeLayout;
    private ImageView xkcdImageScreen;
    private TextView xkcdCrDate;
    private TextView xkcdNo;
    private TextView xkcdAltText;

    @Override /**
     Override means, when we call a method it starts its own codes that are written in Activity. By putting Override we can write our codes inside this method.
 */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); /**this is put insode onCreate method, because when our app goes to background and then launched again,
         it does not creat an app again. The UI of app is already loaded, it does not need to be created again.*/

        // Create default options which will be used for every
//  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
           .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
           .defaultDisplayImageOptions(defaultOptions)
           .build();
        ImageLoader.getInstance().init(config); // Do it on Application start


        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                Toast.makeText(getApplicationContext(), "shake", Toast.LENGTH_SHORT).show();
                execute_URL();
            }
        });

        xkcdImageScreen = (ImageView) findViewById(R.id.xkcd_image);
        xkcdCrDate = (TextView) findViewById(R.id.xkcd_crdate);
        xkcdNo = (TextView) findViewById(R.id.xkcd_no);
        xkcdAltText = (TextView) findViewById(R.id.xkcd_alt_text);

        xkcdImageScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "You clicked on image ,'-)", Toast.LENGTH_SHORT).show();
                execute_URL();
            }
        });

//        imageScreen.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                return false;
//            }
//        });
    }
        private void execute_URL() {//we made many lines of codes that are bolow a method to reuse anywhere
//            String xkcdNo = edtFirst.getText().toString(); //we get a text input
//                if (TextUtils.isEmpty(fName)){
//                    fName = "Chuck";
//                }
//            xkcdNo = TextUtils.isEmpty(fName) ? "0" : fName; // assigned as default texts. if there is no fName or lName input, Chuck Norris will be used as defaults.
//            lName = TextUtils.isEmpty(lName) ? "Norris" : lName;

            // Uri.parse().buildUpon().appendQueryParameter(); // to customize or URL and gitve to it more dinamic view

            new GetImageTask(mListener).execute("https://xkcd.com/info.0.json"); //when we click the button it executes GetJoke Task
//            loadImageFromUrl("https://imgs.xkcd.com/comics/worrying_scientist_interviews.png"); //Picasso library is used
        }

//    private void loadImageFromUrl(String url) { //picasso library is used
//        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)//oprional
//                .error(R.mipmap.ic_launcher)//if error
//                .into(xkcdImageScreen,new com.squareup.picasso.Callback(){
//
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });
//        Log.d("my_text", "img_URL: " + getString(url));
//    }

    private String getString(String img) {
        return null;
    }

    private ImageTaskListener mListener = new ImageTaskListener() {
            @Override
            public void preTask() {
                swipeLayout.setRefreshing(true);
            }

            @Override
            public void postTask(XkcdModel result) {

//                jokeChuck.setText(result); // sets or string 's' to 'jokeChuck' textView
//            dialog.dismiss();
//                progressBar.setVisibility(View.GONE); // after task is finished, we set progress bar to be hidden
//                if (TextUtils.isEmpty(result)) {
//                    Toast.makeText(getApplicationContext(), "No Internet connection", Toast.LENGTH_SHORT)
//                            .show();
//
//                }

                swipeLayout.setRefreshing(false);
            }
        };

    @Override
    public void onRefresh() {
        execute_URL();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }


    //    private ProgressDialog dialog;
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getApplicationContext(), "landscape", Toast.LENGTH_LONG).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_LONG).show();
        }
    }

//    public static class ImageAdapter extends ArrayAdapter {// we need a constructor that contains a List, because in the end we have a list of Models.

//        private XkcdModel xkcdModel;
//        private int resource;
//        private LayoutInflater inflater;
//        public ImageAdapter(Context context, int resource, XkcdModel objects) {
//            super(context, resource, (List) objects);
//            xkcdModel =objects;
//            this.resource=resource; //1st resource is "private int resource", 2nd resource is "ImageAdapter(int resource)"
//            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null){
//                inflater.inflate(resource, null);//It inflates our xkcdview layout into convertView variable and takes a view.
//            }
//            TextView xkcd_title;
//            ImageView xkcd_image;
//            TextView xkcd_alt_text;
//            TextView xkcd_no;
//            TextView xkcd_crdate;
//
//            xkcd_title = (TextView)convertView.findViewById(R.id.xkcd_title);
//            xkcd_image=(ImageView)convertView.findViewById(R.id.xkcd_image);
//            xkcd_alt_text=(TextView)convertView.findViewById(R.id.xkcd_alt_text);
//            xkcd_crdate=(TextView)convertView.findViewById(R.id.xkcd_crdate);
//            xkcd_no=(TextView)convertView.findViewById(R.id.xkcd_no);
//
//            // Then later, when you want to display image
//            ImageLoader.getInstance().displayImage(xkcdModel.getImg(), xkcd_image);
//            Log.d("my_text", "xkcd_image is: " + xkcd_image);
//
//            xkcd_title.setText(xkcdModel.getTitle());
//            xkcd_alt_text.setText(xkcdModel.getAlt());
//            xkcd_crdate.setText("Year:"+ xkcdModel.getYear());
//            xkcd_no.setText("No:"+ xkcdModel.getNum());
//
//            return convertView;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.app_bar_search:
                Toast.makeText(getApplicationContext(), "Search a spesific xkcd", Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                Toast.makeText(getApplicationContext(), "Previous xkcd", Toast.LENGTH_SHORT).show();
                break;
            case R.id.refresh:
                Toast.makeText(getApplicationContext(), "Random xkcd", Toast.LENGTH_SHORT).show();
                execute_URL();
                break;
            case R.id.next:
                Toast.makeText(getApplicationContext(), "Next xkcd", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(getApplicationContext(), "Share this xkcd", Toast.LENGTH_SHORT).show();
                break;
            case R.id.go_to_xkcd:
                Toast.makeText(getApplicationContext(), "Go to xkcd.com", Toast.LENGTH_SHORT).show();

                break;
            case R.id.go_to_xkcd_explain:
                Toast.makeText(getApplicationContext(), "Go to explain xkcd", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}


