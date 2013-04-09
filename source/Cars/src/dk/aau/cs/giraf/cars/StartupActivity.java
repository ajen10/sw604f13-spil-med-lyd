package dk.aau.cs.giraf.cars;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class StartupActivity extends Activity {
//	Bundle temp;
//	private long currentGuardian;
//	private Context mContext;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        temp = savedInstanceState;
//        
//        
//        currentGuardian = getIntent().getExtras().getLong("currentGuardian");
//        Helper helper = new Helper(mContext);
//        Profile guardianProfile = helper.profilesHelper.getProfileById(currentGuardian);
//    
//        TextView textView = (TextView) findViewById(R.id.textView1);
//        textView.setText("Daniel");

        setContentView(R.layout.activity_startup);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.startup, menu);
        return true;
    }
    
    public void showGameView(View view) {
    	Intent intent = new Intent(StartupActivity.this, GameActivity.class);
    	StartupActivity.this.startActivity(intent);
    }
    
    public void showSettingsView(View view) {
    	Intent intent = new Intent(StartupActivity.this, SettingsActivity.class);
//    	intent.putExtra("currentGuardianID", temp.getLong("currentGuardianID"));
    	StartupActivity.this.startActivity(intent);
    }
    
    public void showHelpView(View view) {
    	Intent intent = new Intent(StartupActivity.this, HelpActivity.class);
    	StartupActivity.this.startActivity(intent);
    }
    
}
