package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Select_Division extends AppCompatActivity {
ExpandableListView expand;
List<String> branches;
Map<String, List<String>> divisions;
ExpandableListAdapter listAdapter;

    @SuppressWarnings("unchecked")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__division);
      expand=findViewById(R.id.expand);

      fillData();
      listAdapter=new MyExListAdapter(this,branches,divisions);
      expand.setAdapter(listAdapter);


      expand.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
          @Override
          public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
             final  String select =(String) listAdapter.getChild(i,i1);
             Intent intent;
             switch (select){
                 case "D7B":
                        intent =new Intent(Select_Division.this,Present_absent_list.class);
                        startActivity(intent);
                        break;


             }
             return true;
          }
      });




            }
   public void fillData(){
       branches = new ArrayList<String>();
        divisions=new HashMap<String ,List<String>>();

        branches.add(" CMPN");
        branches.add(" EXTC");
        branches.add(" IT");
        branches.add(" ETRX");


         List<String> divisions0=new ArrayList<>();
       {
           divisions0.add("D7A");
           divisions0.add("D7B");
           divisions0.add("D7C");
       }
       List<String> divisions1=new ArrayList<>();
       {
           divisions1.add("D9A");
           divisions1.add("D9B");
           divisions1.add("D9C");
       }
       List<String> divisions2=new ArrayList<>();
       {
           divisions2.add("D10B");
           divisions2.add("D10B");
           divisions2.add("D10B");
       }
       List<String>  divisions3=new ArrayList<>();
       {
           divisions3.add("D11B");
           divisions3.add("D11B");
           divisions3.add("D11B");
       }






       divisions.put(branches.get(0),divisions0);
       divisions.put(branches.get(1),divisions1);
       divisions.put(branches.get(2),divisions2);
       divisions.put(branches.get(3),divisions3);


   }

}