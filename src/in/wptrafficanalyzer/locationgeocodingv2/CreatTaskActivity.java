package in.wptrafficanalyzer.locationgeocodingv2;

 

 

 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
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

public class CreatTaskActivity extends FragmentActivity implements OnTimeChangedListener {
	
	GoogleMap googleMap;
	final MarkerOptions markerOptions    = new MarkerOptions();
    
	final MarkerOptions markerOptions2 = new MarkerOptions(); 
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
		CalendarView view;
	public static ArrayList<String> dictionary = new ArrayList();
	public  static ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
	ArrayList<XMLGettersSettersClass> mdataent = new ArrayList<XMLGettersSettersClass>();
	XMLGettersSettersClass object= new XMLGettersSettersClass();
	ArrayList<LatLng> markerPoints = new ArrayList<LatLng>();

	 TimePickerDialog.OnTimeSetListener timePickerListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creattask);
	 

			/**
			 * Create a new instance of the SAX parser
			 **/
		 
	
	    	
	 
		 // timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
	        etLocation = (EditText) findViewById(R.id.et_location);
	        Taskspecification=(EditText) findViewById(R.id.et_location2);
			  view = (CalendarView)findViewById(R.id.calendarView1);

	
		// Getting a reference to the map
		 
      
   /*     timePicker1.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
		      
		        Toast.makeText(getApplicationContext(),  view.getCurrentHour()+ "/"+  view.getCurrentMinute(),4000).show();
		        Time=String.valueOf(view.getCurrentHour())+ ":"+  String.valueOf(view.getCurrentMinute());
		        object.setendtime(Time);
		        object.setstarttime(Time);
			}
		});
*/
		// Getting reference to btn_find of the layout activity_main
      
        Button creattask=(Button)findViewById(R.id.button1);
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
     
        creattask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 
		       dictionary.add("Location: "+Location+ " Time: "+Time+" Date: "+Date);
				Intent intent = new Intent(getBaseContext(), CustomListViewAndroidExample.class);
				intent.putExtra("Location", Location);
				intent.putExtra("Time", Time);
				intent.putExtra("Taskspecification", Taskspecification.getText().toString());
				intent.putExtra("Date", Date);
				object.setlocation(etLocation.getText().toString());
		    	object.settype("Entry");

		    	mdataent.set(0, object) ;
		        for(int i=0;i<mdataent.size();i++){
		   		 if(mdataent.get(i).gettype().equals("entry")){
		   				Log.d("entry", mdataent.get(i).gettype());
		   			    Toast.makeText(getApplicationContext(),"entry:"+mdataent.get(i).getlocation(),4000).show();		


		   			}
		   		}
				startActivity(intent);
				
			}
		});
        // Defining button click event listener for the find button
        	
 
		view.setOnDateChangeListener(new OnDateChangeListener() {

		    @Override
		    public void onSelectedDayChange(CalendarView arg0, int year, int month,
		        int date) {
		        Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year,4000).show();
		        Date=String.valueOf(date)+"/"+String.valueOf(month)+"/"+String.valueOf(year);
		        object.setdate(Date);
		    }
		});
	

	
		
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	// An AsyncTask class for accessing the GeoCoding Web Service
	
		
		/** A method to download json data from url */

		
		
		// Fetches data from url passed
		
		/** A class to parse the Google Places in JSON format */

		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			
		}
}
