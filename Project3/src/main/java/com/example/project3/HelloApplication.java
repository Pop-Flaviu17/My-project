package com.example.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 509, 675);
        stage.setTitle("Internet Banking");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("Bank", "postgres", "gardevoir17");
        //db.insert_row(conn,"login","flaviu07", "1234");
        //db.read_data(conn, "account");
        launch();
    }
}