package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAttendanceList extends AppCompatActivity {
    private List<Classes> listData;
    private RecyclerView rv;
    private MyAdapter adapter;
    private ProgressBar mProgressBar;
    String value;
    String teachername,student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_list);
        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        student= getIntent().getStringExtra("Student name");
        teachername=getIntent().getStringExtra("Teacher name");
        value=getIntent().getStringExtra("value");
        String sDivision=getIntent().getStringExtra("Division");
       // Toast.makeText(this, ""+student, Toast.LENGTH_SHORT).show();
        listData = new ArrayList<>();
        adapter = new MyAdapter(ViewAttendanceList.this,listData,teachername,student,value);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        if(teachername!=null&&value!=null){
            Query nm = FirebaseDatabase.getInstance().getReference("Attendence").orderByChild("teacherName")
                    .equalTo(teachername);
            nm.addListenerForSingleValueEvent(valueEventListener);
        }
        else if(teachername!=null) {
            Query nm = FirebaseDatabase.getInstance().getReference("teachers").orderByChild("teacherName")
                    .equalTo(teachername);
            nm.addListenerForSingleValueEvent(valueEventListener);

        }
        else if(student!=null){
            //Query query1=FirebaseDatabase.getInstance().getReference("teachers").child("attendee list");
            Toast.makeText(this, ""+sDivision, Toast.LENGTH_SHORT).show();
            Query query=FirebaseDatabase.getInstance().getReference("Attendence").orderByChild("selectedDivision")
                    .equalTo(sDivision);
            query.addListenerForSingleValueEvent(valueEventListener);
        }
}
//    ValueEventListener valueEventListener1=new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            if (snapshot.exists()) {
//
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            listData.clear();
            if (snapshot.exists()) {
                for (DataSnapshot nmp : snapshot.getChildren()) {
                    Classes l = nmp.getValue(Classes.class);
                    listData.add(l);
                }
                adapter.notifyDataSetChanged();
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}



//
//