package edu.virginia.cs2110.zl3kh.hello;

import java.util.Random;
//import com.google.android.gms.maps.model.Marker;

import android.location.Location;

public class ghost{
	private double longitude;
	private double latitude;
	
	private GPSTracker gps;
	private int speed = 10;
	private Random gen;
	private Location ghostLoc;
	private user player;
	
	public ghost() {
		gen = new Random();
		this.ghostLoc = gps.getLocation();
		setLongitude(gps.getLongitude() + gen.nextInt(16));
		setLatitude(gps.getLatitude() + gen.nextInt(16));
	}
	
	public void move(float elapsedTime) {
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
		
		this.longitude += dy;
		this.latitude += dx;
	}
	
	public boolean collisionWith(ghost g) {
		double deltax = g.getLatitude() - this.getLatitude();
		double deltay = g.getLongitude() - this.getLongitude();
		double distance = Math.sqrt(deltax * deltax + deltay * deltay);
		if (distance < 10) {
			return true;
		}
		return false;
	}
	
	public boolean collisionWith (user p) {
		double deltax = p.getLatitude() - this.getLatitude();
		double deltay = p.getLongitude() - this.getLongitude();
		double distance = Math.sqrt(deltax * deltax + deltay * deltay);
		if (distance < 10) {
			return true;
		}
		return false;
	}
	
	/** 
	 * getters/setters
	 * */
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
