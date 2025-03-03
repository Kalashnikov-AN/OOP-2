module zabsu.telephone_sub_ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens zabsu.telephone_sub_ui to javafx.fxml;
    exports zabsu.telephone_sub_ui;
}