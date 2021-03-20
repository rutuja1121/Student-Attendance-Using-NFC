package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Teacher_Details extends AppCompatActivity {
    String classname=null;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference allteachers=db.collection("TeachersDetails");
    EditText name,phoneno,email,qualification;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__details);
        name=(EditText) findViewById(R.id.updatestudentemailet);
        phoneno=(EditText) findViewById(R.id.updatephonenoet4);
        email=(EditText) findViewById(R.id.updatestudentemailet2);
        qualification=(EditText) findViewById(R.id.udateparentemailet3);
        submit=(Button) findViewById(R.id.udatesubmitbutton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdapterView sclass = null;

                if(!name.getText().toString().isEmpty()&&!phoneno.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()&&!qualification.getText().toString().isEmpty()){
                    Toast.makeText(Teacher_Details.this, ""+classname, Toast.LENGTH_SHORT).show();
                    Map<String,Object> data=new HashMap<String, Object>();
                    data.put("Name",name.getText().toString());
                    data.put("Qualification",qualification.getText().toString());
                    data.put("Phone Number",phoneno.getText().toString());
                    data.put("Email",email.getText().toString());

                    allteachers.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Teacher_Details.this, "Teacher Added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Teacher_Details.this, "faill"+e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(Teacher_Details.this, "null value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}