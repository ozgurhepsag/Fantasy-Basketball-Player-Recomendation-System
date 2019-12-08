package com.example.kamran.bluewhite;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONArray;

public class JSONParser {
    public static String player_name;
    public static String value;
    public static ArrayList<String> playersList = new ArrayList<String>();


    public static void parseJsonResponseOfPhpService(String JSON_STRING){  //value is choice of user to get recommend: tpm,tpa,reb,etc....
        ArrayList<Player> recommendations = new ArrayList<Player>();
        try {

            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(JSON_STRING);
            JSONArray players = obj.getJSONArray("Players");
            // fetch JSONObject named employee
            for (int i = 0; i < players.length(); i++) {
            JSONObject object = players.getJSONObject(i);
            // get player name
            player_name = object.getString("Player");
            playersList.add(player_name);

            }
            //value = object.getString(choice);

        } catch (JSONException e) {
            e.printStackTrace();
            player_name = "error";
            //value="error";
        }
    }
}
