package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class Front_page extends AppCompatActivity {

private final int SPLASH_TIME_OUT=2000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

      new Handler().postDelayed(() -> {
          Intent intent= new Intent(Front_page.this,Login_Page.class);
          startActivity(intent);
          finish();
      },SPLASH_TIME_OUT);


    }
}