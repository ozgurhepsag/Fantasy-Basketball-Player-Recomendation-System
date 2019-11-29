package com.example.kamran.bluewhite;

import java.util.ArrayList;
import android.app.Application;
public class Globals extends Application {

    ArrayList<UserInfo> users = new ArrayList<>();

    public void addUser(UserInfo u){
        users.add(u);
    }
    public UserInfo getUser(int b){
        return users.get(b);
    }

}
