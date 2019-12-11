package com.example.kamran.bluewhite;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.json.JSONArray;

public class JSONParser {
    public static String player_name;
    public static String stat;
    public static ArrayList<String> playersList = new ArrayList<String>();
    public static ArrayList<String> statList = new ArrayList<>();


    public static void parseJsonResponseOfPhpService(String JSON_STRING,String statInput){  //value is choice of user to get recommend: tpm,tpa,reb,etc....
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
            stat = object.getString(statInput);
            playersList.add(player_name + " - "+statInput + ": " + stat );

            }
            //value = object.getString(choice);

        } catch (JSONException e) {
            e.printStackTrace();
            player_name = "error";

            //value="error";
        }
    }
}
