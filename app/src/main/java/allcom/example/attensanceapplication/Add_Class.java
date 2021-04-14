package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Add_Class extends AppCompatActivity {
    Spinner sclass, syear;
    Button submit;
    String id;
    String classname=null,branch=null,year=null;
    EditText Class;
    int counter=0;
    int classnumber=0;
    List<String> classes=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference ref=db.collection("Class");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__class);
        sclass=(Spinner)findViewById(R.id.teacheridspinner3);
        syear =(Spinner)findViewById(R.id.teacheridspinner);
        submit=(Button) findViewById(R.id.submitbutton);
        Class=(EditText)findViewById(R.id.Class) ;
        String[] teacher=getResources().getStringArray(R.array.academcicYear1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item, teacher);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sclass.setAdapter(adapter);
        String[] teacher1=getResources().getStringArray(R.array.academcicYear);
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_item, teacher1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        syear.setAdapter(adapter1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                branch = sclass.getItemAtPosition(sclass.getSelectedItemPosition()).toString();
                year = syear.getItemAtPosition(syear.getSelectedItemPosition()).toString();
                classname = Class.getText().toString();
                ref.whereEqualTo("branch",branch).whereEqualTo("year",year).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> snapshotslist=queryDocumentSnapshots.getDocuments();
                                String datadocs="";
                                for(DocumentSnapshot snapshot:snapshotslist){
                                    System.out.println("helloo "+snapshot.getData().toString());
                                    if(!snapshot.getData().toString().contains(classname.toUpperCase())){
                                        System.out.println(classname);
                                        counter=1;
                                    };
                                }
                                if(counter==1) {

                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                        Docs docs = documentSnapshot.toObject(Docs.class);
                                        docs.setDocumentId(documentSnapshot.getId());
                                        id = docs.getDocumentId();
                                        classes=docs.getClass1();
                                        Class.setText("");
                                        Toast.makeText(Add_Class.this, "Class Added", Toast.LENGTH_SHORT).show();
                                    }
                                    classes.add(classname.toUpperCase());
                                    Map<String, Object> data = new HashMap<String, Object>();
                                    data.put("class1", classes);
                                    db.collection("Class").document(id).update(data);

                                    counter=0;
                                }
                                else {
                                    Class.setText("");
                                    Toast.makeText(Add_Class.this, "Class Already Exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            });
    }
}