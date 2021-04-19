package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Front_page extends AppCompatActivity {

private final int SPLASH_TIME_OUT=2000;

Login_Page l1=new Login_Page();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
      new Handler().postDelayed(() -> {
          if(l1.mAuth.getInstance().getCurrentUser()!= null){
              DocumentReference df = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
              df.get().addOnSuccessListener(documentSnapshot -> {
                  if(documentSnapshot.getString("userType").equals("admin")){
                       startActivity(new Intent(Front_page.this,Admin.class));
                      finish();
                  }
                  else if(documentSnapshot.getString("userType").equals("teacher")){
                      Intent intent=new Intent(Front_page.this,TeacherMainPage.class);
                      intent.putExtra("Teacher name",documentSnapshot.get("Name3").toString());
                      startActivity(intent);
                      finish();
                  }
                  else if(documentSnapshot.getString("userType").equals("student")){
                      Intent i =new Intent(Front_page.this,StudentMainPage.class);
                      i.putExtra("Student name",documentSnapshot.get("Name3").toString());
                      i.putExtra("Division",documentSnapshot.get("Division3").toString());
                      startActivity(i);
                      finish();
                  }
              });
          }else {
              Intent intent = new Intent(Front_page.this, Login_Page.class);
              startActivity(intent);
              finish();
          }
      },SPLASH_TIME_OUT);
    }
}