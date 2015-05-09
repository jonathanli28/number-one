package edu.virginia.cs2110.zl3kh.hello;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MapView extends FragmentActivity implements OnMapClickListener
{
	private GoogleMap map;
	private double user_longitude;
	private double user_latitude;
	private Marker player;
	private GPSTracker gps;
	private ArrayList<ghost> ghosts;
	private TextView textView; 
	private ArrayList<Money> moneys;
	private ArrayList<Marker> ghostsMarker = new ArrayList<Marker>();
	private ArrayList<Marker> moneyMarker;
	private int i=0;
	private Ringtone r ;
	private Ringtone s;
	private int killed = 0;
	private boolean pause = false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		
		gps = new GPSTracker(MapView.this);

		// check if GPS enabled    
		if(gps.canGetLocation()){

			this.user_latitude = gps.getLatitude();
			this.user_longitude = gps.getLongitude();	
		}

		
	  textView = (TextView) findViewById(R.id.score);

		//MapFragment mMapFragment = MapFragment.newInstance();

		map = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map))

				.getMap();
		map.setOnMapClickListener(this);
		

		LatLng a = new LatLng(user_latitude, user_longitude);
//		Log.d("a ",a.toString());
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(a,19.0f) );
		player = map.addMarker(this.makeSqMarkerOption("squirrrel_1.png",a));
//		Log.d("gps ", a.toString());
//		Log.d("beinning p[osition", player.getPosition().toString());
		Random random = new Random();
		for(int i = 0 ; i < 20 ; i ++)
		{
			Marker ghost1 = map.addMarker(makeSqMarkerOption("ghost_marker.png", 
					new LatLng(user_latitude+0.00001*random.nextInt(100),
							user_longitude+0.00001*random.nextInt(100))));
			this.ghostsMarker.add(ghost1);
			Marker ghost2 =map.addMarker(makeSqMarkerOption("ghost_marker.png", 
					new LatLng(user_latitude-0.00001*random.nextInt(100),
							user_longitude+0.00001*random.nextInt(100))));
			this.ghostsMarker.add(ghost2);
			
			Marker ghost3 = map.addMarker(makeSqMarkerOption("ghost_marker.png", 
					new LatLng(user_latitude+0.00001*random.nextInt(100),
							user_longitude-0.00001*random.nextInt(100))));
			this.ghostsMarker.add(ghost3);
			
			Marker ghost4= map.addMarker(makeSqMarkerOption("ghost_marker.png", 
					new LatLng(user_latitude-0.00001*random.nextInt(100),
							user_longitude-0.00001*random.nextInt(100))));
			this.ghostsMarker.add(ghost4);

		}
		//textView.setTextColor(Color.BLUE);

		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		
		Uri notification2 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		s = RingtoneManager.getRingtone(getApplicationContext(), notification2);
		

}
		//		Marker marker=map.addMarker(this.makeSqMarkerOption("squirrrel_1.png",new LatLng(latitude+0.00001, longitude+0.00002)));

		//		m.setAnchor(0.5f, 0.5f);
		//		m.setPosition(a);




		public TextView getTextView() {
		return textView;
	}

		@Override
		protected void onResume() {
			
			super.onResume();
			
				final Handler mHandler = new Handler();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						while(pause){ Thread.sleep(500);}
						//if
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								boolean alarm = false;
								
								//alert user 
								for(Marker a : ghostsMarker)
								{
									move(a,1);
									float[] distance = new float[1];
									Location.distanceBetween(user_latitude, user_longitude, a.getPosition().latitude, a.getPosition().longitude,distance);
//									Log.d("distance", Float.toString(distance[0]));
									if( distance[0]< 10 && a.isVisible() && !alarm)
									{
										s.play();
										alarm=true;
									}
								}
								if (!alarm) {s.stop();}
									
								textView.setTextColor(Color.BLUE);
//								Log.d("user_latitude",Double.toString(user_latitude));
//								Log.d("gps.getLatitude()", Double.toString(gps.getLatitude()));
								user_latitude = gps.getLatitude();
								user_longitude = gps.getLongitude();
								
								player.setPosition(new LatLng(gps.getLatitude(),gps.getLocation().getLongitude()));
//								textView.setText("changed" + i + gps.change+ "/n"+ new LatLng(gps.getLatitude(),gps.getLocation().getLongitude()).toString()+ "/n"+ player.getPosition().toString());
								
							textView.setText(killed +  "/ " +ghostsMarker.size());
	
				
							}


						});
					} catch (Exception e) {
					}
				}
			}
		}).start();

	}

	public MarkerOptions makeSqMarkerOption(String image, LatLng a)
	{

		String imagePath= image;
		BitmapDescriptor icon = BitmapDescriptorFactory.fromAsset(imagePath);

		//		BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.squirrrel);



		MarkerOptions markerOptions = new MarkerOptions().
				position(a)
				.anchor(0.5f, 0.5f).icon(icon);
		//		MarkerOptions markerOptions = new MarkerOptions().icon(icon).position(a)
		//				.title(new LatLng(latitude, longitude).toString());

		return markerOptions;
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the map.
		if (map == null) {
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
					.getMap();
			// Check if we were successful in obtaining the map.
			if (map != null) {
				// The Map is verified. It is now safe to manipulate the map.

			}
		}
	}

	@Override
	public void onMapClick(LatLng arg0) {
		Log.d("Hell0", "mapClicked");
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(user_latitude, user_longitude),20.0f) );
		for(Marker a : this.ghostsMarker)
		{
			float[] distance = new float[1];
			Location.distanceBetween(this.user_latitude, this.user_longitude, a.getPosition().latitude, a.getPosition().longitude,distance);
//			Log.d("distance", Float.toString(distance[0]));
			if( distance[0]< 20 && a.isVisible())
			{
				//textView.setText("killed!");
				
				a.setVisible(false);
				r.play();
				textView.setTextColor(Color.RED);
				killed++;
				
				
			}
		}
		
		
		
	}
	
	public void move(Marker m, double elapsedTime) {
		Random gen = new Random();
	    double speed = 0.000005;
		boolean up = gen.nextBoolean();
		boolean left = gen.nextBoolean();
		double dx;
		double dy;
		if (up) {
			dy = speed * elapsedTime;
		} else {
			dy = -speed * elapsedTime;
		}
		
		if (left) {
			dx = -speed * elapsedTime;
		} else {
			dx = speed*elapsedTime;
		}
		m.setPosition(new LatLng(m.getPosition().latitude+dx, m.getPosition().longitude+dy));
		/*this.longitude += dy;
		this.latitude += dx;*/
	}
	
   public void onPause()
   {
	   super.onPause();
//	   this.pause = true;
   }

}