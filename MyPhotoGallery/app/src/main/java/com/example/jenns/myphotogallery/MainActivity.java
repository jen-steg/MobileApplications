package com.example.jenns.myphotogallery;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
/*
Jennifer Stegina
14 July 2019
CIS 332 Unit 4 Graded Exercise 1
 */
public class MainActivity extends AppCompatActivity {

    // Int array that houses the images
    Integer[] Dogs = {R.drawable.tinyderpface, R.drawable.patrioticderp, R.drawable.bothderpfaces,
            R.drawable.bigderpface, R.drawable.batderp, R.drawable.sharkderp};
    // Variable for the ImageView
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiating the GridView control
        GridView grid = findViewById(R.id.gridView);
        // Instantiating the ImageView
        final ImageView pic = findViewById(R.id.imgLarge);
        // Add class for ImageAdapter and instantiate it
        grid.setAdapter(new ImageAdapter(this));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Derpy Dog Version " + (position+1), Toast.LENGTH_SHORT).show();
                pic.setImageResource(Dogs[position]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        public ImageAdapter (Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return Dogs.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            pic = new ImageView(context);
            pic.setImageResource(Dogs[position]);
            pic.setScaleType(ImageView.ScaleType.FIT_XY);
            pic.setLayoutParams(new GridView.LayoutParams(150,400));
            return pic;
        }
    }
}


