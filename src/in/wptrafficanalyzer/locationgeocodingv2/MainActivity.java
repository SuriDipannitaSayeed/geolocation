package in.wptrafficanalyzer.locationgeocodingv2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity implements OnTimeChangedListener {
	
	GoogleMap googleMap;
	final MarkerOptions markerOptions    = new MarkerOptions();
    
	 MarkerOptions markerOptions2=new MarkerOptions(); ; 
	LatLng latLng;
	int hour ;
	int minute  ;
	String Location;
	String Time;
	String Date;
	EditText Taskspecification;
	EditText etLocation;
	TimePicker timePicker1;
	int taskno;
	String response;
	CalendarView view;
	Button b1;
	JSONArray ja;
	   public static  TextView tv;
	public static String username;
		   public ArrayList<String> position = new ArrayList<String>(); // Member
	public static ArrayList<String> dictionary = new ArrayList();
	public  static ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
	ArrayList<XMLGettersSettersClass> mdataent = new ArrayList<XMLGettersSettersClass>();
    private Handler mUiHandler = new Handler();
	ArrayList<LatLng> markerPoints = new ArrayList<LatLng>();
//	ArrayList<Long> distance = new ArrayList<Long>()  ;
	  HashMap<Integer,Long> distance=new HashMap<Integer,Long>();
	int[] dist;
	 TimePickerDialog.OnTimeSetListener timePickerListener;
	 int k=0;
	 LatLng origin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		Bundle extras = getIntent().getExtras();
		
		if(extras != null) { 
		username=extras.getString("Username");
		username.replaceAll("\\s+","");
		Log.d("username", username);
		}
		b1=(Button) findViewById(R.id.tasklist);
	
		try {

			/**
			 * Create a new instance of the SAX parser
			 **/
		String serverURL = "http://172.20.62.23/get.php";

            new MyAsyncTask().execute(serverURL);

		 
		} 
		catch (Exception e) {
			System.out.println(e);
		}

	 
		SupportMapFragment supportMapFragment = (SupportMapFragment) 
				getSupportFragmentManager().findFragmentById(R.id.map);
		
			
	
		// Getting a reference to the map
		  googleMap = supportMapFragment.getMap();
		  googleMap.setMyLocationEnabled(true);
 			
		

		// Getting reference to btn_find of the layout activity_main
    
    
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
     
 
        // Defining button click event listener for the find button

    	
	    // Defining button click event listener for the find button
   
					
				
				 
				//}
				
	 
		
		// Setting button click event listener for the find button
	 	
		
		// Setting button click event listener for the find button
	}	
 

    private class MyAsyncTask extends AsyncTask<String,Void, Void> {
@Override
protected void onPreExecute() {
	// TODO Auto-generated method stub
	super.onPreExecute();
	 
}
        @Override
        protected Void doInBackground(String... params) {
            // TODO Auto-generated method stub
        	 try {
        	  HttpClient client = new DefaultHttpClient();
              HttpGet request = new HttpGet("http://172.20.62.23/get.php"); 
                                      // replace with your url
              ResponseHandler<String> responseHandler = new BasicResponseHandler();
              
             
                  response = client.execute(request,responseHandler);
               
                  Log.d("Response of GET request", response );
              } catch (ClientProtocolException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
			return null;
       
         }

@Override
protected void onPostExecute(Void result) {
	// TODO Auto-generated method stub
	super.onPostExecute(result);
 
	String location ="BUET,DHAKA";
	 
	Location=location;
	if(location!=null && !location.equals("")){
		new GeocoderTask().execute(location);
	}
	
   
mUiHandler.postDelayed((new Runnable() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			 ja =new JSONArray(response);
			 
			int n=ja.length();
			int count=0;
			for(int i=0;i<n;i++){
				JSONObject jo=ja.optJSONObject(i);
				if(jo.optString("asignee").equals(username))
				{ 
				 
					     
					position.add(jo.optString("Location"));
		  
			String location2 =jo.optString("Location");
			Log.d("location2", location2);
			//Location=location;
			if(location2!=null && !location2.equals("")){
				new GeocoderTask().execute(location2);
			
			}
		 
		 
				}
			}
		
		
			b1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.d("data", ja.toString());
					long temp=0;
					int temp1=0;
					
					dist=new int[distance.size()];
					
					for (int l=0;l<distance.size();l++)
					{	
						for (int j=0;j<distance.size();j++)
						{
						if(distance.get(l)>distance.get(j))
						{
							temp=distance.get(l);
							distance.put(l,distance.get(j));
							distance.put(j,temp);
							dist[l]=j;
						}
					
					//Log.d(" "," "+dist[l]);
					}
						
					}
					for (int l=0;l<distance.size();l++)
					{Log.d("pos"," "+dist[l]);
						//Log.d("Distance"," "+distance.get(l));
					}
					Intent intent = new Intent(MainActivity.this, in.wptrafficanalyzer.locationgeocodingv2.TaskActivity.class);
					intent.putExtra("data", ja.toString());
					intent.putExtra("distance", dist);
					startActivity(intent);
				}
			});
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}),1500);
}
      
            // Create a new HttpClient and Post Header


        

    }	
	
    public static long getDistanceMeters(double lat1, double lng1, double lat2, double lng2) {

        double l1 = Math.toRadians(lat1);
        double l2 = Math.toRadians(lat2);
        double g1 = Math.toRadians(lng1);
        double g2 = Math.toRadians(lng2);

        double dist = Math.acos(Math.sin(l1) * Math.sin(l2) +Math.cos(l1) * Math.cos(l2) * Math.cos(g1 - g2));
        if(dist < 0) {
            dist = dist + Math.PI;
        }

        return Math.round(dist * 6378100);
    }
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	// An AsyncTask class for accessing the GeoCoding Web Service
		private class GeocoderTask extends AsyncTask<String, Void, List<Address>>{
@Override
protected void onPreExecute() {
// TODO Auto-generated method stub
super.onPreExecute();

}
			@Override
			protected List<Address> doInBackground(String... locationName) {
				// Creating an instance of Geocoder class
				Geocoder geocoder = new Geocoder(getBaseContext(),Locale.getDefault());
				List<Address> addresses = null;
				
				try {
					// Getting a maximum of 3 Address that matches the input text
					addresses = geocoder.getFromLocationName(locationName[0],3);
				} catch (IOException e) {
					e.printStackTrace();
				}		
				
				return addresses;
			}
			
			
			@Override
			protected void onPostExecute(List<Address> addresses) {
			 
			 
		 
					 if(addresses==null || addresses.size()==0){
							Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
						}
					// TODO Auto-generated method stub
				     googleMap.clear();
						//if(addresses!=null)
				        // Adding Markers on Google Map for each matching address
						for(int i=0;i<addresses.size();i++){				
							 
							Address address = (Address) addresses.get(i);
							if(address!= null){
 
							    double longitude = address.getLongitude();	 
							    double latitude = address.getLatitude();
					        // Creating an instance of GeoPoint, to display in Google Map
							      latLng = new LatLng(latitude, longitude);

									//  Log.d("Distance1", " "+distance[0]);
							}
					    /*    String addres = addresses.get(0).getAddressLine(0);
					        Log.d("addres", addres);
					   // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
					        String city = addresses.get(0).getLocality();
					        Log.d("city", city);
					        String state = addresses.get(0).getAdminArea();
					        Log.d("state", state);
					        String country = addresses.get(0).getCountryName();
					        Log.d("country", country);
					        String postalCode = addresses.get(0).getPostalCode();
					        Log.d("postalCode", postalCode);*/
					    

					        markerPoints.add(latLng);
					      
					       
					     
					     
					     
					   
							//if(markerPoints.size()==1){
								
							//}
							// Getting URL to the Google Directions API
					   
							 
					
					        // Locate the first location
					        if(i==0)			        	
								googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.4f));
					    	if(markerPoints.size() >= 2){	
					    		 
					    		LatLng	   origin=new LatLng( googleMap.getMyLocation().getLatitude(),  googleMap.getMyLocation().getLongitude());

								 
								//LatLng origin = markerPoints.get(0);
								for(int j=1;j<markerPoints.size();j++)
								{
									LatLng dest = markerPoints.get(j);
									if(!distance.containsValue(getDistanceMeters(origin.latitude,origin.longitude, dest.latitude, dest.longitude)))
									{
									 distance.put(k,getDistanceMeters(origin.latitude,origin.longitude, dest.latitude, dest.longitude));
									k++;
									}
									//Log.d("Distance"," "+getDistanceMeters(origin.latitude,origin.longitude,dest.latitude, dest.longitude));
 

									  markerOptions2.position(dest);
							
									 
										markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
										markerOptions2.title("Task"+j); 
										//  markerOptions2.title(addressText);
										  googleMap.addMarker(markerOptions2);
									// Getting URL to the Google Directions API
										  
												
								 
											String url = getDirectionsUrl(origin, dest);
											// TODO Auto-generated method stub
											DownloadTask downloadTask = new DownloadTask();
											
											// Start downloading json data from Google Directions API
											downloadTask.execute(url);	
										
								
								
								}
								
							}
							//downloadTask.execute(url);
			
						}		
				 
						
					/*	Log.d("Distance1", " "+distance[0]);
						Log.d("Distance2", " "+distance[1]);*/
		       
		        
		        // Clears all the existing markers on the map
		   		
			}		
		}
		private String getDirectionsUrl(LatLng origin,LatLng dest){
			
			// Origin of route
			String str_origin = "origin="+origin.latitude+","+origin.longitude;
			
			// Destination of route
			String str_dest = "destination="+dest.latitude+","+dest.longitude;		
		
			 
			  
						
			// Sensor enabled
			String sensor = "sensor=false";			
						
			// Building the parameters to the web service
			String parameters = str_origin+"&"+str_dest+"&"+sensor;
						
			// Output format
			String output = "json";
			 markerOptions.position(origin);
			markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			  
				  googleMap.addMarker(markerOptions);
			// Building the url to the web service
			String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
			
			
			return url;
		}
		
		/** A method to download json data from url */
	    private String downloadUrl(String strUrl) throws IOException{
	        String data = "";
	        InputStream iStream = null;
	        HttpURLConnection urlConnection = null;
	        try{
	                URL url = new URL(strUrl);

	                // Creating an http connection to communicate with url 
	                urlConnection = (HttpURLConnection) url.openConnection();

	                // Connecting to url 
	                urlConnection.connect();

	                // Reading data from url 
	                iStream = urlConnection.getInputStream();

	                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

	                StringBuffer sb  = new StringBuffer();

	                String line = "";
	                while( ( line = br.readLine())  != null){
	                        sb.append(line);
	                }
	                
	                data = sb.toString();

	                br.close();

	        }catch(Exception e){
	                Log.d("Exception while downloading url", e.toString());
	        }finally{
	                iStream.close();
	                urlConnection.disconnect();
	        }
	        return data;
	     }

		
		
		// Fetches data from url passed
		private class DownloadTask extends AsyncTask<String, Void, String>{			
					
			// Downloading data in non-ui thread
			@Override
			protected String doInBackground(String... url) {
					
				// For storing data from web service
				String data = "";
						
				try{
					// Fetching the data from web service
					data = downloadUrl(url[0]);
				}catch(Exception e){
					Log.d("Background Task",e.toString());
				}
				return data;		
			}
			
			// Executes in UI thread, after the execution of
			// doInBackground()
			@Override
			protected void onPostExecute(String result) {			
				super.onPostExecute(result);			
			 
						// TODO Auto-generated method stub
						ParserTask parserTask = new ParserTask();
						
						// Invokes the thread for parsing the JSON data
						parserTask.execute(result);	
				 
			 
			
					
			}		
		}
		
		/** A class to parse the Google Places in JSON format */
	    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
	    	
	    	// Parsing the data in non-ui thread    	
			@Override
			protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
				
				JSONObject jObject;	
				List<List<HashMap<String, String>>> routes = null;			           
	            
	            try{
	            	jObject = new JSONObject(jsonData[0]);
	            	DirectionsJSONParser parser = new DirectionsJSONParser();
	            	
	            	// Starts parsing data
	            	routes = parser.parse(jObject);    
	            }catch(Exception e){
	            	e.printStackTrace();
	            }
	            return routes;
			}
			
			// Executes in UI thread, after the parsing process
			@Override
			protected void onPostExecute(List<List<HashMap<String, String>>> result) {
				ArrayList<LatLng> points = null;
				PolylineOptions lineOptions = null;
				MarkerOptions markerOptions = new MarkerOptions();
				
				// Traversing through all the routes
				for(int i=0;i<result.size();i++){
					points = new ArrayList<LatLng>();
					lineOptions = new PolylineOptions();
					
					// Fetching i-th route
					List<HashMap<String, String>> path = result.get(i);
					
					// Fetching all the points in i-th route
					for(int j=0;j<path.size();j++){
						HashMap<String,String> point = path.get(j);					
						
						double lat = Double.parseDouble(point.get("lat"));
						double lng = Double.parseDouble(point.get("lng"));

						LatLng position = new LatLng(lat, lng);	
						
						points.add(position);						
					}
					
					// Adding all the points in the route to LineOptions
					lineOptions.addAll(points);
					lineOptions.width(2);
					lineOptions.color(Color.RED);	
					
				}
				if (lineOptions != null)
				{
				// Drawing polyline in the Google Map for the i-th route
				googleMap.addPolyline(lineOptions);	
				}
			}			
	    } 

		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			
		}
}