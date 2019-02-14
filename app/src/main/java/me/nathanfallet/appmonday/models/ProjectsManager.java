package me.nathanfallet.appmonday.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ProjectsManager {

    public ArrayList<Project> getList(int start, int limit) {
        ArrayList<Project> projects = new ArrayList<>();

        try {

            URL url = new URL("https://api.appmonday.xyz/project/project.php?start="+start+"&limit="+limit);

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

                        Project project = new Project(object.getString("name"), object.getString("description"),
                                object.getString("user"), object.getString("link"),
                                object.getString("publish"), object.getString("logo"));

                        projects.add(project);
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return projects;
    }

}
