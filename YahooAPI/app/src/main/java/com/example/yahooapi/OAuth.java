package com.example.yahooapi;

import android.accounts.AuthenticatorException;
import android.app.AuthenticationRequiredException;

import com.github.scribejava.apis.YahooApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.example.yahooapi.Requests;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class OAuth {

    private static final String clientId = "dj0yJmk9WmlvejA1NHRyVnYxJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTdl";
    private static final String clientSecret = "45999f794933d33e013c5a3882e038bd80ff02bb";
    private String accessToken = null;
    private String refreshToken = null;

    final OAuth20Service service = new ServiceBuilder(clientId)
            .apiSecret(clientSecret)
            .callback(OAuthConstants.OOB)
            .build(YahooApi20.instance());

    private static OAuth2AccessToken oAuth2AccessToken = null;

    public String startAuthentication(){
        return service.getAuthorizationUrl();
    }

    public void finishAuthentication(String code) throws InterruptedException, ExecutionException, IOException {
        oAuth2AccessToken = service.getAccessToken(code);
    }

    public void refreshAccessToken() throws InterruptedException, ExecutionException, IOException {
        OAuth2AccessToken accessToken = service.refreshAccessToken(refreshToken);
    }

    public void updateTokens(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String sendRequest(String requestUrl) throws InterruptedException, ExecutionException, IOException {
        OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl);
        service.signRequest(oAuth2AccessToken, request);
        Response response = service.execute(request);

        if(response.getCode() != 200)
            resetAuthentication();

        updateTokens(oAuth2AccessToken.getAccessToken(), oAuth2AccessToken.getRefreshToken());
        refreshAccessToken();
        return response.getBody();
    }

    public void resetAuthentication() {
        oAuth2AccessToken = null;
    }

}
