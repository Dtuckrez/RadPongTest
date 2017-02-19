package example.com.radpong.radpong;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import example.com.radpong.JsonHelper;
import example.com.radpong.callbacks.APICallback;

/**
 * Created by User on 15/02/2017.
 */

public class GetAllPlayerTask extends AsyncTask<Void, Void, Boolean> {

    JsonArray playerArray = null;
    APICallback callback;


    public GetAllPlayerTask(APICallback callback) {
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        Boolean success = false;

        try {
            url = new URL("https://mobilebuild.rockpooldigital.com/radpong/api/" + "players?sort=:sort");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("X-RadPong-API-Key", "0fa5b5ba-f20a-11e6-bc64-92361f002671");

            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            urlConnection.connect();
            int response = urlConnection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                playerArray = new Gson().fromJson(isw, JsonArray.class);

                if (playerArray != null) {
                    success = true;
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return success;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            callback.foundAllPlayer(JsonHelper.getInstance().createPlayerListFromJson(playerArray));
        }
    }
}
