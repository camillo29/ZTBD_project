package org.project.PostgreSQL;

import org.flywaydb.core.Flyway;
import org.project.Exceptions.ErrorException;
import org.project.Factories.*;
import org.project.Models.*;
import org.project.PostgreSQL.CRUD.DeleteManager;
import org.project.PostgreSQL.CRUD.InsertManager;
import org.project.PostgreSQL.CRUD.SelectManager;
import org.project.PostgreSQL.CRUD.UpdateManager;

import java.util.LinkedList;
import java.util.Properties;
import java.sql.*;

public class PostgreSQLManager {
    private String dataBaseURL = "jdbc:postgresql://localhost/";
    private Properties props;
    private Connection conn;
    //Factories
    private DishFactory dishFactory;
    private OfficeFactory officeFactory;
    private PersonFactory personFactory;
    private UserFactory userFactory;
    private OrderFactory orderFactory;

    //CRUD
    private SelectManager selectManager;
    private InsertManager insertManager;
    private UpdateManager updateManager;
    private DeleteManager deleteManager;

    public PostgreSQLManager(final String dataBaseName, final String user, final String password) throws ErrorException{
        dataBaseURL += dataBaseName;
        props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        Flyway flyway =
                Flyway.configure()
                        .dataSource( "jdbc:postgresql://localhost/"+dataBaseName , user , password )  // (url, user, password)
                        .locations("db/migration")
                        .load();                                                           // Returns a `Flyway` object.
        flyway.migrate();
        try {
            conn = DriverManager.getConnection(dataBaseURL, props);
            System.out.println("PostgreSQL successfully migrated and connected to " + conn.getCatalog());
        } catch(SQLException e){
            e.printStackTrace();
            throw new ErrorException("PostgreSQL connection error!");
        }
        dishFactory = new DishFactory();
        officeFactory = new OfficeFactory();
        personFactory = new PersonFactory();
        userFactory = new UserFactory();
        orderFactory = new OrderFactory();
        selectManager = new SelectManager(conn);
        insertManager = new InsertManager(conn);
        updateManager = new UpdateManager(conn);
        deleteManager = new DeleteManager(conn);
    }

    public double insertInBulk(int n,
                               LinkedList<Dish> dishes,
                               LinkedList<Office> offices,
                               LinkedList<Person> people,
                               LinkedList<User> users,
                               LinkedList<Order> orders){
        double start = System.currentTimeMillis();
        insertManager.insertInBulk(n, dishes, offices, people, users, orders);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }
    public double deleteInBulk(int n){
        double start = System.currentTimeMillis();
        deleteManager.deleteInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }
    public double updateInBulk(int n, LinkedList<Dish> dishes){
        double start = System.currentTimeMillis();
        updateManager.updateInBulk(n, dishes);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }
    public double selectInBulk(int n){
        double start = System.currentTimeMillis();
        selectManager.selectAllWithLimit(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public DishFactory getDishFactory() {
        return dishFactory;
    }

    public OfficeFactory getOfficeFactory() {
        return officeFactory;
    }

    public PersonFactory getPersonFactory() {
        return personFactory;
    }

    public UserFactory getUserFactory() {
        return userFactory;
    }

    public OrderFactory getOrderFactory() {
        return orderFactory;
    }

    public Connection getConn() {
        return conn;
    }
}
