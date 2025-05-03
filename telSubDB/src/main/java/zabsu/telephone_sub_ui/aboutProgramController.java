package zabsu.telephone_sub_ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class aboutProgramController {
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        Image icon = new Image(getClass().getResourceAsStream("icons/DB.png"));
        dialogStage.getIcons().add(icon);
    }

    @FXML
    private void handleClose() {
        dialogStage.close();
    }

}
