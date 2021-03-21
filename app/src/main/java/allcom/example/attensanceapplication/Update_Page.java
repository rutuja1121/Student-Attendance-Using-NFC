package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Update_Page extends AppCompatActivity {
    EditText sId,sName,sPhoneNo,sEmailId,pEmailId;
    Button submit;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference documentReference=db.collection("ID's").document("UUNxmWrdhLWJpZuuVrt2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__page);
        sId=(EditText)findViewById(R.id.sid);
        sName=(EditText)findViewById(R.id.updatestudentname);
        sPhoneNo=(EditText)findViewById(R.id.updatephoneno);
        sEmailId=(EditText)findViewById(R.id.updatestudentemail);
        pEmailId=(EditText)findViewById(R.id.updateparentemail);
        submit=(Button)findViewById(R.id.updatesubmitbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sId.getText().toString().isEmpty()&&!sName.getText().toString().isEmpty()&&!sPhoneNo.getText().toString().isEmpty()&&!sEmailId.getText().toString().isEmpty()&&!pEmailId.getText().toString().isEmpty()){
                    String id=sId.getText().toString();
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot document=task.getResult();
                            if(document.exists()){
                                String Address=document.get(id).toString();
                                Map<String,Object> UpdateValues =new HashMap<>();
                                UpdateValues.put("Name",sName.getText().toString());
                                UpdateValues.put("Email",sEmailId.getText().toString());
                                UpdateValues.put("Phone Number",sPhoneNo.getText().toString());
                                UpdateValues.put("Parent Email",pEmailId.getText().toString());
                                DocumentReference documentReference1=db.collection("StudentsDetails").document(Address);
                                documentReference1.set(UpdateValues, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Update_Page.this, "Success", Toast.LENGTH_SHORT).show();
                                        sId.setText("");
                                        sName.setText("");
                                        sEmailId.setText("");
                                        pEmailId.setText("");
                                        sPhoneNo.setText("");
                                    }
                                });
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Update_Page.this, "null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}