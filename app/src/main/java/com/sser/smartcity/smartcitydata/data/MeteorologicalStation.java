package com.sser.smartcity.smartcitydata.data;

public class MeteorologicalStation {

    private int id;

    private Double airTemperature, airHumidity, airQuality, co2Level, coLevel;
    private Boolean dangerousGases;

    public MeteorologicalStation(int id) {
        this.id = id;

        airTemperature = null;
        airHumidity = null;
        airQuality = null;
        co2Level = null;
        coLevel = null;
        dangerousGases = null;
    }


    /*
     * Getters and setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(Double airHumidity) {
        this.airHumidity = airHumidity;
    }

    public Double getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(Double airQuality) {
        this.airQuality = airQuality;
    }

    public Double getCo2Level() {
        return co2Level;
    }

    public void setCo2Level(Double co2Level) {
        this.co2Level = co2Level;
    }

    public Double getCoLevel() {
        return coLevel;
    }

    public void setCoLevel(Double coLevel) {
        this.coLevel = coLevel;
    }

    public Boolean getDangerousGases() {
        return dangerousGases;
    }

    public void setDangerousGases(Boolean dangerousGases) {
        this.dangerousGases = dangerousGases;
    }

}
