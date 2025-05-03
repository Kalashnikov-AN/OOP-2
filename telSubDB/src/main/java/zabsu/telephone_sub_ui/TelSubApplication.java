package zabsu.telephone_sub_ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/// Класс приложения TelSubApplication
public class TelSubApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Задаем иконку
        Image icon = new Image(getClass().getResourceAsStream("icons/DB.png"));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(TelSubApplication.class.getResource("mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800); // Ширина и высота окна
        stage.setTitle("Telephone Subscriber"); // Заголовок окна
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}