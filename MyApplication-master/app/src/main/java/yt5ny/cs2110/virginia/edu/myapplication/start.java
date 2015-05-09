package yt5ny.cs2110.virginia.edu.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Student on 3/31/2015.
 */
public class start extends Activity {
    Drawing v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = new Drawing(this);
        setContentView(v);

    }
}
