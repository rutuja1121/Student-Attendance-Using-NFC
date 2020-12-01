package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Welcome_screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        new Handler() {
            public void postDelayed(Runnable runnable, int splashTimeOut) {
            }

            @Override
            public void publish(LogRecord logRecord) {
                
            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        }.postDelayed(new Runnable(){
            @Override
                    public void run(){
                Intent homeIntent = new Intent(Welcome_screen.this, Login_Page.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
       
    
    }
}