module zabsu.telephone_sub_ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens zabsu.telephone_sub_ui to javafx.fxml;
    exports zabsu.telephone_sub_ui;
}