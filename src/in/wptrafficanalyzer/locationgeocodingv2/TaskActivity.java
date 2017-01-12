 
package in.wptrafficanalyzer.locationgeocodingv2;
  
import java.util.ArrayList;  
import java.util.Arrays;  
  











import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;  
import android.content.Intent;
import android.os.Bundle;  
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;  
import android.widget.Button;
import android.widget.ListView;  
import android.widget.TextView;
import android.widget.Toast;
  
public class TaskActivity extends Activity {  
	public static JSONArray ja;
	public static ListView mainListView ;  
  public static  ArrayAdapter<String> listAdapter ;  
    int position=0;
  /** Called when the activity is first created. */  
  @Override  
  public void onCreate(Bundle savedInstanceState) {  
    super.onCreate(savedInstanceState);  
    setContentView(R.layout.list);  
      
    // Find the ListView resource.   
    mainListView = (ListView) findViewById( R.id.mainListView );  
    Button b1=(Button) findViewById(R.id.button1);
 
    Intent i= getIntent();
    String data=i.getExtras().getString("data");
    try {
		  ja= new JSONArray(data);
		Toast.makeText(getBaseContext(), ja.toString(), Toast.LENGTH_SHORT).show();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  
    // Create and populate a List of planet names.  
    String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",  
                                      "Jupiter", "Saturn", "Uranus", "Neptune"};    
    ArrayList<String> planetList = new ArrayList<String>();  
    planetList.addAll( Arrays.asList(planets) );  
      
    // Create ArrayAdapter using the planet list.  
    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow);  
      
    // Add more planets. If you passed a String[] instead of a List<String>   
    // into the ArrayAdapter constructor, you must not add more items.   
    // Otherwise an exception will occur.  
    int n= ja.length();
    for (int j=0;j<n;j++){
         listAdapter.add( "\t\t\tTask"+(j+1) );  
	
    }
   
      
    // Set the ArrayAdapter as the ListView's adapter.  
    mainListView.setAdapter( listAdapter );    
    mainListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			position=arg2;
			// TODO Auto-generated method stub
			try {
				JSONObject jo= ja.getJSONObject(arg2);
				Intent i=new Intent(TaskActivity.this, Task.class);
				i.putExtra("Task id", ""+(arg2+1));
				i.putExtra("Start Time",jo.getString("Start"));
				i.putExtra("End Time",jo.getString("End"));
				i.putExtra("Title", jo.getString("Title"));
				i.putExtra("Location", jo.getString("Location"));
				startActivity(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
 
  }  
}