package ashiana.com.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.NameList;

import java.util.ArrayList;
import java.util.List;

public class IngredientSearchActivity extends AppCompatActivity {


    private EditText Ingredient1Input;
    private EditText Ingredient2Input;
    private EditText Ingredient3Input;

    private Button search;

    List<Details_Model> list; // As we will use the same table used in Details Activity.

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();  // firebase reference


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_search);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search By Ingredient");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this);
        loadAds();

        Ingredient1Input = (EditText)findViewById(R.id.editText1);
        Ingredient1Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(IngredientSearchActivity.this, Ingredient1Input.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Ingredient2Input = (EditText)findViewById(R.id.editText2);
        Ingredient2Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(IngredientSearchActivity.this, Ingredient2Input.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Ingredient3Input = (EditText) findViewById(R.id.editText3);

        search = (Button) findViewById(R.id.search);
        list = new ArrayList<>();
/*
        final String input1 = Ingredient1Input.getText().toString().trim();
        final String input2 = Ingredient2Input.getText().toString().trim();
        final String input3 = Ingredient3Input.getText().toString().trim(); */


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(Ingredient1Input.getText().toString())){
                    Ingredient1Input.setError("Ingredient 1 is required.");
                    return;
                }
                if(TextUtils.isEmpty(Ingredient2Input.getText().toString())){
                    Ingredient1Input.setError("Ingredient 2 is required.");
                    return;
                }
                if(TextUtils.isEmpty(Ingredient3Input.getText().toString())){
                    Ingredient1Input.setError("Ingredient 3 is required.");
                    return;
                }
                Intent intent = new Intent(IngredientSearchActivity.this, IngredientSearchResult.class);
                intent.putExtra("input1", Ingredient1Input.getText().toString());
                intent.putExtra("input2", Ingredient2Input.getText().toString());
                intent.putExtra("input3", Ingredient3Input.getText().toString());
                startActivity(intent);
            }
        });

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
