package example.com.radpong.radpong;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import example.com.radpong.JsonHelper;
import example.com.radpong.Player;
import example.com.radpong.callbacks.APICallback;

/**
 * Created by User on 15/02/2017.
 */

public class CreatePlayerTask extends AsyncTask<String, Void, Boolean> {

    APICallback callback;
    JsonObject playerObject;

    public CreatePlayerTask(APICallback callback) {
        this.callback = callback;
    }


    @Override
    protected Boolean doInBackground(String... paramsIn) {
        boolean success = false;
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("https://mobilebuild.rockpooldigital.com/radpong/api/" + "players");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("X-RadPong-API-Key", "0fa5b5ba-f20a-11e6-bc64-92361f002671");
            urlConnection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjlkYjAwNzEwODM4NjU0NWE4ZGE4MjJiMGM3Y2EyMWZiNzUwNWQwODEifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbmliYmxlLWRldmludCIsInByb3ZpZGVyX2lkIjoiYW5vbnltb3VzIiwiYXVkIjoibmliYmxlLWRldmludCIsImF1dGhfdGltZSI6MTQ4NTk1NTI4NCwidXNlcl9pZCI6IkFaT0RGbHpGWW1RNnFFRnpoYXBWSm1xVEhycjIiLCJzdWIiOiJBWk9ERmx6RlltUTZxRUZ6aGFwVkptcVRIcnIyIiwiaWF0IjoxNDg1OTU1Mjg0LCJleHAiOjE0ODU5NTg4ODQsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnt9LCJzaWduX2luX3Byb3ZpZGVyIjoiYW5vbnltb3VzIn19.JaRWUj8RThMWTLvtQPaNTCoyTAO1ozcJOnXVPq-eNV_1COHu7TidggBc55WHmK4mPtbNGlWNakaK1KuIkgGJoL6xMSZij9kAuIRqVtiemtrTRXNCLY7s0AvIPbQpnzGGFrlSeGJoKr6p9vrw96fC6uc1LZJc58hlEhdUerXMz5LMx9KrCEYPtmY38oIxgqhDuq1nP1v27GF_E7pousbvzIm3RxejfYicNfszT4ssXJQUHM5c3B-De6C9jecQveqD8TNSY3EepvC0Ywt8vaXtag29qSSV5Ib9UzZZR-NIYMCwIEjpt47TuuPql4-p8_gAuejpoNOhSkGl9-YQuz_rTg");

            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            HashMap<String, String> params = new HashMap();
            params.put("name", paramsIn[0]);

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));


            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    result.append("&");
                }
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            writer.write(result.toString());

            writer.flush();
            writer.close();
            os.close();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);

                playerObject = new Gson().fromJson(isw, JsonObject.class);

                success = true;

            }
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
            callback.addedPlayerSuccess(JsonHelper.getInstance().createPlayerFromJson(playerObject));
        }
    }
}
