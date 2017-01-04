package com.mentor.mentor;

import android.Manifest;
import android.content.*;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    EditText username,password,retype_password,email_id,mobile_no;
    String username_valid="no",password_valid="no",retype_password_valid="no",email_id_valid="no",mobile_no_valid="no";
    String username_value,password_value,retype_password_value,email_id_value,mobile_no_value;

    Location location;
    GoogleApiClient googleApiClient;

    String cityname="";
    String state="";

    String latitude="";
    String longitude="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username= (EditText) findViewById(R.id.edit_text_login_username);
        password= (EditText) findViewById(R.id.edit_text_sign_up_password);
        retype_password= (EditText) findViewById(R.id.edit_text_sign_up_retype_password);
        email_id= (EditText) findViewById(R.id.edit_text_sign_up_email_id);
        mobile_no= (EditText) findViewById(R.id.edit_text_sign_up_mobile_no);

        username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        retype_password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        email_id.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        mobile_no.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

        buildGoogleApiClient();

        check_valid();

    }

    protected void onStart()
    {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        googleApiClient.disconnect();
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
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
            this.latitude=String.valueOf(latitude);
            this.longitude=String.valueOf(longitude);

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

            this.cityname=cityname;
            get=get.trim();
            c=get.toCharArray();
            for(int i=j+1;i<c.length-6;i++)
            {
                State+=c[i];
            }
            this.state=State;
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }




    public void go_to_login(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
    }

    public void go_to_valide_login(final View view)
    {
        store_value();
        if(!(username_valid.equals("yes")&&password_valid.equals("yes")&&email_id_valid.equals("yes")&&mobile_no_valid.equals("yes")))
        {
            new Custom_Toast(getApplicationContext(),view,getLayoutInflater()).show("Invalid data");
        }
        else
        {
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
            SharedPreferences sharedPreferences=getSharedPreferences("mentor",Context.MODE_PRIVATE);
            StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.1.9:8000/user_signup", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    new Custom_Toast(getApplicationContext(),view,getLayoutInflater()).show(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    new Custom_Toast(getApplicationContext(),view,getLayoutInflater()).show(error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringMap = new Hashtable<>();
                    stringMap.put("username", username.getText().toString());
                    stringMap.put("password", password.getText().toString());
                    stringMap.put("email", email_id.getText().toString());
                    stringMap.put("phone", mobile_no.getText().toString());
                    stringMap.put("city", cityname);
                    stringMap.put("state", state);
                    stringMap.put("longitude", longitude);
                    stringMap.put("latitude", latitude);
                    return stringMap;
                }
            };
            RequestQueue requestQueue= com.android.volley.toolbox.Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
    }

    public void store_value()
    {
        if(username_valid.equals("yes")&&password_valid.equals("yes")&&email_id_valid.equals("yes")&&mobile_no_valid.equals("yes"))
        {
            android.content.SharedPreferences sharedPreferences=getSharedPreferences("mentor",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("username",username.getText().toString());
            editor.putString("password",password.getText().toString());
            editor.putString("email",email_id.getText().toString());
            editor.putString("phone",mobile_no.getText().toString());
            editor.apply();
        }
    }

    public void check_valid()
    {
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(username.getText().toString().length()<10)
                    {
                        username.setError("Invalid Username");
                    }
                }
                else
                {
                    username_valid="yes";
                    username_value=username.getText().toString();
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(password.getText().toString().length()<10)
                    {
                        password.setError("Invalid password");
                    }
                    else
                    {
                        password_valid="yes";
                        password_value=password.getText().toString();
                    }
                }
            }
        });

        retype_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(retype_password.getText().toString().length()<10)
                    {
                        retype_password.setError("Invalid password");
                    }
                    else
                    {
                        if(password_value.equals(retype_password.getText().toString()))
                        {
                            retype_password.setError("Password not matched");
                        }
                    }
                }
            }
        });

        email_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(!Pattern.matches(Patterns.EMAIL_ADDRESS.toString(),email_id.getText().toString()))
                    {
                        email_id.setError("Invalid email id");
                    }
                    else
                    {
                        email_id_value=email_id.getText().toString();
                        email_id_valid="yes";
                    }
                }
            }
        });

        mobile_no.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if (mobile_no.getText().toString().length()<10)
                    {
                        mobile_no.setError("Invalid mobile no");
                    }
                    else
                    {
                        mobile_no_value=mobile_no.getText().toString();
                        mobile_no_valid="yes";
                    }
                }
            }
        });
    }
}
