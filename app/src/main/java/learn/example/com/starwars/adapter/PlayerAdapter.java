package learn.example.com.starwars.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import learn.example.com.starwars.DetailActivity;
import learn.example.com.starwars.R;
import learn.example.com.starwars.model.PlayerModel;

/**
 * Created by manisha.sharan on 18/05/19.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.Viewmodel>{

    private Context context;
    private List<PlayerModel> players;

    public PlayerAdapter(Context context) {
        this.context = context;
    }

    public void setPlayerResult(List<PlayerModel> players) {
        this.players = players;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Viewmodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewmodel(LayoutInflater.from(context).inflate(R.layout.item_player,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewmodel viewmodel, int position) {
        if(position < players.size()) {
            viewmodel.title.setText(players.get(position).getName());
            viewmodel.description.setText(String.valueOf(players.get(position).getPoints()));
            Glide.with(context).load(players.get(position).getIcon()).centerCrop().into(viewmodel.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if(players!=null )
            return players.size();
        return 0;
    }

    class Viewmodel extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView description,title;

        public Viewmodel(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int POSITION = getAdapterPosition();
                    Intent i = new Intent(context,DetailActivity.class);
                    PlayerModel model = new PlayerModel();
                    List<PlayerModel> list = new ArrayList<PlayerModel>();
                    list.add(players.get(POSITION));
                    i.putExtra("LIST", (Serializable) players.get(POSITION));
                    context.startActivity(i);

                }
            });
        }
    }
}
