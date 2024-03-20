module person.nicholas.crm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires static lombok;
    requires java.sql;

    opens person.nicholas.crm to javafx.fxml;
    exports person.nicholas.crm;
    exports person.nicholas.crm.controller;
    exports person.nicholas.crm.entity;

    opens person.nicholas.crm.entity to javafx.fxml;
    opens person.nicholas.crm.controller to javafx.fxml;
}