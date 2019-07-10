package learn.example.com.starwars.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by manisha.sharan on 18/05/19.
 */
public class PlayerModel implements Serializable {
    private int id;
    private String name;
    private String icon;
    private int points;
    private int totalScore;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;
    private List<MatchModel> matchesPlayed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<MatchModel> getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(List<MatchModel> matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

}
