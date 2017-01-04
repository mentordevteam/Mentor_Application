package com.mentor.mentor;

import android.app.AlertDialog;
import android.content.*;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    FragmentTransaction fragmentTransaction;

    String search_text="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout= (DrawerLayout) findViewById(R.id.activity_main);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);

        NavigationView navigationView= (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_drawer_layout,new Fragment_home(),"Home");
        fragmentTransaction.commit();
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.search_menu:
                /*getSupportActionBar().setDisplayShowCustomEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setCustomView(R.layout.custom_actionbar);
                ImageButton imageButton= (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.button_back_search);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        getSupportActionBar().setDisplayShowCustomEnabled(false);
                    }
                });*/
        }
        return toggle.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu,menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_header_home:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_home(),"Home").commit();
                item.isChecked();
                break;
            case R.id.nav_header_store_header:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_store_locator(),"Store Locator").commit();
                item.isChecked();
                break;
            case R.id.nav_header_online_shop:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_online_shop(),"Online Shop").commit();
                item.isChecked();
                break;
            case R.id.nav_header_profile:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_profile(),"Profile").commit();
                item.isChecked();
                break;
            case R.id.nav_header_switch_as_a_dealer:

                Intent intent=new Intent(getApplicationContext(),Seller.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_header_settings:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_settings(),"Settings").commit();
                item.isChecked();
                break;
            case R.id.nav_header_logout:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
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
                        SharedPreferences sharedPreferences=getSharedPreferences("mentor",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("login_status","no");
                        editor.apply();
                        Intent intent1=new Intent(getApplicationContext(), Login.class);
                        startActivity(intent1);
                        finish();
                    }
                });
                alertDialog.show();
                break;
        }
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
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
                MainActivity.this.finish();
                System.exit(0);
            }
        });
        alertDialog.show();
    }

    int i=0;

    public void go_to_news_feed(View view)
    {
        if(i==0)
        {
            i=1;
            FloatingActionButton floatingActionButton= (FloatingActionButton) findViewById(R.id.floating_action_button);
            floatingActionButton.setImageResource(R.drawable.ic_home);
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_news_feed(),"News feed");
            fragmentTransaction.commit();
        }
        else if(i==1)
        {
            i=0;
            FloatingActionButton floatingActionButton= (FloatingActionButton) findViewById(R.id.floating_action_button);
            floatingActionButton.setImageResource(R.drawable.ic_news_feed);
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_drawer_layout,new Fragment_home(),"Home");
            fragmentTransaction.commit();
        }

    }
}
