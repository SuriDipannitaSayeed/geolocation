package in.wptrafficanalyzer.locationgeocodingv2;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import in.wptrafficanalyzer.locationgeocodingv2.MainActivity;

public class CustomListViewAndroidExample extends Activity {

	ListView list;
	CustomAdapter adapter;
	  String Location;
	    String Time ;
	    String Date ;
	    String Taskspecification;
	public  CustomListViewAndroidExample CustomListView = null;
	public static ListModel sched = new ListModel();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_list_view_android_example);

		
		CustomListView = this;
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter(CustomListView, MainActivity.CustomListViewValuesArr,res);
        list.setAdapter(adapter);
		
	}

	/****** Function to set data in ArrayList *************/
    public void setListData()
    {Bundle extras = getIntent().getExtras();
	if (extras != null) {
	      Location = extras.getString("Location");
	      Time = extras.getString("Time");
	      Date = extras.getString("Date");
	     
	      Taskspecification=extras.getString("Taskspecification");
	    //The key argument here must match that used in the other activity
        Toast.makeText(getApplicationContext(),"Location:"+Location+ "Time:"+Time+"Date:"+Date,4000).show();
        MainActivity.dictionary.add("Location: "+Location+ " Time: "+Time+" Date: "+Date);
        

	}
    
			
			
			    int taskno=MainActivity.CustomListViewValuesArr.size()+1;
			  /******* Firstly take data in model object ******/
			   sched.setLocation(Location);
			   sched.setTime(Time);
			   sched.setDate(Date);
			   sched.setTaskno("Task"+taskno);
			   sched.setTaskspecification(Taskspecification);
			/******** Take Model Object in ArrayList **********/
			   
				   MainActivity.CustomListViewValuesArr.add(sched);
			  

	 	}
		
    
    
    public void onItemClick(int mPosition)
    {
    	ListModel tempValues = (ListModel) MainActivity.CustomListViewValuesArr.get(mPosition);
    	Intent intent = new Intent(getBaseContext(), MainActivity.class);
		intent.putExtra("Location", tempValues.getLocation());
		intent.putExtra("Time", tempValues.getTime());
		intent.putExtra("Taskspecification", tempValues.getTaskspecification());
		intent.putExtra("Date", tempValues.getDate());
		intent.putExtra("Taskno", mPosition);
		startActivity(intent);
    	
     
    	 
    }
   

}
