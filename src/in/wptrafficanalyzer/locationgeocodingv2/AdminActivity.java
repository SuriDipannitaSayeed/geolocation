package in.wptrafficanalyzer.locationgeocodingv2;

 

//great for async tasks in android!
//FROM http://stackoverflow.com/questions/7860538/android-http-post-asynctask
/*
HashMap<String, String> data = new HashMap<String, String>();
data.put("key1", "value1");
data.put("key2", "value2");
AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
asyncHttpPost.execute("http://example.com");
*/

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

 

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* constructor
*/
public class AdminActivity extends FragmentActivity implements View.OnClickListener {
  public static TextView start, end;
  EditText title, location, asignee,id;
  Button creattask,updatetask,logout;
  String update,creat,Location;
  String date, time;
  int count = 0;
  HttpPost httppost;
  LinearLayout lp;
  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */
 

  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */


  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.task1);
      Bundle extras = getIntent().getExtras();
		if(extras != null) { 
			Location=extras.getString("Location");
		Log.d("Location", Location);
		}
      id = (EditText) findViewById(R.id.id);

      start = (TextView) findViewById(R.id.start);
      end = (TextView) findViewById(R.id.end);
      title = (EditText) findViewById(R.id.title);
      location = (EditText) findViewById(R.id.location);
      asignee = (EditText) findViewById(R.id.asignee);
      creattask = (Button) findViewById(R.id.task);
      updatetask=(Button)findViewById(R.id.update);
      logout=(Button)findViewById(R.id.logout);
      location.setOnTouchListener(new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
	 
			 Intent intent = new Intent(AdminActivity.this, in.wptrafficanalyzer.locationgeocodingv2.DialogActivity.class);
	    	 
	         startActivity(intent);
			return false;
		}
	});
      location.setText(Location);
      start.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
     showEditDialog(1);

          return false;
      }
  });
      end.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              showEditDialog(2);

              return false;
          }
      });
      creattask.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              count++;
              creat="creat";
              new MyAsyncTask().execute(creat,id.getText().toString(), start.getText().toString(), end.getText().toString(), title.getText().toString(), location.getText().toString(), asignee.getText().toString().replaceAll("\\s+",""));
          }
      });
      updatetask.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              count++;
              creat="update";
              new MyAsyncTask().execute(creat,id.getText().toString(), start.getText().toString(), end.getText().toString(), title.getText().toString(), location.getText().toString(), asignee.getText().toString().replaceAll("\\s+",""));
          }
      });
      logout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
                finish();
            }
      });
      // ATTENTION: This was auto-generated to implement the App Indexing API.
      // See https://g.co/AppIndexing/AndroidStudio for more information.
      
  }

  @Override
  protected void onResume() {
      super.onResume();

  }

  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */
  private void showEditDialog(int a) {
      Bundle args = new Bundle();
      args.putInt("position",a);

      FragmentManager fm = getSupportFragmentManager();
      EditNameDialogFragment editNameDialogFragment = EditNameDialogFragment.newInstance("Some Title");
      editNameDialogFragment.setArguments(args);
      editNameDialogFragment.show(fm,"");
  }


  @Override
  public void onClick(View v) {

  }

  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */
 

  /**
   * ATTENTION: This was auto-generated to implement the App Indexing API.
   * See https://g.co/AppIndexing/AndroidStudio for more information.
   */


  private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

      @Override
      protected Double doInBackground(String... params) {
          // TODO Auto-generated method stub
          postData(params);
          return null;
      }

      protected void onPostExecute(Double result) {
          Toast.makeText(getApplicationContext(), "command sent",
                  Toast.LENGTH_LONG).show();
      }

      protected void onProgressUpdate(Integer... progress) {
      }

      public void postData(String[] valueIWantToSend) {
          // Create a new HttpClient and Post Header


          try {
              // Add your data

              List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
              Log.d("Respons", valueIWantToSend[0]);
              Log.d("Respons", valueIWantToSend[1]);
              Log.d("Respons", valueIWantToSend[2]);
              Log.d("Respons", valueIWantToSend[3]);
              Log.d("Respons", valueIWantToSend[4]);

              nameValuePairs.add(new BasicNameValuePair("id", valueIWantToSend[1]));
              nameValuePairs.add(new BasicNameValuePair("Start", valueIWantToSend[2]));
              nameValuePairs.add(new BasicNameValuePair("End", valueIWantToSend[3]));
              nameValuePairs.add(new BasicNameValuePair("Title", valueIWantToSend[4]));
              nameValuePairs.add(new BasicNameValuePair("Location", valueIWantToSend[5]));
              nameValuePairs.add(new BasicNameValuePair("asignee", valueIWantToSend[6]));
              DefaultHttpClient httpclient = new DefaultHttpClient();
              if(creat.equals("creat")){    httppost = new HttpPost(
                      "http://172.20.62.23/insert.php");}
            else if(creat.equals("update")){    httppost = new HttpPost(
                      "http://172.20.62.23/update.php");}{}
              httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));

              // Execute HTTP Post Request
              HttpResponse response = httpclient.execute(httppost);

              HttpEntity entity = response.getEntity();
              Log.d("Respons", response.toString());

          } catch (ClientProtocolException e) {
              // TODO Auto-generated catch block
          } catch (IOException e) {
              // TODO Auto-generated catch block
          }
      }

  }
}