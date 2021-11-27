package org.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.project.PostgreSQL.PostgreSQLManager;
import org.project.UI.UIManager;

import java.util.LinkedList;

public class App extends Application {
    private UIManager uiManager;
    private PostgreSQLManager postgresManager;

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

}