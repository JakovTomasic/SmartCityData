package com.sser.smartcity.smartcitydata.data;

import java.util.ArrayList;

// Class for storing one parking data
public class Parking {

    // Unique id of a parking
    private int id;

    // List of parking spots on the parking
    private ArrayList<ParkingSpot> parkingSpots;

    // Constructor
    public Parking(int id, int numberOfParkingSpots) {
        this.id = id;

        // Create new ArrayList with given number of spots
        parkingSpots = new ArrayList<>();

        for(int i = 0; i < numberOfParkingSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
    }


    /*
     * Public getters and setters (and add method)
     */

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
