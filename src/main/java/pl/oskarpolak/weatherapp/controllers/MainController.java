package pl.oskarpolak.weatherapp.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import pl.oskarpolak.weatherapp.model.WeatherData;
import pl.oskarpolak.weatherapp.service.IWeatherObserver;
import pl.oskarpolak.weatherapp.service.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lenovo on 30.08.2017.
 */
public class MainController implements IWeatherObserver, Initializable{

    @FXML
    JFXButton buttonShow;

    @FXML
    JFXTextField textCity;

    @FXML
    JFXTextArea textInfo;

    @FXML
    ProgressIndicator progressLoad;


    private WeatherService weatherService = WeatherService.getService();



    @Override
    public void onWeatherUpdate(WeatherData data) {
        textInfo.setText("Temp: " + data.getTemp());
        progressLoad.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        registerShowButtonAction();
        registerEnterListener();

    }

    private void registerShowButtonAction() {
        buttonShow.setOnMouseClicked(e -> {
              prepareRequestAndClear();
        });
    }
    private void registerEnterListener() {
        textCity.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                prepareRequestAndClear();
            }
        });
    }

    private void prepareRequestAndClear() {
        progressLoad.setVisible(true);
        weatherService.init(textCity.getText());
        textCity.clear();
    }
}
