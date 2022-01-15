package org.project.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.project.Models.*;
import org.project.Mongo.MongoManager;
import org.project.Mongo.RowCountFetcher;
import org.project.PostgreSQL.PostgreSQLManager;

import java.util.HashMap;
import java.util.LinkedList;

public class UIManager {
    private int width = 0, height = 0;
    //CONNECTION SCENE
    Stage connectionStage;
    private TextField postgreUser;
    private TextField postgrePassword;
    private TextField postgreDatabase;
    private TextField mongoUser;
    private TextField mongoPassword;
    private TextField mongoOneDB;
    private TextField mongoMultipleDB;
    private Text connectionError;
    private Button connectButton;
    //MAIN SCENE
    Stage primaryStage;
    private TextField quantity;
    private Button createButton;
    private Button readButton;
    private Button updateButton;
    private Button deleteButton;
    private Text postgresTime;
    private Text mongoTime;
    private Text discount_typeCount,
            dishesCount,
            officesCount,
            ordersCount,
            peopleCount,
            rolesCount,
            usersCount,
            documentsInOneCollection,
            orders_dishesCount;


    private RowCountFetcher countFetcher;

    public UIManager(final int width, final int height){
        this.width = width;
        this.height = height;
    }

    public void setUpConnectionScene(){
        connectionStage = new Stage();
        Pane pane = new Pane();
        Text description = createText("Provide connection details to databases", 30, 50);
        Text postgreDesc = createText("PostgreSQL", 30, 80);
        postgreUser = createTextField("User", 20, 100, width-50, 20);
        postgrePassword = createTextField("Password", 20, 150, width-50, 20);
        postgreDatabase = createTextField("Database name", 20, 200, width-50, 20);
        Text mongoDesc = createText("MongoDB", 30, 300);
        mongoUser = createTextField("User", 20, 330, width-50, 20);
        mongoPassword = createTextField("Password", 20, 380, width-50, 20);
        mongoOneDB = createTextField("One collection database name", 20, 430, width-50, 20);
        mongoMultipleDB = createTextField("Multiple collection database name", 20, 480, width-50, 20);
        //Debug
        mongoOneDB.setText("ZTBD_projekt_mongoOneCollection");
        mongoMultipleDB.setText("ZTBD_projekt_mongoMultipleCollections");
        //
        connectButton = createButton("Connect", width/2-50, height-50, 100, 30);
        connectionError = createText("", width/2-70, 530);
        connectionError.setFill(Color.RED);
        pane.getChildren().addAll(description,
                postgreDesc, postgreUser, postgrePassword, postgreDatabase,
                mongoDesc, mongoUser, mongoPassword, mongoOneDB, mongoMultipleDB,
                connectButton, connectionError);
        connectionStage.setScene(new Scene(pane, width, height));
    }

    public void showConnectionScene(){
        connectionStage.show();
    }

    public void closeConnectionScene(){
        connectionStage.close();
    }

    public void showConnectionError(String content){
        connectionError.setText(content);
    }

    public void setUpMainScene(){
        primaryStage = new Stage();
        Pane pane = new Pane();
        Text quantityDescription = createText("Quantity of operations", 20, 70);
        quantity = createTextField("Quantity", 20, 100, 100, 20);
        Text operationsDescription = createText("Operations", 20, 170);
        createButton = createButton("CREATE", 20, 200, 100, 30);
        readButton = createButton("READ", 20, 250, 100, 30);
        updateButton = createButton("UPDATE", 20, 300, 100, 30);
        deleteButton = createButton("DELETE", 20, 350, 100, 30);
        Text timeDesc = createText("Completion time", 400, 170);
        Text postgresText = createText("PostgreSQL", 250, 220);
        Text mongoText = createText("MongoDB", 600, 220);
        postgresTime = createText("", 250, 270);
        mongoTime = createText("", 600, 270);
        Text count = createText("ROW COUNT", 20, 400);
        discount_typeCount = createText("", 20, 420);
        dishesCount = createText("", 20, 440);
        orders_dishesCount = createText("", 20, 460);
        ordersCount = createText("", 20, 480);
        officesCount = createText("", 20, 500);
        peopleCount = createText("", 20, 520);
        rolesCount = createText("", 20, 540);
        usersCount = createText("", 20, 560);
        documentsInOneCollection = createText("", 20, 580);

        updateCount(countFetcher.getCounts());
        pane.getChildren().addAll(quantityDescription, quantity, operationsDescription, createButton, readButton, updateButton, deleteButton,
                timeDesc, postgresText, mongoText, postgresTime, mongoTime,
                count, discount_typeCount, dishesCount, officesCount, ordersCount, peopleCount, rolesCount, usersCount, documentsInOneCollection,
                orders_dishesCount);
        primaryStage.setScene(new Scene(pane, width, height));
    }

    public void showMainScene(){
        primaryStage.show();
    }

    public void addHandlersToCRUDButtons(PostgreSQLManager postgres, MongoManager oneCollectionManager, MongoManager multipleCollectionsManager){
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LinkedList<Dish> dishes = postgres.getDishFactory().createDishesInBulk(Integer.parseInt(quantity.getText()));
                LinkedList<Office> offices = postgres.getOfficeFactory().createOfficesInBulk(Integer.parseInt(quantity.getText()));
                LinkedList<Person> people = postgres.getPersonFactory().createPeopleInBulk(Integer.parseInt(quantity.getText()));
                LinkedList<User> users = postgres.getUserFactory().createUsersInBulk(Integer.parseInt(quantity.getText()));
                LinkedList<Order> orders = postgres.getOrderFactory().createOrdersInBulk(Integer.parseInt(quantity.getText()));

                double timeNeededPostgres = postgres.insertInBulk(Integer.parseInt(quantity.getText()),
                        dishes,
                        offices,
                        people,
                        users,
                        orders);

                double timeNeededOneCollection = oneCollectionManager.oneCollectionInsertInBulk(Integer.parseInt(quantity.getText()),
                        dishes,
                        offices,
                        people,
                        users,
                        orders);

                double timeNeededMultipleCollections = multipleCollectionsManager.multipleCollectionsInsertInBulk(Integer.parseInt(quantity.getText()),
                        dishes,
                        offices,
                        people,
                        users,
                        orders);

                System.out.println("Time to insert "+quantity.getText()+" records into PostgreSQL = "+timeNeededPostgres + "s");
                System.out.println("Time to insert "+quantity.getText()+" records into MongoOneCollection = "+timeNeededOneCollection + "s");
                System.out.println("Time to insert "+quantity.getText()+" records into MongoMultipleCollections = "+timeNeededMultipleCollections + "s");

                setPostgresTimeText(timeNeededPostgres + " s");
                setMongoTimeText("One collection: "+timeNeededOneCollection + " s\nMultiple collections: "+timeNeededMultipleCollections + " s");
                updateCount(countFetcher.getCounts());
            }
        });

        readButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double timeNeededPostgres = postgres.selectInBulk(Integer.parseInt(quantity.getText()));

                double timeNeededOneCollection = oneCollectionManager.oneCollectionFindInBulk(Integer.parseInt(quantity.getText()));

                double timeNeededMultipleCollections = multipleCollectionsManager.multipleCollectionsFindInBulk(Integer.parseInt(quantity.getText()));

                System.out.println("Time to select "+quantity.getText()+" records from PostgreSQL = "+timeNeededPostgres + "s");
                System.out.println("Time to select "+quantity.getText()+" records from MongoDBOneCollection = "+timeNeededOneCollection + "s");
                System.out.println("Time to select "+quantity.getText()+" records from MongoDBOneCollection = "+timeNeededMultipleCollections + "s");
                setPostgresTimeText(timeNeededPostgres + " s");
                setMongoTimeText("One collection: "+timeNeededOneCollection + " s\nMultiple collections: "+timeNeededMultipleCollections + " s");
            }
        });

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LinkedList<Dish> dishes = postgres.getDishFactory().createDishesInBulk(Integer.parseInt(quantity.getText()));
                double timeNeededPostgres = postgres.updateInBulk(Integer.parseInt(quantity.getText()),
                        dishes);

                double timeNeededOneCollection = oneCollectionManager.oneCollectionUpdateInBulk(Integer.parseInt(quantity.getText()),
                        dishes);

                double timeNeededMultipleCollections = multipleCollectionsManager.multipleCollectionsUpdateInBulk(Integer.parseInt(quantity.getText()),
                        dishes);

                System.out.println("Time to update "+quantity.getText()+" records in PostgreSQL = "+timeNeededPostgres + "s");
                System.out.println("Time to update "+quantity.getText()+" records in MongoDBOneCollection = "+timeNeededOneCollection + "s");
                System.out.println("Time to update "+quantity.getText()+" records in MongoDBMultipleCollections = "+timeNeededMultipleCollections + "s");
                setPostgresTimeText(timeNeededPostgres + " s");
                setMongoTimeText(timeNeededOneCollection + " s");
                setMongoTimeText("One collection: "+timeNeededOneCollection + " s\nMultiple collections: "+timeNeededMultipleCollections + " s");
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double timeNeededPostgres = postgres.deleteInBulk(Integer.parseInt(quantity.getText()));

                double timeNeededOneCollection = oneCollectionManager.oneCollectionDeleteInBulk(Integer.parseInt(quantity.getText()));

                double timeNeededMultipleCollections = multipleCollectionsManager.multipleCollectionsDeleteInBulk(Integer.parseInt(quantity.getText()));

                System.out.println("Time to delete "+quantity.getText()+" records from PostgreSQL = "+timeNeededPostgres + "s");
                System.out.println("Time to delete "+quantity.getText()+" records from MongoDBOneCollection = "+timeNeededOneCollection + "s");
                System.out.println("Time to delete "+quantity.getText()+" records from MongoDBMultipleCollections = "+timeNeededMultipleCollections + "s");
                setPostgresTimeText(timeNeededPostgres + " s");
                setMongoTimeText("One collection: "+timeNeededOneCollection + " s\nMultiple collections: "+timeNeededMultipleCollections + " s");
                updateCount(countFetcher.getCounts());
            }
        });
    }

    public void updateCount(HashMap<String, Long> counts){
        discount_typeCount.setText("Discount_type = "+counts.get("discount_type"));
        dishesCount.setText("Dishes = "+counts.get("dishes"));
        orders_dishesCount.setText("Order-Dish(Only PGSQL) = "+counts.get("order_dish"));
        ordersCount.setText("Orders = "+counts.get("orders"));
        officesCount.setText("Offices = "+counts.get("offices"));
        peopleCount.setText("People = "+counts.get("people"));
        rolesCount.setText("Roles = "+counts.get("roles"));
        usersCount.setText("Users = "+counts.get("users"));
        documentsInOneCollection.setText("OneCollection = "+counts.get("oneCollection"));
    }

    public Text createText(String content, int x, int y){
        Text text = new Text();
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setX(x);
        text.setY(y);
        text.setText(content);
        return text;
    }

    public TextField createTextField(String content, int layoutX, int layoutY, int width, int height) {
        TextField textField = new TextField();
        textField.setPromptText(content);
        textField.setLayoutX(layoutX);
        textField.setLayoutY(layoutY);
        textField.setPrefSize(width, height);
        return textField;
    }

    public Button createButton(String title, int layoutX, int layoutY, int width, int height){
        Button button = new Button(title);
        button.setLayoutX(layoutX); button.setLayoutY(layoutY);
        button.setPrefSize(width, height);
        return button;
    }

    public void setPostgresTimeText(String content){
        postgresTime.setText(content);
    }
    public void setMongoTimeText(String content){
        mongoTime.setText(content);
    }

    public TextField getPostgreUser() {
        return postgreUser;
    }

    public void setPostgreUser(TextField postgreUser) {
        this.postgreUser = postgreUser;
    }

    public TextField getPostgrePassword() {
        return postgrePassword;
    }

    public void setPostgrePassword(TextField postgrePassword) {
        this.postgrePassword = postgrePassword;
    }

    public TextField getPostgreDatabase() {
        return postgreDatabase;
    }

    public void setPostgreDatabase(TextField postgreDatabase) {
        this.postgreDatabase = postgreDatabase;
    }

    public Button getConnectButton() {
        return connectButton;
    }

    public void setConnectButton(Button connectButton) {
        this.connectButton = connectButton;
    }

    public TextField getMongoUser() {
        return mongoUser;
    }

    public void setMongoUser(TextField mongoUser) {
        this.mongoUser = mongoUser;
    }

    public TextField getMongoPassword() {
        return mongoPassword;
    }

    public void setMongoPassword(TextField mongoPassword) {
        this.mongoPassword = mongoPassword;
    }

    public TextField getMongoOneDB() {
        return mongoOneDB;
    }

    public TextField getMongoMultipleDB() {
        return mongoMultipleDB;
    }

    public void setCountFetcher(RowCountFetcher countFetcher) {
        this.countFetcher = countFetcher;
    }
}
