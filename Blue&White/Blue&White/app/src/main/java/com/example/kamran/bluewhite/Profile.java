package com.example.kamran.bluewhite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.View;

public class Profile extends AppCompatActivity {
    private ViewFlipper vf;
    LinearLayout ly;
    private TextView twTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Globals g = (Globals)getApplication();

        ly = (LinearLayout)findViewById(R.id.ly);
        vf=findViewById(R.id.VF);
        twTeam=findViewById(R.id.tw1);
        twTeam.setText(g.getUser(0).getTeamName());
        TextView tw = new TextView(this);
        tw.setText("Added");
        tw.setGravity(Gravity.CENTER);


        ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                // final String yahooCode = et.getText().toString();
                vf.showNext();
            }
        });
    }
    public void previousView(View v) {
        vf.showPrevious();
    }
    public void nextView(View v) {
        vf.showNext();
    }
}
