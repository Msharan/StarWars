package learn.example.com.starwars.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

import learn.example.com.starwars.R;
import learn.example.com.starwars.model.MatchReaponseModel;
import learn.example.com.starwars.model.PlayerResponseModel;

/**
 * Created by manisha.sharan on 18/05/19.
 */
public class MatchRepository {
    private static MatchRepository mInstance;
    private static Context mContext;
    public static MatchRepository getInstance(Context context) {
        if(mInstance == null) {
            synchronized (MatchRepository.class) {
                if(mInstance == null) {
                    mInstance = new MatchRepository();
                    mContext = context;
                }
            }
        }
        return mInstance;
    }

    public LiveData<List<MatchReaponseModel>> getAllMatches() {
        final MutableLiveData<List<MatchReaponseModel>> mutableLiveData = new MutableLiveData<>();
        String jsonString ="";
        try {

            InputStream is = mContext.getResources().openRawResource(R.raw.match_json);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                is.close();
            }
            jsonString = writer.toString();
            System.out.println("manisha "+jsonString);
        } catch (Exception e) {

        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<MatchReaponseModel>>(){}.getType();
        List<MatchReaponseModel> posts = gson.fromJson(jsonString, listType);
        mutableLiveData.setValue(posts);
        return mutableLiveData;
    }

}
