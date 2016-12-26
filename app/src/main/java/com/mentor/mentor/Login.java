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

    public void go_to_home(View view)
    {
        if(username_valid.equals("yes")&&password_valid.equals("yes"))
        {
            if(username_value.equals("9876543210")&&password_value.equals("mentoradmin"))
            {
                Custom_Toast c=new Custom_Toast(getApplicationContext(),view,getLayoutInflater());
                c.show("Login Successful");

                new com.mentor.mentor.SharedPreferences(getApplicationContext()).set("login_status","yes");

                Intent i=new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                finish();
            }
            else
            {
                Custom_Toast c=new Custom_Toast(getApplicationContext(),view,getLayoutInflater());
                c.show("Invalid credentials");
            }
        }
        else
        {
            Custom_Toast c=new Custom_Toast(getApplicationContext(),view,getLayoutInflater());
            c.show("Invalid credentials");
        }
    }



    private void check_valid()
    {
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    if(username.getText().toString().length()<10)
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
