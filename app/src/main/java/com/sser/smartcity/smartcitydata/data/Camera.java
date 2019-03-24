package com.sser.smartcity.smartcitydata.data;

import java.util.ArrayList;

public class Camera {

    private int id;

    private ArrayList<String> plates;
    private ArrayList<String> times;

    public Camera(int id) {
        this.id = id;

        plates = new ArrayList<>();
        times = new ArrayList<>();
    }


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

    public String getTime(int index) {
        return times.get(index);
    }

    public void addTime(String time) {
        this.times.add(time.replace("T", " "));
    }

    public void setTime(int index, String time) {
        this.times.set(index, time);
    }
}
