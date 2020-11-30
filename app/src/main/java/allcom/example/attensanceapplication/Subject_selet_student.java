package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject_selet_student extends AppCompatActivity {
    ExpandableListView expand1;
    List<String> langs1;
    Map<String, List<String>> topics1;
    ExpandableListAdapter llistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selet_student);
        expand1=findViewById(R.id.expand);
        fillData();
        llistAdapter=new MyExListAdapter(this,langs1,topics1);
        expand1.setAdapter(llistAdapter);
        expand1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return false;
            }
        });
    }
    public void fillData(){
        langs1 = new ArrayList<>();
        topics1=new HashMap<>();

        langs1.add(" CMPN");
        langs1.add(" EXTC");
        langs1.add(" IT");
        langs1.add(" ETRX");


        List<String> cmpn=new ArrayList<>();
        List<String> extc=new ArrayList<>();
        List<String> it=new ArrayList<>();
        List<String> etrx=new ArrayList<>();

        cmpn.add("DLCOA");
        cmpn.add("OOPM");
        cmpn.add("CG");


        extc.add("DLCOA");
        extc.add("OOPM");
        extc.add("CG");

        it.add("DLCOA");
        it.add("OOPM");
        it.add("CG");

        etrx.add("DLCOA");
        etrx.add("OOPM");
        etrx.add("CG");


        topics1.put(langs1.get(0),cmpn);
        topics1.put(langs1.get(1),extc);
        topics1.put(langs1.get(2),it);
        topics1.put(langs1.get(3),etrx);

    }
}