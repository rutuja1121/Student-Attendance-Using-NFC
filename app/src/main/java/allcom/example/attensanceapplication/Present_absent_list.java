package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Present_absent_list extends AppCompatActivity {

        private List<Classes> listData;
        private AdapterRec recadapter;
        private ProgressBar mProgressBar;
private EditText time_et,date_et;
        private TextView textview;
        RecyclerView recview;
@SuppressWarnings("unchecked")
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_absent_list);

time_et=findViewById(R.id.time_et);
date_et=findViewById(R.id.date_et);
textview=findViewById(R.id.textView);
recview=findViewById(R.id.recview);


    String date=date_et.getText().toString();
    date=getIntent().getStringExtra("selectedDate");



    String time=time_et.getText().toString();
   time=getIntent().getStringExtra("sselectedTime");


    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recview.setLayoutManager(llm);





recadapter=new AdapterRec(listData);

recview.setAdapter(recadapter);


listData=new ArrayList<>();

//mProgressBar=new ProgressBar();






String teacherId=getIntent().getStringExtra("teacher id");




                //Query query1=FirebaseDatabase.getInstance().getReference("teachers").child("attendee list");
                Toast.makeText(this, ""+teacherId,Toast.LENGTH_SHORT).show();
                Query query= FirebaseDatabase.getInstance().getReference("Attendence").child(teacherId).child("attendee list");

                query.addListenerForSingleValueEvent(valueEventListener);
        }









    ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                       // listData.clear();
                        if (snapshot.exists()) {
                                for (DataSnapshot nmp : snapshot.getChildren()) {
                                        Classes student1 = nmp.getValue(Classes.class);
                                     listData.add(student1);
                                    System.out.println("heiiii " +student1);

                                }




                           recadapter.notifyDataSetChanged();
                             // mProgressBar.setVisibility(ProgressBar.INVISIBLE);
}
}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
    };
}
