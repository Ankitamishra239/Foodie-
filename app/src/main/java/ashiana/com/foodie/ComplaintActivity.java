package ashiana.com.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ComplaintActivity extends AppCompatActivity {

   private EditText user_name, user_complaint;
   private Button submitbtn;


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        //for toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this);
        loadAds();


        // initialization of variables.
        user_name = (EditText)findViewById(R.id.name);
        user_complaint = (EditText)findViewById(R.id.complaint);
        submitbtn = findViewById(R.id.sumbit);



        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user_name.getText().toString();
                String complaint = user_complaint.getText().toString();

                if(TextUtils.isEmpty(name) )
                {
                    user_name.setError("This field cannot be Empty.");
                }
                else if(TextUtils.isEmpty(complaint))
                {
                    user_complaint.setError("This field cannot be Empty.");
                }
                else {
                    addDataToFirebase(name,complaint);
                }
            }
        });


    }
    private void addDataToFirebase(String name, String complaint){


        HashMap<String, String> newMap= new HashMap<>();

        newMap.put("name",name);
        newMap.put("complaint", complaint);

        myRef.child("Complaint").push().setValue(newMap);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadAds(){
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}