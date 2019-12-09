package com.example.kamran.bluewhite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;
import okhttp3.Call;
import android.os.Handler;


public class Profile extends AppCompatActivity {
    private OkHttpClient client;
    private ViewFlipper vf;
    LinearLayout ly;
    private TextView twTeam;
    private TextView roster;
    private Spinner spinner;
    private ArrayAdapter<String> dataAdapterForAttributes;
    private String[] attributes = {"PTS", "REB", "AST", "BLK", "STL", "FG", "FT", "TPM", "TOX"};
    private ProgressBar loading;
    private Handler mHandler = new Handler();
    private LinearLayout recommend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_test);
        loading = (ProgressBar)findViewById(R.id.progressBar);
        loading.setVisibility(View.GONE);
        client = new OkHttpClient();
        final Globals g = (Globals) getApplication();
        ly = (LinearLayout) findViewById(R.id.ly);
        //vf=findViewById(R.id.VF);
        twTeam = findViewById(R.id.name);  //tw1
        final ListView list = findViewById(R.id.list);
        ArrayList<Player> arrayList = new ArrayList<Player>();
        spinner = findViewById(R.id.spinner1);
        //recommend = (LinearLayout)findViewById(R.id.loginwithyahoo5);


        dataAdapterForAttributes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, attributes);
        dataAdapterForAttributes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapterForAttributes);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            private OnItemSelectedListener listener;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //if(parent.getSelectedItem().toString().equals(attributes[0])) {
                String item = parent.getItemAtPosition(position).toString();
                g.setSpinnerItem(item);
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

                //}
            }

            @Override
            public void onNothingSelected(AdapterView<?> aParentView) {
                listener.onNothingSelected(aParentView);

            }
        });

        if (g.fetched) {
            twTeam.setText(g.getUser(0).getUserName());
            for (int i = 0; i < g.users.get(0).roster.size(); i++) {
                arrayList.add(g.users.get(0).roster.get(i));  //add players of the user to arraylist
                CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
                list.setAdapter(customAdapter);
            }
        }
        //twNumOfTeams.setText(g.getUser(0).getNumberOfTeaminLeague());

        /*recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

    }); */
    }

    public void recommend(View v) throws IOException {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.VISIBLE);
            }
        });

        new Thread(new Runnable() {
            public void run() {


                final Globals g = (Globals) getApplication();
                String url = "";
                switch (g.getSpinnerItem()) {
                    case "PTS":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?pts";
                        break;
                    case "REB":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?reb";
                        break;
                    case "AST":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?ast";
                        break;
                    case "BLK":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?blk";
                        break;
                    case "STL":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?stl";
                        break;
                    case "FG":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?fg";
                        break;
                    case "FT":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?ft";
                        break;
                    case "TPM":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?tpm";
                        break;
                    case "TOX":
                        url = "http://fantasytestingg.000webhostapp.com/handler.php?tox";
                        break;
                    default:
                        url = "http://fantasytestingg.000webhostapp.com/handler.php";
                        break;
                }
                try {
                    final Request request = new Request.Builder()
                            .url(url)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    twTeam.setText("Failure");
                                }
                            });
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loading.setVisibility(View.GONE);
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, final Response response) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        g.response = response.body().string();
                                        //JSONParser.parseJsonResponseOfPhpService(g.response);
                                        Toast.makeText(getApplicationContext(), "Response saved", Toast.LENGTH_LONG).show();
                                        Intent it = new Intent(Profile.this, Recommendation.class);
                                        startActivity(it);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                loading.setVisibility(View.GONE);
                                            }
                                        });
                                    } catch (IOException ioe) {
                                        twTeam.setText("Error during get body");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                loading.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
                }
                catch (Exception e){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            spinner.setVisibility(View.GONE);
                        }
                    });
                    Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        }).start();
        //mHandler.postDelayed(mLaunchTask,MYDELAYTIME);



    }

    /*
     OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://fantasytestingg.000webhostapp.com/handler.php?tox")
                .build();

        Response response = client.newCall(request).execute();
        String rsp = response.body().string();
        twTeam.setText(rsp);
        //Intent it = new Intent(Profile.this,Recommendation.class);
        //startActivity(it);

     */
}




