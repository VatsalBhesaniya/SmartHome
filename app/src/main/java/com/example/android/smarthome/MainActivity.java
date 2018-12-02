package com.example.android.smarthome;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Creating variables
    private AdapterViewFlipper adapterViewFlipper;
    private static final int[] Images = {R.drawable.smart_home_one, R.drawable.smart_home_two, R.drawable.smart_home_three, R.drawable.smart_home_four, R.drawable.smart_home_five};
    private TextView emailAdress;
    private TextView ContactNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAdress = (TextView) findViewById(R.id.emailAdress);
        ContactNumber = (TextView)findViewById(R.id.contactNumber);
        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.adapterViewFlipper);

//        Creating adapter object
        FlipperAdapter adapter = new FlipperAdapter(this, Images);
        adapterViewFlipper.setAdapter(adapter);
        adapterViewFlipper.setAutoStart(true);
    }

    class FlipperAdapter extends BaseAdapter{
        Context ctx;
        int[] images;
        LayoutInflater inflater;

        public FlipperAdapter(Context context, int[] myImages){
            this.ctx = context;
            this.images = myImages;
            inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount(){
            return images.length;
        }

        @Override
        public Object getItem(int i){
            return null;
        }

        @Override
        public long getItemId(int i){
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup){
            view = inflater.inflate(R.layout.flipper, null);
            ImageView image = (ImageView) view.findViewById(R.id.cover_image);
            image.setImageResource(images[i]);
            return view;
        }
    }

//    Creating intent for map to show location
    public void getLocation(View v){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse(getString(R.string.map_location_search_link)));
        startActivity(mapIntent);
    }

//    Creating intent for phone call
    public void phoneCall(View v){
        String contactNumber = ContactNumber.getText().toString();

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + contactNumber));
        startActivity(callIntent);
    }

//    Creating intent for email
    public void contactUs(View veiw) {
        String mailto = emailAdress.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + mailto));
        emailIntent.putExtra(emailIntent.EXTRA_SUBJECT, "Inquiry for Interior Service");
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}
