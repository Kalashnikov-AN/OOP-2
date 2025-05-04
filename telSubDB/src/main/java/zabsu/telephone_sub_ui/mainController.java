package zabsu.telephone_sub_ui;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

/// Класс-контроллер главного окна
public class mainController {

    @FXML
    /// Кнопка для добавления записи
    private Button addTool;

    @FXML
    /// Кнопка для удаления записи
    private Button deleteTool;

    @FXML
    /// Кнопка для редактирования записи
    private Button editTool;

    @FXML
    /// Получение справки о программе через меню
    private MenuItem menuAbout;

    @FXML
    /// Добавление записи в базу данных через меню
    private MenuItem menuAdd;

    @FXML
    /// Получение информации аб авторе через меню
    private MenuItem menuAuthor;

    @FXML
    /// Меню базы данных
    private MenuBar menuDB;

    @FXML
    /// Удаление записи из базы данных через меню
    private MenuItem menuDelete;

    @FXML
    /// Редактирование записи в базе данных через меню
    private MenuItem menuEdit;

    @FXML
    /// Загрузка базы данных из файла через меню
    private MenuItem menuOpen;

    @FXML
    /// Сохранение базы данных в файл через меню
    private MenuItem menuSave;

    @FXML
    /// Меню
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
    /// Поле для ввода поискового запроса
    private TextField searchField;

    @FXML
    /// Выбор фильтра для поиска
    private ComboBox<String> searchCategory;

    /// Объект класса базы данных
    public TelSubDatabase DB;

    /**
     * Обработчик события открытия окна добавления нового абонента
     * @throws IOException если не удалось загрузить FXML-файл окна
     */
    @FXML
    void enterAddWindow(ActionEvent event) throws IOException {
        try{

            // Загрузка FXML для окна добавления записи
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/zabsu/telephone_sub_ui/addSub.fxml"));
            DialogPane dialogPane = loader.load();

            Stage mainStage = (Stage) sub_table.getScene().getWindow(); // получаем ссылку на главное окно

            // Создание нового окна
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить абонента");

            // Настройка модальности
            dialogStage.initModality(Modality.APPLICATION_MODAL); // Блокируем взаимодействие с другими окнами
            dialogStage.initOwner(mainStage); // Родительское окно

            // Передача Stage в контроллер добавления записи
            addController controller = loader.getController();
            controller.setDialogStage(dialogStage); // Передаём созданное окно в контроллер добавления записи
            controller.setMainController(this); // Передаем ссылку на главный контроллер

            // Создание сцены и отображение окна
            Scene scene = new Scene(dialogPane);
            dialogStage.setScene(scene);
            dialogStage.showAndWait(); // Блокирует основное окно
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Обработчик события удаления выбранного абонента из базы данных
     */
    @FXML
    void onDeleteClick(ActionEvent event) {
        // Получаем выбранный элемент в таблице
        TelSub selectedSubscriber = sub_table.getSelectionModel().getSelectedItem();

        // Удаляем выбранный элемент из ObservableList
        DB.deleteSubscriber(selectedSubscriber);
    }

    /**
     * Обработчик события открытия окна редактирования выбранного абонента
     * @throws IOException если не удалось загрузить FXML-файл окна
     */
    @FXML
    void onEditClick(ActionEvent event) throws IOException {
        // Получаем выбранный элемент в таблице
        TelSub selected = sub_table.getSelectionModel().getSelectedItem();

        Stage mainStage = (Stage) sub_table.getScene().getWindow(); // получаем ссылку на главное окно

        // Загрузка FXML для окна редактирования записи
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editSub.fxml"));
        Parent root = loader.load();

        editController controller = loader.getController(); // Создаём контроллер окна редактирования записи
        controller.init(selected); // Передаем выбранный объект
        controller.setMainController(this); // Передаем ссылку на главный контроллер

        Stage stage = new Stage(); // Создание нового окна
        controller.setDialogStage(stage); // Передаём созданное окно в контроллер редактирования записи

        stage.initModality(Modality.APPLICATION_MODAL); // Блокируем взаимодействие с другими окнами
        stage.initOwner(mainStage); // Родительское окно

        stage.setScene(new Scene(root));
        stage.showAndWait(); // Блокирует основное окно
    }

    /**
     * Обработчик события сохранения текущей базы данных в файл
     */
@FXML
void onSaveFile(ActionEvent event) {
    FileChooser fileChooser = new FileChooser(); // Системный диалог выбора файла
    fileChooser.setTitle("Сохранить базу данных");

    fileChooser.getExtensionFilters().addAll( // Ограничивает выбор файлов по расширению
            new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
            new FileChooser.ExtensionFilter("Все файлы", "*.*")
    );

    // Показываем модальное окно диалога сохранения
    // передаём как параметр родительское окно
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

    /**
     * Обработчик события загрузки базы данных из выбранного файла
     */
    @FXML
    void onLoadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser(); // Системный диалог выбора файла
        fileChooser.setTitle("Загрузить базу данных");

        fileChooser.getExtensionFilters().addAll( // Ограничивает выбор файлов по расширению
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        // Показываем модальное окно диалога открытия файла
        // передаём как параметр родительское окно
        File file = fileChooser.showOpenDialog(sub_table.getScene().getWindow());

        sub_table.setItems(DB.data); // если перед этим был поиск

        if (file != null) {
            try {
                sub_table.getItems().clear(); // Очищаем текущие данные
                files.loadHistory(sub_table.getItems(), file.getAbsolutePath());
            } catch (Exception e) {
                showAlert("Ошибка загрузки", "Не удалось загрузить файл: " + e.getMessage());
            }
        }
    }

    /**
     * Отображает информационное окно с указанным заголовком и текстом
     * @param title заголовок окна
     * @param content текст сообщения
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        // Добавляем иконку
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icons/DB.png")));
        alert.showAndWait();
    }

    /**
     * Обработчик события поиска среди записей таблицы по введённому запросу и выбранной категории
     */
    @FXML
    void onSearchClick(ActionEvent event) {
        String query = searchField.getText().toLowerCase(); // поисковый запрос
        String category = searchCategory.getValue(); // фильтр для поиска

        sub_table.setItems(DB.data); // для повторного поиска возвращаемся к исходным данным

        DB.filteredData.setPredicate(sub -> { // Устанавливает условие фильтрации для filteredData

            if (query.isEmpty()) return true; // Возвращает true для всех элементов, показывая всю таблицу
            if (category == null || category.equals("Все поля")) {
                // Проверяет, содержится ли query в любом из полей
                return sub.getName().toLowerCase().contains(query) ||
                        sub.getPhone_number().contains(query) ||
                        sub.getAccount_number().contains(query) ||
                        sub.getTariff().toLowerCase().contains(query) ||
                        String.valueOf(sub.getBalance()).contains(query);
            } else {
                switch (category) {
                    // Поиск по конкретной категории
                    case "ФИО": return sub.getName().toLowerCase().contains(query);
                    case "Телефон": return sub.getPhone_number().contains(query);
                    case "Лицевой счёт": return sub.getAccount_number().contains(query);
                    case "Тариф": return sub.getTariff().toLowerCase().contains(query);
                    case "Баланс": return String.valueOf(sub.getBalance()).contains(query);
                    default: return true;
                }
            }
        });

        sub_table.setItems(DB.sortedData); // Обновление таблицы после поиска
    }

    /**
     * Сбрасывает фильтрацию и возвращает исходный список записей таблицы
     */
    @FXML
    void onResetFilter(ActionEvent event) {
        searchField.clear();
        searchCategory.getSelectionModel().clearSelection();
        sub_table.setItems(DB.data); // возвращаем исходные данные в таблицу
    }

    /**
     * Обработчик события отображения информации об авторе программы
     */
    @FXML
    void onAboutAuthorClick(ActionEvent event){
    showAlert("Об авторе", "Автор - Калашников А.Н.");
    }

    /**
     * Обработчик события открытия окна с информацией о программе
     */
    @FXML
    void onAboutProgramClick(ActionEvent event){
        try{
        // Загрузка FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/zabsu/telephone_sub_ui/about_program.fxml"));
        AnchorPane root = loader.load();

        // Создание нового окна
        Stage aboutStage = new Stage();
        aboutStage.setTitle("Об авторе");

        aboutStage.initModality(Modality.APPLICATION_MODAL); // Блокируем взаимодействие с другими окнами
        aboutStage.initOwner(sub_table.getScene().getWindow()); // Родительское окно

        // Настройка контроллера
        aboutProgramController controller = loader.getController();
        controller.setDialogStage(aboutStage);

        // Отображение окна
        aboutStage.setScene(new Scene(root));
        aboutStage.setResizable(false);
        aboutStage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /// Инициализация контроллера главного окна
    @FXML
    void initialize() {
        // Связываем столбцы таблицы с соответствующими полями класса
        table_name.setCellValueFactory(new PropertyValueFactory<TelSub, String>("name"));
        table_pn.setCellValueFactory(new PropertyValueFactory<TelSub, String>("phone_number"));
        table_an.setCellValueFactory(new PropertyValueFactory<TelSub, String>("account_number"));
        table_tariff.setCellValueFactory(new PropertyValueFactory<TelSub, String>("tariff"));
        table_balance.setCellValueFactory(new PropertyValueFactory<TelSub, Double>("balance"));
        // Объект класса базы данных
        DB = new TelSubDatabase();
        DB.data = FXCollections.observableArrayList(); // массив для записей
        DB.filteredData = new FilteredList<>(DB.data); // массив для записей после поиска
        // массив для записей после поиска(можно сортировать по столбцам)
        DB.sortedData = new SortedList<>(DB.filteredData);
        // Привязываем сортировку таблицы к SortedList
        DB.sortedData.comparatorProperty().bind(sub_table.comparatorProperty()); // устанавливаем компаратор
        sub_table.setItems(DB.data);

    }

}
