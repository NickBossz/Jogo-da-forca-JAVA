module com.nick {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.nick to javafx.fxml;
    exports com.nick;
}
