package in.wptrafficanalyzer.locationgeocodingv2;

 
 

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends Activity {
    EditText uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button login=(Button) findViewById(R.id.button1);
          uname= (EditText)findViewById(R.id.stime);
          pass= (EditText)findViewById(R.id.pass);
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(uname.getText().toString().equals("admin")){
                    Intent intent = new Intent(getBaseContext(), in.wptrafficanalyzer.locationgeocodingv2.AdminActivity.class);
                    startActivity(intent);
                }

    else
                {
    	 Intent intent = new Intent(loginActivity.this, in.wptrafficanalyzer.locationgeocodingv2.MainActivity.class);
    	 intent.putExtra("Username", uname.getText().toString());
         startActivity(intent);

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        uname.setText(" ");

    }
}