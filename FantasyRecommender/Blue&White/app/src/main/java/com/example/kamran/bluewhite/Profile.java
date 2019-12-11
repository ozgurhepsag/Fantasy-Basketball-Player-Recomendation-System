package com.example.kamran.fantasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private ViewFlipper vf;
    LinearLayout ly;
    private TextView twTeam;
    private TextView roster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_test);
        Globals g = (Globals)getApplication();

        ly = (LinearLayout)findViewById(R.id.ly);

        //vf=findViewById(R.id.VF);
        twTeam=findViewById(R.id.name);  //tw1
        final ListView list = findViewById(R.id.list);
        ArrayList<Player> arrayList = new ArrayList<Player>();
        /*Player p1=new Player();
        p1.setImageUrl("https://www.tutorialspoint.com/java/images/java-mini-logo.jpg");
        p1.setName("test");
        p1.setTeam("team");
        Player p2=new Player();
        p2.setImageUrl("https://www.tutorialspoint.com/java/images/java-mini-logo.jpg");
        p2.setName("test2");
        p2.setTeam("team2");

        arrayList.add(p1);
        arrayList.add(p2); */


        if(g.fetched==true) {
            twTeam.setText(g.getUser(0).getUserName());
            for (int i=0; i<g.users.get(0).roster.size(); i++) {
                arrayList.add(g.users.get(0).roster.get(i));  //add players of the user to arraylist
                CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
                list.setAdapter(customAdapter);
            }
            //twNumOfTeams.setText(g.getUser(0).getNumberOfTeaminLeague());
        }
        else {
            twTeam.setText("null");
            }


        /*TextView tw = new TextView(this);
        tw.setText("Added");
        tw.setGravity(Gravity.CENTER); */


        /* ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                 //final String yahooCode = et.getText().toString();
                vf.showNext();
            }
        }); */
    }

/*
    public void previousView(View v) {
        vf.showPrevious();
    }
    public void nextView(View v) {
        vf.showNext();
    }

*/
}
