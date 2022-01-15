package org.project;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoSecurityException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.project.Exceptions.ErrorException;
import org.project.Mongo.MongoManager;
import org.project.Mongo.RowCountFetcher;
import org.project.PostgreSQL.PostgreSQLManager;
import org.project.UI.UIManager;

import java.sql.SQLException;
import java.util.LinkedList;

public class App extends Application {
    private UIManager uiManager;
    private PostgreSQLManager postgresManager;
    private MongoManager mongoOneCollectionManager;
    private MongoManager mongoMultipleCollectionsManager;

    @Override
    public void start(Stage stage) {
        uiManager = new UIManager(900, 600);
        uiManager.setUpConnectionScene();
        uiManager.getConnectButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    postgresManager = new PostgreSQLManager(
                            uiManager.getPostgreDatabase().getText(),
                            uiManager.getPostgreUser().getText(),
                            uiManager.getPostgrePassword().getText());
                    mongoOneCollectionManager = new MongoManager(
                            uiManager.getMongoOneDB().getText(),
                            "27017",
                            "FoodDelivery",
                            uiManager.getMongoUser().getText(),
                            uiManager.getMongoPassword().getText());
                    mongoMultipleCollectionsManager = new MongoManager(
                            uiManager.getMongoMultipleDB().getText(),
                            "27017",
                            uiManager.getMongoUser().getText(),
                            uiManager.getMongoPassword().getText());
                    uiManager.closeConnectionScene();
                    uiManager.setCountFetcher(new RowCountFetcher(mongoOneCollectionManager, mongoMultipleCollectionsManager, postgresManager.getConn()));
                    uiManager.setUpMainScene();
                    uiManager.addHandlersToCRUDButtons(postgresManager, mongoOneCollectionManager, mongoMultipleCollectionsManager);
                    uiManager.showMainScene();
                }catch(ErrorException e) {
                    uiManager.showConnectionError(e.getMessage());
                }catch(Exception e) {
                    e.printStackTrace();
                    uiManager.showConnectionError("Connection error!");
                }
            }
        });
        uiManager.showConnectionScene();
    }

    public static void main(String[] args) {
        launch();
    }

}