package allcom.example.attensanceapplication;


import android.app.Activity;
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
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.os.Build.ID;

public class Select_Division extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> top250 = new ArrayList<String>();
    List<String> nowShowing = new ArrayList<String>();
    List<String> comingSoon = new ArrayList<String>();
    List<String> classes=new ArrayList<>();
    HashMap<String, List<String>> listDataChild;
    Button logout1;
    Docs docs;
    int count=1;
    String id,cla,div;
    String TeacherDiv;
    MyExplistAdapter myExplistAdapter;
    String TeacherYear;
    String TeacherName;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference ref=db.collection("Class");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__division);
        TeacherName = getIntent().getStringExtra("Teacher name");
        TeacherYear = getIntent().getStringExtra("Teacher Year");

        Toast.makeText(this, "" + TeacherName + TeacherYear, Toast.LENGTH_SHORT).show();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();


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

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                        Intent i =new Intent(getApplicationContext(),TeacherSubject.class);
                        i.putExtra("Year",TeacherYear);
                        i.putExtra("Class",listDataHeader.get(groupPosition));
                        i.putExtra("Teacher name", TeacherName);
                        i.putExtra("Division",listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition));
                        startActivity(i);
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        List<List<String>> branch = Collections.singletonList(new ArrayList<String>());
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        String year=null;
        if(TeacherYear.equals("First Year")){
            year="1st year";
        }
        else if(TeacherYear.equals("Second Year")){
            year="2nd year";
        }
        else if(TeacherYear.equals("Third Year")){
            year="3rd year";
        }
        else if(TeacherYear.equals("Fourth Year")){
            year="4th year";
        }
        // Adding child data
       Task task1=ref.whereEqualTo("year",year)
                .orderBy("priority", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                          @Override
                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                              List<DocumentSnapshot> snapshotslist = queryDocumentSnapshots.getDocuments();
                                              String datadocs = "";

                                              for (DocumentSnapshot snapshot : snapshotslist) {
                                                  listDataHeader.add(snapshot.get("branch").toString());
                                                //  branch.add(snapshot.get("class1").toString());

                                              }

                                          }

                                      });

       Task task2= ref.whereEqualTo("year",year)
                .whereEqualTo("branch","CMPN").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            docs = documentSnapshot.toObject(Docs.class);
                            top250 = docs.getClass1();
                        }
                    }
                });
        Task task3=ref.whereEqualTo("year",year)
                .whereEqualTo("branch","IT").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            docs = documentSnapshot.toObject(Docs.class);
                            nowShowing = docs.getClass1();
                        }
                    }
                });
        Task task4=ref.whereEqualTo("year",year)
                .whereEqualTo("branch","EXTC").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            docs = documentSnapshot.toObject(Docs.class);
                            comingSoon = docs.getClass1();
                        }
                    }
                });
        Task task5=ref.whereEqualTo("year",year)
                .whereEqualTo("branch","ETRX").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            docs = documentSnapshot.toObject(Docs.class);
                            classes = docs.getClass1();
                        }
                    }
                });


        Task combine=Tasks.whenAllSuccess(task1,task2,task3,task4,task5).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
            @Override
            public void onSuccess(List<Object> list) {
                listDataChild.put(listDataHeader.get(0), top250);
                listDataChild.put(listDataHeader.get(1), nowShowing);
                listDataChild.put(listDataHeader.get(2), comingSoon);
                listDataChild.put(listDataHeader.get(3), classes);
                myExplistAdapter = new MyExplistAdapter(Select_Division.this, listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(myExplistAdapter);

            }
        });
    }

}
class MyExplistAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    public MyExplistAdapter(Context context , List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
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