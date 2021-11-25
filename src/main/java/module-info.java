module org.project {
    requires javafx.controls;
    requires mongo.java.driver;
    requires java.sql;
    requires org.flywaydb.core;
    requires android.json;
    opens db.migration;
    exports org.project;
}
