package com.example.kamran.fantasy;


public class Requests {
    public static final String BASE_PATH = "https://fantasysports.yahooapis.com/fantasy/v2";
    public static final String USER_PATH = BASE_PATH + "/users;use_login=1";
    public static final String PTS_URL = "http://fantasytestingg.000webhostapp.com/handler.php?pts";
    public static final String REB_URL = "http://fantasytestingg.000webhostapp.com/handler.php?reb";
    public static final String AST_URL = "http://fantasytestingg.000webhostapp.com/handler.php?ast";
    public static final String BLK_URL = "http://fantasytestingg.000webhostapp.com/handler.php?blk";
    public static final String STL_URL = "http://fantasytestingg.000webhostapp.com/handler.php?stl";
    public static final String FG_URL = "http://fantasytestingg.000webhostapp.com/handler.php?fg";
    public static final String FT_URL = "http://fantasytestingg.000webhostapp.com/handler.php?ft";
    public static final String TPM_URL = "http://fantasytestingg.000webhostapp.com/handler.php?tpm";
    public static final String TOX_URL = "http://fantasytestingg.000webhostapp.com/handler.php?tox";



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

/*
    public static String doGetRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
*/



}
