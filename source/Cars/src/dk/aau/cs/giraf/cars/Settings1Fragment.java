package dk.aau.cs.giraf.cars;

import java.util.ArrayList;
import java.util.List;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import dk.aau.cs.giraf.oasis.lib.controllers.ProfilesHelper;
import dk.aau.cs.giraf.oasis.lib.models.App;
import dk.aau.cs.giraf.oasis.lib.models.Profile;
import dk.aau.cs.giraf.oasis.lib.models.Setting;
import dk.aau.cs.giraf.oasis.lib.controllers.AppsHelper;

public class Settings1Fragment extends ListFragment {
	private ProfileAdapter adapter;
	private Profile guardian;
	private View previousColoredItem = null;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fillData(getActivity().getIntent().getExtras().getLong("currentGuardianID"));
		this.getListView().setDivider(null);
		this.getListView().setDividerHeight(0);
		this.getListView().setPadding(0, 0, 125, 0);
	}

	public void fillData(long currentGuardianID) {
		ProfilesHelper helper = new ProfilesHelper(getActivity());
		guardian = helper.getProfileById(currentGuardianID);
		List<Profile> nameList = new ArrayList<Profile>();
		nameList.addAll(helper.getChildrenByGuardian(guardian));
		adapter = new ProfileAdapter(getActivity(), nameList);	
		setListAdapter(adapter);
	}

	public String getSettingsById(long Id) {
		long appId;
		Setting<String, String, String> tmp;
		String profileCarSettings = null;

		App app = new App();
		appId = app.getId();
		AppsHelper appshelper = new AppsHelper(getActivity());
		tmp = appshelper.getSettingByIds(appId, Id);

		profileCarSettings = Setting.toStringSetting(tmp);
		
		System.out.println(profileCarSettings);
		
		return profileCarSettings;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if(previousColoredItem != null)
			previousColoredItem.setBackgroundColor(Color.BLACK);
		v.setBackgroundColor(Color.MAGENTA);
		previousColoredItem = v;
		getSettingsById(((Profile) l.getAdapter().getItem(position)).getId());
	}
}



