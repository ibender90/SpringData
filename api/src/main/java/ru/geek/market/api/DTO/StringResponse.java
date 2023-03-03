package ru.geek.market.api.DTO;

public class StringResponse {
    private String value;

    public StringResponse(String response) {
        this.value = response;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
