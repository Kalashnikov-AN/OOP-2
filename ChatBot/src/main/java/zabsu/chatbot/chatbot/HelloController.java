package zabsu.chatbot.chatbot;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    private static String name;

    @FXML
    public TextField textfield_name;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea messages;

    @FXML
    private Button send;

    @FXML
    private TextField text;

    @FXML
    void onSendClick(ActionEvent event) {

    }
    @FXML
    public static Button enter_button;

    /// Изменяет поле имени абонента на имя name1
    public void setName(String name1) throws RuntimeException {
        // Используем регулярное выражение для проверки, состоит ли строка только из букв и пробелов
        Pattern pattern = Pattern.compile("[a-zA-Z\\s]+");
        Matcher matcher = pattern.matcher(name1);
        boolean isNameValid = matcher.matches();

        if (isNameValid) {
            name = name1;
        } else {
            name = "Name Surname";
            throw new RuntimeException("Ошибка: неверно введено ФИО");
        }
    }

    @FXML
    void enter_buttonOnClick(ActionEvent event) throws IOException  {
        setName(textfield_name.getText());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 680);
        stage.setScene(scene);
        stage.show();
   }


    @FXML
    void initialize() {

    }

}
