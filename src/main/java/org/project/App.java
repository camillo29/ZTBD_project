package org.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.project.Mongo.MongoManager;
import org.project.PostgreSQL.Car;
import org.project.PostgreSQL.CarFactory;
import org.project.PostgreSQL.PostgreSQLManager;
import org.project.UI.UIManager;

import java.util.LinkedList;

public class App extends Application {
    private UIManager uiManager;
    private PostgreSQLManager postgresManager;
    public LinkedList<Car> generateCars(int n){
        CarFactory factory = new CarFactory();
        LinkedList<Car> cars = new LinkedList<>();
        for(int i = 0; i<n; i++){
            cars.add(factory.createCar(i));
        }
        return cars;
    }

    @Override
    public void start(Stage stage) {
        uiManager = new UIManager(900, 600);
        uiManager.setUpConnectionScene();
        uiManager.getConnectButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    postgresManager = new PostgreSQLManager(uiManager.getPostgreDatabase().getText(), uiManager.getPostgreUser().getText(), uiManager.getPostgrePassword().getText());
                    uiManager.closeConnectionScene();
                    uiManager.setUpMainScene();
                    uiManager.addHandlersToCRUDButtons(postgresManager);
                    uiManager.showMainScene();
                    //application();
                } catch(Exception e){
                    e.printStackTrace();
                    //---------------SET UP CONNECTION ERROR TEXT LATER----------------//
                    //System.out.println("it works");
                }
            }
        });
        uiManager.showConnectionScene();
        /*
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
        */
    }

    public static void main(String[] args) {
        launch();
    }

    public void application(){
        //MongoManager mongoManager = new MongoManager("test", "27017");
        //PostgreSQLManager postgresManager = new PostgreSQLManager("ZTBD_projekt", "postgres", "zaq1@WSX");
        int n = 10;
        LinkedList<Car> cars = generateCars(n);
        //INSERT
        double start = System.currentTimeMillis();
        postgresManager.insertCarsInBulk(n, cars);
        double finish = System.currentTimeMillis();
        System.out.println("Time to insert "+n+" records into PostgreSQL = "+(finish-start)/1000 + "s");
        //UPDATE
        cars = generateCars(n);
        start = System.currentTimeMillis();
        postgresManager.updateCarsInBulk(n, cars);
        finish = System.currentTimeMillis();
        System.out.println("Time to update "+n+" records in PostgreSQL = "+(finish-start)/1000 + "s");
        //SELECT
        start = System.currentTimeMillis();
        postgresManager.selectCarsInBulk(n);
        finish = System.currentTimeMillis();
        System.out.println("Time to select "+n+" records from PostgreSQL = "+(finish-start)/1000 + "s");
        //DELETE
        start = System.currentTimeMillis();
        postgresManager.deleteCarsInBulk(n);
        finish = System.currentTimeMillis();
        System.out.println("Time to delete "+n+" records from PostgreSQL = "+(finish-start)/1000 + "s");
    }

}