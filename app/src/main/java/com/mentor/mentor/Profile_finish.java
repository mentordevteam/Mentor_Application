package com.mentor.mentor;

import android.Manifest;
import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Profile_finish extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ImageView imageView;
    Bitmap bitmap;
    EditText textView1,textView2;

    Location location;
    GoogleApiClient googleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_finish);

        imageView= (ImageView) findViewById(R.id.image_view_profile_finish);
        textView1= (EditText) findViewById(R.id.edit_text_city_name);
        textView2= (EditText) findViewById(R.id.edit_text_state_name);

        buildGoogleApiClient();

        android.content.SharedPreferences sharedPreferences=getSharedPreferences("mentor", Context.MODE_APPEND);
        String s=sharedPreferences.getString("login_status","default");
        switch (s)
        {
            case "default":
            case "no":
            {
                Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:"+getApplicationContext().getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                getApplicationContext().startActivity(intent);
                break;
            }
            default:
                break;
        }

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }


    public void choose_image(View v)
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            Uri filepath=data.getData();
            try
            {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null)
        {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String get = addresses.get(0).getAddressLine(2);

            String cityname="";
            String State="";

            char[] c=get.toCharArray();
            int j=0;

            for (char aC : c) {
                if (aC != ',') {
                    cityname += aC;
                    j++;
                }
                else if(aC==',')
                {
                    break;
                }
            }

            textView1.setText(cityname);
            get=get.trim();
            c=get.toCharArray();
            for(int i=j+1;i<c.length-6;i++)
            {
                State+=c[i];
            }
            textView2.setText(State);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
