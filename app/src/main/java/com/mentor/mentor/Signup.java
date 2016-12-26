package com.mentor.mentor;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity
{
    EditText username,password,retype_password,email_id,mobile_no;
    String username_valid="no",password_valid="no",retype_password_valid="no",email_id_valid="no",mobile_no_valid="no";
    String username_value,password_value,retype_password_value,email_id_value,mobile_no_value;


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

        check_valid();
    }

    public void go_to_login(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Profile_finish.class);
        startActivity(intent);
        finish();
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
