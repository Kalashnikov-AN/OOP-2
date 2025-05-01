package zabsu.telephone_sub_ui;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class addController {
    private Stage dialogStage;

    // Метод для установки Stage диалога
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void closeDialog() {
        dialogStage.close();
    }
}
