package yt5ny.cs2110.virginia.edu.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        Button but2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //buttonSound.start();
                startActivity(new Intent("yt5ny.cs2110.virginia.edu.myapplication.INSTRUCTION"));
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //buttonSound.start();
                startActivity(new Intent("yt5ny.cs2110.virginia.edu.myapplication.START"));


            }
            public void button1Click() {

            }
        });

    }

    @Override
    protected void onPause() {
    }
}

//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
