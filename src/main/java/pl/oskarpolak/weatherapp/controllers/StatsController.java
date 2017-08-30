package pl.oskarpolak.weatherapp.controllers;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lenovo on 30.08.2017.
 */
public class StatsController implements Initializable{
    @FXML
    BarChart<String, Number> chartWeather;

    @FXML
    JFXListView listOfCity;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Pogoda");
        series1.getData().add(new XYChart.Data("Warszawa", 20));
        series1.getData().add(new XYChart.Data("Kraków", 34.4));
        series1.getData().add(new XYChart.Data("Wrocław", 13));

        chartWeather.getData().add(series1);

    }
}
