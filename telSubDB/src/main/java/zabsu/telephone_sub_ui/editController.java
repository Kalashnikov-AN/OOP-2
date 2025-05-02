package zabsu.telephone_sub_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    }

    // Метод для сохранения изменений
    @FXML
    private void handleSave(ActionEvent event) {
        try {
           // System.out.println(mainController.data.get(0)); //todo: Убрать задание базовых по типу Name Surname
            // Обновляем объект новыми значениями
//            selectedSubscriber.setName(text_name.getText());
//            selectedSubscriber.setPhone_number(text_pn.getText());
//            selectedSubscriber.setAccount_number(text_an.getText());
//            selectedSubscriber.setTariff(text_tariff.getText());
//            selectedSubscriber.balance = (Double.parseDouble(text_balance.getText()));
            mainController.DB.editSubscriber(selectedSubscriber, text_pn.getText(), text_an.getText(),
                    text_name.getText(), text_tariff.getText(), Double.parseDouble(text_balance.getText()));
            // Закрываем окно
            dialogStage.close();
            mainController.sub_table.refresh();;
           // System.out.println(mainController.data.get(0));

        } catch (Exception e) {
            // Обработка ошибок валидации
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
