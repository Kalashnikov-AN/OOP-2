module zabsu.chatbot.chatbot {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens zabsu.chatbot.chatbot to javafx.fxml;
    exports zabsu.chatbot.chatbot;
}