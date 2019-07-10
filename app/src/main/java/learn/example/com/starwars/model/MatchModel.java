package learn.example.com.starwars.model;

import java.io.Serializable;

/**
 * Created by manisha.sharan on 18/05/19.
 */
public class MatchModel implements Serializable {
    private int id;
    private PlayerModel player1;
    private  PlayerModel player2;
    private int PlayerWonId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerModel getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerModel player1) {
        this.player1 = player1;
    }

    public PlayerModel getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerModel player2) {
        this.player2 = player2;
    }

    public int getPlayerWonId() {
        return PlayerWonId;
    }

    public void setPlayerWonId(int playerWonId) {
        PlayerWonId = playerWonId;
    }

}
