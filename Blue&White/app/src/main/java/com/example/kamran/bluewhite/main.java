package com.example.kamran.bluewhite;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import com.example.kamran.bluewhite.Requests;
import com.example.kamran.bluewhite.OAuth;

public class main extends AppCompatActivity {
    private Requests requests = new Requests();
    private OAuth oAuth = new OAuth();

    TextView sin;
    LinearLayout getyahoocode;
    LinearLayout login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getyahoocode = (LinearLayout)findViewById(R.id.getyahoocode);
        sin = (TextView)findViewById(R.id.sin);
        login = (LinearLayout)findViewById(R.id.login);

        getyahoocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = oAuth.startAuthentication();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
               // final String yahooCode = et.getText().toString();
                Intent it = new Intent(main.this,signin.class);
                startActivity(it);
            }
        });


    }


}
