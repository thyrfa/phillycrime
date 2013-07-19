package com.example.phillycrime;

import java.io.File;
import java.util.Calendar;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	private GoogleMap map;
	public MainActivity(){

	}
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
		MapFragment mMapFragment = MapFragment.newInstance();
		GoogleMapOptions option= new GoogleMapOptions();
		option.camera(new CameraPosition(map.getCameraPosition().target,(float)14.0,(float)0.0,(float)0.0));
		setUpMap();
		map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		map.setMyLocationEnabled(true);
		GPSGrabber locdate= new GPSGrabber(this, mMapFragment);
	}
	protected void setUpMap() {
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
	public void addMark(MarkerOptions mark){
		map.addMarker(mark);
	}
	 public class GPSGrabber  extends AsyncTask<Void, Void, Void>{
			LocationClient mLocationClient;
			Location mCurrentLocation;
			MainActivity main;
			MapFragment mmap;

	    	public GPSGrabber(MainActivity man, MapFragment mapfrag){
	    		main=man;
	    		mmap=mapfrag;
	    		
	    	}

			@Override
			protected Void doInBackground(Void... params) {
				FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
				fragmentTransaction.add(R.id.map, mmap);
				fragmentTransaction.commit();
				Calendar c = Calendar.getInstance(); 
				File file=new File(main.getFilesDir()+File.separator+c.DATE+".csv");
				if (file.exists()){
					
				}
				return null;
			}
	    	
	    }

}
