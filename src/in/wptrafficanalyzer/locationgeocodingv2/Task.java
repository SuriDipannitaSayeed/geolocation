package in.wptrafficanalyzer.locationgeocodingv2;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import in.wptrafficanalyzer.locationgeocodingv2.TaskActivity;

public class Task extends Activity implements OnClickListener {
	   TextView tx;
	    String id;
	    String Start;
	    String End;
	    String Title;
	    String Location;
	    JSONArray ja;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.specific);  
	    Intent i= getIntent();
	      id=i.getExtras().getString("Task id");
	      Start=i.getExtras().getString("Start Time");
	      End=i.getExtras().getString("End Time");
	      Title=i.getExtras().getString("Title");
	      Location=i.getExtras().getString("Location");


	      String data=i.getExtras().getString("data");
	      try {
	    	  ja= new JSONArray(data);
	  	} catch (JSONException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  	}
	    Toast.makeText(getBaseContext(), id, Toast.LENGTH_SHORT).show();
	    tx=(TextView) findViewById(R.id.textView1);
	    tx.setText("Task Id: "+id+"\n"+"Start Time: "+Start+"\n"+"End Time: "+End+"\n"+"Title: "+Title+"\n"+"Location: "+Location);
	    Button b1=(Button) findViewById(R.id.button1);
	    Button b2=(Button) findViewById(R.id.button2);
	    b1.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
/* 	Intent newintent=new Intent(Task.this,TaskActivity.class);
 	newintent.putExtra("data", ja.toString());
 	newintent.putExtra("Status", "Done");
 	newintent.putExtra("id", id);
	startActivityForResult(newintent, 1);*/	
	finish();
			}
		});
	    
	    b2.setOnClickListener(new OnClickListener() {
			
				@SuppressLint("NewApi")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
				/*	Intent newintent=new Intent(Task.this,TaskActivity.class);
				 	newintent.putExtra("data", ja.toString());
				 	newintent.putExtra("Status", "Cancel");
					newintent.putExtra("id", id);
				 	startActivityForResult(newintent, 2);*/
					finish();
				}
			});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
