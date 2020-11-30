package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Forgot_Password extends AppCompatActivity {
    TextView Verify4,ForgotPassword4,VesId4;
    EditText Email4;
    Button SubmitButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        Verify4=findViewById(R.id.Verify4);
        Email4=findViewById(R.id.Email4);
        ForgotPassword4=findViewById(R.id.ForgotPassword4);
        VesId4=findViewById(R.id.VesId4);
        SubmitButton4=findViewById(R.id.SubmitButton4);
        SubmitButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Forgot_Password.this,Create_new_Password.class);
                startActivity(intent);
            }
        });

    }
}