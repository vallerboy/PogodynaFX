package pl.oskarpolak.weatherapp.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import pl.oskarpolak.weatherapp.model.WeatherData;
import pl.oskarpolak.weatherapp.model.WeatherStat;
import pl.oskarpolak.weatherapp.model.dao.WeatherStatDao;
import pl.oskarpolak.weatherapp.model.dao.impl.WeatherStatDaoImpl;
import pl.oskarpolak.weatherapp.model.service.IWeatherObserver;
import pl.oskarpolak.weatherapp.model.service.WeatherService;
import pl.oskarpolak.weatherapp.model.utils.DatabaseConnector;

import java.io.IOException;
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

    @FXML
    JFXButton buttonStats;

    private String lastCityName;



    private WeatherService weatherService = WeatherService.getService();
    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
    private WeatherStatDao weatherStatDao = new WeatherStatDaoImpl();



    @Override
    public void onWeatherUpdate(WeatherData data) {
        weatherStatDao.saveStat(new WeatherStat(lastCityName, (float) data.getTemp()));
        textInfo.setText("Temp: " + data.getTemp());
        progressLoad.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        registerShowButtonAction();
        registerEnterListener();
        registerButtonStatsAction();
    }

    private void registerButtonStatsAction() {
        buttonStats.setOnMouseClicked(e -> {
             Stage stage = (Stage) buttonStats.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stats.fxml"));
                stage.setScene(new Scene(root, 600, 400));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
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
        lastCityName = textCity.getText();
        progressLoad.setVisible(true);
        weatherService.init(textCity.getText());
        textCity.clear();
    }
}
