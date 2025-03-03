package zabsu.chatbot.chatbot;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

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
    private Button enter_button;

    @FXML
    void enter_buttonOnClick(ActionEvent event) throws IOException  {
        Stage input = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 680);
        input.setTitle("Hello!");
        input.setScene(scene);
        input.show();
   }

    @FXML
    void initialize() {

    }

}
