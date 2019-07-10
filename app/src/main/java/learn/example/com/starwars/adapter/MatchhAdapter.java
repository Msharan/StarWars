package learn.example.com.starwars.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import learn.example.com.starwars.R;
import learn.example.com.starwars.model.MatchModel;
import learn.example.com.starwars.model.PlayerModel;

/**
 * Created by manisha.sharan on 18/05/19.
 */
public class MatchhAdapter extends RecyclerView.Adapter<MatchhAdapter.Viewmodel> {
    private List<MatchModel> matches;
    private Context context;

    public MatchhAdapter(Context context) {
        this.context = context;
    }

    public void setMatchDetails(PlayerModel player) {
        this.matches = player.getMatchesPlayed();
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public Viewmodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewmodel(LayoutInflater.from(context).inflate(R.layout.item_match,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewmodel viewmodel, int position) {
        if(position < matches.size()) {
            viewmodel.player1.setText(matches.get(position).getPlayer1().getName());
            viewmodel.player2.setText(matches.get(position).getPlayer2().getName());
            int score1 = matches.get(position).getPlayer1().getScore();
            int score2 = matches.get(position).getPlayer2().getScore();
            String s = score1 +" - " +score2;
            viewmodel.score.setText(s);

        }
    }

    @Override
    public int getItemCount() {
        if(matches!=null )
            return matches.size();
        return 0;
    }

    class Viewmodel extends RecyclerView.ViewHolder {
        private TextView player1,score,player2;

        public Viewmodel(@NonNull View itemView) {
            super(itemView);
            player1 = itemView.findViewById(R.id.player1);
            player2 = itemView.findViewById(R.id.player2);
            score = itemView.findViewById(R.id.score);
        }
    }
}
