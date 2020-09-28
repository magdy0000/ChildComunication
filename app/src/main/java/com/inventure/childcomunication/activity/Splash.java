package com.inventure.childcomunication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.inventure.childcomunication.R;
import com.inventure.childcomunication.helpers.MyDataControl;
import com.inventure.childcomunication.helpers.MySharedPreference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Splash extends AppCompatActivity {
  ImageView imageView ;

     MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.image);

      MySharedPreference.init(this);

        mediaPlayer = MediaPlayer.create(this,R.raw.splash);
        mediaPlayer.start();


        Glide.with(this).load(R.drawable.gif_splash).into(imageView);
         new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {


                 if ("".equals(MySharedPreference.getGender())) {
                      mediaPlayer.stop();
                      mediaPlayer = null;
                     Intent intent = new Intent(Splash.this, SelectColor.class);
                     startActivity(intent);
                     finish();
                 } else {
                     mediaPlayer.stop();
                     mediaPlayer = null;
                     Intent intent = new Intent(Splash.this, Home.class);
                     startActivity(intent);
                     finish();
                 }



         }
     },4000);







    }




}
