package me.nathanfallet.appmonday.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TipsManager {

    public ArrayList<Tip> getList(int start, int limit) {
        ArrayList<Tip> tips = new ArrayList<>();

        try {

            URL url = new URL("https://api.appmonday.xyz/tip/tip.php?start="+start+"&limit="+limit);

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

                        Tip tip = new Tip(object.getInt("id"), object.getString("name"), object.getString("description"), object.getString("publish"));

                        tips.add(tip);
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return tips;
    }

}
