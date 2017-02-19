package example.com.radpong.radpong;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import example.com.radpong.JsonHelper;
import example.com.radpong.callbacks.APICallback;
import example.com.radpong.callbacks.GetPlayersGamesCallback;

/**
 * Created by User on 18/02/2017.
 */

public class GetGamesForPlayerTask extends AsyncTask<String, Void, Boolean> {

    JsonArray gamesArray = null;
    GetPlayersGamesCallback callback;

    public GetGamesForPlayerTask(GetPlayersGamesCallback callback) {
        this.callback = callback;
    }


    @Override
    protected Boolean doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        Boolean success = false;
        try {
            url = new URL("https://mobilebuild.rockpooldigital.com/radpong/api/" + "players/"+ params[0] +"/games");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("X-RadPong-API-Key", "0fa5b5ba-f20a-11e6-bc64-92361f002671");
            urlConnection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjlkYjAwNzEwODM4NjU0NWE4ZGE4MjJiMGM3Y2EyMWZiNzUwNWQwODEifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbmliYmxlLWRldmludCIsInByb3ZpZGVyX2lkIjoiYW5vbnltb3VzIiwiYXVkIjoibmliYmxlLWRldmludCIsImF1dGhfdGltZSI6MTQ4NTk1NTI4NCwidXNlcl9pZCI6IkFaT0RGbHpGWW1RNnFFRnpoYXBWSm1xVEhycjIiLCJzdWIiOiJBWk9ERmx6RlltUTZxRUZ6aGFwVkptcVRIcnIyIiwiaWF0IjoxNDg1OTU1Mjg0LCJleHAiOjE0ODU5NTg4ODQsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnt9LCJzaWduX2luX3Byb3ZpZGVyIjoiYW5vbnltb3VzIn19.JaRWUj8RThMWTLvtQPaNTCoyTAO1ozcJOnXVPq-eNV_1COHu7TidggBc55WHmK4mPtbNGlWNakaK1KuIkgGJoL6xMSZij9kAuIRqVtiemtrTRXNCLY7s0AvIPbQpnzGGFrlSeGJoKr6p9vrw96fC6uc1LZJc58hlEhdUerXMz5LMx9KrCEYPtmY38oIxgqhDuq1nP1v27GF_E7pousbvzIm3RxejfYicNfszT4ssXJQUHM5c3B-De6C9jecQveqD8TNSY3EepvC0Ywt8vaXtag29qSSV5Ib9UzZZR-NIYMCwIEjpt47TuuPql4-p8_gAuejpoNOhSkGl9-YQuz_rTg");

            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            int response = urlConnection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);


                gamesArray = new Gson().fromJson(isw, JsonArray.class);

                if (gamesArray != null) {
                    success = true;
                }
            }
            return success;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            callback.foundGames(JsonHelper.getInstance().createPlayersGameListFromJson(gamesArray));
        }
    }
}
