package dk.aau.cs.giraf.cars;


import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import dk.aau.cs.giraf.oasis.lib.controllers.ProfilesHelper;
import dk.aau.cs.giraf.oasis.lib.models.Profile;


public class Settings1Fragment extends ListFragment {
	private ArrayAdapter<String> adapter;
	private Profile guardian;
	private View previousColoredItem = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fillData(getActivity().getIntent().getExtras().getLong("currentGuardianID"));
	}

	public void fillData(long currentGuardianID) {
		ProfilesHelper helper = new ProfilesHelper(getActivity());
		guardian = helper.getProfileById(currentGuardianID);
		List<Profile> tmp = new ArrayList<Profile>();
		tmp.addAll(helper.getChildrenByGuardian(guardian));

		//Cant use profile as adapter type
		String[] nameList = new String[tmp.size()];
		int i = 0;

		for (Profile p : tmp) {
			nameList[i] = p.getFirstname();
			i++;
		}

		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameList);						
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if(previousColoredItem != null)
			previousColoredItem.setBackgroundColor(Color.BLACK);
		v.setBackgroundColor(Color.BLUE);
		previousColoredItem = v;
	}
}



