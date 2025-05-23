

package zabsu.telephone_sub_ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/* Классы в Java - ссылочный тип данных.
Ссылочные типы данных в Java не содержат значения, а ссылаются на место, где они расположены. */
/// Класс приложения TelSubApplication
public class TelSubApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TelSubApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800); // Ширина и высота окна
        stage.setTitle("Telephone Subscriber"); // Заголовок окна
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}