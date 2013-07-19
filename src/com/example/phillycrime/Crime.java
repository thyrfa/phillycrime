package com.example.phillycrime;

import com.google.android.gms.maps.model.LatLng;

public class Crime {
	protected LatLng location;
	protected String crime;
	protected String time;
	public Crime(LatLng loc, String c, String t){
		location=loc;
		crime=c;
		time=t;
	}
	public LatLng getLocation(){
		return location;
	}
}
