module com.project.cryptgame.cryptgame_vi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.project.cryptgame to javafx.fxml;
    exports com.project.cryptgame;
}