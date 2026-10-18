package com.substring.agent.backend.model;

public class Flight {

    private String airline;
     private String source;
    private String destination;
    private String date;
    private Integer price;


    public Flight() {
    }

   public Flight(String airline,
              String source,
              String destination,
              String date,
              Integer price) {
    this.airline = airline;
    this.source = source;
    this.destination = destination;
    this.date = date;
    this.price = price;
}

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
