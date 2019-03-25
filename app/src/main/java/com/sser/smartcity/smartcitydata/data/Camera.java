package com.sser.smartcity.smartcitydata.data;

import java.util.ArrayList;

// Class for storing one camera data
public class Camera {

    // Unique id of a camera
    private int id;

    // List of car plates camera has seen
    private ArrayList<String> plates;
    // List of times camera has seen this plates (must be equally long)
    private ArrayList<String> times;

    // Constructor
    public Camera(int id) {
        this.id = id;

        // Create new ArrayLists
        plates = new ArrayList<>();
        times = new ArrayList<>();
    }


    /*
     * Public get/set methods (and add and clear methods)
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlate(int index) {
        return plates.get(index);
    }

    public ArrayList<String> getAllPlates() {
        return this.plates;
    }

    public void addPlate(String plate) {
        this.plates.add(plate);
    }

    public void setPlate(int index, String plate) {
        this.plates.set(index, plate);
    }

    public void clearPlats() {
        this.plates.clear();
    }


    public String getTime(int index) {
        return times.get(index);
    }

    public void addTime(String time) {
        this.times.add(time.replace("T", " "));
    }

    public void setTime(int index, String time) {
        this.times.set(index, time);
    }

    public void clearTimes() {
        this.times.clear();
    }
}
