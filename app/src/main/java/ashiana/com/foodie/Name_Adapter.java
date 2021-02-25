package ashiana.com.foodie;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Name_Adapter extends RecyclerView.Adapter<Name_Adapter.Viewholder> {

    List<Name_Model> name_modelList;

    public Name_Adapter(List<Name_Model> name_modelList) {
        this.name_modelList = name_modelList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_listlayout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.setData(name_modelList.get(position).getUrl(),name_modelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return name_modelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_View);
            name = itemView.findViewById(R.id.name);
        }
        private void setData(String url, String name){

            Glide.with(itemView.getContext()).load(url).into(imageView);
            this.name.setText(name);
        }
    }


}
