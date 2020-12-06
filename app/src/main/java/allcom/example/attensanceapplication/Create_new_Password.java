package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Create_new_Password extends AppCompatActivity {
    EditText NewPassword5,ReEnterPassword5;
    Button SubmitButton5;
    TextView CreatePassword5;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new__password);
        CreatePassword5=findViewById(R.id.CreatePassword5);
        NewPassword5=findViewById(R.id.NewPassword5);
        ReEnterPassword5=findViewById(R.id.ReEnterPassword5);
        SubmitButton5=findViewById(R.id.SubmitButton5);
        SubmitButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Create_new_Password.this,Otp_Page.class);
                startActivity(intent);
            }
        });
    }
}