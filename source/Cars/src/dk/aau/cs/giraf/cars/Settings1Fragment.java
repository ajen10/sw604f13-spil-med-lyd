package dk.aau.cs.giraf.cars;


import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class Settings1Fragment extends ListFragment {
	ArrayAdapter<String> adapter;
		
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fillData();
	}

	public void fillData() {
		
		//Load names from DB
		String[] test = {"hej", "dit", "lille", "pis"};
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, test);						
		setListAdapter(adapter);
	}
}
