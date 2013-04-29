package dk.aau.cs.giraf.cars;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

	public void showMicHelp(View v) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Mikrofon");
		alertDialogBuilder.setMessage("For at indstille mikrofonen skal du ude på startskærmen trykke på indstillinger og derefter på knappen Start til højre for teksten Mikrofon Indstillinger. Når knappen trykkes bliver din stemme testet ved at optage dig snakke med en dyb stemme og derefter en høj stemme. Til sidst kan du se hvordan din stemme nu påvirker figuren der flytter sig op og ned når du snakker. Hvis resultatet ikke er tilfredsstillende kan testen tages igen, før du begynder at spille.");

		alertDialogBuilder.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// Dismiss dialog
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	public void showSettingsHelp(View v) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Indstillinger");
		alertDialogBuilder.setMessage("For at ændre indstillinger i spillet skal du ude på startskærmen trykke på Indstillinger hvorefter et nyt vindue vil vise den nuværendes profils indstillinger. I højre side kan du ændre dine ønskede indstillinger og derefter tryk på knappen Gem. I venstre side er der en liste over af de profile den nuværende bruger kan tilgå. Hvis der trykkes på et navn vises den profils indstillinger.");

		alertDialogBuilder.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// Dismiss dialog
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}


	public void showGameHelp(View v) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Spil Hjælp");
		alertDialogBuilder.setMessage("Spillet går ud på at styre bilen ind i garagerne der er placeret i højre side af skærmen. Der er forhindringer på kørebanen som skal undgåes. Hvis et objekt bliver påkørt er bilen ødelagt og du skal starte forfra. Du kan styre bilen ned ved at lave en dyb lyd og ligeledes op ved at lave en høj lyd. Hvis alle biler køres ind i deres garage har du vundet spillet");

		alertDialogBuilder.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// Dismiss dialog
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
