package org.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.project.Mongo.MongoManager;
import org.project.PostgreSQL.PostgreSQLManager;
import org.project.UI.UIManager;

import java.util.LinkedList;

public class App extends Application {
    private UIManager uiManager;
    private PostgreSQLManager postgresManager;
    private MongoManager mongoOneCollectionManager;

    @Override
    public void start(Stage stage) {
        uiManager = new UIManager(900, 600);
        uiManager.setUpConnectionScene();
        uiManager.getConnectButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    postgresManager = new PostgreSQLManager(uiManager.getPostgreDatabase().getText(), uiManager.getPostgreUser().getText(), uiManager.getPostgrePassword().getText());
                    mongoOneCollectionManager = new MongoManager("ZTBD_projekt_mongoOneCollection", "27017", "FoodDelivery");

                    uiManager.closeConnectionScene();
                    uiManager.setUpMainScene();
                    uiManager.addHandlersToCRUDButtons(postgresManager, mongoOneCollectionManager);
                    uiManager.showMainScene();
                } catch(Exception e){
                    e.printStackTrace();
                    //---------------SET UP CONNECTION ERROR TEXT LATER----------------//
                    //System.out.println("it works");
                }
            }
        });
        uiManager.showConnectionScene();
    }

    public static void main(String[] args) {
        launch();
    }

}