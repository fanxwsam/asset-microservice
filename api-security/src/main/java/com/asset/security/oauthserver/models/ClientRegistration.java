package com.asset.security.oauthserver.models;




        import java.util.HashSet;
        import java.util.Set;
        import java.util.function.Consumer;

public class ClientRegistration {


    private String clientId;
    private String clientSecret;
    private Set<String> redirectUris;
    private Integer accessTokenTTLMinutes = 1209600;
    private Integer refreshTokenTTLMinutes = 31540000;

    public ClientRegistration() {

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(String[] redirectUris) {
        this.redirectUris = new HashSet<>();
        for (String uri : redirectUris) {
            this.redirectUris.add(uri);
        }
    }

    public Integer getAccessTokenTTLMinutes() {
        return accessTokenTTLMinutes;
    }

    public void setAccessTokenTTLMinutes(Integer accessTokenTTLMinutesIn) {
        this.accessTokenTTLMinutes = accessTokenTTLMinutesIn;
    }

    public Integer getRefreshTokenTTLMinutes() {
        return refreshTokenTTLMinutes;
    }

    public void setRefreshTokenTTLMinutes(Integer refreshTokenTTLMinutesIn) {
        this.refreshTokenTTLMinutes = refreshTokenTTLMinutesIn;
    }

    public void accept(Set<String> t) {
        if (redirectUris != null) {
            t.addAll(redirectUris);
        }
    }
}
