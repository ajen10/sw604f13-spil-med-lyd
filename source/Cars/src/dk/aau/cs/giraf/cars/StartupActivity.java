package dk.aau.cs.giraf.cars;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.*;

public class StartupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    	StartupActivity.this.startActivity(intent);
    }
    
    public void showHelpView(View view) {
    	Intent intent = new Intent(StartupActivity.this, HelpActivity.class);
    	StartupActivity.this.startActivity(intent);
    }
    
}
