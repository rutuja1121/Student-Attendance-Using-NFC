package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Marked extends AppCompatActivity {
   // Button logout;
    private final int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marked);
      //  logout=findViewById(R.id.view);
        String sName=getIntent().getStringExtra("student name");
        String sDivision=getIntent().getStringExtra("student division");
        new Handler().postDelayed(() -> {
            Intent i=new Intent(getApplicationContext(),StudentMainPage.class);
            //i.putExtra("val",1);
            i.putExtra("Student name",sName);
            i.putExtra("Division",sDivision);
            startActivity(i);
            finish();
            },SPLASH_TIME_OUT);

    }
}