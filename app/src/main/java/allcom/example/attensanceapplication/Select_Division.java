package allcom.example.attensanceapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Select_Division extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__division);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener


//        expListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
//            if(groupPosition==0){
//                if(childPosition == 0){
//                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
//                    startActivity(child0Intent);}
//                if(childPosition == 1){
//                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
//                    startActivity(child0Intent);}
//                if(childPosition == 2){
//                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
//                    startActivity(child0Intent);}
//            }
//            if(groupPosition==1){
//                if(childPosition == 3){
//                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
//                    startActivity(child0Intent);}
//                if(childPosition == 4){
//                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
//                    startActivity(child0Intent);}
//                if(childPosition == 5){
//                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
//                    startActivity(child0Intent);}
//            }
//            return false;
//        });

        expListView.setOnGroupClickListener((parent, v, groupPosition, id) -> false);

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(groupPosition -> Toast.makeText(getApplicationContext(),
                listDataHeader.get(groupPosition) + " Expanded",
                Toast.LENGTH_SHORT).show());

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(groupPosition -> Toast.makeText(getApplicationContext(),
                listDataHeader.get(groupPosition) + " Collapsed",
                Toast.LENGTH_SHORT).show());


        expListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            if(groupPosition==0){
                if(childPosition == 0){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 1){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 2){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 3){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}                }
            if(groupPosition==1){
                if(childPosition == 0){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 1){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 2){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
            }
            if(groupPosition==2){
                if(childPosition == 0){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 2){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 3){
                    Intent child0Intent = new Intent(getBaseContext(), Present_absent_list.class);
                    startActivity(child0Intent);}
            }            return false;
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("CMPN1");
        listDataHeader.add("cmpn2");
        listDataHeader.add("IT");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("D7a");
        top250.add("d7b");
        top250.add("D2A");
        top250.add("D2B");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("D5A");
        nowShowing.add("D5B");
        nowShowing.add("D6A");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("D10A");
        comingSoon.add("D10B");
        comingSoon.add("D10C");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}















