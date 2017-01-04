package com.mentor.mentor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity
{
    EditText username,password;
    String username_value,password_value,username_valid="no",password_valid="no";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username= (EditText) findViewById(R.id.edit_text_login_username);
        password= (EditText) findViewById(R.id.edit_text_login_password);

        username.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        password.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

        check_valid();
    }

    public void go_to_sign_up(View view)
    {
        Intent i=new Intent(getApplicationContext(),Signup.class);
        startActivity(i);
        finish();
    }

    public void go_to_home(final View view)
    {
        if(username.getText().toString().equals("9876543210")&&password.getText().toString().equals("mentoradmin"))
        {
            Custom_Toast c=new Custom_Toast(getApplicationContext(),view,getLayoutInflater());
            c.show("Login Successful");

            new com.mentor.mentor.SharedPreferences(getApplicationContext()).set("login_status","yes");

            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.9:8000/user_login", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {

                        finish();

                        SharedPreferences sharedPreferences = getSharedPreferences("mentor", Context.MODE_PRIVATE);
                        String s = sharedPreferences.getString("profile", "default");
                        if (s.equals("default"))
                        {
                            new com.mentor.mentor.SharedPreferences(getApplicationContext()).set("profile", "go");
                            Intent intent = new Intent(getApplicationContext(), Profile_finish.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            new com.mentor.mentor.SharedPreferences(getApplicationContext()).set("login_status","yes");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        new Custom_Toast(getApplicationContext(), view, getLayoutInflater()).show("Invalid credentials");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    new Custom_Toast(getApplicationContext(), view, getLayoutInflater()).show(error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringMap = new Hashtable<>();
                    stringMap.put("phone", username.getText().toString());
                    stringMap.put("password", password.getText().toString());
                    return stringMap;
                }
            };
            RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }



    private void check_valid()
    {
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(username.getText().toString().length()!=10)
                    {
                        username.setError("Invalid Mobile No");
                    }
                    else
                    {
                        username_value=username.getText().toString();
                        username_valid="yes";
                    }
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
                        password_value=password.getText().toString();
                        password_valid="yes";
                    }
                }
            }
        });
    }
}
