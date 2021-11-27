package org.project.PostgreSQL;

import org.flywaydb.core.Flyway;
import org.project.Factories.DishFactory;
import org.project.Factories.OfficeFactory;
import org.project.Models.Dish;
import org.project.Models.Office;
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
    private DishFactory dishFactory;
    private OfficeFactory officeFactory;
    private SelectManager selectManager;
    private InsertManager insertManager;
    private UpdateManager updateManager;
    private DeleteManager deleteManager;

    public PostgreSQLManager(final String dataBaseName, final String user, final String password){
        dataBaseURL += dataBaseName;
        props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        Flyway flyway =
                Flyway.configure()
                        .dataSource( "jdbc:postgresql://localhost/ZTBD_projekt" , user , password )  // (url, user, password)
                        .locations("db/migration")
                        .load();                                                           // Returns a `Flyway` object.
        flyway.migrate();
        try {
            conn = DriverManager.getConnection(dataBaseURL, props);
            System.out.println("PostgreSQL successfully migrated and connected to " + conn.getCatalog());
        } catch(SQLException e){
            e.printStackTrace();
        }
        dishFactory = new DishFactory();
        officeFactory = new OfficeFactory();
        selectManager = new SelectManager(conn);
        insertManager = new InsertManager(conn);
        updateManager = new UpdateManager(conn);
        deleteManager = new DeleteManager(conn);
    }

    public void selectCars(){
        selectManager.selectAll();
    }

    public double insertInBulk(int n,
                               LinkedList<Dish> dishes,
                               LinkedList<Office> offices){
        double start = System.currentTimeMillis();
        insertManager.insertInBulk(n, dishes, offices);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }
    public double deleteCarsInBulk(int n){
        double start = System.currentTimeMillis();
        deleteManager.deleteInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }
    public double updateCarsInBulk(int n, LinkedList<Dish> dishes){
        double start = System.currentTimeMillis();
        updateManager.updateInBulk(n, dishes);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }
    public double selectCarsInBulk(int n){
        double start = System.currentTimeMillis();
        selectManager.selectCarsInBulk(n);
        double finish = System.currentTimeMillis();
        return (finish-start)/1000;
    }

    public DishFactory getDishFactory() {
        return dishFactory;
    }

    public OfficeFactory getOfficeFactory() {
        return officeFactory;
    }
}
