

package zabsu.telephone_sub_ui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/// Класс-контроллер
public class mainController {

    @FXML
    private Button addTool;

    @FXML
    private Button deleteTool;

    @FXML
    private Button editTool;

    @FXML
    private MenuItem menuAbout;

    @FXML
    private MenuItem menuAdd;

    @FXML
    private MenuItem menuAuthor;

    @FXML
    private MenuBar menuDB;

    @FXML
    private MenuItem menuDelete;

    @FXML
    private MenuItem menuEdit;

    @FXML
    private MenuItem menuOpen;

    @FXML
    private MenuItem menuSave;

    @FXML
    private MenuItem menuSearch;

    @FXML
    /// Кнопка "Добавить" - добавляет записи в таблицу
    private Button save_button;

    @FXML
    /// Таблица с записями телефонных абонентов
    private TableView<TelSub> sub_table;

    @FXML
    /// Столбец - лицевой счёт
    private TableColumn<TelSub, String> table_an;

    @FXML
    /// Столбец - баланс
    private TableColumn<TelSub, Double> table_balance;

    @FXML
    /// Столбец - ФИО
    private TableColumn<TelSub, String> table_name;

    @FXML
    /// Столбец - номер телефона
    private TableColumn<TelSub, String> table_pn;

    @FXML
    /// Столбец - тариф абонента
    private TableColumn<TelSub, String> table_tariff;

    @FXML
    /// Поле для ввода лицевого счёта
    private TextField text_an;

    @FXML
    /// Поле для ввода баланса
    private TextField text_balance;

    @FXML
    /// Поле для ввода ФИО
    private TextField text_name;

    @FXML
    /// Поле для ввода номера телефона
    private TextField text_pn;

    @FXML
    /// Поле для ввода тарифа
    private TextField text_tariff;

    @FXML
    void enterAddWindow(ActionEvent event) throws IOException {
        try{
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        // Загружаем FXML для главного окна чат-бота
//        FXMLLoader fxmlLoader = new FXMLLoader(TelSubApplication.class.getResource("addSub.fxml"));
//        // Создаём экземпляр ChatController и заранее устанавливаем имя пользователя
//        addController add_contr = new addController();
//        // Программно задаём контроллер для FXML
//        fxmlLoader.setController(add_contr);
//        Scene scene = new Scene(fxmlLoader.load(), 1024, 724);
//        // Подключаем CSS-стили
//        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
//        // Устанавливаем новую сцену и отображаем её
//        stage.setScene(scene);
//        stage.show();
            // 1. Загрузка FXML для диалога
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/zabsu/telephone_sub_ui/addSub.fxml"));
            DialogPane dialogPane = loader.load();

            Stage mainStage = (Stage) sub_table.getScene().getWindow();

            // 2. Создание нового окна (Stage)
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить абонента");

            // 3. Настройка модальности
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(mainStage); // Родительское окно
           // dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow()); // Родительское окно

            // 4. Передача Stage в контроллер диалога
            addController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // 5. Создание сцены и отображение окна
            Scene scene = new Scene(dialogPane);
            dialogStage.setScene(scene);
            dialogStage.showAndWait(); // Блокирует основное окно
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    /// Функция добавления записей при нажатии на кнопку "Добавить"
    void save_buttonOnClick(ActionEvent event){
        try{
            // Создаём объект телефонного абонента через конструктор с параметрами
    TelSub sub = new TelSub(text_pn.getText(), text_an.getText(), text_tariff.getText(), text_name.getText(), Double.parseDouble(text_balance.getText()));
            // Добавляем объект в таблицу
            sub_table.getItems().add(sub); }
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


    @FXML
    void initialize() {
        // Связываем столбцы таблицы с соответствующими полями класса
        table_name.setCellValueFactory(new PropertyValueFactory<TelSub, String>("name"));
        table_pn.setCellValueFactory(new PropertyValueFactory<TelSub, String>("phone_number"));
        table_an.setCellValueFactory(new PropertyValueFactory<TelSub, String>("account_number"));
        table_tariff.setCellValueFactory(new PropertyValueFactory<TelSub, String>("tariff"));
        table_balance.setCellValueFactory(new PropertyValueFactory<TelSub, Double>("balance"));

    }

}
