package learn.example.com.starwars.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learn.example.com.starwars.Repository.PlayerRepository;
import learn.example.com.starwars.model.PlayerModel;

/**
 * Created by manisha.sharan on 18/05/19.
 */
public class PlayerViewModel extends AndroidViewModel {
    private LiveData<List<PlayerModel>> players;
    private PlayerRepository playerRepository;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        playerRepository = PlayerRepository.getInstance(application);
    }

    public LiveData<List<PlayerModel>> getAllPlayer() {
        players = playerRepository.getAllPlayer();
        return players;
    }

}
