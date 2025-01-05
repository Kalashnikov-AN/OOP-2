module zabsu.chatbot.chatbot {
    requires javafx.controls;
    requires javafx.fxml;


    opens zabsu.chatbot.chatbot to javafx.fxml;
    exports zabsu.chatbot.chatbot;
}