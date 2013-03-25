package dk.aau.cs.giraf.cars;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;


public class Settings1Fragment extends Fragment {
	private static final String NAME = "NAME";
	private static final String IS_EVEN = "IS_EVEN";
	private ExpandableListAdapter mAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_settings1, container, false);
		return v;
		
		
   }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fillData();

	}
		    
   public void fillData() {
	   
	   List<Map<String,String>> group = new ArrayList<Map<String, String>>();
	   List<List<Map<String,String>>> child = new ArrayList<List<Map<String, String>>>();
	   
	   ExpandableListView listView = (ExpandableListView) getActivity().findViewById(R.id.expandableListView1);
	   
	   for (int i = 0; i < 20; i++) {
           Map<String, String> curGroupMap = new HashMap<String, String>();
           group.add(curGroupMap);
           curGroupMap.put(NAME, "Item " + i);
           curGroupMap.put(IS_EVEN, (i % 2 == 0) ? "This group is even" : "This group is odd");
            
	   List<Map<String, String>> children = new ArrayList<Map<String, String>>();
       for (int j = 0; j < 5; j++) {
           Map<String, String> curChildMap = new HashMap<String, String>();
           children.add(curChildMap);
          // curChildMap.put(NAME, "Child " + j);
           curChildMap.put(IS_EVEN, (j % 2 == 0) ? "Hello " + j: "Good Morning "+ j);
       }
       child.add(children);
	   }
	   
	   mAdapter = new SimpleExpandableListAdapter(
			   getActivity(),
			   group,
			   android.R.layout.simple_expandable_list_item_1,
			   new String[] { NAME, IS_EVEN },
			   new int[] { android.R.id.text1, android.R.id.text2 },
               child,
               android.R.layout.simple_expandable_list_item_2,
               new String[] { NAME, IS_EVEN },
               new int[] { android.R.id.text1, android.R.id.text2 }
               ); 
	   
	   listView.setAdapter(mAdapter);
	   
   }
   
   
	
}
