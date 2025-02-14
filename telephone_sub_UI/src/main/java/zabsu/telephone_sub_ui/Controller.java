package zabsu.telephone_sub_ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
public class Controller {

    @FXML
    private Button save_button;

    @FXML
    private ComboBox<String> sub_combobox;

    @FXML
    private TableView<?> sub_table;

    @FXML
    void save_buttonOnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ObservableList<String> combolist = FXCollections.observableArrayList("Баланс", "Имя пользователя");
    sub_combobox.setItems(combolist);
    }

}
