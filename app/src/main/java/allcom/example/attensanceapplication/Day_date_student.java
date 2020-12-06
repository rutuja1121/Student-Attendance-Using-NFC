package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Day_date_student extends AppCompatActivity {
Spinner day,date,month,year;
TextView editTextTime,editTextTime2;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_date_student);
        year=findViewById(R.id.year);
        day=findViewById(R.id.day);
        date=findViewById(R.id.date);
        month=findViewById(R.id.month);
        editTextTime=findViewById(R.id.editTextTime);
        editTextTime2=findViewById(R.id.editTextTime2);
        String[] day1=getResources().getStringArray(R.array.Day);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,day1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        day.setAdapter(adapter);
        String[] date1=getResources().getStringArray(R.array.Date);
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,date1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        date.setAdapter(adapter1);
        String[] month1=getResources().getStringArray(R.array.Month);
        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,month1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        date.setAdapter(adapter2);
        String[] year1=getResources().getStringArray(R.array.Year);
        ArrayAdapter adapter3=new ArrayAdapter(this,android.R.layout.simple_spinner_item,year1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        year.setAdapter(adapter3);


    }
}