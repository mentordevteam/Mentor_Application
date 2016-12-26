package com.mentor.mentor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        check_new_user();
    }


    public void check_new_user()
    {
        //Checking New user or Already logged in
        //'yes'- user already logged in
        //'no'-user logged out
        //'default'-first time to the application

        SharedPreferences sharedPreferences=getSharedPreferences("mentor", Context.MODE_APPEND);
        String s=sharedPreferences.getString("login_status","default");
        switch (s)
        {
            case "default":
            case "no":
            {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
                break;
            }
            case "yes":
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            default:
                break;
        }
    }
}
