package learn.example.com.starwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import learn.example.com.starwars.adapter.MatchAdapter;
import learn.example.com.starwars.adapter.MatchhAdapter;
import learn.example.com.starwars.adapter.PlayerAdapter;
import learn.example.com.starwars.model.PlayerModel;

public class DetailActivity extends AppCompatActivity {
    private PlayerModel model;
    private RecyclerView mMatchRecyclerView;
    private MatchhAdapter mMatchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //int playerId = getIntent().getIntExtra("PlayerId",-1);
        Intent i = getIntent();
        model = (PlayerModel) i.getSerializableExtra("LIST");
        mMatchRecyclerView = (RecyclerView)findViewById(R.id.match_rv);
        mMatchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMatchAdapter = new MatchhAdapter(DetailActivity.this);
        mMatchRecyclerView.setAdapter(mMatchAdapter);
        mMatchAdapter.setMatchDetails(model);

    }
}
