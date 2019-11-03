package com.example.yahooapi;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> roster;
    private String teamID;

    public Team(ArrayList<Player> roster, String teamID) {
        this.roster = roster;
        this.teamID = teamID;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public void setRoster(ArrayList<Player> roster) {
        this.roster = roster;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

}
