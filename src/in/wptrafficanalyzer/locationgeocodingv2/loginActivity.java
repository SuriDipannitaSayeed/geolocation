package in.wptrafficanalyzer.locationgeocodingv2;

 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class loginActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.login);
	Button login=(Button) findViewById(R.id.button1);
	login.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getBaseContext(), MainActivity.class);
			startActivity(intent);
			
		}
	});
	
}
}
