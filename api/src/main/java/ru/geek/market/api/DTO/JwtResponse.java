package ru.geek.market.api.DTO;


public class JwtResponse {
    private String token;

    public JwtResponse(String jwtToken) {
        this.token = jwtToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
