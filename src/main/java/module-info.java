module pl.kornet.netnote_dwa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;

    opens pl.kornet.netnote_dwa to javafx.fxml;
    exports pl.kornet.netnote_dwa;
}