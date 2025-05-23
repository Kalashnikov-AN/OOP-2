package zabsu.telephone_sub_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
// Автор: Калашников А.Н.

/**
 * Контроллер окна редактирования записи абонента.
 */
public class editController {
    /// Кнопка сохранения изменений
    @FXML
    private Button save_button;

    /// Поле для ввода лицевого счёта
    @FXML
    private TextField text_an;

    /// Поле для ввода баланса */
    @FXML
    private TextField text_balance;

    /// Поле для ввода ФИО
    @FXML
    private TextField text_name;

    /// Поле для ввода номера телефона
    @FXML
    private TextField text_pn;

    /// Поле для ввода тарифа
    @FXML
    private TextField text_tariff;

    /// Окно диалога редактирования
    private Stage dialogStage;

    /// Ссылка на экземляр модели
    private TelSubDatabase DB;

    /// Поле для хранения редактируемого объекта
    private TelSub selectedSubscriber;

    /**
     * Устанавливает модель и иконку окна
     * @param DB экземпляр модели
     */
    public void setModel(TelSubDatabase DB) {
        this.DB = DB;
    }

    /**
     * Устанавливает окно редактирования и добавляет иконку
     *
     * @param dialogStage устанавливаемое окно редактирования
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        Image icon = new Image(getClass().getResourceAsStream("icons/DB.png"));
        dialogStage.getIcons().add(icon);
    }

    /**
     * Обработчик события нажатия на кнопку "Сохранить":
     * применяет изменения к абоненту, закрывает окно и обновляет таблицу.
     * В случае ошибки при проверке — отображает соответствующее сообщение пользователю.
     */
    @FXML
    private void handleSave(ActionEvent event) {
        try {

            // Обновляем объект новыми значениями
            DB.editSubscriber(selectedSubscriber, text_pn.getText(), text_an.getText(),
                    text_name.getText(), text_tariff.getText(), Double.parseDouble(text_balance.getText()));
            // Закрываем окно
            dialogStage.close();


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

    /**
     * Инициализирует поля формы данными выбранного абонента.
     *
     * @param subscriber объект абонента для редактирования
     */
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
