package com.adjiekurniawan.sumbission2_dicoding.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.adjiekurniawan.sumbission2_dicoding.Fragments.FragmentMovie;
import com.adjiekurniawan.sumbission2_dicoding.Fragments.FragmentTvShow;
import com.adjiekurniawan.sumbission2_dicoding.R;

public class MainMovieActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView toolbarTitle,toolbarDescription;
    private BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

    }


    private void initToolbar(){
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.putih));
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponent();
        defaultFragment();
    }

    private void launchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content,fragment,null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void defaultFragment(){
        toolbarTitle.setText(getString(R.string.fragment_title_movie));
        toolbarDescription.setText(getString(R.string.fragment_description_movie));
        navigation.setSelectedItemId(R.id.navMovie);
        Fragment defaultfragment = new FragmentMovie();
        launchFragment(defaultfragment);
    }

    private void initComponent(){
        navigation = findViewById(R.id.bottomNav);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarDescription = findViewById(R.id.toolbarDescription);
        ImageButton btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(this);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navMovie:
                        toolbarTitle.setText(getString(R.string.fragment_title_movie));
                        toolbarDescription.setText(getString(R.string.fragment_description_movie));
                        Fragment movieFragment = new FragmentMovie();
                        launchFragment(movieFragment);
                        break;
                    case R.id.navTvShow:
                        toolbarTitle.setText(getString(R.string.fragment_title_tv_show));
                        toolbarDescription.setText(getString(R.string.fragment_description_tv_show));
                        Fragment tvshowFragment = new FragmentTvShow();
                        launchFragment(tvshowFragment);
                        break;
                    default:
                        Fragment defaultfragment = new FragmentMovie();
                        launchFragment(defaultfragment);
                }

                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSetting) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
    }

    @Override
    public void onBackPressed() {
        int back = getSupportFragmentManager().getBackStackEntryCount();
        if(back >= 1){
            ShowDialogExit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void ShowDialogExit(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle(R.string.notificationTitle)
                .setMessage(R.string.notificationMessage)
                .setPositiveButton(R.string.notificationButtonPositive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }})
                .setNegativeButton(R.string.notificationButtonNegative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }})
                .show();
    }
}
