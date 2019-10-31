package com.example.yahooapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yahooapi.Requests;
import com.example.yahooapi.OAuth;

import com.github.scribejava.apis.YahooApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Requests requests = new Requests();
    private OAuth oAuth = new OAuth();
    ArrayList<String> allPlayers = new ArrayList<String>();;
    String response1;
    String response2;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);

    }

    public void getAllRosters(View view) throws InterruptedException, ExecutionException, IOException {

        final String yahooCode = et.getText().toString();

        new Thread(new Runnable() {
            public void run() {
                try {
                    oAuth.finishAuthentication(yahooCode);

                    for (int i = 1; i < 21; i++) { // instead of 21, team number needs to be got dynamically
                        allPlayers.add(oAuth.sendRequest(Requests.getUserTeamPlayers(Integer.toString(i), "44180")));
                    }

                }
                catch (Exception e){
                    String ex = e.toString();
                }
            }
        }).start();

    }

    public void getYahooCode(View view){
        String url = oAuth.startAuthentication();

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
