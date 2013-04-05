package dk.aau.cs.giraf.cars;


import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import dk.aau.cs.giraf.oasis.lib.controllers.ProfilesHelper;
import dk.aau.cs.giraf.oasis.lib.models.Profile;


public class Settings1Fragment extends ListFragment {
	ArrayAdapter<String> adapter;
		
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fillData();
	}

	public void fillData() {
		
		ProfilesHelper helper = new ProfilesHelper(getActivity());
		List<Profile> tmp = new ArrayList<Profile>();
		tmp.addAll(helper.getProfiles());
		System.out.println(tmp.size()); //Test
		
		//Cant use profile as adapter type
		String[] nameList = new String[tmp.size()];
		int i = 0;
		
		for (Profile p : tmp) {
			nameList[i] = p.getFirstname();
			i++;
		}
		
//		Profile Daniel = new Profile();
//		String firstname = "testestest";
//		Daniel.setFirstname(firstname);	
//				
//		//Load names from DB
//		List<String> test = new ArrayList<String>();
//		String tmp1 = "Daniel";
//		String tmp2 = "Boecker";
//		String tmp3 = "Soerensen";
//		test.add(tmp1);
//		test.add(tmp2);
//		test.add(tmp3);
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameList);						
		setListAdapter(adapter);
	}
}
