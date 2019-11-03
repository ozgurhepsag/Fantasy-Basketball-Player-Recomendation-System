package com.example.yahooapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yahooapi.Requests;
import com.example.yahooapi.OAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Requests requests = new Requests();
    private OAuth oAuth = new OAuth();
    ArrayList<String> allPlayers = new ArrayList<String>();
    ArrayList<Team> teams = new ArrayList<Team>();
    UserInfo user = new UserInfo();
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        showToast(MainActivity.this, "Hello");
    }

    public void getAllRosters(View view) throws InterruptedException, ExecutionException, IOException {

        final String yahooCode = et.getText().toString();
        showToast(MainActivity.this, "Fethcing information in progress.");

        new Thread(new Runnable() {
            public void run() {
                try {
                    oAuth.finishAuthentication(yahooCode);

                    String leagueXML = oAuth.sendRequest(Requests.getLeagueInfo());
                    user = XMLParser.parseLeagueInfo(leagueXML, user);
                    String teamXML = oAuth.sendRequest(Requests.getTeamInfo());
                    user = XMLParser.parseTeamInfo(teamXML, user);

                    for (int i = 1; i < user.getNumberOfTeaminLeague() + 1; i++) { // instead of 21, team number needs to be got dynamically
                        allPlayers.add(oAuth.sendRequest(Requests.getUserTeamPlayers(Integer.toString(i), user.getLeagueID())));
                        teams.add(XMLParser.parseTeam(allPlayers.get(i - 1)));
                        showToast(MainActivity.this, "Fetchinginformation from Team " + i + ".");

                        if (Integer.parseInt(teams.get(i - 1).getTeamID()) == Integer.parseInt(user.getTeamID()))
                            user.setRoster(teams.get(i - 1).getRoster());

                    }
                    showToast(MainActivity.this, "Fethcing information completed.");

                }
                catch (Exception e){
                    showToast(MainActivity.this, "An error occured.");
                    String ex = e.toString();
                }
            }
        }).start();
        String asd = null;
    }

    public void getYahooCode(View view){
        String url = oAuth.startAuthentication();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void showToast(final Context context, final String text)
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
