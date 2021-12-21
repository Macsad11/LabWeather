package com.example.labweather;

public class Goroda {

    private String gorod;
    private String temper;

    public Goroda(String gorod, String temper) {
        this.gorod = gorod;
        this.temper = temper;
    }

    public String getGorod() {
        return gorod;
    }

    public String getTemper() {
        return temper;
    }

    public void setGorod(String gorod) {
        this.gorod = gorod;
    }

    public void setTemper(String temper) {
        this.temper = temper;
    }
}
