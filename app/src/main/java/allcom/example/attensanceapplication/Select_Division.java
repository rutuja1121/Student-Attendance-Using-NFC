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


public class Select_Division extends AppCompatActivity {
ExpandableListView expand;
List<String> langs;
Map<String, List<String>> topics;
ExpandableListAdapter listAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__division);
      expand=findViewById(R.id.expand);
      fillData();
      listAdapter=new MyExListAdapter(this,langs,topics);
      expand.setAdapter(listAdapter);
      expand.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
          @Override
          public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
              return false;
          }
      });



            }
   public void fillData(){
        langs = new ArrayList<>();
        topics=new HashMap<>();

         langs.add(" CMPN");
         langs.add(" EXTC");
       langs.add(" IT");
       langs.add(" ETRX");


         List<String> cmpn=new ArrayList<>();
       List<String> extc=new ArrayList<>();
       List<String> it=new ArrayList<>();
       List<String> etrx=new ArrayList<>();

       cmpn.add("D7A");
       cmpn.add("D7B");
       cmpn.add("D7C");


       extc.add("D9A");
       extc.add("D9B");
       extc.add("D9C");

       it.add("D10B");
       it.add("D10B");
       it.add("D10B");

       etrx.add("D11B");
       etrx.add("D11B");
       etrx.add("D11B");


       topics.put(langs.get(0),cmpn);
       topics.put(langs.get(1),extc);
       topics.put(langs.get(2),it);
       topics.put(langs.get(3),etrx);



   }

}