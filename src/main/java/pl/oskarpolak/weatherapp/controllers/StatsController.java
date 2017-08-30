package pl.oskarpolak.weatherapp.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Pogoda");
        series1.getData().add(new XYChart.Data("Warszawa", 20));
        series1.getData().add(new XYChart.Data("Kraków", 34.4));
        series1.getData().add(new XYChart.Data("Wrocław", 13));

        chartWeather.getData().add(series1);
        registerButtonStatsAction();

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
