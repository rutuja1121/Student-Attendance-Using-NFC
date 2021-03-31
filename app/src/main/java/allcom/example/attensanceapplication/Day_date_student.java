package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Day_date_student extends AppCompatActivity {
    CalendarView calender;
    Button timebtn,timebtn2, submit_data;
    EditText time,time2;
    TextView choose_date;
    private int mHour, mMinute;


    @SuppressWarnings("unchecked")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_date_student);
        calender = findViewById(R.id.calender);
        submit_data = findViewById(R.id.submit_data);
        time = findViewById(R.id.time);
        timebtn = findViewById(R.id.timebtn);
        time2 = findViewById(R.id.totime);
        timebtn2 = findViewById(R.id.timebtn1);
        choose_date = findViewById(R.id.choose_date);
        String Class=getIntent().getStringExtra("Class");
        String Division=getIntent().getStringExtra("Division");
        String Year=getIntent().getStringExtra("Year");
        Toast.makeText(this, ""+Class+Division+Year, Toast.LENGTH_SHORT).show();
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == timebtn) {

                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Day_date_student.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
            }
        });
        timebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Day_date_student.this, "hello", Toast.LENGTH_SHORT).show();
                if (view == timebtn2) {

                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Day_date_student.this, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time2.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
            }
        });
        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Day_date_student.this,TeacherMainPage.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        }

    }

