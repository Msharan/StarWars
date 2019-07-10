package learn.example.com.starwars;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import learn.example.com.starwars.ViewModel.MatchViewModel;
import learn.example.com.starwars.ViewModel.PlayerViewModel;
import learn.example.com.starwars.adapter.PlayerAdapter;
import learn.example.com.starwars.model.MatchModel;
import learn.example.com.starwars.model.MatchReaponseModel;
import learn.example.com.starwars.model.PlayerModel;
import learn.example.com.starwars.model.PlayerResponseModel;

public class MainActivity extends AppCompatActivity {
    private String jsonString;
    private RecyclerView mRecyclerView;
    private PlayerViewModel mPlayerViewModel;
    private MatchViewModel mMatchViewModel;
    private PlayerAdapter mPlayerAdapter;
    private List<PlayerModel> playerList;
    private List<MatchReaponseModel> matchList;
    private String query = "";
    private HashMap<Integer,PlayerModel> idToPlayerMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.player_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPlayerAdapter = new PlayerAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mPlayerAdapter);
        mPlayerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        ((PlayerViewModel) mPlayerViewModel).getAllPlayer().observe(MainActivity.this, new Observer<List<PlayerModel>>() {
            @Override
            public void onChanged(@Nullable List<PlayerModel> playerResponseModels) {
                System.out.println("mohit"+playerResponseModels.size());
                playerList = playerResponseModels;
                computePlayer();

            }
        });
        mMatchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        ((MatchViewModel) mMatchViewModel).getAllMatches().observe(MainActivity.this, new Observer<List<MatchReaponseModel>>() {
            @Override
            public void onChanged(@Nullable List<MatchReaponseModel> matchReaponseModels) {
                matchList = matchReaponseModels;
                computeMatch();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void computeMatch() {
        ListIterator<MatchReaponseModel> iterator = matchList.listIterator();

        while (iterator.hasNext()) {
            MatchReaponseModel obj = iterator.next();
            PlayerResponseModel player1 = obj.getPlayer1();
            PlayerResponseModel player2 = obj.getPlayer2();
            int player1Id = player1.getId();
            int player2Id = player2.getId();
            int player1Point = 0;
            int player2Point = 0;
            int playerWonId = -1;

            if(player1.getScore() > player2.getScore()) {
                player1Point = 3;
                player2Point = 0;
                playerWonId = player1.getId();
            } else if(player1.getScore() < player2.getScore()) {
                player2Point = 3;
                player1Point = 0;
                playerWonId = player2.getId();
            } else {
                player1Point = 1;
                player2Point = 1;
            }

            if(idToPlayerMap.containsKey(player1Id) && idToPlayerMap.containsKey(player2Id)) {
                PlayerModel player = idToPlayerMap.get(player1Id);
                int points = player.getPoints();
                points = points + player1Point;
                player.setPoints(points);

                int score = player1.getScore();
                player.setTotalScore(player.getTotalScore() + score);
                player.setScore(score);



                PlayerModel p = idToPlayerMap.get(player2Id);
                int points2 = p.getPoints();
                points2 = points2 + player2Point;
                p.setPoints(points2);

                int score2 = player2.getScore();
                p.setTotalScore(p.getTotalScore() + score2);
                p.setScore(score2);

                List<MatchModel> matchesPlayedForPlayer2 = p.getMatchesPlayed();
                MatchModel matchModel2 = new MatchModel();
                matchModel2.setId(obj.getMatch());
                matchModel2.setPlayer2(p);
                matchModel2.setPlayer1(player);
                matchModel2.setPlayerWonId(playerWonId);
                matchesPlayedForPlayer2.add(matchModel2);

                List<MatchModel> matchesPlayed = player.getMatchesPlayed();
                MatchModel matchModel = new MatchModel();
                matchModel.setId(obj.getMatch());
                matchModel.setPlayer1(player);
                matchModel.setPlayer2(p);
                matchModel.setPlayerWonId(playerWonId);
                matchesPlayed.add(matchModel);


            }

            if(idToPlayerMap.containsKey(player2Id)) {
                /*PlayerModel player = idToPlayerMap.get(player2Id);
                int points = player.getPoints();
                points = points + player2Point;
                player.setPoints(points);

                int score = player2.getScore();
                player.setTotalScore(player.getTotalScore() + score);
                player.setScore(score);
                List<MatchModel> matchesPlayed = player.getMatchesPlayed();
                MatchModel matchModel = new MatchModel();
                matchModel.setId(obj.getMatch());
                matchModel.setPlayer2(player);
                matchModel.setPlayer1(idToPlayerMap.get(player1Id));
                matchModel.setPlayerWonId(playerWonId);
                matchesPlayed.add(matchModel);*/
            }
        }

        List<PlayerModel> playerList = new ArrayList<>();
        for (Map.Entry<Integer,PlayerModel> entry : idToPlayerMap.entrySet())
        {
            playerList.add(entry.getValue());
            System.out.println("manisha is : "
                    + entry.getValue().getId());
        }

        Comparator<PlayerModel> cmp = new Comparator<PlayerModel>() {
            public int compare(PlayerModel o1, PlayerModel o2) {

                return Integer.valueOf(o2.getPoints()).compareTo(Integer.valueOf(o1.getPoints()));

            }
        };
        Collections.sort(playerList, cmp);

        mPlayerAdapter.setPlayerResult(playerList);
        mRecyclerView.setAdapter(mPlayerAdapter);
    }

    private void computePlayer() {
        System.out.println("manisha is : "
                + playerList.size());
        ListIterator<PlayerModel> iterator = playerList.listIterator();

        while (iterator.hasNext()) {

            PlayerModel obj = iterator.next();
            System.out.println("Value is : "
                    + obj.getId());
            PlayerModel player = new PlayerModel();
            player.setId(obj.getId());
            player.setIcon(obj.getIcon());
            player.setName(obj.getName());
            List<MatchModel> matchList = new ArrayList<>();
            player.setMatchesPlayed(matchList);

            idToPlayerMap.put(obj.getId(),player);
        }
        for (Map.Entry<Integer,PlayerModel> entry : idToPlayerMap.entrySet())
        {
            System.out.println("kkkk is : " + entry.getKey() + "     "
                    + entry.getValue().getId());
        }

    }
}
