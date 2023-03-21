package com.example.stressaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, JournalFragment.OnFragmentInteractionListener{

    ImageView journal, books, music, movie, diet, yoga, location;
    String fragmentSwitch ="nul";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (id){
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.profile:
                        Toast.makeText(MainActivity.this,"Profile",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mentorChat:
                        Toast.makeText(MainActivity.this,"Chat",Toast.LENGTH_SHORT).show();

                    case R.id.mentorDetails:
                        Toast.makeText(MainActivity.this,"Details",Toast.LENGTH_SHORT).show();

                    case R.id.aboutUs:
                        Toast.makeText(MainActivity.this,"About us",Toast.LENGTH_SHORT).show();

                    case R.id.settings:
                        Toast.makeText(MainActivity.this,"Settings",Toast.LENGTH_SHORT).show();

                    default:
                        return true;
                }
                return true;
            }
        });


        switch (fragmentSwitch){

            case "journal":
                replaceFragment(new JournalFragment());
                break;
            default:

        }


    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }


    public void messageFromParentFragment(Uri uri) {
        Log.i("TAG", "received communication from parent fragment");
    }


    public void messageFromChildFragment(Uri uri) {
        Log.i("TAG", "received communication from child fragment");
    }

    public void onInputHomeSent(CharSequence input){

    }
}