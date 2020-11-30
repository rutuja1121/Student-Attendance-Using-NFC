package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Login_Page extends AppCompatActivity {
    Button LoginTwo;
    TextView Or, SignUpTwo, ForgotpasswordTwo;
    EditText EmailTwo, PasswordTwo;
    ImageView Logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        LoginTwo = findViewById(R.id.LoginTwo);
        Or = findViewById(R.id.Or);
        SignUpTwo = findViewById(R.id.SignUpTwo);
        ForgotpasswordTwo = findViewById(R.id.ForgotpasswordTwo);
        EmailTwo = findViewById(R.id.EmailTwo);
        PasswordTwo = findViewById(R.id.PasswordTwo);
        Logo = findViewById(R.id.Logo);
        LoginTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(Login_Page.this,Select_year_teacher.class);
               startActivity(intent);
            }
        });

        SignUpTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Page.this,New_user_signUp.class);
                startActivity(intent);
            }
        });
       ForgotpasswordTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Page.this,Forgot_Password.class);
                startActivity(intent);
            }
        });
    }


}

