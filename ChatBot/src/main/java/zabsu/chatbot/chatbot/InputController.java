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

public class InputController {

    public static String name; //todo: private НЕ static, передавать в ChatController.SetName; InputController.GetController

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
        try{
            setName(textfield_name.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(ChatBotApplication.class.getResource("chat-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1024, 680);
            //
             //
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
