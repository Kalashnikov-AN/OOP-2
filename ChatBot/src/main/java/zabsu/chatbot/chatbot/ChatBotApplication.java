package zabsu.chatbot.chatbot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class ChatBotApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Задаем иконку
        Image icon = new Image(getClass().getResourceAsStream("chatbot-icon.png"));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(ChatBotApplication.class.getResource("input-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 724);
        // Подключаем css файл c шрифтом и цветом фона
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("ChatBot");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}