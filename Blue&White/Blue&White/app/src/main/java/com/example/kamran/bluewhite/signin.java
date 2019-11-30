package com.example.kamran.bluewhite;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class signin extends AppCompatActivity {
    private OAuth oAuth = new OAuth();
    ArrayList<String> allPlayers = new ArrayList<String>();
    ArrayList<Team> teams = new ArrayList<Team>();
    UserInfo user = new UserInfo();
    ImageView sback;
    LinearLayout loginn;
    EditText et;

    //private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sback = (ImageView)findViewById(R.id.sinb);
        loginn = (LinearLayout)findViewById(R.id.loginn);
        et = (EditText)findViewById(R.id.yahoocode);
        //spinner = (ProgressBar)findViewById(R.id.loading);
        //spinner.setVisibility(View.GONE);

        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String yahooCode = et.getText().toString();
                showToast(signin.this, "Fethcing information in progress.");
                final Globals g = (Globals)getApplication();

                //spinner.setVisibility(View.VISIBLE);
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
                                showToast(signin.this, "Fetchinginformation from Team " + i + ".");

                                if (Integer.parseInt(teams.get(i - 1).getTeamID()) == Integer.parseInt(user.getTeamID()))
                                    user.setRoster(teams.get(i - 1).getRoster());

                            }
                            g.addUser(user);
                            g.fetched=true;
                            g.test = leagueXML;
                            g.test2=teamXML;
                            showToast(signin.this, "Fethcing information completed.");
                            Intent it = new Intent(signin.this,Profile.class);
                            startActivity(it);
                        }
                        catch (Exception e){
                            showToast(signin.this, "An error occured.");
                            String ex = e.toString();
                        }
                    }
                }).start();
                String asd = null;
            }
        });
        sback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(signin.this,Profile.class);
                startActivity(it);
            }
        });

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
