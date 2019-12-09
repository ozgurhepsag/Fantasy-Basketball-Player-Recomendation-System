package com.example.kamran.bluewhite;

public class Requests {
    public static final String BASE_PATH = "https://fantasysports.yahooapis.com/fantasy/v2";
    public static final String USER_PATH = BASE_PATH + "/users;use_login=1";


    // to get teamkey and team name (maybe team foto)
    public static String getTeamInfo() {
        return USER_PATH + "/games/teams";
    }

    // to get league key and team number
    public static String getLeagueInfo() {
        return USER_PATH + "/games/leagues";
    }

    // to get all players info in the intented team. players' names, positions, fotos...
    public static String getUserTeamPlayers(String teamKey, String leagueKey) {
        return BASE_PATH + "/team/nba.l." + leagueKey + ".t." + teamKey + "/roster/players/";
    }

    public static String getBASE(){
        return BASE_PATH;
    }

    public static String getUSER(){
        return USER_PATH;
    }

}
