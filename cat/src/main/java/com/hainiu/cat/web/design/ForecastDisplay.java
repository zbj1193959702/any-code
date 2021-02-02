package com.hainiu.cat.web.design;

import java.util.Observable;
import java.util.Observer;

/**
 * create by biji.zhao on 2020/12/10
 */
public class ForecastDisplay implements Observer, DisplayElement {
    private float temperature; // 温度
    private float humidity; // 湿度
    // 主题
    private Observable observable;

    public ForecastDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println(String.format("温度湿度比例： %s", temperature / humidity));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
