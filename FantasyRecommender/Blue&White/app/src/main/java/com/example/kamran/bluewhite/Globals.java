package com.example.kamran.fantasy;

import java.util.ArrayList;
import android.app.Application;
public class Globals extends Application {

    ArrayList<UserInfo> users = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();
    Boolean fetched=false; // datas fetched from yahoo or not?
    String test;
    String test2;

    public void addUser(UserInfo u){
        users.add(u);
    }
    public UserInfo getUser(int b){
        return users.get(b);
    }
    public void addPlayer(Player p){
        players.add(p);
    }
    public Player getPlayer(int p){
        return players.get(p);
    }


}
