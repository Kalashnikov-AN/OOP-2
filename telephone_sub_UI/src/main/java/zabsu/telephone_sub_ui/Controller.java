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
    tel_sub sub = new tel_sub(text_pn.getText(), text_an.getText(), text_tariff.getText(), text_name.getText(), Double.parseDouble(text_balance.getText()));

            sub_table.getItems().add(sub); }
        catch(RuntimeException ex){
            System.err.println(ex.getMessage());
            if(ex.getMessage().equals("Придерживайтесь формата +.(...)...-..-..")) {
                text_pn.clear();
                text_pn.setStyle("-fx-prompt-text-fill: red;");
                text_pn.setPromptText(ex.getMessage());
            }
            if(ex.getMessage().equals("Придерживайтесь  шестизначного числового формата")) {
                text_an.clear();
                text_an.setStyle("-fx-prompt-text-fill: red;");
                text_an.setPromptText(ex.getMessage());
            }
            if(ex.getMessage().equals("Выберите тариф из списка тарифов")) {
                text_tariff.clear();
                text_tariff.setStyle("-fx-prompt-text-fill: red;");
                text_tariff.setPromptText(ex.getMessage());
            }
            if(ex.getMessage().equals("Ошибка: неверно введено ФИО")) {
                text_name.clear();
                text_name.setStyle("-fx-prompt-text-fill: red;");
                text_name.setPromptText(ex.getMessage());
            }
            if(ex.getMessage().equals("Ошибка: неверно введена сумма пополнения баланса")) {
                text_balance.clear();
                text_balance.setStyle("-fx-prompt-text-fill: red;");
                text_balance.setPromptText(ex.getMessage());
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
