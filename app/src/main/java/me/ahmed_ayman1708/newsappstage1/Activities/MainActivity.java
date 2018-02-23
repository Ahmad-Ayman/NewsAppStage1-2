package me.ahmed_ayman1708.newsappstage1.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import me.ahmed_ayman1708.newsappstage1.Fragments.TechFragment;
import me.ahmed_ayman1708.newsappstage1.Fragments.EnvironmentFragment;
import me.ahmed_ayman1708.newsappstage1.Fragments.PoliticsFragment;
import me.ahmed_ayman1708.newsappstage1.Fragments.SportFragment;
import me.ahmed_ayman1708.newsappstage1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_sport:
                        SportFragment sportFragment = new SportFragment();
                        FragmentManager managerSport = getSupportFragmentManager();
                        managerSport.beginTransaction().replace(R.id.articalsLayout, sportFragment).commit();
                        return true;
                    case R.id.navigation_environment:
                        EnvironmentFragment environmentFragment = new EnvironmentFragment();
                        FragmentManager managerEnvironment = getSupportFragmentManager();
                        managerEnvironment.beginTransaction().replace(R.id.articalsLayout, environmentFragment).commit();
                        return true;
                    case R.id.navigation_politics:
                        PoliticsFragment politicsFragment = new PoliticsFragment();
                        FragmentManager managerPolitics = getSupportFragmentManager();
                        managerPolitics.beginTransaction().replace(R.id.articalsLayout, politicsFragment).commit();
                        return true;
                    case R.id.navigation_technology:
                        TechFragment techFragment = new TechFragment();
                        FragmentManager managerEconomics = getSupportFragmentManager();
                        managerEconomics.beginTransaction().replace(R.id.articalsLayout, techFragment).commit();
                        return true;
                }
                return false;
            }
        });

        SportFragment sportFragment = new SportFragment();
        FragmentManager managerSport = getSupportFragmentManager();
        managerSport.beginTransaction().replace(R.id.articalsLayout, sportFragment).commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_settings){
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
