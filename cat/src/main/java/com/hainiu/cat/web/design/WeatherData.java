package com.hainiu.cat.web.design;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Observable;

/**
 * create by biji.zhao on 2020/12/10
 */
public class WeatherData extends Observable {

    private float temperature;
    private float humidity;
    private float pressure; // 压力

    public WeatherData() {
    }

    // 天气变化的方法回调
    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
