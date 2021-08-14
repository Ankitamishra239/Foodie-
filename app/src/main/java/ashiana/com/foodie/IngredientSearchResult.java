package ashiana.com.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IngredientSearchResult extends AppCompatActivity {

    ListView IngredientList;

    RecyclerView foodrv;
    List<Details_Model> list;
    List<Details_Model> finalList;

    int position =0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_search_result);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FOODIE!");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String input1 =getIntent().getStringExtra("input1");
        final String input2 =getIntent().getStringExtra("input2");
        final String input3 =getIntent().getStringExtra("input3");

        final String[] Ingredients = {input1, input2,input3};

        IngredientList = (ListView)findViewById(R.id.ingredientListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_layout, R.id.textView, Ingredients);
        IngredientList.setAdapter(arrayAdapter);

        foodrv = findViewById(R.id.foodrv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        foodrv.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        finalList = new ArrayList<>();

        final IngredientSearch_Adapter adapter = new IngredientSearch_Adapter(finalList);
        foodrv.setAdapter(adapter);



        myRef.child("Food_Details").child("Food_Items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                Log.i("ok","Data is coming");
                for(DataSnapshot dataSnapshot1 : snapshot1.getChildren())
                {
                    Log.i("ok 1","Data is coming here");
                    list.add(dataSnapshot1.getValue(Details_Model.class));
                    if (input1.equalsIgnoreCase(list.get(position).getIngredient1()) || input2.equalsIgnoreCase(list.get(position).getIngredient1())
                    || input3.equalsIgnoreCase(list.get(position).getIngredient1()))
                    {
                        finalList.add(dataSnapshot1.getValue(Details_Model.class));
                    }
                    else if(input1.equalsIgnoreCase(list.get(position).getIngredient2()) || input2.equalsIgnoreCase(list.get(position).getIngredient2())
                            || input3.equalsIgnoreCase(list.get(position).getIngredient2()))
                    {
                        finalList.add(dataSnapshot1.getValue(Details_Model.class));
                    }
                    else if(input1.equalsIgnoreCase(list.get(position).getIngredient3()) || input2.equalsIgnoreCase(list.get(position).getIngredient3())
                            || input3.equalsIgnoreCase(list.get(position).getIngredient3()))
                    {
                        finalList.add(dataSnapshot1.getValue(Details_Model.class));
                    }
                    position++;
                    Log.i("ok 2","detailsmodelList");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(IngredientSearchResult.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
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
}