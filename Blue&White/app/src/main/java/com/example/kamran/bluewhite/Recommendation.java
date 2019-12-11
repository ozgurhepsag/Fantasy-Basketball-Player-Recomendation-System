package com.example.kamran.bluewhite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Text;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class Recommendation extends AppCompatActivity  {
    private TextView tw;
    private TextView name;
    ArrayList<String> arrayList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        Globals g = (Globals)getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        tw=findViewById(R.id.textView7);
        name=findViewById(R.id.name);
        final ListView list = findViewById(R.id.list);
        tw.setText("Best Recommendations for "+ g.getSpinnerItem()+":");
        JSONParser.parseJsonResponseOfPhpService(g.response,g.getSpinnerItem());

       // name.setText(JSONParser.playersList.get(0));

        checkPlayersInExistAnyTeam();
        final ArrayAdapter<String> arrayadap=new ArrayAdapter<String>
         (this, android.R.layout.simple_list_item_1, android.R.id.text1,JSONParser.playersList);

        list.setAdapter(arrayadap);


        list.post(new Runnable() {
            @Override
            public void run() {

                arrayadap.notifyDataSetChanged();

            }
        });
    }

    public void checkPlayersInExistAnyTeam() {
        Globals g = (Globals) getApplication();
        for (int j = 0; j < JSONParser.playersList.size(); j++) {
            for (int i = 0; i < g.teams.size(); i++) {
                for (int q = 0; q < g.teams.get(i).getRoster().size(); q++) {
                    if ((JSONParser.playersList.get(j)).equals(g.teams.get(i).getRoster().get(q).getName())) {
                        JSONParser.playersList.remove(j);
                    }
                }
            }
        }
    }




}
