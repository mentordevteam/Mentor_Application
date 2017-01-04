package com.mentor.mentor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Seller extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_drawer_layout_seller,new Fragment_seller_main(),"Seller").commit();

        drawerLayout= (DrawerLayout) findViewById(R.id.activity_seller);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView= (NavigationView) findViewById(R.id.navigation_seller);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(Seller.this);
        alertDialog.setTitle("Do you go back to user mode?");
        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
FragmentTransaction fragmentTransaction;
        switch (item.getItemId())
        {
            case R.id.nav_seller_view_products:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_drawer_layout_seller,new Fragment_seller_view_products(),"view products").commit();
                item.isChecked();
                break;
            case R.id.nav_seller_switch_to_user:
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                item.isChecked();
                break;
            case R.id.nav_header_profile:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_drawer_layout_seller,new Fragment_profile(),"Profile").commit();
                item.isChecked();
                break;
            case R.id.nav_header_settings:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_drawer_layout_seller,new Fragment_settings(),"Settings").commit();
                item.isChecked();
                break;
            case R.id.nav_header_logout:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Seller.this);
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
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
