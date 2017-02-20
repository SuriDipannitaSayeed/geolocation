 
package in.wptrafficanalyzer.locationgeocodingv2;
  
import java.util.ArrayList;  
import java.util.Arrays;  
  











import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;  
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;  
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
	public static JSONArray ja,ja1;
	public static ListView mainListView ;  
  public static  ArrayAdapter<String> listAdapter ;  
  ArrayList<String> task=new  ArrayList<String>();
  ArrayList<String> tasklist=new  ArrayList<String>();
    int position=0;
    JSONObject jo;
    int[] distance ;
   
   String status = null;
   String id=null;
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
    distance=i.getExtras().getIntArray ("distance");
  //  Arrays.sort(distance);
   
    for (int l=0;l<distance.length;l++)
	{	 
		Log.d(" "," "+distance[l]);
	}
    id=i.getExtras().getString("id");
    status=i.getExtras().getString("Status");
		Toast.makeText(getBaseContext(), status, Toast.LENGTH_SHORT).show();
    try {
		  ja= new JSONArray(data);
		 
		  
		  
		//Toast.makeText(getBaseContext(), ja.toString(), Toast.LENGTH_SHORT).show();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
/*	Bundle extras=getIntent().getExtras();
	if(extras!=null)
	{
		String status=extras.getString("Status");
		Log.d("Status", status);
		
	}*/
  
    // Create and populate a List of planet names.  
    
      
    // Create ArrayAdapter using the planet list.  
    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow)
    {@Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	// TODO Auto-generated method stub
    	View view= super.getView(position, convertView, parent);
       

        // Set the text color of TextView (ListView Item)
        if(status!=null&&status.equals("Done")&&(position==(Integer.parseInt(id)-1))){
        MainActivity.tv = (TextView) view.findViewById( R.id.rowTextView);
        	 int img = R.drawable.right;

        	 MainActivity.tv.setCompoundDrawablesWithIntrinsicBounds(0, 0,img, 0);
        }
        if(status!=null&&status.equals("Cancel")&&(position==(Integer.parseInt(id)-1))){
        	 MainActivity.tv= (TextView) view.findViewById( R.id.rowTextView);
       	 int img = R.drawable.wrong;

       	 MainActivity.tv.setCompoundDrawablesWithIntrinsicBounds(0, 0,img, 0);
       }
    	return view;
    }};
 
/* if(status!=null)
 {
	
	 int img = R.drawable.right;

	tv.setCompoundDrawablesWithIntrinsicBounds(0, 0,img, 0);
 }*/
    // Add more planets. If you passed a String[] instead of a List<String>   
    // into the ArrayAdapter constructor, you must not add more items.   
    // Otherwise an exception will occur.  
    int n= ja.length();
    int k=0;
    for (int j=0;j<n;j++){
    	try {
			jo=ja.getJSONObject(j);
			if(jo.getString("asignee").equals(MainActivity.username))
			{ listAdapter.add( "\t\t\tTask"+(k+1) ); k++ ;
				task.add( String.valueOf(j));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	
    }
   
      
    // Set the ArrayAdapter as the ListView's adapter.  
    mainListView.setAdapter( listAdapter );    
    mainListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			position=arg2;
			int tid=(distance[arg2]);
			// TODO Auto-generated method stub
			try {
				  jo= ja.getJSONObject(tid);
				Intent i=new Intent(TaskActivity.this, Task.class);
				i.putExtra("data", ja.toString());
				i.putExtra("Task id", ""+(arg2+1));
				i.putExtra("Start Time",jo.getString("Start"));
				i.putExtra("End Time",jo.getString("End"));
				i.putExtra("Title", jo.getString("Title"));
				i.putExtra("Location", jo.getString("Location"));
				startActivity(i);
				finish();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});
 
  }  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  	// TODO Auto-generated method stub
  	super.onActivityResult(requestCode, resultCode, data);
  	   if(requestCode==2)  
         {  
  	Bundle extras=data.getExtras();
  	if(extras!=null){
  		status=extras.getString("Status");
  	}
  	}
  	
  }
}