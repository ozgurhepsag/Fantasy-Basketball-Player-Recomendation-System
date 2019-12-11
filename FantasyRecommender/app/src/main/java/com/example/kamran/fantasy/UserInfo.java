package com.example.kamran.fantasy;

import java.util.ArrayList;

public class UserInfo {
    private String userName;
    private String teamID;
    private String teamName;
    private String leagueID;
    public ArrayList<Player> roster;
    private String numberOfTeaminLeague;

    public String getUserName() { return userName;}

    public void setUserName(String uname) { this.userName = uname;}

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(String leagueKey) {
        this.leagueID = leagueKey;
    }

    public int getNumberOfTeaminLeague() {
        return Integer.parseInt(numberOfTeaminLeague);
    }

    public void setNumberOfTeaminLeague(String numberOfTeaminLeague) {
        this.numberOfTeaminLeague = numberOfTeaminLeague;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public void setRoster(ArrayList<Player> roster) {
        this.roster = roster;
    }
}
