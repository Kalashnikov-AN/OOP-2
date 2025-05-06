package zabsu.telephone_sub_ui;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;
// Автор: Калашников А.Н.

/// Класс-контроллер окна со справкой о программе
public class aboutProgramController {

    /// Окно справки
    private Stage dialogStage;

    /**
     * Устанавливает окно справки и добавляет иконку
     * @param dialogStage устанавливаемое окно
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        Image icon = new Image(getClass().getResourceAsStream("icons/DB.png"));
        dialogStage.getIcons().add(icon);
    }

    /// Обработчик события для закрытия окна отображения справки
    @FXML
    private void handleClose() {
        dialogStage.close();
    }

}
