package com.sser.smartcity.smartcitydata.data;

public class ParkingSpot {

    private int id;

    private boolean available;

    public ParkingSpot(int id) {
        this.id = id;

        available = true;
    }

    public ParkingSpot(int id, boolean available) {
        this.id = id;
        this.available = available;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
