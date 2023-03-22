package com.example.task_4;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
public class HelloController implements Initializable {
    public TextField filterField;

    public TableView<Passenger> tableview;
    public TableColumn<Passenger, String> fname;
    public TableColumn<Passenger, String> sname;
    public TableColumn<Passenger, String> vehicleNo;
    public TableColumn<Passenger, Integer> NoLiters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        sname.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        vehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        NoLiters.setCellValueFactory(new PropertyValueFactory<>("liters"));
        tableview.setItems(HelloApplication.q1.getPassenger());
    }

}
