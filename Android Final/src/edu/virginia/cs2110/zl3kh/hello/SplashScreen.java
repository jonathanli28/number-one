package edu.virginia.cs2110.zl3kh.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 5000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash2);
        Log.d("mainactivity","---");

     //  New Handler to start the Menu-Activity 
        // and close this Splash-Screen after some seconds.
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                 //Create an Intent that will start the Menu-Activity. 
                Intent mainIntent = new Intent(SplashScreen.this,MapView.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
              
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}