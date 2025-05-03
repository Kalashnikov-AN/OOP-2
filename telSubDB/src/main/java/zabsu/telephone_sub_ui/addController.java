package zabsu.telephone_sub_ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/// Класс-контроллер окна добавления новых записей в базу данных
public class addController {

    /// Кнопка для добавления нового абонента
    @FXML
    private Button save_button;

    /// Поле ввода лицевого счёта
    @FXML
    private TextField text_an;

    /// Поле ввода баланса абонента
    @FXML
    private TextField text_balance;

    /// Поле ввода ФИО абонента
    @FXML
    private TextField text_name;

    /// Поле ввода номера телефона
    @FXML
    private TextField text_pn;

    /// Поле ввода тарифа
    @FXML
    private TextField text_tariff;

    /// Окно диалога добавления нового абонента
    private Stage dialogStage;

    /// Ссылка на главный контроллер
    private mainController mainController; // Ссылка на главный контроллер

    /**
     * Устанавливает окно добавления записи
     * @param dialogStage окно добавления записи
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Устанавливает главный контроллер и иконку окна
     * @param mainController контроллер главного окна приложения
     */
    public void setMainController(mainController mainController) {
        this.mainController = mainController;
        Image icon = new Image(getClass().getResourceAsStream("icons/DB.png"));
        dialogStage.getIcons().add(icon);
    }

    /// Закрывает окно добавления записи в базу данных
    @FXML
    private void closeDialog() {
        dialogStage.close();
    }

    @FXML
    /// Обработчик события добавления записи при нажатии на кнопку "Добавить"
    void save_buttonOnClick(ActionEvent event){
        try{
            // Создаём объект телефонного абонента через конструктор с параметрами
    TelSub sub = new TelSub(text_pn.getText(), text_an.getText(), text_tariff.getText(), text_name.getText(), Double.parseDouble(text_balance.getText()));
            TableView sub_table = new TableView();
            // Добавляем объект в таблицу
            mainController.DB.addSubscriber(sub); // Вызываем метод главного контроллера
            closeDialog();
        }
        // Обрабатываем исключительные ситуации
        catch(RuntimeException ex){
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
}
