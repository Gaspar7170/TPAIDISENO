module com.example.tpaidiseno {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens com.example.tpaidiseno to javafx.fxml;
   // opens com.example.tpaidiseno.Pantallas to javafx.fxml;

    exports com.example.tpaidiseno;
    //exports com.example.tpaidiseno.Pantallas;
}
