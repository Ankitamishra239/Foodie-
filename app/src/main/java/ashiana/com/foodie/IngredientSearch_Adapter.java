package ashiana.com.foodie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IngredientSearch_Adapter extends RecyclerView.Adapter<IngredientSearch_Adapter.Viewholder>{

    List<Details_Model> list;

    public IngredientSearch_Adapter(List<Details_Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientSearch_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_listlayout,parent,false);
        return new IngredientSearch_Adapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientSearch_Adapter.Viewholder holder, int position) {

        holder.setData(list.get(position).getFood_Image(),list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_View);
            name = itemView.findViewById(R.id.name);
        }
        private void setData(String url, final String name){

            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.name.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setIntent = new Intent(itemView.getContext(), Details.class);
                    setIntent.putExtra("name", name);
                    itemView.getContext().startActivity(setIntent);
                }
            });
        }
    }

}
