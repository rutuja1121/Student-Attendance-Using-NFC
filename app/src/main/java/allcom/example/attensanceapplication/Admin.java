package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Admin extends AppCompatActivity {
private Button adminLogout;
private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        adminLogout=findViewById(R.id.admin_logout);
        textview=findViewById(R.id.textView4);
    }
}