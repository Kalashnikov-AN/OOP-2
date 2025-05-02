

package zabsu.telephone_sub_ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
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
    public TableView<TelSub> sub_table;

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
    public final ObservableList<TelSub> data = FXCollections.observableArrayList();

    public void addSubscriber(TelSub subscriber) {
        data.add(subscriber); // Добавляем данные в ObservableList
    }

    @FXML
    void enterAddWindow(ActionEvent event) throws IOException {
        try{

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
            controller.setMainController(this); // Передаем ссылку на главный контроллер

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
    void onDeleteClick(ActionEvent event) {
        // 1. Получаем выбранный элемент в таблице
        TelSub selectedSubscriber = sub_table.getSelectionModel().getSelectedItem();

        // 2. Проверяем, что строка действительно выбрана
        if (selectedSubscriber == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите абонента для удаления!");
            alert.showAndWait();
            return;
        }

        // 3. Удаляем выбранный элемент из ObservableList
        System.out.println(data.get(0));
        sub_table.getItems().remove(selectedSubscriber); //todo: написать тест, что удаляется именно из массива
        //System.out.println(data.get(0));
        // 4. (Опционально) Показываем подтверждение
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно");
        alert.setHeaderText(null);
        alert.setContentText("Абонент " + selectedSubscriber.getName() + " удалён!");
        alert.showAndWait();
    }

    @FXML
    void onEditClick(ActionEvent event) throws IOException {
        TelSub selected = sub_table.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("editSub.fxml"));
        Parent root = loader.load();

        editController controller = loader.getController();
        controller.init(selected); // Передаем выбранный объект
        controller.setMainController(this);

        Stage stage = new Stage();
        controller.setDialogStage(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

@FXML
void onSaveFile(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Сохранить базу данных");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
            new FileChooser.ExtensionFilter("Все файлы", "*.*")
    );

    // Показываем диалог сохранения
    File file = fileChooser.showSaveDialog(sub_table.getScene().getWindow());

    if (file != null) {
        try {
            files.saveHistory(sub_table.getItems(), file.getAbsolutePath());
            showAlert("Сохранение завершено", "Файл успешно сохранён: " + file.getName());
        } catch (Exception e) {
            showAlert("Ошибка сохранения", "Не удалось сохранить файл: " + e.getMessage());
        }
    }
}

    @FXML
    void onLoadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить базу данных");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        // Показываем диалог открытия
        File file = fileChooser.showOpenDialog(sub_table.getScene().getWindow());

        if (file != null) {
            try {
                sub_table.getItems().clear(); // Очищаем текущие данные
                files.loadHistory(sub_table.getItems(), file.getAbsolutePath());
                showAlert("Загрузка завершена", "Файл успешно загружен: " + file.getName());
            } catch (Exception e) {
                showAlert("Ошибка загрузки", "Не удалось загрузить файл: " + e.getMessage());
            }
        }
    }

    // Вспомогательный метод для показа уведомлений
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        // Связываем столбцы таблицы с соответствующими полями класса
        table_name.setCellValueFactory(new PropertyValueFactory<TelSub, String>("name"));
        table_pn.setCellValueFactory(new PropertyValueFactory<TelSub, String>("phone_number"));
        table_an.setCellValueFactory(new PropertyValueFactory<TelSub, String>("account_number"));
        table_tariff.setCellValueFactory(new PropertyValueFactory<TelSub, String>("tariff"));
        table_balance.setCellValueFactory(new PropertyValueFactory<TelSub, Double>("balance"));
        sub_table.setItems(data);

    }

}
