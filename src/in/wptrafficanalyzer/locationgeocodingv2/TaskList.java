package in.wptrafficanalyzer.locationgeocodingv2;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import in.wptrafficanalyzer.locationgeocodingv2.MainActivity;
public class TaskList extends Activity {
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task);
		
	
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String Location = extras.getString("Location");
		    String Time = extras.getString("Time");
		    String Date = extras.getString("Date");
		    //The key argument here must match that used in the other activity
	        Toast.makeText(getApplicationContext(),"Location:"+Location+ "Time:"+Time+"Date:"+Date,4000).show();
	        MainActivity.dictionary.add("Location: "+Location+ " Time: "+Time+" Date: "+Date);
	        

		}
		ListView lv = (ListView) findViewById(R.id.listView1);

      
 
         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                 this, 
                 android.R.layout.simple_list_item_1,
                 MainActivity.dictionary );

         lv.setAdapter(arrayAdapter);
	}

}
