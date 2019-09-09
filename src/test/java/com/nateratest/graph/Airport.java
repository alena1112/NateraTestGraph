package com.nateratest.graph;

import java.util.Objects;

public class Airport {
    private String airportName;
    private String city;

    public Airport(String airportName, String city) {
        this.airportName = airportName;
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) ||
                obj instanceof Airport && Objects.equals(((Airport) obj).airportName, airportName) &&
                        Objects.equals(((Airport) obj).city, city);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
