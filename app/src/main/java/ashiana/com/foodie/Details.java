package ashiana.com.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Details extends AppCompatActivity {

    //Variables declaration
    private List<Details_Model> list;
    private List<Url_Model> url_modelList;
    private List<Exercise_Model> exercise_modelList;
    private TextView Calories,Protine,Fat,Carbs,Fibre,name,category,serving_size, cycl,swim,run,walk;
    private ImageView Food_image;
    private RecyclerView recyclerView;

    // Firebase declaration
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.toolbar);

        String Name =getIntent().getStringExtra("name");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final int position = getIntent().getIntExtra("position", 0);

        // intializing all the viwes.

        name = findViewById(R.id.name);
        Protine = findViewById(R.id.protein);
        Fat = findViewById(R.id.fats);
        Calories = findViewById(R.id.calories);
        Fibre= findViewById(R.id.fibre);
        Carbs = findViewById(R.id.carbs);
        Food_image = findViewById(R.id.foodimage);
        category = findViewById(R.id.classi);
        serving_size = findViewById(R.id.serving);
        cycl = findViewById(R.id.cycl);
        run = findViewById(R.id.run);
        walk = findViewById(R.id.walk);
        swim = findViewById(R.id.swim);


        recyclerView = findViewById(R.id.linkrv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        MobileAds.initialize(this);
        loadAds();

// retrieving data from firebase .
        myRef.child("Food_Details").child("Food_Items").orderByChild("name").equalTo(Name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("ok","Data is coming");
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Log.i("ok 1","Data is coming here");
                    list.add(dataSnapshot.getValue(Details_Model.class));
                    name.setText(list.get(position).getName());
                    Protine.setText(list.get(position).getProtein());
                    Fat.setText(list.get(position).getFat());
                    Calories.setText(list.get(position).getCalories());
                    Carbs.setText(list.get(position).getCarbs());
                    Fibre.setText(list.get(position).getFibre());
                    serving_size.setText(list.get(position).getServing_size());
                    Float fat = Float.parseFloat(list.get(position).getCalories_from_fat());
                    int cal = Integer.parseInt(list.get(position).getCalories());
                    Float calculatedFat = (fat/cal)*100;
                    if(calculatedFat <30){
                        category.setText("Low Fat Food Item");
                    }
                    else if(calculatedFat >35){
                        category.setText("High Fat Food Item");
                    }
                    else
                    {
                        category.setText("Normal Fat Food Item");
                    }
                    // Adding food image from url(Food_Image) using picasso
                    Picasso.get().load(list.get(position).getFood_Image()).into(Food_image);



                    Log.i("ok 2","list");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Details.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

// retriving data for exercise layout.

        exercise_modelList = new ArrayList<>();
        myRef.child("Food_Details").child("exercise").orderByChild("name").equalTo(Name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                for(DataSnapshot dataSnapshot2 : snapshot2.getChildren()) {
                    Log.i("ok 1", "Exercise Data is coming here");
                    exercise_modelList.add(dataSnapshot2.getValue(Exercise_Model.class));
                    Log.i("ok 2", "exercisemodelList");
                    cycl.setText(exercise_modelList.get(position).getCycling());
                    walk.setText(exercise_modelList.get(position).getWalking());
                    run.setText(exercise_modelList.get(position).getRunning());
                    swim.setText(exercise_modelList.get(position).getSwimming());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Details.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        url_modelList = new ArrayList<>();

        final Url_Adapter adapter = new Url_Adapter(url_modelList);
        recyclerView.setAdapter(adapter);


// retrieving data for youtube links.
        myRef.child("Food_Details").child("Recipies").child(Name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                Log.i("ok","Data is coming");
                for(DataSnapshot dataSnapshot1 : snapshot1.getChildren())
                {
                    Log.i("ok 1","Data is coming here");
                    url_modelList.add(dataSnapshot1.getValue(Url_Model.class));
                    Log.i("ok 2","urlmodelList");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Details.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    // for toolbar back button
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
