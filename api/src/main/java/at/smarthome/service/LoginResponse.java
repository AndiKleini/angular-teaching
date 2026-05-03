package at.smarthome.service;

public class LoginResponse {
    private String accessToken;
    private String fact;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public LoginResponse(String accessToken, String fact) {
        this.accessToken = accessToken;
        this.fact = fact;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public LoginResponse() { }
}
