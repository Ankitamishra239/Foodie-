package ashiana.com.foodie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.webkit.WebChromeClient;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.method.LinkMovementMethod;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Url_Adapter extends RecyclerView.Adapter<Url_Adapter.Viewholder>{

    List<Url_Model> url_modelList;
    private String videoId;

    public Url_Adapter(List<Url_Model> url_modelList) {
        this.url_modelList = url_modelList;
    }

    @NonNull
    @Override
    public Url_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_item,parent,false);
        return new Url_Adapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Url_Adapter.Viewholder holder, int position) {

        holder.setData( url_modelList.get(position).getImage(), url_modelList.get(position).getTitle());
        videoId = url_modelList.get(position).getVideoId();
    }

    @Override
    public int getItemCount() {
        return url_modelList.size();
    }


    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title= itemView.findViewById(R.id.link);

        }
        private void setData(String image, final String title){

            this.title.setText(title);
            Picasso.get().load(image).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setIntent = new Intent(itemView.getContext(), UrlViewActivity.class);
                    setIntent.putExtra("videoId", videoId );
                    setIntent.putExtra("title", title );
                    itemView.getContext().startActivity(setIntent);
                }
            });


        }
    }

}
