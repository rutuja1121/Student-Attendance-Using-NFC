package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.CertPathTrustManagerParameters;


public class Select_Division extends AppCompatActivity {

ExpandableListView expand;
List<String> branches;
Map<String,List<String>>classes;
ExpandableListAdapter listadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__division);
        expand=findViewById(R.id.expand);
        fillData();
        listadapter=new Myadapter();
        expand.setAdapter(listadapter);
        expand.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                return false;
            }
        });


    }
    public void fillData()
    {
        branches=new ArrayList<>();
        classes=new HashMap<>();

        branches.add("CMPN");
        branches.add("EXTC");

        List<String> cmpn=new ArrayList<>();
        List<String> entc=new ArrayList<>();
        cmpn.add("D7A");
      cmpn.add("D7B");

      entc.add("D9A");
        entc.add("D9B");
        classes.put(branches.get(0),cmpn);
        classes.put(branches.get(1),entc);

    }
}