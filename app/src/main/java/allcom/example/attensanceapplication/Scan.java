package allcom.example.attensanceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class Scan extends AppCompatActivity {
    Button sunmit;
    String student,sDivision,str,sRoll;
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    TextView t1;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        sDivision=getIntent().getStringExtra("student division");
        sRoll=getIntent().getStringExtra("Student roll");
        str=getIntent().getStringExtra("teacher id");
        student=getIntent().getStringExtra("student name");
       // Toast.makeText(this, ""+student, Toast.LENGTH_SHORT).show();
        t1=(TextView)findViewById(R.id.textView2);
        if (nfcAdapter == null){
            Toast.makeText(this,"NO NFC Capabilities",
                    Toast.LENGTH_SHORT).show();
            t1.setText("Your Phone Does Not Support NFC");
            i=1;
        }else if (!nfcAdapter.isEnabled()) {
            // NFC is available for device but not enabled
            t1.setText("NFC is Not enabled");
        }else{
            t1.setText("Scan/Tap your NFC Tag");
        }
        pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);

//        sunmit=findViewById(R.id.log);
//        sunmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(i==0) {
            assert nfcAdapter != null;
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }
    protected void onPause() {
        super.onPause();
        if(i==0) {
            //Onpause stop listening
            if (nfcAdapter != null) {
                nfcAdapter.disableForegroundDispatch(this);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(i==0) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag != null) {
                Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
                Ndef ndef = Ndef.get(tag);
                detectTagData(ndef);
            }
        }
        }
    private void detectTagData(Ndef ndef) {
        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            if(message.equals(sDivision)){
                Intent i=new Intent(Scan.this,Marked.class);
                i.putExtra("student name",student);
                i.putExtra("student division",sDivision);
                i.putExtra("Student roll",sRoll);
                startActivity(i);
                DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Attendence").child(str).child("attendee list");
                String id=reference.push().getKey();
                Classes classes=new Classes(student,sRoll);
                reference.child(id).setValue(classes);
                Toast.makeText(Scan.this, "Attendance Taken", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
            else {
                t1.setText("Error Try Again");
            }
            ndef.close();

        } catch (IOException | FormatException e) {
            e.printStackTrace();

        }
    }
}