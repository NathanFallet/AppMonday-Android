package me.nathanfallet.appmonday.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CompetitionsManager {

    public ArrayList<Competition> getList(int start, int limit) {
        ArrayList<Competition> competitions = new ArrayList<>();

        try {

            URL url = new URL("https://api.appmonday.xyz/competition/competition.php?start="+start+"&limit="+limit);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");

            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                JSONArray array = new JSONArray(sb.toString());

                for (int i = 0; i < array.length(); i++) {
                    if (array.get(i) instanceof JSONObject) {
                        JSONObject object = array.getJSONObject(i);

                        Competition competition = new Competition(object.getInt("id"), object.getString("name"), object.getString("description"),
                                object.has("criterias") ? object.getString("criterias") : "", object.getString("start"),
                                object.getString("end"), object.getBoolean("coming"), object.getBoolean("playing"));

                        competitions.add(competition);
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return competitions;
    }
}
