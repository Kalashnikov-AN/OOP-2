package zabsu.telephone_sub_ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button save_button;

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
    private TextField text_an;

    @FXML
    private TextField text_balance;

    @FXML
    private TextField text_name;

    @FXML
    private TextField text_pn;

    @FXML
    private TextField text_tariff;

    @FXML
    void save_buttonOnClick(ActionEvent event){
        try{
    tel_sub sub = new tel_sub(text_pn.getText(), text_an.getText(), text_tariff.getText(), text_name.getText(), Double.parseDouble(text_balance.getText()),
            text_pn, text_an, text_name, text_tariff, text_balance);

            sub_table.getItems().add(sub); }
        catch(RuntimeException ex){
            System.err.println(ex.getMessage());
            if(ex.getMessage().equals("Придерживайтесь формата +.(...)...-..-..")) {
                text_pn.clear();
                text_pn.setStyle("-fx-prompt-text-fill: red;");
                text_pn.setPromptText(ex.getMessage());
            }
        }

    }

    @FXML
    void initialize() {

        table_name.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("name"));
        table_pn.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("phone_number"));
        table_an.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("account_number"));
        table_tariff.setCellValueFactory(new PropertyValueFactory<tel_sub, String>("tariff"));
        table_balance.setCellValueFactory(new PropertyValueFactory<tel_sub, Double>("balance"));


       // sub_table.getItems().add(new tel_sub());
    }

}
