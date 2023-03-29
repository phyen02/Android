package com.myapplication.models;
import java.io.Serializable;

public class Beer implements Serializable {
        int beerThumb;
        String beerName;
        double beerPrice;

public Beer(int beerThumb, String beerName, double beerPrice) {
        this.beerThumb = beerThumb;
        this.beerName = beerName;
        this.beerPrice = beerPrice;
        }

public int getBeerThumb() {
        return beerThumb;
        }

public void setBeerThumb(int beerThumb) {
        this.beerThumb = beerThumb;
        }

public String getBeerName() {
        return beerName;
        }

public void setBeerName(String beerName) {
        this.beerName = beerName;
        }

public double getBeerPrice() {
        return beerPrice;
        }

public void setBeerPrice(double beerPrice) {
        this.beerPrice = beerPrice;
        }
        }

