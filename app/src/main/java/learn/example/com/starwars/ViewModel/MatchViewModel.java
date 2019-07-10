package learn.example.com.starwars.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import learn.example.com.starwars.Repository.MatchRepository;
import learn.example.com.starwars.Repository.PlayerRepository;
import learn.example.com.starwars.model.MatchReaponseModel;
import learn.example.com.starwars.model.PlayerResponseModel;

/**
 * Created by manisha.sharan on 18/05/19.
 */
public class MatchViewModel extends AndroidViewModel {
    private LiveData<List<MatchReaponseModel>> matches;
    private MatchRepository matchRepository;

    public MatchViewModel(@NonNull Application application) {
        super(application);
        matchRepository = MatchRepository.getInstance(application);
    }

    public LiveData<List<MatchReaponseModel>> getAllMatches() {
        matches = matchRepository.getAllMatches();
        return matches;
    }
}
