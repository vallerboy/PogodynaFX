package pl.oskarpolak.weatherapp.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import pl.oskarpolak.weatherapp.model.WeatherStat;
import pl.oskarpolak.weatherapp.model.dao.WeatherStatDao;
import pl.oskarpolak.weatherapp.model.dao.impl.WeatherStatDaoImpl;
import pl.oskarpolak.weatherapp.model.utils.DatabaseConnector;

import java.io.IOException;
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

    @FXML
    JFXButton buttonBack;

    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
    private WeatherStatDao weatherStatDao = new WeatherStatDaoImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Pogoda");
        series1.getData().add(new XYChart.Data("Warszawa", 20));
        series1.getData().add(new XYChart.Data("Kraków", 34.4));
        series1.getData().add(new XYChart.Data("Wrocław", 13));

        chartWeather.getData().add(series1);
        registerButtonStatsAction();
        loadCityNames();

    }


    private void loadCityNames(){
        listOfCity.setItems(FXCollections.observableList(weatherStatDao.getAllCities()));
    }

    private void registerButtonStatsAction() {
        buttonBack.setOnMouseClicked(e -> {
            Stage stage = (Stage) buttonBack.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
                stage.setScene(new Scene(root, 600, 400));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
    }
}
