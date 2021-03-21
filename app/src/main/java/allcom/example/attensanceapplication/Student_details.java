package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.util.GAuthToken;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student_details extends AppCompatActivity {
    String classname=null;
    int count = 1,ID=0;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    //    private DocumentReference documentReferenc=new FirebaseFirestore();
//    private CollectionReference allstudent=db.collection("StudentsDetails");
    DocumentReference documentReference=db.collection("ID's").document("fKOr22Rz082zWpjfnuZH");

    EditText name,phoneno,email,parentname,parentphoneno,parentemail;
    Button submit;
    TextView StudentID;
    Spinner sclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        name=(EditText) findViewById(R.id.studentnameet);
        phoneno=(EditText) findViewById(R.id.phonenoet);
        email=(EditText) findViewById(R.id.studentemailet);
        parentname=(EditText) findViewById(R.id.parentnameet);
        parentphoneno=(EditText) findViewById(R.id.parentnameet1);
        parentemail=(EditText) findViewById(R.id.parentemailet);
        submit=(Button) findViewById(R.id.studentsubmitbutton);
        sclass=(Spinner)findViewById(R.id.classspinner);
        StudentID=(TextView)findViewById(R.id.StudentID);
        String[] teacher=getResources().getStringArray(R.array.academcicYear);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item, teacher);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        count = Integer.parseInt(document.get("id1").toString());
                        ID=count+1;
                        StudentID.setText("Student ID :-"+ID);

                    }
                }
            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sclass.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classname=sclass.getItemAtPosition(sclass.getSelectedItemPosition()).toString();
                if(!name.getText().toString().isEmpty()&&(!classname.equals("--select--"))&&!phoneno.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()&&!parentname.getText().toString().isEmpty()&&!parentphoneno.getText().toString().isEmpty()&&!parentemail.getText().toString().isEmpty()){
                    String id1 = db.collection("StudentsDetails").document().getId();
                    Map<String,Object> data=new HashMap<String, Object>();
                    data.put("Name",name.getText().toString());
                    data.put("Class",classname);
                    data.put("Phone Number",phoneno.getText().toString());
                    data.put("Email",email.getText().toString());
                    data.put("Parent Name",parentname.getText().toString());
                    data.put("Parent PhoneNumber",parentphoneno.getText().toString());
                    data.put("Parent Email",parentemail.getText().toString());

                    db.collection("StudentsDetails").document(id1).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Student_details.this, "success", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    count =Integer.parseInt(document.get("id1").toString());
                                    count++;
                                    Map<String,Object> userID=new HashMap<String, Object>();
                                    userID.put("id1", count);
                                    db.collection("ID's").document("fKOr22Rz082zWpjfnuZH").update(userID);
                                    Map<String,Object> userAddress=new HashMap<String, Object>();
                                    userAddress.put(count+"",id1);
                                    db.collection("ID's").document("UUNxmWrdhLWJpZuuVrt2").update(userAddress);
                                    int number=count+1;
                                    StudentID.setText("Student ID :-"+number);
                                } else {
                                    Map<String,Object> data1=new HashMap<String, Object>();
                                    data1.put("id1", count);
                                    System.out.println("hello0 "+data1);
                                    db.collection("ID's").document("fKOr22Rz082zWpjfnuZH").set(data1);
                                    Map<String,Object> userAddress=new HashMap<String, Object>();
                                    userAddress.put(count+"",id1);
                                    db.collection("ID's").document("UUNxmWrdhLWJpZuuVrt2").set(userAddress);
                                }
                            } else {

                            }
                        }
                    });
                    name.setText("");
                    phoneno.setText("");
                    email.setText("");
                    parentphoneno.setText("");
                    parentemail.setText("");
                    parentname.setText("");
                    sclass.setSelection(0);
                }
                else{
                    Toast.makeText(Student_details.this, "null value", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}