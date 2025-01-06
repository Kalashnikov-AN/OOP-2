package zabsu.chatbot.chatbot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    void initialize() {

    }

}
