package com.sser.smartcity.smartcitydata.data;

import java.util.ArrayList;

public class Parking {

    private int id;

    private ArrayList<ParkingSpot> parkingSpots;

    public Parking(int id, int numberOfParkingSpots) {
        this.id = id;

        parkingSpots = new ArrayList<>();

        for(int i = 0; i < numberOfParkingSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot(int index) {
        return parkingSpots.get(index);
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpots.add(parkingSpot);
    }
}
