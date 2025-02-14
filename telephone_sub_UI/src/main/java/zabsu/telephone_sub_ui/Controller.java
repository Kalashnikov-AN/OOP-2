package zabsu.telephone_sub_ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private Button save_button;

    @FXML
    private ComboBox<String> sub_combobox;

    @FXML
    private TableView<tel_sub> sub_table;

    @FXML
    private TableColumn<tel_sub, String> table_an;

    @FXML
    private TableColumn<tel_sub, Double> table_balance;

    @FXML
    private TableColumn<tel_sub, String> table_name;

    @FXML
    private TableColumn<tel_sub, String> table_pn;

    @FXML
    private TableColumn<tel_sub, String> table_tariff;

    @FXML
    void save_buttonOnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ObservableList<String> combolist = FXCollections.observableArrayList("Баланс", "Имя пользователя");

        table_name.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("name"));
        table_pn.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("phone_number"));
        table_an.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("account_number"));
        table_tariff.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("tariff"));
        table_balance.setCellValueFactory(new PropertyValueFactory<tel_sub, Double>("balance"));

        sub_combobox.setItems(combolist);

        sub_table.getItems().add(new tel_sub());
    }

}
