package com.example.phillycrime;

import android.support.v4.app.FragmentTransaction;

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
		locdate.execute((Void)null);
	}
	protected void setUpMap() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (map == null) {
	        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (map == null) {
	            finish();

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
					 CSVReader reader;
					try {
						reader = new CSVReader(new FileReader(main.getFilesDir()+File.separator+c.DATE+".csv"));
						List<String[]> myEntries = reader.readAll();
						LatLng[] latlngs= new LatLng[myEntries.get(0).length];
						for (int i=0;i<myEntries.size();i++){
							for (int n=0;n<myEntries.get(i).length;n++){
								if (i==0){
									latlngs[n]=new LatLng(Double.parseDouble(myEntries.get(0)[n]), Double.parseDouble(myEntries.get(1)[n]));
								}
								else if (i==1){
									
								}
								else if (i==2){
									
								}
							}
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return null;
			}
	    	
	    }

}
