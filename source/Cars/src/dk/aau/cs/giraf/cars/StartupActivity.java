package dk.aau.cs.giraf.cars;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import dk.aau.cs.giraf.oasis.lib.Helper;
import dk.aau.cs.giraf.oasis.lib.models.Profile;


public class StartupActivity extends Activity {
	private long guardianId;
	public static long childId;
	private TextView textViewG;
	private TextView textViewC;
	public static boolean appPaused = false;
	public static boolean appResumed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		Helper helper = new Helper(this);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {        	   
			try{
				guardianId = extras.getLong("currentGuardianID");
				Profile guardian = helper.profilesHelper.getProfileById(guardianId);

				textViewG = (TextView)findViewById(R.id.textView1);
				textViewG.setText("Guardian: " + guardian.getFirstname() + " " + guardian.getSurname());
			}
			catch(NullPointerException e) {

			}
			finally{
				childId = extras.getLong("currentChildID");
				Profile child = helper.profilesHelper.getProfileById(childId);
				
			    System.out.println("old childid: " + childId);
				textViewC = (TextView)findViewById(R.id.textView2);
				textViewC.setText("Barn: " + child.getFirstname() + " " + child.getSurname());
			}
		}
		
		Settings.load(childId, this);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		Helper helper = new Helper(this);
		Profile guardian = helper.profilesHelper.getProfileById(guardianId);
		if (guardian != null) {
			textViewG.setText("Guardian: " + guardian.getFirstname() + " " + guardian.getSurname());
		}
		Profile child = helper.profilesHelper.getProfileById(childId);
		if (child != null) {
			textViewC.setText("Barn: " + child.getFirstname() + " " + child.getSurname());			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.startup, menu);
		return true;
	}

	public void showGameView(View view) {
		Intent intent = new Intent(StartupActivity.this, GameActivity.class);
		StartupActivity.this.startActivityForResult(intent, 0);	
	}

	public void showSettingsView(View view) {
		Intent intent = new Intent(StartupActivity.this, SettingsActivity.class);
		intent.putExtra("currentGuardianID", guardianId);
		intent.putExtra("currentChildId", childId);
		StartupActivity.this.startActivityForResult(intent, 0);
	}

	public void showHelpView(View view) {
		Intent intent = new Intent(StartupActivity.this, HelpActivity.class);
		StartupActivity.this.startActivityForResult(intent, 0);
	}
}
