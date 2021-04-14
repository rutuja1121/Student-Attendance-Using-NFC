package allcom.example.attensanceapplication;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Day_date_student extends AppCompatActivity {
    DatabaseReference databaseReference,attendanceRef;
    CalendarView calender;
    Button timebtn1,timebtn2, submit_data;
    EditText time,time2;
    TextView choose_date;
    private int mHour, mMinute;
    String day1;
    SimpleDateFormat simpleDateFormat;
    String teacherName,Class,Division,Subject,Year;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_date_student);
        calender = findViewById(R.id.calender);
        submit_data = findViewById(R.id.submit_data);
        time = findViewById(R.id.time);
        timebtn1 = findViewById(R.id.timebtn);
        time2 = findViewById(R.id.totime);
        timebtn2 = findViewById(R.id.timebtn1);
        choose_date = findViewById(R.id.choose_date);
        teacherName=getIntent().getStringExtra("Teacher name");
        Class=getIntent().getStringExtra("Class");
        Division=getIntent().getStringExtra("Division");
        Subject=getIntent().getStringExtra("Subject");
        Year=getIntent().getStringExtra("Year");
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        day1=simpleDateFormat.format(new Date());
        databaseReference= FirebaseDatabase.getInstance().getReference("teachers");
        Toast.makeText(this, teacherName+" "+Class+" "+Division+" "+Year+" "+Subject, Toast.LENGTH_SHORT).show();

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
             day1=day+"-"+(month+1)+"-"+year;
             }
        });


        timebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        timebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Day_date_student.this, "hello", Toast.LENGTH_SHORT).show();
                if (view == timebtn1) {

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
        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(time.getText().toString().isEmpty())&&!(time2.getText().toString().isEmpty())){
                    String finaltime=time.getText().toString()+" to "+time2.getText().toString();
                    String id=databaseReference.push().getKey();
                    attendanceRef=FirebaseDatabase.getInstance().getReference("teachers").child(id);
                    String id1=attendanceRef.push().getKey();
                    Lectures lectures=new Lectures(id,teacherName,Year,Class,Division,Subject,day1,finaltime);
                    databaseReference.child(id).setValue(lectures);
                    attendanceRef.child("attendee list").setValue("");
                    Toast.makeText(Day_date_student.this, "Attendance Started", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Day_date_student.this, TeacherMainPage.class);
                    intent.putExtra("Teacher name",teacherName);
                    startActivity(intent);
                    finishAffinity();
                }else {
                    Toast.makeText(Day_date_student.this, "select time "+day1, Toast.LENGTH_SHORT).show();
                }
            }
        });

        }

    }

