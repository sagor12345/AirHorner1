package com.example.airhorner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    MediaPlayer player;
    AnimationDrawable animationDrawable;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        button = findViewById( R.id.btn );

        ImageView img = findViewById(R.id.image);
        img.setBackgroundResource(R.drawable.anim);
        animationDrawable = (AnimationDrawable) img.getBackground();

        final int[] sound= {R.raw.air2,R.raw.air2,R.raw.horn,R.raw.horn,R.raw.air2,R.raw.air2,R.raw.horn,R.raw.horn};

        button.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction()== MotionEvent.ACTION_DOWN) {
                    animationDrawable.start();

                    Random random = new Random();
                    int low=0;
                    int high=8;
                    int rnd = random.nextInt(high-low)+low;

                    player= MediaPlayer.create( MainActivity.this,sound[rnd] );
                    player.start();

                    v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                    v.invalidate();
                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    animationDrawable.stop();
                    animationDrawable.selectDrawable(0);
                    player.stop();
                    player.release();

                    v.getBackground().clearColorFilter();
                    v.invalidate();
                }
                return true;

            }

        } );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about_us:
             //   addSomething();
                Toast.makeText(MainActivity.this,"About Us",Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_settings:
              //  startSettings();
                Toast.makeText(MainActivity.this,"About Us",Toast.LENGTH_LONG).show();
                return true;

            case R.id.share:
                //  startshare();
                //Toast.makeText(MainActivity.this,"About Us",Toast.LENGTH_LONG).show();
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Air Horner Apps");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
