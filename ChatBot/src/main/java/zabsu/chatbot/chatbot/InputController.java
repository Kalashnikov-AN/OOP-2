package zabsu.chatbot.chatbot;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Контроллер для экрана ввода имени пользователя.
 * <p>
 * Данный класс отвечает за валидацию имени, заданного пользователем,
 * и за переход на главный экран чат-бота с передачей имени в {@link ChatController}.
 * </p>
 */
public class InputController {

    @FXML
    public TextField textfield_name;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField text;

    @FXML
    public Button enter_button;

    /**
     * Проверяет введённое имя и возвращает его, если имя корректное.
     * <p>
     * Метод использует регулярное выражение для проверки, что имя состоит только из букв и пробелов.
     * Если имя не соответствует требованиям, выбрасывается исключение с сообщением об ошибке.
     * </p>
     *
     * @param name1 Введённое имя для проверки.
     * @return имя, если оно корректное.
     * @throws RuntimeException если имя не проходит проверку (содержащее недопустимые символы).
     */
    public String setName(String name1) throws RuntimeException {
        // Используем регулярное выражение для проверки, состоит ли строка только из букв и пробелов
        Pattern pattern = Pattern.compile("[a-zA-Z\\s]+");
        Matcher matcher = pattern.matcher(name1);
        boolean isNameValid = matcher.matches();
        if (isNameValid) {
            return name1;
        } else {
            // Если имя некорректное, можно установить имя по умолчанию и выбросить исключение
            name1 = "Name Surname";
            throw new RuntimeException("Ошибка: неверно введено ФИО");
        }
    }

    /**
     * Обработчик нажатия кнопки входа.
     * <p>
     * Метод получает имя, введённое пользователем, проверяет его с помощью {@link #setName(String)},
     * затем загружает новый экран (chat-view.fxml), передаёт введённое имя в {@link ChatController}
     * и переключает сцену.
     * </p>
     *
     * @param event событие, вызванное нажатием кнопки.
     * @throws IOException если происходит ошибка загрузки FXML-файла.
     */
    @FXML
    void enter_buttonOnClick(ActionEvent event) throws IOException  {
        try{
            String inputName = setName(textfield_name.getText());
            // Получаем текущее окно (Stage) через источник события
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Загружаем FXML для главного окна чат-бота
            FXMLLoader fxmlLoader = new FXMLLoader(ChatBotApplication.class.getResource("chat-view.fxml"));
            // Создаём экземпляр ChatController и заранее устанавливаем имя пользователя
            ChatController chatController = new ChatController();
            // Передаем проверенное имя в ChatController
            chatController.name = inputName;
            // Программно задаём контроллер для FXML
            fxmlLoader.setController(chatController);
            Scene scene = new Scene(fxmlLoader.load(), 1024, 724);
            // Подключаем CSS-стили
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            // Устанавливаем новую сцену и отображаем её
            stage.setScene(scene);
            stage.show();
         }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.setContentText("Имя должно состоять только из букв и символа пробел");
            errorAlert.showAndWait();
        }

   }


    @FXML
    void initialize() {

    }

}
