package zabsu.telephone_sub_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class editController {
    @FXML
    private Button save_button;

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

    private Stage dialogStage;

    private mainController mainController; // Ссылка на главный контроллер

    private TelSub selectedSubscriber; // Добавляем поле для хранения редактируемого объекта

    public void setMainController(mainController mainController) {
        this.mainController = mainController;
    }

    // Метод для установки Stage диалога
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        Image icon = new Image(getClass().getResourceAsStream("icons/DB.png"));
        dialogStage.getIcons().add(icon);
    }

    // Метод для сохранения изменений
    @FXML
    private void handleSave(ActionEvent event) {
        try {
            //todo: Убрать задание базовых по типу Name Surname

            // Обновляем объект новыми значениями
            mainController.DB.editSubscriber(selectedSubscriber, text_pn.getText(), text_an.getText(),
                    text_name.getText(), text_tariff.getText(), Double.parseDouble(text_balance.getText()));
            // Закрываем окно
            dialogStage.close();
            mainController.sub_table.refresh();;

        } catch (Exception ex) {
            // Обработка ошибок валидации
            System.err.println(ex.getMessage());
            // Если неправильно введён номер телефона
            if(ex.getMessage().equals("Придерживайтесь формата +.(...)...-..-..")) {
                text_pn.clear();
                text_pn.setStyle("-fx-prompt-text-fill: red;");
                text_pn.setPromptText(ex.getMessage());
            }
            // Если неправильно введён лицевой счёт
            if(ex.getMessage().equals("Придерживайтесь  шестизначного числового формата")) {
                text_an.clear();
                text_an.setStyle("-fx-prompt-text-fill: red;");
                text_an.setPromptText(ex.getMessage());
            }
            // Если неправильно введён тариф
            if(ex.getMessage().equals("Выберите тариф из списка тарифов")) {
                text_tariff.clear();
                text_tariff.setStyle("-fx-prompt-text-fill: red;");
                text_tariff.setPromptText(ex.getMessage());
            }
            // Если неправильно введено ФИО
            if(ex.getMessage().equals("Ошибка: неверно введено ФИО")) {
                text_name.clear();
                text_name.setStyle("-fx-prompt-text-fill: red;");
                text_name.setPromptText(ex.getMessage());
            }
            // Если неправильно введён баланс
            if(ex.getMessage().equals("Ошибка: неверно введена сумма пополнения баланса")) {
                text_balance.clear();
                text_balance.setStyle("-fx-prompt-text-fill: red;");
                text_balance.setPromptText(ex.getMessage());
            }
        }
    }

    void init(TelSub subscriber) {
        this.selectedSubscriber = subscriber;

        // Заполняем поля текущими значениями
        text_name.setText(subscriber.getName());
        text_pn.setText(subscriber.getPhone_number());
        text_an.setText(subscriber.getAccount_number());
        text_tariff.setText(subscriber.getTariff());
        text_balance.setText(String.valueOf(subscriber.getBalance()));

    }

}
