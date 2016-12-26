package com.mentor.mentor;

import android.app.AlertDialog;
import android.content.*;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    FragmentTransaction fragmentTransaction;
    DrawerLayout drawer;
    NavigationView navigationView;

    ActionBarDrawerToggle toggle;
    String search_text;

    @Override
    protected void onCreate(Bundle savedInstanceState)throws NullPointerException
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        drawer= (DrawerLayout) findViewById(R.id.main_drawer);

        /*ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        View custom_action_bar=getLayoutInflater().inflate(R.layout.custom_actionbar,null);
        actionBar.setCustomView(custom_action_bar);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        actionBar.setDisplayShowCustomEnabled(true);*/

        toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView= (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_drawer_layout,new Fragment_home(),"Home");
        fragmentTransaction.commit();
    }

    public void go_to_news_feed(View view)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_news_feed(),"News feed");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Toast.makeText(getApplicationContext(),"cool",Toast.LENGTH_LONG).show();
        switch (item.getItemId())
        {
            case R.id.nav_header_home:
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                Toast.makeText(getApplicationContext(),"cool",Toast.LENGTH_LONG).show();
                fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_home(),"Home").commit();
                item.isChecked();
                break;
            case R.id.nav_header_logout:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Home.this);
                alertDialog.setTitle("Do you want to Logout?");
                alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent1=new Intent(getApplicationContext(), Login.class);
                        startActivity(intent1);
                        finish();
                    }
                });
                alertDialog.show();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(Home.this);
        alertDialog.setTitle("Do you want to Exit?");
        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Home.this.finish();
                System.exit(0);
            }
        });
        alertDialog.show();
    }

}
