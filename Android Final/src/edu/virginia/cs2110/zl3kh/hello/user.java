package edu.virginia.cs2110.zl3kh.hello;

import java.util.Random;

import android.location.Location;

public class user {
	private double longitude;
	private double latitude;
	
	private GPSTracker gps;
	private Random gen;
	private Location userLoc;
	
	public user() {
		userLoc = gps.getLocation();
		longitude = gps.getLongitude();
		latitude = gps.getLatitude();
		gen = new Random();
	}
	
	public boolean lost(ghost g) {
		double deltax = g.getLatitude() - this.getLatitude();
		double deltay = g.getLongitude() - this.getLongitude();
		double distance = Math.sqrt(deltax * deltax + deltay * deltay);
		
		if (distance < 10) {
			return true;
		}
		
		return false;
	}
	
	/** 
	 * getters/setters for location
	 * */
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Location getUserLoc() {
		return userLoc;
	}

	public void setUserLoc(Location userLoc) {
		this.userLoc = userLoc;
	}
	
	
}
