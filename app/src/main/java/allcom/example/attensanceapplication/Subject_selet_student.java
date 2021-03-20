package allcom.example.attensanceapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subject_selet_student extends Activity {

    allcom.example.attensanceapplication.ExpandableListAdapter listAdapter;
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
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 1){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 2){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 3){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}                }
            if(groupPosition==1){
                if(childPosition == 0){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 1){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 2){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
            }
            if(groupPosition==2){
                if(childPosition == 0){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 2){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
                    startActivity(child0Intent);}
                if(childPosition == 3){
                    Intent child0Intent = new Intent(getBaseContext(), Teacher_list.class);
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
        listDataHeader.add("D7A");
        listDataHeader.add("D7B");
        listDataHeader.add("D7C");
//        listDataHeader.add("D8C");
//        listDataHeader.add("D9C");
//        listDataHeader.add("D10C");
//        listDataHeader.add("D11C");
//        listDataHeader.add("D12C");
//        listDataHeader.add("D13C");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("MATHS");
        top250.add("OS");
        top250.add("AOA");
        top250.add("DLCOA");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("DBMS");
        nowShowing.add("CP");
        nowShowing.add("MP");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("MATHS4");
        comingSoon.add("PYTHON");
        comingSoon.add("JAVA");
//
//        List<String> class = new ArrayList<String>();
//        top250.add("MATHS");
//        top250.add("OS");
//        top250.add("AOA");
//        top250.add("DLCOA");
//
//        List<String> top250 = new ArrayList<String>();
//        top250.add("MATHS");
//        top250.add("OS");
//        top250.add("AOA");
//        top250.add("DLCOA");
//
//        List<String> top250 = new ArrayList<String>();
//        top250.add("MATHS");
//        top250.add("OS");
//        top250.add("AOA");
//        top250.add("DLCOA");
//
//        List<String> top250 = new ArrayList<String>();
//        top250.add("MATHS");
//        top250.add("OS");
//        top250.add("AOA");
//        top250.add("DLCOA");
//
//        List<String> top250 = new ArrayList<String>();
//        top250.add("MATHS");
//        top250.add("OS");
//        top250.add("AOA");
//
//        top250.add("DLCOA"); List<String> top250 = new ArrayList<String>();
//        top250.add("MATHS");
//        top250.add("OS");
//        top250.add("AOA");
//        top250.add("DLCOA");
//

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}









