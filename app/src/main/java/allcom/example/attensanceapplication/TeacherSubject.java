package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherSubject extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    DatabaseReference databaseReference/*,attendanceRef*/;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Button logout1;
    String TeacherDiv;
    String Class;
    MyExplistAdapter1 myExplistAdapter;
    String TeacherYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_subject);
           String TeacherName = getIntent().getStringExtra("Teacher name");
            TeacherYear = getIntent().getStringExtra("Year");
        Class=getIntent().getStringExtra("Class");
        String Division=getIntent().getStringExtra("Division");
            System.out.println("helloo " + TeacherYear);
        databaseReference= FirebaseDatabase.getInstance().getReference("teachers");
        Toast.makeText(this, ""+Class, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + TeacherName + TeacherYear, Toast.LENGTH_SHORT).show();
            // get the listview
            expListView = (ExpandableListView) findViewById(R.id.lvExp);

            // preparing list data
            prepareListData();
            myExplistAdapter = new MyExplistAdapter1(this, listDataHeader, listDataChild);

            // setting list adapter
            expListView.setAdapter(myExplistAdapter);

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


            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                    Toast.makeText(
//                            getApplicationContext(),
//                            listDataHeader.get(groupPosition)
//                                    + " : "
//                                    + listDataChild.get(
//                                    listDataHeader.get(groupPosition)).get(
//                                    childPosition), Toast.LENGTH_SHORT)
//                            .show();
//                    Intent i =new Intent(getApplicationContext(),Day_date_student.class);
//                    i.putExtra("Teacher name",TeacherName);
//                    i.putExtra("Division",Division);
//                    i.putExtra("Year",TeacherYear);
//                    i.putExtra("Class",listDataHeader.get(groupPosition));
//                    i.putExtra("Subject",listDataChild.get(
//                            listDataHeader.get(groupPosition)).get(
//                            childPosition));
//                    startActivity(i);
                    String id=databaseReference.push().getKey();
                   // attendanceRef=FirebaseDatabase.getInstance().getReference("teachers").child(id);
                   // String id1=attendanceRef.push().getKey();
                    Lectures lectures=new Lectures(id,TeacherName,TeacherYear,Class,Division,listDataChild.get(
                            listDataHeader.get(groupPosition))
                            .get(childPosition));
                    databaseReference.child(id).setValue(lectures);
                    //attendanceRef.child("attendee list").setValue("");
                    Toast.makeText(getApplicationContext(), "Attendance Started", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Admin.class);
                    startActivity(intent);
                    finishAffinity();

                    return false;
                }
            });
        }

        /*
         * Preparing the list data
         */
        private void prepareListData() {
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();

            // Adding child data
            if(TeacherYear.equals("First Year")) {
                listDataHeader.add("Semester 1");

                listDataHeader.add("Semester 2");
            }
            else if(TeacherYear.equals("Second Year")) {
                listDataHeader.add("Semester 3");

                listDataHeader.add("Semester 4");
            }
            else if(TeacherYear.equals("Third Year")) {
                listDataHeader.add("Semester 5");

                listDataHeader.add("Semester 6");
            }
            else if(TeacherYear.equals("Fourth Year")) {
                listDataHeader.add("Semester 7");

                listDataHeader.add("Semester 8");
            }


            // Adding child data
            List<String> top250 = new ArrayList<String>();
            if(TeacherYear.equals("First Year")){
                top250.add("Engineering Mathematics-I");
                top250.add("Engineering Physics-I");
                top250.add("Engineering Chemistry-I");
                top250.add("Engineering Mechanics");
                top250.add("Basic Electrical Engineering");

            }else if(TeacherYear.equals("Second Year")&&Class.equals("CMPN")){
                top250.add("Engineering Mathematics-III");
                top250.add("Discrete Structures and Graph Theory");
                top250.add("Data Structure");
                top250.add("Digital Logic & Computer Architecture");
                top250.add("Computer Graphics");
            }else if(TeacherYear.equals("Third Year")){
                top250.add("D12A");
                top250.add("D12B");
                top250.add("D12C");
            }else if(TeacherYear.equals("Fourth Year")){
                top250.add("D17A");
                top250.add("D17B");
                top250.add("D17C");
            }
            List<String> nowShowing = new ArrayList<String>();
            if(TeacherYear.equals("First Year")) {
                nowShowing.add("Engineering Mathematics-II");
                nowShowing.add("Engineering Physics-II");
                nowShowing.add("Engineering Chemistry-II");
                nowShowing.add("Engineering Graphics");
                nowShowing.add("C programming");
            }
            else if(TeacherYear.equals("Second Year")&&Class.equals("CMPN")) {
                nowShowing.add("Engineering Mathematics-IV");
                nowShowing.add("Analysis of Algorithm");
                nowShowing.add("Database Management System");
                nowShowing.add("Operating System ");
                nowShowing.add("Microprocessor");
            }
            else if(TeacherYear.equals("Third Year")){
                nowShowing.add("D12A");
                nowShowing.add("D12B");
                nowShowing.add("D12C");
            }
            else if(TeacherYear.equals("Fourth Year")){
                nowShowing.add("D16A");
                nowShowing.add("D16B");
                nowShowing.add("D16C");
            }
            List<String> comingSoon = new ArrayList<String>();
            if(TeacherYear.equals("First Year")) {
                comingSoon.add("Engineering Mathematics-II");
                comingSoon.add("Engineering Physics-II");
                comingSoon.add("Engineering Chemistry-II");
                comingSoon.add("Engineering Graphics");
                comingSoon.add("C programming");
            }
            else if(TeacherYear.equals("Second Year")){
                comingSoon.add("D15A");
                comingSoon.add("D15B");
                comingSoon.add("D15C");
            }
            else if(TeacherYear.equals("Third Year")){
                comingSoon.add("D20A");
                comingSoon.add("D20B");
                comingSoon.add("D20C");
            }
            else if(TeacherYear.equals("Fourth Year")){
                comingSoon.add("D25A");
                comingSoon.add("D25B");
                comingSoon.add("D25C");
            }

            listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
            listDataChild.put(listDataHeader.get(1), nowShowing);
         //   listDataChild.put(listDataHeader.get(2), comingSoon);
        }
    }
    class MyExplistAdapter1 extends BaseExpandableListAdapter {

        private Context context;
        private List<String> listDataHeader;
        private HashMap<String, List<String>> listDataChild;
        public MyExplistAdapter1(Context context , List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
            this.context =context;
            this.listDataHeader=listDataHeader;
            this.listDataChild=listDataChild;
        }


        @Override
        public int getChildrenCount(int i) {
            return  this.listDataChild.get(this.listDataHeader.get(i))
                    .size();
        }

        @Override
        public Object getGroup(int i) {
            return this.listDataHeader.get(i);
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }


        @Override
        public int getGroupCount() {
            return this.listDataHeader.size();
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
            String headerTitle = (String) getGroup(groupPosition);

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_row_group, null);

            TextView textViewGroup = convertView
                    .findViewById(R.id.textViewGroup);
            textViewGroup.setTypeface(null, Typeface.BOLD);
            textViewGroup.setText(headerTitle);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
            final String childText = (String) getChild(groupPosition, childPosition);

            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_child, null);
            TextView textViewChild = convertView
                    .findViewById(R.id.textViewChild);

            textViewChild.setText(childText);

            return convertView;
        }
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }